package com.cityiot.guanxin.old.charts.entity;


public class HistogramData<X,Y> {
    private X month;
    private X x;
    private X name;
    private Y y1;
    private Y y2;
    private Y y3;
    private Y value;

    public X getX() {
        return x;
    }

    public void setX(X x) {
        this.x = x;
    }

    public Y getY1() {
        return y1;
    }

    public void setY1(Y y1) {
        this.y1 = y1;
    }

    public Y getY2() {
        return y2;
    }

    public void setY2(Y y2) {
        this.y2 = y2;
    }

    public Y getY3() {
        return y3;
    }

    public void setY3(Y y3) {
        this.y3 = y3;
    }

    public X getName() {
        return name;
    }

    public void setName(X name) {
        this.name = name;
    }

    public Y getValue() {
        return value;
    }

    public void setValue(Y value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "HistogramData{" +
                "x=" + x +
                ", y1=" + y1 +
                ", y2=" + y2 +
                ", y3=" + y3 +
                '}';
    }

    public X getMonth() {
        return month;
    }

    public void setMonth(X month) {
        this.month = month;
    }
}
