package com.cityiot.guanxin.monitor.entity;

public class DeviceStatisticalInfoVo {
    private String upMonth;
    private String upDay;
    private int status;
    private long statusCount;

    public String getUpMonth() {
        return upMonth;
    }

    public void setUpMonth(String upMonth) {
        this.upMonth = upMonth;
    }

    public String getUpDay() {
        return upDay;
    }

    public void setUpDay(String upDay) {
        this.upDay = upDay;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getStatusCount() {
        return statusCount;
    }

    public void setStatusCount(long statusCount) {
        this.statusCount = statusCount;
    }
}
