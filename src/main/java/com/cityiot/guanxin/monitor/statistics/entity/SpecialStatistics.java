package com.cityiot.guanxin.monitor.statistics.entity;

public class SpecialStatistics extends Statistics {
    // 设备类型
    private long deviceType;
    // 设备类型名称
    private long deviceTypeName;
    // 警告次数
    private long warnCount;
    // 在线数
    private long onlineCount;

    public long getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(long deviceType) {
        this.deviceType = deviceType;
    }

    public long getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(long deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }

    public long getWarnCount() {
        return warnCount;
    }

    public void setWarnCount(long warnCount) {
        this.warnCount = warnCount;
    }

    public long getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(long onlineCount) {
        this.onlineCount = onlineCount;
    }
}
