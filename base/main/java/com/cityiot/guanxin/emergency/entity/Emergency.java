package com.cityiot.guanxin.emergency.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import com.cityiot.guanxin.common.entity.OnlyIdEntity;
import com.cityiot.guanxin.deviceInformation.entity.DeviceType;
import com.cityiot.guanxin.deviceInformation.entity.Deviceinformation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.jpaquery.repository.annotations.FieldPath;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;

import javax.persistence.*;
import java.util.Date;

@ApiModel(value="告警")
@CnName("告警")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Emergency extends OnlyIdEntity {
    @ApiModelProperty(value="设备id",name="deviceId",example="")
    @CnName("设备id")
    @JoinEntity(name="id", joinEntityClass = Deviceinformation.class)
    private long deviceId;
    @ApiModelProperty(value="设备类型id",name="deviceTypeId",example="")
    @CnName("设备类型id")
    @JoinEntity(name = "id", joinEntityClass = DeviceType.class)
    private long deviceTypeId;
    @ApiModelProperty(value="告警类型",name="emergencyType",example="")
    @CnName("告警类型")
    private Integer emergencyType;
    @ApiModelProperty(value="告警描述",name="emergencyDesc",example="")
    @CnName("告警描述")
    private String emergencyDesc;
    @ApiModelProperty(value="发布时间",name="publishTime",example="")
    @CnName("发布时间")
    private Date publishTime;
    @ApiModelProperty(value="是否已读",name="isReaded",example="")
    @CnName("是否已读") //1 未读 2 已读
    private int isReaded;
    @ApiModelProperty(value="设备地址",name="address",example="")
    @CnName("设备地址")
    @Transient
    @FieldPath(name = "area", joinEntityClass = Deviceinformation.class)
    private String address;
    @ApiModelProperty(value="经度",name="longitude",example="")
    @CnName("经度")
    @Transient
    @FieldPath(name = "longitude", joinEntityClass = Deviceinformation.class)
    private String longitude;
    @ApiModelProperty(value="纬度",name="latitude",example="")
    @CnName("纬度")
    @Transient
    @FieldPath(name = "latitude", joinEntityClass = Deviceinformation.class)
    private String latitude;
    @ApiModelProperty(value="设备编号",name="deviceCode",example="")
    @CnName("设备编号")
    @Transient
    @FieldPath(name = "deviceCode", joinEntityClass = Deviceinformation.class)
    private String deviceCode;
    @ApiModelProperty(value="设备类型名称",name="deviceTypeName",example="")
    @CnName("设备类型名称")
    @Transient
    @FieldPath(name = "deviceTypeName", joinEntityClass = DeviceType.class)
    private String deviceTypeName;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }


    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public long getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(long deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public Integer getEmergencyType() {
        return emergencyType;
    }

    public void setEmergencyType(Integer emergencyType) {
        this.emergencyType = emergencyType;
    }

    public String getEmergencyDesc() {
        return emergencyDesc;
    }

    public void setEmergencyDesc(String emergencyDesc) {
        this.emergencyDesc = emergencyDesc;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public int getIsReaded() {
        return isReaded;
    }

    public void setIsReaded(int isReaded) {
        this.isReaded = isReaded;
    }
}
