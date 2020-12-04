package com.cityiot.guanxin.monitor.entity.dto;

/**
 * @author hjy
 * 图表结构
 */
public class ChartDto<T> {
    // 图表序号
    private int chartIndex;
    // 图表数据
    private T chartData;

    public int getChartIndex() {
        return chartIndex;
    }

    public void setChartIndex(int chartIndex) {
        this.chartIndex = chartIndex;
    }

    public T getChartData() {
        return chartData;
    }

    public void setChartData(T chartData) {
        this.chartData = chartData;
    }
}
