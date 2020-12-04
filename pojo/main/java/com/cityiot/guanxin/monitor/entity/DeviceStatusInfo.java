package com.cityiot.guanxin.monitor.entity;

import java.util.Date;

public class DeviceStatusInfo {
    // 设备编号
    private String deviceCode;
    // 设备ID
    private long deviceId;
    // 数据更新时间
    private Date updateTime;
    // 数据更新地点编号
    private String code;
    // 数据更新地点
    private String address;
    // 数据的状态数值
    private int status;
    private String lng;
    private String lat;

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
    public static final class DeviceStatus {
        public static final Integer NORMAL = 1;
        public static final Integer EXCEPTION = 0;
    }
}
