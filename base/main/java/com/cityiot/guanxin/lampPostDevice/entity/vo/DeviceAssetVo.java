package com.cityiot.guanxin.lampPostDevice.entity.vo;

import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Transient;

/**
 * 资产管理（灯杆设备）
 */
public class DeviceAssetVo extends LampPostDeviceVo{

    @Transient
    private String area;// 地址

    @Transient
    private long areaCode;// 区域编码

    @Transient
    private String areaName;// 区域名称

    @Transient
    private String deviceGroupId;// 设备分组ID

    @Transient
    private String groupName;// 设备分组名称

    @Transient
    private String deviceCode;// 设备编码

    @Transient
    @CnName("外部系统设备ID")
    private String cid;

    @Transient
    @CnName("外部系统编码")
    private String appcode;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public long getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(long areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getDeviceGroupId() {
        return deviceGroupId;
    }

    public void setDeviceGroupId(String deviceGroupId) {
        this.deviceGroupId = deviceGroupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
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
}
