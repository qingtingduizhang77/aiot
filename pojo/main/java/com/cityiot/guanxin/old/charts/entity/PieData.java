package com.cityiot.guanxin.old.charts.entity;

public class PieData<R,V> {
    private R name;
    private V value;

    public R getName() {
        return name;
    }

    public void setName(R name) {
        this.name = name;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "PieData{" +
                "name=" + name +
                ", value=" + value +
                '}';
    }
}
