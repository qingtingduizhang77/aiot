package com.cityiot.guanxin.monitor.statistics.entity;

public class JinggaiStatistics extends Statistics {
    // 打开数
    private long openCount;
    // 合法打开数
    private long normalCount;
    // 非法打开数
    private long nonormalCount;

    public long getOpenCount() {
        return openCount;
    }

    public void setOpenCount(long openCount) {
        this.openCount = openCount;
    }

    public long getNormalCount() {
        return normalCount;
    }

    public void setNormalCount(long normalCount) {
        this.normalCount = normalCount;
    }

    public long getNonormalCount() {
        return nonormalCount;
    }

    public void setNonormalCount(long nonormalCount) {
        this.nonormalCount = nonormalCount;
    }
}
