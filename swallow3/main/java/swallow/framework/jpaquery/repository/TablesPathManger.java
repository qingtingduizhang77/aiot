package swallow.framework.jpaquery.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.querydsl.core.types.EntityPath;


/**
 * 表路径表达式管理器接口
 * @author aohanhe
 *
 */
public class TablesPathManger {
	
	private static final Logger logger = LoggerFactory.getLogger(TablesPathManger.class);

	public static Map<Integer,EntityPath<?>> entityMap=new ConcurrentHashMap<>(60);
	
	/**
	 * 通过表Id取得对应的路径表达式
	 * @param <EntityPathBase>
	 * @param tableId
	 * @return
	 */
	public static  EntityPath<?>  getTablePathByClass(Class<?> entityClass,String name){
		Assert.notNull(entityClass, "参数entityClass不允许为空");
		String className = entityClass.getName() + "_" + (StringUtils.isEmpty(name) ? "" : name);
		try {
			int key = className.hashCode();
			// 如果原来没有同一路径值，则创建
			if (!entityMap.containsKey(key)) {
				if (StringUtils.isEmpty(name)) {
					name = StringUtils.uncapitalize(entityClass.getSimpleName());					
				}
				// 取得这个实体类的querydsl类
				String queryClassName = entityClass.getSimpleName();
				queryClassName = entityClass.getPackageName() + ".Q" + queryClassName;

				var queryClass = (Class<?>) Class.forName(queryClassName);

				var constructor=queryClass.getConstructor(String.class);
				Object path=constructor.newInstance(name);
				
				entityMap.put(key, (EntityPath<?>)path);
			}
			return entityMap.get(key);
		} catch (Exception ex) {
			logger.error(String.format("取得实体类%s对应的EntityPath对象失败:" + ex.getMessage(), entityClass.toString()),ex);
			throw new RuntimeException(
					String.format("取得实体类%s对应的EntityPath对象失败:" + ex.getMessage(), entityClass.toString()),ex);
		}		
	}
}
