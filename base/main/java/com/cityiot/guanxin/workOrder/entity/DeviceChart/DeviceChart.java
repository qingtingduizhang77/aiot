package com.cityiot.guanxin.workOrder.entity.DeviceChart;

import java.util.Map;

public class DeviceChart {

     private Map<String, Map<String,Double>> chartItem;

    public Map<String, Map<String, Double>> getChartItem() {
        return chartItem;
    }

    public void setChartItem(Map<String, Map<String, Double>> chartItem) {
        this.chartItem = chartItem;
    }
}
