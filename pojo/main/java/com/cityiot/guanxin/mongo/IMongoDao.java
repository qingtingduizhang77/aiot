package com.cityiot.guanxin.mongo;

import com.mongodb.DBObject;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * mongodb 操作的常用接口
 * @author hxl
 * 
 * 尽量让业务开发人员不用去了解mongoDB
 * 提供了相应的CRUD、分页、正则、范围（大于、小于等）、
 * 排序、嵌套级AND、OR 、、、
 * 后续再进行扩展
 * 若不能满足时可以直接调用template，更甚至调用mongo的api
 *
 *
 *  存在删除的插入
 *  批量
 *
 */
public interface IMongoDao {
	
	/**
	 * 插入单个数据
	 * @param objectToSave
	 * @param tableName
	 */
    void insert(Object objectToSave, String tableName);
	/**
	 * 插入单个数据
	 * @param objectToSave
	 */
    void insert(Object objectToSave);
	/**
	 * 保存单个数据  若是存在就更新
	 * @param objectToSave
	 * @param tableName
	 */
    void save(Object objectToSave, String tableName);
	
	/**
	 * 插入批量数据
	 * @param batchToSave
	 * @param tableName
	 */
    void insertBath(Collection<? extends Object> batchToSave, String tableName);
	
	/**
	 * 插入批量数据 若是存在就更新
	 * @param batchToSave
	 * @param tableName
	 */
    void saveBath(Collection<? extends Object> batchToSave, String tableName);

	/**
	 * 根据条件删除数据
	 * @param criteriaMap
	 * @param tableName
	 */
    void deleteByMap(CriteriaMap criteriaMap, String tableName);

	/**
	 * 删除所有数据
	 * @param tableName
	 */
    void deleteAll(String tableName);
	
	/**
	 * 更新数据
	 * @param criteriaMap
	 * @param setMap
	 * @param tableName
	 */
    void update(CriteriaMap criteriaMap, Map<String, Object> setMap, String tableName) ;
	
	/**
	 * 查询数据
	 * @param criteriaMap
	 * @param t
	 * @param tableName
	 * @return
	 */
    <T> List<T> findByMap(CriteriaMap criteriaMap, Class<T> t, String tableName) ;
	
	/**
	 * 查询数据 带排序
	 * @param criteriaMap
	 * @param sorters
	 * @param t
	 * @param tableName
	 * @return
	 */
    <T> List<T> findByMapWithSorter(CriteriaMap criteriaMap, List<Sorter> sorters, Class<T> t, String tableName) ;
	
	/**
	 * 查询数据，带指定起止行
	 * @param criteriaMap
	 * @param t
	 * @param start
	 * @param limit
	 * @param tableName
	 * @return
	 */
    <T> List<T> findByMap(CriteriaMap criteriaMap, Class<T> t, int start,
                          int limit, String tableName) ;

	/**
	 * 查询数据，带指定起止行，带排序
	 * @param criteriaMap
	 * @param sorters
	 * @param t
	 * @param start
	 * @param limit
	 * @param tableName
	 * @return
	 */
    <T> List<T> findByMapWithSorter(CriteriaMap criteriaMap, List<Sorter> sorters, Class<T> t, int start,
                                    int limit, String tableName) ;

	/**
	 * 查询数据，带分页
	 * @param criteriaMap
	 * @param t
	 * @param pageNo
	 * @param pageSize
	 * @param tableName
	 * @return
	 */
    <T> List<T> findByMap4Page(CriteriaMap criteriaMap, Class<T> t, int pageNo, int pageSize, String tableName) ;

	/**
	 * 查询数据，带分页，带排序
	 * @param criteriaMap
	 * @param sorters
	 * @param t
	 * @param pageNo
	 * @param pageSize
	 * @param tableName
	 * @return
	 */
    <T> List<T> findByMap4PageWithSorter(CriteriaMap criteriaMap, List<Sorter> sorters, Class<T> t, int pageNo, int pageSize, String tableName) ;

	/**
	 * 查询所有数据
	 * @param t
	 * @param tableName
	 * @return
	 */
    <T> List<T> findAll(Class<T> t, String tableName);

	/**
	 * 查询所有数据，带排序
	 * @param t
	 * @param sorters
	 * @param tableName
	 * @return
	 */
    <T> List<T> findAllWithSorter(Class<T> t, List<Sorter> sorters, String tableName);

	/**
	 * 查询数据并修改，返回修改前的数据
	 * @param criteriaMap
	 * @param setMap
	 * @param t
	 * @param tableName
	 * @return
	 */
    <T> T findAndModify(CriteriaMap criteriaMap, Map<String, Object> setMap, Class<T> t,
                        String tableName) ;

	/**
	 * 查询数据并删除，返回删除前的数据
	 * @param criteriaMap
	 * @param t
	 * @param tableName
	 * @return
	 */
    <T> T findAndRemove(CriteriaMap criteriaMap, Class<T> t, String tableName);

	/**
	 * 统计
	 * @param criteriaMap
	 * @param tableName
	 * @return
	 */
    long count(CriteriaMap criteriaMap, String tableName);

	/**
	 * spring data mongo  原生接口
	 * @return
	 */
    MongoTemplate getMongoTemplate();

	/**
	 * group聚合
	 * @param criteriaMap 条件    不用时设为 null
	 * @param tableName 表
	 * @param t 返回的泛型
	 * @param groupBy 分组的 field
	 * @param initial 分组计算时初始化  不用时设为 null; 格式:{field:value,field:value.....}
	 * @param reduce 分组计算时函数 格式：function(current, out){ example：out.field += current.field; .....}
	 * @param finalize 分组计算后函数 不用时设为 null; 格式：function(out){ example：out.field = out.field+100; .....}
	 * @return
	 */
    <T> Iterator<T> group(CriteriaMap criteriaMap, String tableName, Class<T> t, String[] groupBy,
                          String initial, String reduce, String finalize) ;
	
	
//	public List<DBObject> polygonsIncludePonit(String collection, String locationField, double[] points);
	
	
	
	boolean isCollectionExists(String collectionName);
}
