package com.cityiot.guanxin.workOrder.deviceLog.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import com.cityiot.guanxin.deviceInformation.entity.DeviceBrand;
import com.cityiot.guanxin.deviceInformation.entity.DeviceModel;
import com.cityiot.guanxin.deviceInformation.entity.DeviceType;
import com.cityiot.guanxin.deviceInformation.entity.Deviceinformation;
import com.cityiot.guanxin.user.entity.Userview;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.jpaquery.repository.annotations.FieldPath;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Transient;
import java.util.Date;

@ApiModel(value="设备日志")
@Entity
@CnName("设备日志")
@EntityListeners(AuditingEntityListener.class)
@JoinEntity(fieldType = Long.class ,mainFiledName = "deviceModelId" , mainEnityClass = Deviceinformation.class,
        name = "id", joinEntityClass = DeviceModel.class)
// 品牌和厂商
@JoinEntity(fieldType = Long.class ,mainFiledName = "deviceTypeId" , mainEnityClass = DeviceModel.class,
        name = "id", joinEntityClass = DeviceType.class)
@JoinEntity(fieldType = Long.class ,mainFiledName = "deviceBrandId" , mainEnityClass = DeviceModel.class,
        name = "id", joinEntityClass = DeviceBrand.class)
public class DeviceLog extends BaseEntity {

    @ApiModelProperty(value="记录时间",name="markTime",example="")
    @CnName("记录时间")
    @Column(nullable = false)
    private Date markTime;

    @ApiModelProperty(value="操作类型",name="operationType",example="")
    @CnName("操作类型")
    @Column(nullable = false)
    private Integer operationType;

    @ApiModelProperty(value="设备Id",name="deviceId",example="")
    @CnName("设备Id")
    @Column(nullable = false)
    @JoinEntity(name = "id",joinEntityClass = Deviceinformation.class)
    private Long deviceId;

    @ApiModelProperty(value="设备名称",name="deviceName",example="")
    @CnName("设备名称")
    @Transient
    @FieldPath(name = "deviceName",joinEntityClass = Deviceinformation.class)
    private String deviceName;

    @ApiModelProperty(value="设备型号",name="deviceModel",example="")
    @CnName("设备型号")
    @Transient
    @FieldPath(name = "deviceModel",joinEntityClass = DeviceModel.class)
    private String deviceModel;

    @ApiModelProperty(value="设备编号",name="deviceCode",example="")
    @CnName("设备编号")
    @Transient
    @FieldPath(name = "deviceCode",joinEntityClass = Deviceinformation.class)
    private String deviceCode;

    //

    // 品牌
    @ApiModelProperty(value="设备厂家",name="deviceManufacturer",example="")
    @CnName("设备厂家")
    @Transient
    @FieldPath(name = "deviceManufacturer",joinEntityClass = DeviceBrand.class)
    private String deviceManufacturer;

    @ApiModelProperty(value="设备品牌",name="deviceBrandName",example="")
    @CnName("设备品牌")
    @Transient
    @FieldPath(name = "deviceBrandName",joinEntityClass = DeviceBrand.class)
    private String deviceBrandName;

    // 类别
    @ApiModelProperty(value="设备类型",name="deviceTypeName",example="")
    @CnName("设备类型")
    @Transient
    @FieldPath(name = "deviceTypeName",joinEntityClass = DeviceType.class)
    private String deviceTypeName;

    //

    @ApiModelProperty(value="操作人id",name="operatorId",example="")
    @CnName("操作人id")
    @Column(nullable = false)
    @JoinEntity(name = "id",joinEntityClass = Userview.class)
    private Long operatorId;

    @ApiModelProperty(value="操作人名称",name="operatorName",example="")
    @CnName("操作人名称")
    @Transient
    @FieldPath(name = "username",joinEntityClass = Userview.class)
    private String operatorName;

    @ApiModelProperty(value="操作人电话",name="operatorPhone",example="")
    @CnName("操作人电话")
    private String operatorPhone;

    @ApiModelProperty(value="备注",name="remark",example="")
    @CnName("备注")
    private String remark;

    @ApiModelProperty(value="描述",name="description",example="")
    @CnName("描述")
    private String description;

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceManufacturer() {
        return deviceManufacturer;
    }

    public void setDeviceManufacturer(String deviceManufacturer) {
        this.deviceManufacturer = deviceManufacturer;
    }

    public String getDeviceBrandName() {
        return deviceBrandName;
    }

    public void setDeviceBrandName(String deviceBrandName) {
        this.deviceBrandName = deviceBrandName;
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public Date getMarkTime() {
        return markTime;
    }

    public void setMarkTime(Date markTime) {
        this.markTime = markTime;
    }

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorPhone() {
        return operatorPhone;
    }

    public void setOperatorPhone(String operatorPhone) {
        this.operatorPhone = operatorPhone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
}
