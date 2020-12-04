package swallow.framework.jpaquery.repository;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.transaction.Transactional;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import swallow.framework.exception.SwallowConstrainException;
import swallow.framework.exception.SwallowException;
import swallow.framework.jpaentity.IOnlyIdEntity;
import swallow.framework.jpaquery.repository.annotations.FieldPath;
import swallow.framework.jpaquery.repository.annotations.JoinEntities;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;
import swallow.framework.web.PageListData;

public class SwallBaseRepository<T extends IOnlyIdEntity> {
	private static final Logger logger = LoggerFactory.getLogger(SwallBaseRepository.class);
	@Autowired
	protected EntityManager em;

	private Class<T> entityClassInfo;

	

	private EntityPath<T> mainTable;

	/**
	 * querydsl构建工具
	 */

	protected JPAQueryFactory factory;
	
	/**
	 * id字段的路径
	 */
	private Path<Serializable> idPath;

	/**
	 * 实体查询的主体查询部分，包括了select 以及 from
	 */
	private JPAQuery<Tuple> query;

	/**
	 * 提取数据使用的映射列表
	 */
	private List<EntityFieldFetchor> fecthDataList;

	@SuppressWarnings("unchecked")
	@PostConstruct
	protected void init() {
		Assert.notNull(em, "EntityManager没有实例化");

		factory = new JPAQueryFactory(em);

		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		var actualType = type.getActualTypeArguments();

		Assert.isTrue(actualType != null && actualType.length > 0, "没有取得实例化参数的class信息");

		entityClassInfo = (Class<T>) actualType[0];
		

		// 取得主表表达式
		mainTable = (EntityPath<T>) this.getMainTableExpression();
		Assert.notNull(mainTable, "getMainTableExpression返回的主表表达式为空");

		// 初始化查询
		initQuery();
	}

	// 初始化查询
	private void initQuery() {
		var selects = new ArrayList<Expression<?>>();
		var joins = new ArrayList<EntityJoinDescribe>();
		var fecthList = new ArrayList<EntityFieldFetchor>();

		// 分析实体对象数据
		selects.add(this.mainTable); // 首先查询主表

		for (var field : getClassAllFields(entityClassInfo)) {
			field.setAccessible(true);

			if (field.isAnnotationPresent(JoinEntity.class)) {
				var join = this.getJoinInfoFromField(field, entityClassInfo);
				if (join != null)
					joins.add(join);
			}
			if (field.isAnnotationPresent(FieldPath.class)) {
				var fectchor = this.getFetchorInfoFromField(field, entityClassInfo);
				fecthList.add(fectchor);
			}
			
			if (field.isAnnotationPresent(Id.class)) {
				this.idPath = Expressions.path(Serializable.class, this.mainTable, field.getName());
			}
		}

		// 分析实体类上的join注解
		joins.addAll(getJoinInfoListFromClassInfo(entityClassInfo));

		for (var fector : fecthList) {
			boolean bIshas = false;
			var table = fector.getTable();
			for (var join : joins) {

				if (join.isEqtable(table)) {
					bIshas = true;
					break;
				}
			}
			if (!bIshas) {
				throw new RuntimeException(String.format("初始化仓库%s失败，选择语句有外表%s，但是没有字段@JoinEntity设置请检查是否设置了联表",
						this.getClass().getName(), table.toString()));
			}
		}

		// 生成查询语句
		fecthList.forEach(fecthor -> {
			selects.add(fecthor.getEntityPath());
		});

		// 添加select语句
		var queryTemp = factory.select(selects.toArray(new Expression[] {})).from(mainTable);

		for (var join : joins) {
			queryTemp = join.addToQuery(queryTemp);
		}

		this.query = queryTemp;
		this.fecthDataList = fecthList;

		logger.info(String.format("初始化SwallBaseRepository(%s)查询语句为:%s", this.getClass().getName(), query.toString()));
	}

	/**
	 * 从类信息中取得联结信息
	 * @param classInfo
	 * @return
	 */
	private List<EntityJoinDescribe> getJoinInfoListFromClassInfo(Class<?> classInfo) {
		Assert.notNull(classInfo, "参数classInfo不允许为空");
		if (!(classInfo.isAnnotationPresent(JoinEntities.class)||classInfo.isAnnotationPresent(JoinEntity.class)))
			return new ArrayList<>();

		
		var joinEntities = classInfo.getDeclaredAnnotationsByType(JoinEntity.class);

		var stream = Stream.of(joinEntities).map(joinInfo -> {
			EntityPath<?> mainTable = this.mainTable;

			if (!joinInfo.mainEnityClass().equals(Object.class)) {
				mainTable = TablesPathManger.getTablePathByClass(joinInfo.mainEnityClass(), joinInfo.mainEntityAlias());
			}

			String name = joinInfo.name();

			String mainFieldName = joinInfo.mainFiledName();
			Class<?> fieldType = joinInfo.fieldType();

			Assert.hasText(mainFieldName, "JoinEntity 注解的mainFiledName一定要设置");
			Assert.isTrue(!StringUtils.isEmpty(name), "JoinEntity 注解的name一定要设置");
			var table = TablesPathManger.getTablePathByClass(joinInfo.joinEntityClass(), joinInfo.joinEntityAlias());

			var mainTablePath = Expressions.path(fieldType, (Path) mainTable, mainFieldName);
			var jointTablePath = Expressions.path(fieldType, (Path) table, name);

			Predicate extendsOn = null;
			if (!StringUtils.isEmpty(joinInfo.extOnMethod())) {
				String strMethod = joinInfo.extOnMethod();
				try {
					var method = classInfo.getDeclaredMethod(strMethod);
					extendsOn = (Predicate) method.invoke(null);
					return new EntityJoinDescribe(table,mainTablePath, jointTablePath, extendsOn);
				} catch (Exception e) {
					throw new RuntimeException(String.format("生成实体类%s的leftjoin语句出错，extOnMethod=‘%s’取得条件值出错%s",
							classInfo.getName(), strMethod, e.getMessage()), e);
				}
			} else {
				return new EntityJoinDescribe(table, mainTablePath, jointTablePath);
			}
		});

		return stream.collect(Collectors.toList());
	}

	/**
	 * 从字段信息中提取join信息
	 * 
	 * @param field
	 * @return
	 */
	@SuppressWarnings("unused")
	private EntityJoinDescribe getJoinInfoFromField(Field field, Class<?> classInfo) {
		if (!field.isAnnotationPresent(JoinEntity.class))
			return null;

		JoinEntity joinInfo = field.getAnnotation(JoinEntity.class);
		Class<?> joinEntity = joinInfo.joinEntityClass();
		Assert.notNull(joinEntity, "没有@JoinEntity，需要给joinEntityClass赋值");

		var table = TablesPathManger.getTablePathByClass(joinEntity, joinInfo.joinEntityAlias());

		Class<?> fieldType = field.getType();
		String name = joinInfo.name();

		Assert.hasText(name, "JoinEntity 注解的name一定要设置");

		var mainTablePath = Expressions.path(fieldType, (Path) this.mainTable, field.getName());
		var jointTablePath = Expressions.path(fieldType, (Path) table, name);

		if (!StringUtils.isEmpty(joinInfo.extOnMethod())) {
			String strMethod = joinInfo.extOnMethod();
			try {
				var method = classInfo.getDeclaredMethod(strMethod);
				Predicate extendsOn = (Predicate) method.invoke(null);
				return new EntityJoinDescribe(table, mainTablePath, jointTablePath, extendsOn);
			} catch (Exception e) {
				throw new RuntimeException(String.format("生成实体类%s的leftjoin语句出错，extOnMethod=‘%s’取得条件值出错%s",
						classInfo.getName(), strMethod, e.getMessage()), e);
			}
		} else {
			return new EntityJoinDescribe(table, mainTablePath, jointTablePath);
		}
	}

	/**
	 * 从字段中提取数据提取信息
	 * 
	 * @param field
	 * @param classInfo
	 * @return
	 */
	public EntityFieldFetchor getFetchorInfoFromField(Field field, Class<?> classInfo) {
		// 没有带Transient标签则返回空
		if (!field.isAnnotationPresent(Transient.class)) {
			Assert.isTrue(!field.isAnnotationPresent(FieldPath.class),
					"字段" + field.toString() + "没添加Transient标签但又添加了FieldPath标注");
			return null;
		}

		var fieldPath = field.getAnnotation(FieldPath.class);
		String name = fieldPath.name();
		Class<?> joinEntity = fieldPath.joinEntityClass();

		Assert.notNull(joinEntity, "没有@JoinEntity，需要给joinEntityClass赋值");
		Assert.isTrue(!StringUtils.isEmpty(name), "字段" + field.toString() + "的FieldPath 注解的name一定要设置");

		var table = TablesPathManger.getTablePathByClass(joinEntity, fieldPath.joinEntityAlias());

		var express = Expressions.path(field.getType(), (Path) table, name);

		return new EntityFieldFetchor(field, express, table);
	}

	/**
	 * 取得当前的主表表达式
	 * 
	 * @return
	 */
	protected EntityPath<?> getMainTableExpression() {
		return TablesPathManger.getTablePathByClass(this.entityClassInfo, null);
	}

	/**
	 * 取得类的所有字段信息，包括基础类
	 * 
	 * @param classInfo
	 * @return
	 */
	private List<Field> getClassAllFields(Class<?> classInfo) {
		var listFields = new ArrayList<Field>();
		while (classInfo != null) {
			listFields.addAll(Arrays.asList(classInfo.getDeclaredFields()));

			classInfo = classInfo.getSuperclass();
		}
		return listFields;
	}

	// 从tuple取得item数据
	public T fectItemFromTuple(Tuple tuple) {
		T item = (T) tuple.get(this.mainTable);
		fecthDataList.forEach(fetch -> {
			try {
				fetch.getField().set(item, tuple.get(fetch.getEntityPath()));
			} catch (Exception e) {
				logger.error("从tuple提取数据失败:" + e.getMessage(), e);
			}
		});

		return item;
	}
	
	protected JPAQuery<Tuple> getQuery() {
		return this.query.clone();
	}

	

	/**
	 * 通过ID取得实体对象
	 * 
	 * @param id
	 * @return
	 */
	public T getItemById(Serializable id) {
		var idValue = Expressions.constant(id);
		var pre = Expressions.predicate(Ops.EQ, this.idPath, idValue);
		var result = this.getQuery().where(pre).limit(5).fetch();
		

		if (result == null||result.size()==0)
			return null;
		return this.fectItemFromTuple(result.get(0));
	}

	/**
	 * 通过ID删除对应的记录
	 * 
	 * @param id
	 */
	@Transactional
	public long deleteItemById(Serializable id) {
		return deleteItemById(id,null);
	}
	
	/**
	 * 通过ID删除对应的记录
	 * 
	 * @param id
	 */
	@Transactional
	public long deleteItemById(Serializable id,Predicate predicate) {
		var idValue = Expressions.constant(id);
		
		var pre = Expressions.predicate(Ops.EQ, this.idPath, idValue);
		var query=factory.delete(mainTable).where(pre);
		if(predicate!=null)
			query=query.where(predicate);

		return query.execute();
	}

	@Transactional
	public T updateItem(T entity) throws SwallowException {
		return updateItem(entity,null);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public T updateItem(T entity,Predicate predicate) throws SwallowException {
		try {
			if(predicate!=null)
			{
				var query=this.getQuery();
				var idValue = Expressions.constant(entity.getId());
				var idwhere = Expressions.predicate(Ops.EQ, this.idPath,idValue );
				long count=query.where(predicate)
					.where(idwhere).fetchCount();
				if(count==0) 
					throw new SwallowException("更新ID="+entity.getId()+"对象时出错：没有操作权限");
			}			
			
			em.merge(entity);			

			// 重新取得记录，以更新对应的值
			return this.getItemById((Serializable)(Long)entity.getId());

		}catch (Exception ex) {
			var cause=ex.getCause();
			if(cause != null && ConstraintViolationException.class.isAssignableFrom(cause.getClass())) {
				var constraErr=(ConstraintViolationException)cause;
				throw new SwallowConstrainException("更新对象时出错,违反唯一约束"+constraErr.getConstraintName(), ex,
						constraErr.getConstraintName());
			}else
			  throw new SwallowException("更新对象时出错：" + ex.getMessage(), ex);
		}
	}

	@Transactional
	public T insertItem(T entity) throws SwallowException {
		try {
			em.persist(entity);
			Serializable id = (Serializable) (Long) entity.getId();
			// 重新取得记录，以更新对应的值
			return this.getItemById(id);
		}		
		catch (Exception ex) {
			var cause=ex.getCause();
			if(ConstraintViolationException.class.isAssignableFrom(cause.getClass())) {
				var constraErr=(ConstraintViolationException)cause;		
				
				throw new SwallowConstrainException("插入对象时出错,违反唯一约束:"+constraErr.getConstraintName(), ex,
						constraErr.getConstraintName());
			}else
			  throw new SwallowException("插入对象时出错：" + ex.getMessage(), ex);
		}
	}

	/**
	 * 取得所有实体对象
	 * 
	 * @param initQuery 通过回调可以定制查询条件，或排序
	 * @return
	 */
	public List<T> getAllItems(Function<JPAQuery<Tuple>, JPAQuery<Tuple>> initQuery) {
		var fetchQuery = this.getQuery();
		if (initQuery != null)
			fetchQuery = initQuery.apply(this.getQuery());

		var list = fetchQuery.fetch();
		if (list == null)
			return null;

		return list.stream().map(this::fectItemFromTuple).collect(Collectors.toList());
	}
	
	
	
	/**
	 * 取得满足查询的数据项
	 * @param initQuery
	 * @return
	 */
	public T getItem(Function<JPAQuery<Tuple>, JPAQuery<Tuple>> initQuery) {
		var fetchQuery = this.getQuery();
		if (initQuery != null)
			fetchQuery = initQuery.apply(this.getQuery());

		var tuple = fetchQuery.fetchOne();
		if (tuple == null)
			return null;

		return this.fectItemFromTuple(tuple);
	}

	/**
	 * 分页返回数据
	 * 
	 * @param initQuery 回调，可以修改条件以及排序
	 * @param page      页号
	 * @param pageSize  页面大小
	 * @return 分页结果集
	 */
	public PageListData<T> getAllItemsByPage(Function<JPAQuery<Tuple>, JPAQuery<Tuple>> initQuery, int page,
			int pageSize) {
		var fetchQuery = this.getQuery();
		if (initQuery != null)
			fetchQuery = initQuery.apply(this.getQuery());
		var res = fetchQuery.offset((page - 1) * pageSize).limit(pageSize).fetchResults();

		if (res == null)
			return null;

		var items = res.getResults().stream().map(this::fectItemFromTuple).collect(Collectors.toList());

		return new PageListData<T>(pageSize, res.getTotal(), items);
	}

}
