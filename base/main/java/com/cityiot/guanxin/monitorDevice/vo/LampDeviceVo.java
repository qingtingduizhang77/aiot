package com.cityiot.guanxin.monitorDevice.vo;

import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@CnName("路灯设备")
@Entity
public class LampDeviceVo {
    @Id
    @CnName("设备ID")
    private long deviceId;
    @CnName("区域")
    private String area;
    @CnName("亮度")
    private Integer brightness;
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
    @CnName("开关状态") //1 开启 2 关闭
    private Integer switchStatus;
    @CnName("最后更新时间")
    private Date updateTime;

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

    public Integer getBrightness() {
        return brightness;
    }

    public void setBrightness(Integer brightness) {
        this.brightness = brightness;
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

    public Integer getSwitchStatus() {
        return switchStatus;
    }

    public void setSwitchStatus(Integer switchStatus) {
        this.switchStatus = switchStatus;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}
