package com.cityiot.guanxin.monitor.statistics.entity;

public class LajitongStatistics extends Statistics {
    // 满载次数
    private long fillCount;
    // 清空次数
    private long clearCount;
    // 当前满载数
    private long currentFillCount;
    // 当前未满数
    private long currentNotFillCount;

    public long getFillCount() {
        return fillCount;
    }

    public void setFillCount(long fillCount) {
        this.fillCount = fillCount;
    }

    public long getClearCount() {
        return clearCount;
    }

    public void setClearCount(long clearCount) {
        this.clearCount = clearCount;
    }

    public long getCurrentFillCount() {
        return currentFillCount;
    }

    public void setCurrentFillCount(long currentFillCount) {
        this.currentFillCount = currentFillCount;
    }

    public long getCurrentNotFillCount() {
        return currentNotFillCount;
    }

    public void setCurrentNotFillCount(long currentNotFillCount) {
        this.currentNotFillCount = currentNotFillCount;
    }
}
