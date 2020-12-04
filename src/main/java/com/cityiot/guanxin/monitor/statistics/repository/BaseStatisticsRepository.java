package com.cityiot.guanxin.monitor.statistics.repository;

import com.cityiot.guanxin.common.repository.BaseMongoRepository;
import com.cityiot.guanxin.monitor.statistics.control.StatisticsControl;
import org.springframework.data.mongodb.core.query.Criteria;
import swallow.framework.web.PageListData;

import java.util.List;

public class BaseStatisticsRepository<T> extends BaseMongoRepository<T> {

    public PageListData<List<T>> queryAllItemsPageByQueryBean(StatisticsControl.QueryBean queryBean) {
        final Criteria criteria = new Criteria();
        if(null!=queryBean.getAreaId()) {
            criteria.and("areaId").is(queryBean.getAreaId());
        }
        if(null!=queryBean.getStreetId()) {
            criteria.and("streetId").is(queryBean.getStreetId());
        }
        if(null!=queryBean.getStartTime()) {
            criteria.and("startTime").gte(queryBean.getStartTime());
        }
        if(null!=queryBean.getEndTime()) {
            criteria.and("endTime").lte(queryBean.getEndTime());
        }
        List<T> list =  findEntitysPageByColumns(
                queryBean.getPage(),
                queryBean.getPageSize(),
                criteria1 -> {
            return criteria;
        });
        return new PageListData(queryBean.getPage(),queryBean.getPageSize(),list);
    }
}
