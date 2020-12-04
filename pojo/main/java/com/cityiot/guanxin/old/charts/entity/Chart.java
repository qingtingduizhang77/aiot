package com.cityiot.guanxin.old.charts.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@Document
public class Chart<T> {
    @Id
    private ObjectId id;
    // 图的唯一名称
    private String chartName;
    private Collection<T> chartData;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getChartName() {
        return chartName;
    }

    public void setChartName(String chartName) {
        this.chartName = chartName;
    }

    public Collection<T> getChartData() {
        return chartData;
    }

    public void setChartData(Collection<T> chartData) {
        this.chartData = chartData;
    }

    @Override
    public String toString() {
        return "Chart{" +
                "id=" + id +
                ", chartName='" + chartName + '\'' +
                ", chartData=" + chartData +
                '}';
    }
}
