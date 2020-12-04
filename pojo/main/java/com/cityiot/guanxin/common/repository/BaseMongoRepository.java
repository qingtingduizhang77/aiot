package com.cityiot.guanxin.common.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 查询mongodb仓库类
 * @param <T>
 */
public abstract class BaseMongoRepository<T> {
    private T entity;
    @Autowired
    protected MongoTemplate mongoTemplate;


    public Class<T> getTClass()
    {
        Class<T> tClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return tClass;
    }

    public BaseMongoRepository() {

    }

    public T insertOneEntity(T entity){
        return mongoTemplate.insert(entity);
    }

    public T insertOneEntity(T entity,String collectionName){
        return mongoTemplate.insert(entity,collectionName);
    }

    public Collection<T> insertEntitys(List<T> entitys){
        return mongoTemplate.insert(entitys,getTClass());
    }

    /**
     * 这个方法可以通过lamda表达式定制查询指令 
     * @param criteriaFunction
     * @return
     */
    public List<T> findEntitysByColumns(Function<Criteria,Criteria> criteriaFunction) {
        Criteria criteria = new Criteria();
        criteria = criteriaFunction.apply(criteria);
        String entityClassName = getTClass().getSimpleName().toLowerCase();
        List<T> res = mongoTemplate.find(
                new Query().addCriteria(criteria),
                getTClass());
        return res;
    }

    /**
     * 分页返回定制查询的页面
     */
    public List<T> findEntitysPageByColumns(long page,int pageSize,Function<Criteria,Criteria> criteriaFunction) {
        if(page<=0) {
            page = 1;
        }
        Criteria criteria = new Criteria();
        criteria = criteriaFunction.apply(criteria);
        String entityClassName = getTClass().getSimpleName().toLowerCase();
        List<T> res = mongoTemplate.find(
                new Query()
                        .addCriteria(criteria).limit(pageSize).skip((page-1)*pageSize),
                getTClass());
        return res;
    }
    // 直接替换一个实体
    public T replaceOneEntity(T entity) {
        return mongoTemplate.save(entity);
    }

    /**
     * 通过定制查询，查询一个对象
     */
    public T findOneEntityByColumns(Function<Criteria,Criteria> criteriaFunction) {
        Criteria criteria = new Criteria();
        criteria = criteriaFunction.apply(criteria);
        String entityClassName = getTClass().getSimpleName().toLowerCase();
        T res = mongoTemplate
                .findOne(
                new Query().addCriteria(criteria),
                getTClass(),
                entityClassName);
        return res;
    }

    /** 根据条件更新或保存一个实体
     * @param collectionName
     * @param criteriaFunction
     * @param updateFunction
     * @return
     */
    public long updateOrSaveOneEntity(String collectionName, Function<Criteria,Criteria> criteriaFunction, Supplier<Update> updateFunction) {
        Criteria criteria = new Criteria();
        criteria = criteriaFunction.apply(criteria);
        return mongoTemplate.upsert(
                new Query().addCriteria(criteria),
                updateFunction.get(),
                collectionName
                ).getModifiedCount();
    }
}
