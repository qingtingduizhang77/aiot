package com.cityiot.guanxin.config.filter.handler;

import com.cityiot.guanxin.config.filter.provider.FilterCauseProvider;
import com.cityiot.guanxin.config.filter.where.WhereJoiner;
import com.querydsl.jpa.impl.JPAQuery;

public interface DataFilterWhereHandler<T> {
    boolean support(FilterCauseProvider filterCauseProvider);
    JPAQuery<?> filter(T t, JPAQuery<?> jpaQuery, WhereJoiner whereJoiner);
}
