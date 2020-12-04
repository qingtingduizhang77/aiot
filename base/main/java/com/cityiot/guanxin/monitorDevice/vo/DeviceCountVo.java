package com.cityiot.guanxin.monitorDevice.vo;

import javax.persistence.Entity;
import javax.persistence.Id;

import swallow.framework.jpaquery.repository.annotations.CnName;

// 监控设备数量统计
@Entity
@CnName("监控设备统计")
public class DeviceCountVo {
    @Id
    @CnName("ID主键")
    private long id;
    @CnName("设备数量")
    private Integer deviceCount;
    @CnName("设备类型名称")
    private String deviceTypeName;
    //1路灯 2视频监控 3充电桩 4垃圾桶
    @CnName("设备类型值：1路灯 2视频监控 3充电桩 4垃圾桶")
    private Integer deviceType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(Integer deviceCount) {
        this.deviceCount = deviceCount;
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }
}
