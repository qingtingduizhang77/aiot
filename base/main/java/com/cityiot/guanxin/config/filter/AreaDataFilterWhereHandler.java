package com.cityiot.guanxin.config.filter;

import com.cityiot.guanxin.config.filter.handler.DataFilterWhereHandler;
import com.cityiot.guanxin.config.filter.provider.AreaFilterCauseProvider;
import com.cityiot.guanxin.config.filter.provider.FilterCauseProvider;
import com.cityiot.guanxin.config.filter.where.WhereJoiner;
import com.querydsl.jpa.impl.JPAQuery;

import java.util.List;

/**
 * 区域数据过滤处理器
 */
public class AreaDataFilterWhereHandler implements DataFilterWhereHandler<AreaFilterCauseProvider> {
    @Override
    public boolean support(FilterCauseProvider filterCauseProvider) {
        return filterCauseProvider instanceof AreaFilterCauseProvider;
    }

    @Override
    public JPAQuery<?> filter(AreaFilterCauseProvider areaFilterCauseProvider, JPAQuery<?> jpaQuery, WhereJoiner whereJoiner) {
        // 当前用户所属的区域
        List<Long> areaIdsOfUser =  areaFilterCauseProvider.getFilterCause();
        return jpaQuery.where(whereJoiner.getPredicate(areaIdsOfUser));
    }
}
