package com.cityiot.guanxin.monitorDevice.vo;


import javax.persistence.Entity;
import javax.persistence.Id;

import swallow.framework.jpaquery.repository.annotations.CnName;

import java.util.Date;

@Entity
@CnName("监控设备信息")
public class DeviceVo {
    @Id
    @CnName("主键ID")
    private long deviceId;
    @CnName("设备编码")
    private String deviceCode;
    @CnName("设备名称")
    private String deviceName;
    @CnName("坐标 lat")
    private String lat;
    @CnName("坐标 lng")
    private String lng;
    @CnName("设备类型名")
    private String deviceTypeName;
    @CnName("坐标 distance")
    private String distance;
    @CnName("状态")
    private Integer status;
    @CnName("更新时间")
    private Date updateTime;
    @CnName("区域")
    private String area;
    @CnName("设备类型")
    private Integer deviceType;
    @CnName("外部系统设备ID")
    private String cid;
    @CnName("外部系统编码")
    private String appcode;
    @CnName("告警状态")
    private Integer alarmStatus;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
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

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getAppcode() {
		return appcode;
	}

	public void setAppcode(String appcode) {
		this.appcode = appcode;
	}

    public Integer getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(Integer alarmStatus) {
        this.alarmStatus = alarmStatus;
    }
}
