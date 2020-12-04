package swallow.framework.jpaquery.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import swallow.framework.jpaentity.IOnlyIdEntity;
import swallow.framework.jpaquery.repository.annotations.*;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.BaseQueryBean;
import swallow.framework.web.PageListData;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 支持querybean查询的快速查询仓库
 * 
 * @author aohanhe
 *
 */
public abstract class SwallowRepository<T extends IOnlyIdEntity> extends SwallBaseRepository<T> {	
	private static final Logger logger = LoggerFactory.getLogger(SwallowRepository.class);

	/**
	 * 通过querybean返回所有数据
	 * @param queryBean
	 * @return
	 */
	public List<T> getAllItemByQuerybean(BaseQueryBean queryBean){
		return getAllItemByQuerybean(queryBean,null);
	}
	
	/**
	 * 通过querybean返回所有数据
	 * @param queryBean
	 * @return
	 */
	public List<T> getAllItemByQuerybean(BaseQueryBean queryBean,Function<JPAQuery<Tuple>, JPAQuery<Tuple>> initQuery){
		var query=this.getQuery();
		query=addPredicateAndSortFromQueryBean(query, queryBean);
		if(initQuery!=null) {
			query=initQuery.apply(query);
		}
		var res=query.fetch();
		if(res==null) return null;
		
		return res.stream().map(this::fectItemFromTuple).collect(Collectors.toList());
	}
	
	/**
	 * 通过querybean分页返回数据
	 * @param queryBean
	 * @return
	 */
	public PageListData<T> getAllItemPageByQuerybean(BasePageQueryBean queryBean){
		return getAllItemPageByQuerybean(queryBean,null);
	}
	
	/**
	 * 通过querybean分页返回数据
	 * @param queryBean
	 * @return
	 */
	public PageListData<T> getAllItemPageByQuerybean(BasePageQueryBean queryBean,Function<JPAQuery<Tuple>, JPAQuery<Tuple>> initQuery){
		var query=this.getQuery();
		query=addPredicateAndSortFromQueryBean(query, queryBean);
		
		if(initQuery!=null) {
			query=initQuery.apply(query);
		}

		var res=query.fetchResults();
		if(res==null) return null;
		
		var items=res.getResults().stream().map(this::fectItemFromTuple).collect(Collectors.toList());
		return new PageListData<>(queryBean.getPageSize(), res.getTotal(), items);
	}

	/**
	 * 通过querybean向查询添加where与order条件
	 * 
	 * @param query
	 * @param queryBean
	 * @return
	 */
	public JPAQuery<Tuple> addPredicateAndSortFromQueryBean(JPAQuery<Tuple> query, BaseQueryBean queryBean) {
		Assert.notNull(query, "参数query不能为空");

		// 如果查询bean为空，直接返回
		if (queryBean == null)
			return query;

		var mainTable = this.getMainTableExpression();

		Assert.notNull(mainTable, "主表表达式没有设置，请使用getMainTableExpression返回");

		

		// 生成查询条件
		BooleanBuilder where=this.createWhereFromQueryBean(queryBean);
		query=query.where(where);
		
		// 生成排序条件
		var orders = createOrderFromQueryBean(queryBean);
		
		if(orders!=null) {
			
			query=query.orderBy(orders.toArray(new OrderSpecifier[] {}));
		}else {
			// 默认添加Id降序作为排序条件
		}
		
		// 添加分页处理
		if (queryBean instanceof BasePageQueryBean) {
			var page = ((BasePageQueryBean) queryBean).getPage();
			var size = ((BasePageQueryBean) queryBean).getPageSize();

			int skip = (page - 1) * size;

			logger.debug("set pagesize=" + size + " page=" + page);
			query = query.offset(skip).limit(size);
		}

		return query;
	}

	/**
	 * 创建查询bean的where表达式
	 * @param queryBean
	 * @return
	 */
	private BooleanBuilder createWhereFromQueryBean(BaseQueryBean queryBean) {
		// 从属性生成查询条件
		// 从属性生成查询条件
		var classInfo = queryBean.getClass();
		
		//将所有的生成表达式，并以and在前or在后的方式进行排序
		var flux = Flux.fromArray(classInfo.getDeclaredFields()).map(f -> createConditionFromField(queryBean, f))
				.filter(pre -> pre.isPresent())
				.sort((item1, item2) -> item1.get().getLeft().compareTo(item2.get().getLeft()));

		BooleanBuilder builder = new BooleanBuilder();

		// 将所有条件组合
		flux.subscribe(v -> {
			if (v == null)
				return;
			var item = v.get();
			boolean isAnd = item.getLeft().equals('a');

			if (isAnd)
				builder.and(item.getRight());
			else
				builder.or(item.getRight());

		}, ex -> {
			System.out.println(ex.getMessage());
		});

		return builder;
	}

	private Optional<Pair<Character, Predicate>> createConditionFromField(BaseQueryBean queryBean, Field field)
			throws RuntimeException {
		try {
			if(field.isAnnotationPresent(IgnorePredicate.class)){
				return Optional.empty();
			}

			boolean isAnd = !field.isAnnotationPresent(Or.class);
			boolean notIgnoreNull = field.isAnnotationPresent(NotIgnoreNull.class);

			// 如果设置了跳过空，则返回空值
			field.setAccessible(true);// 设置充许访问
			Object value = field.get(queryBean);
			if (value == null && !notIgnoreNull)
				return Optional.empty();

			String fieldName = field.getName();
			Expression<?> tableExpression = this.getMainTableExpression();

			// 取得设置的路径表达式
			if (field.isAnnotationPresent(FieldPath.class)) {
				FieldPath fieldPath = field.getAnnotation(FieldPath.class);
				var nFieldName = fieldPath.name();
				var enityClass = fieldPath.joinEntityClass();

				if (!StringUtils.isEmpty(nFieldName))
					fieldName = nFieldName;
				
				tableExpression =TablesPathManger.getTablePathByClass(enityClass, fieldPath.joinEntityAlias());				
			}

			Predicate pre = null;
			
			
			if (field.isAnnotationPresent(PredicateMethod.class)) {
				pre = getPredicateFromMehtod(queryBean, field.getAnnotation(PredicateMethod.class));
			}
			else
			{
				Path nodePath = Expressions.path(field.getType(), (Path) tableExpression, fieldName);
				
				if(value==null&notIgnoreNull) {
					pre=this.getPredicateFromIsNull(nodePath);
				}else {

					// 根据操作符生成操作
					if (field.isAnnotationPresent(Gt.class)) {
						pre = this.getPredicateFromGt(nodePath, value);
					} else if (field.isAnnotationPresent(Gte.class)) {
						pre = this.getPredicateFromGte(nodePath, value);
					} else if (field.isAnnotationPresent(Lt.class)) {
						pre = this.getPredicateFromLt(nodePath, value);
					} else if (field.isAnnotationPresent(Lte.class)) {
						pre = this.getPredicateFromLte(nodePath, value);
					} else if (field.isAnnotationPresent(Like.class)) {
						var likeInfo = field.getAnnotation(Like.class);
						pre = this.getPredicateFromLike(nodePath, value, likeInfo.isStartWith());
					} else
						pre = this.getPredicateFromEq(nodePath, value);

					Assert.notNull(pre, "没有取得对应的Predicate表达式");
				}
			}

			return Optional.of(Pair.of(isAnd ? 'a' : 'o', pre));
		} catch (Exception ex) {
			throw new RuntimeException("构建字段对应的表达式出错:" + ex.getMessage(), ex);
		}
	}
	
	/**
	 * 通过查询bean取得排序列表
	 * @param queryBean
	 * @return
	 */
	private  List<OrderSpecifier> createOrderFromQueryBean(BaseQueryBean queryBean){
		var orders=queryBean.getOrders();
		if(orders==null||orders.size()==0) {
			return null;
		}
		return Flux.fromIterable(orders)
				.map(v -> createOrderProperty(v, queryBean)).collect(Collectors.toList())
				.block();		
	}

	/**
	 * 取得函数标注对应的表达式
	 * 
	 * @param queryBean
	 * @param methodInfo
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private Predicate getPredicateFromMehtod(BaseQueryBean queryBean, PredicateMethod methodInfo)
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		Assert.notNull(methodInfo, "参数methodInfo不允许为空");
		var name = methodInfo.value();

		Assert.notNull(name, "参数PredicateMethod的value值不允许为空");

		// 取得method对象
		var method = queryBean.getClass().getMethod(name);
		method.setAccessible(true);
		return (Predicate) method.invoke(queryBean);
	}
	
	/**
	 * 节点是空
	 * @param nodePath
	 * @return
	 */
	@SuppressWarnings("unused")
	private Predicate getPredicateFromIsNull(Path nodePath) {
		return Expressions.predicate(Ops.IS_NULL, nodePath);
	}

	/**
	 * 取得等于操作表达式
	 * 
	 * @param nodePath
	 * @param value
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private Predicate getPredicateFromEq(Path nodePath, Object value) {
		Constant constant = (Constant) Expressions.constant(value);
		return Expressions.predicate(Ops.EQ, nodePath, constant);
	}

	/**
	 * 取得大于操作表达式
	 * 
	 * @param nodePath
	 * @param value
	 * @return
	 */
	private Predicate getPredicateFromGt(Path nodePath, Object value) {
		Constant constant = (Constant) Expressions.constant(value);
		return Expressions.predicate(Ops.GT, nodePath, constant);
	}

	/**
	 * 取得大于等于操作表达式
	 * 
	 * @param nodePath
	 * @param value
	 * @return
	 */
	private Predicate getPredicateFromGte(Path nodePath, Object value) {
		Constant constant = (Constant) Expressions.constant(value);
		return Expressions.predicate(Ops.GOE, nodePath, constant);
	}

	/**
	 * 取得小于操作表达式
	 * 
	 * @param nodePath
	 * @param value
	 * @return
	 */
	private Predicate getPredicateFromLt(Path nodePath, Object value) {
		Constant constant = (Constant) Expressions.constant(value);
		return Expressions.predicate(Ops.LT, nodePath, constant);
	}

	/**
	 * 取得小于等于操作表达式
	 * 
	 * @param nodePath
	 * @param value
	 * @return
	 */
	private Predicate getPredicateFromLte(Path nodePath, Object value) {
		Constant constant = (Constant) Expressions.constant(value);
		return Expressions.predicate(Ops.LOE, nodePath, constant);
	}

	/**
	 * 取得Like等于操作表达式
	 * 
	 * @param nodePath
	 * @param value
	 * @return
	 */
	private Predicate getPredicateFromLike(Path nodePath, Object value, boolean isStartWith) {
		Constant constant = (Constant) Expressions.constant(value);
		var op = isStartWith ? Ops.STARTS_WITH : Ops.STRING_CONTAINS;
		return Expressions.predicate(op, nodePath, constant);
	}
	
	/**
	 * 取得order对应的表达式
	 * @param order
	 * @param queryBean
	 * @return
	 */
	private  OrderSpecifier createOrderProperty(Order order, BaseQueryBean queryBean) {
		try {
			Class<?> classType = queryBean.getClass();
			var field = classType.getDeclaredField(order.getProperty());
			field.setAccessible(true);

			String fieldName = order.getProperty();
			
			Assert.isTrue(!StringUtils.isEmpty(fieldName), "排序字段名不能为空");
			
			Expression<?> tableExpression = this.getMainTableExpression();

			// 取得设置的路径表达式
			if (field.isAnnotationPresent(FieldPath.class)) {
				FieldPath fieldPath = field.getAnnotation(FieldPath.class);
				var nFieldName = fieldPath.name();
				var entityClass=fieldPath.joinEntityClass();

				if (!StringUtils.isEmpty(nFieldName))
					fieldName = nFieldName;
				tableExpression = TablesPathManger.getTablePathByClass(entityClass, fieldPath.joinEntityAlias());
				
			}
			
			OrderSpecifier res=null;
			
			if(field.isAnnotationPresent(OrderMethod.class)) {
				OrderMethod orderMethod=field.getAnnotation(OrderMethod.class);
				res=getOrderFromMehtod(queryBean,orderMethod);
			}else {
				Path nodePath = Expressions.path(field.getType(), (Path)tableExpression, fieldName);
				res = new OrderSpecifier(order.getDirection() == Direction.ASC ? com.querydsl.core.types.Order.ASC
						: com.querydsl.core.types.Order.DESC, nodePath);
			}
			return res;
		}catch(Exception ex) {
			throw new RuntimeException("取得order表达式时出错:"+ex.getMessage(),ex);
		}		
	}
	
	/**
	 * 通过OrderMethod取得对应排序条件
	 * @param queryBean
	 * @param methodInfo
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private OrderSpecifier getOrderFromMehtod(BaseQueryBean queryBean, OrderMethod methodInfo)
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		Assert.notNull(methodInfo, "参数methodInfo不允许为空");
		var name = methodInfo.value();

		Assert.notNull(name, "注解OrderMethod的value值不允许为空");

		// 取得method对象
		var method = queryBean.getClass().getMethod(name);
		method.setAccessible(true);
		return (OrderSpecifier) method.invoke(queryBean);
	}

	

}
