package com.cityiot.guanxin.monitorDevice.vo;

import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@CnName("垃圾桶设备")
@Entity
public class DustbinDeviceVo {
    @Id
    @CnName("设备ID")
    private long deviceId;
    @CnName("区域")
    private String area;
    @CnName("设备编号")
    private String deviceCode;
    @CnName("设备名称")
    private String deviceName;
    @CnName("距离")
    private String distance;
    @CnName("纬度")
    private String lat;
    @CnName("经度")
    private String lng;
    @CnName("最后更新时间")
    private Date updateTime;
    @CnName("在线状态") //1 正常 2 离线
    private int DustbinStatus;
    @CnName("使用状态") //1 未满 2 已满
    private int useStatus;

    public int getDustbinStatus() {
        return DustbinStatus;
    }

    public void setDustbinStatus(int dustbinStatus) {
        DustbinStatus = dustbinStatus;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(int useStatus) {
        this.useStatus = useStatus;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}
