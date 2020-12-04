package com.cityiot.guanxin.old.charts.service;

import com.cityiot.guanxin.old.charts.entity.Chart;
import com.cityiot.guanxin.old.charts.respository.ChartMongoRepository;
import com.cityiot.guanxin.common.service.BaseMongoService;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

@Service
public class ChartService extends BaseMongoService<ChartMongoRepository> {

    /** 通过图表的名字获取图表
     * @param chartName
     * @return
     */
    public Chart getChartByChartName(String chartName) {
        return repository.findOneEntityByColumns(criteria -> {
            return Criteria.where("chartName").is(chartName);
        });
    }
}
