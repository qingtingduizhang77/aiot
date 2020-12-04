package com.cityiot.guanxin.monitor.statistics.service;

import com.cityiot.guanxin.monitor.statistics.control.StatisticsControl;
import com.cityiot.guanxin.monitor.statistics.repository.BaseStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import swallow.framework.web.PageListData;

import java.util.List;

public class BaseStatisticsService<T extends BaseStatisticsRepository> {
    @Autowired
    protected T repository;
    public PageListData<List<T>> queryAllItemsPageByQueryBean(StatisticsControl.QueryBean queryBean) {
        return repository.queryAllItemsPageByQueryBean(queryBean);
    }
}
