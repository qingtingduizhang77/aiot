package com.cityiot.guanxin.common.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import swallow.framework.jpaentity.IOnlyIdEntity;
import swallow.framework.jpaquery.repository.SwallowRepository;

import java.util.List;
import java.util.function.Function;

public class BaseRepository <T extends IOnlyIdEntity> extends SwallowRepository<T> {
    /** 根据某些条件查找对应的实体
     * @param call
     * @return
     */
    public List<T> findEntitysByColumns(Function<JPAQuery<Tuple>,JPAQuery<Tuple>> call) {
        JPAQuery query = factory
                .query()
                .select(getMainTableExpression())
                .from(getMainTableExpression());
        return call.apply(query).fetch();
    }
    /** 根据某些条件查找对应的唯一实体
     * @param call
     * @return
     */
    public T findOneEntityByColumns(Function<JPAQuery<Tuple>,JPAQuery<Tuple>> call) {
        JPAQuery query = factory
                .query()
                .select(getMainTableExpression())
                .from(getMainTableExpression());
        return (T)call.apply(query).fetchOne();
    }

    public long deleteEntityByColumns(Function<JPADeleteClause,JPADeleteClause> call) {
        JPADeleteClause jpaDeleteClause = factory
                .delete(getMainTableExpression());
        return call.apply(jpaDeleteClause).execute();
    }

    /**根据某个条件统计记录数
     * @return
     */
    public long count(Predicate...pre){
        return factory
                .select(getMainTableExpression())
                .from(getMainTableExpression())
                .where(pre)
                .fetchCount();
    }
}
