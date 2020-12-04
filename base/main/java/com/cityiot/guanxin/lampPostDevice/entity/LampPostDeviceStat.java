package com.cityiot.guanxin.lampPostDevice.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import java.util.Date;


@ApiModel(value="灯杆统计视图")
@Entity
@CnName("灯杆统计视图")
@EntityListeners(AuditingEntityListener.class)
public class LampPostDeviceStat extends BaseEntity {

    @ApiModelProperty(value="设备名称",name="deviceName",example="")
    @CnName("设备名称")
    private String deviceName;

    @ApiModelProperty(value="地址",name="area",example="")
    @CnName("地址")
    private String area;

    @ApiModelProperty(value="设备状态",name="status",example="")
    @CnName("设备状态")
    private int status;

    @ApiModelProperty(value="亮灯状态",name="lightUpCount",example="")
    @CnName("亮灯状态")
    private int lightUpCount;

    @ApiModelProperty(value="告警状态",name="alarmCount",example="")
    @CnName("告警状态")
    private int alarmCount;

    @ApiModelProperty(value="最后通讯时间",name="lastComTime",example="")
    @CnName("最后通讯时间")
    private Date lastComTime;

    @ApiModelProperty(value="设备类型ID",name="deviceTypeId",example="")
    @CnName("设备类型ID")
    private long deviceTypeId;

    @ApiModelProperty(value="设备编号",name="deviceCode",example="")
    @CnName("设备编号")
    private String deviceCode;

    @ApiModelProperty(value="坐标",name="coordinates",example="")
    @CnName("坐标")
    private String coordinates;

    @ApiModelProperty(value="经度",name="longitude",example="")
    @CnName("经度")
    private String longitude;

    @ApiModelProperty(value="纬度",name="latitude",example="")
    @CnName("纬度")
    private String latitude;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getLightUpCount() {
        return lightUpCount;
    }

    public void setLightUpCount(int lightUpCount) {
        this.lightUpCount = lightUpCount;
    }

    public int getAlarmCount() {
        return alarmCount;
    }

    public void setAlarmCount(int alarmCount) {
        this.alarmCount = alarmCount;
    }

    public Date getLastComTime() {
        return lastComTime;
    }

    public void setLastComTime(Date lastComTime) {
        this.lastComTime = lastComTime;
    }

    public long getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(long deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
