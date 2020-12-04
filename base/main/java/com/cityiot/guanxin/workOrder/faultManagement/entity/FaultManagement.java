package com.cityiot.guanxin.workOrder.faultManagement.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import com.cityiot.guanxin.deviceInformation.entity.DeviceBrand;
import com.cityiot.guanxin.deviceInformation.entity.DeviceModel;
import com.cityiot.guanxin.deviceInformation.entity.DeviceType;
import com.cityiot.guanxin.deviceInformation.entity.Deviceinformation;
import com.cityiot.guanxin.user.entity.Userview;
import com.cityiot.guanxin.workOrder.entity.ERepairWorkOrder;
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

@ApiModel(value="设备故障管理")
@Entity
@CnName("设备故障管理")
@EntityListeners(AuditingEntityListener.class)
@JoinEntity(fieldType = Long.class ,mainFiledName = "deviceModelId" , mainEnityClass = Deviceinformation.class,
        name = "id", joinEntityClass = DeviceModel.class)
// 品牌和厂商
@JoinEntity(fieldType = Long.class ,mainFiledName = "deviceTypeId" , mainEnityClass = DeviceModel.class,
        name = "id", joinEntityClass = DeviceType.class)
@JoinEntity(fieldType = Long.class ,mainFiledName = "deviceBrandId" , mainEnityClass = DeviceModel.class,
        name = "id", joinEntityClass = DeviceBrand.class)
public class FaultManagement extends BaseEntity {

    @ApiModelProperty(value="故障工单Id",name="faultBillNo",example="")
    @CnName("故障工单Id")
    @JoinEntity(name = "id", joinEntityClass = ERepairWorkOrder.class)
    private Long faultBillNo;

    @ApiModelProperty(value="维修工单Code",name="faultBillCode",example="")
    @CnName("维修工单Code")
    @Transient
    @FieldPath(name = "code",joinEntityClass = ERepairWorkOrder.class)
    private String faultBillCode;

    @ApiModelProperty(value="设备ID",name="deviceId",example="")
    @CnName("设备ID")
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

    @ApiModelProperty(value="总故障次数",name="faultCount",example="")
    @CnName("总故障次数")
    private Integer faultCount;

    @ApiModelProperty(value="故障描述",name="faultInfo",example="")
    @CnName("故障描述")
    private String faultInfo;

    @ApiModelProperty(value="故障时间",name="faultTime",example="")
    @CnName("故障时间")
    @Column(nullable = false)
    private Date faultTime;

    @ApiModelProperty(value="登记人",name="registrarId",example="")
    @CnName("登记人")
    @Column(nullable = false)
    @JoinEntity(name = "id",joinEntityClass = Userview.class)
    private Long registrarId;

    @ApiModelProperty(value="登记人名称",name="registrarName",example="")
    @CnName("登记人名称")
    @Transient
    @FieldPath(name = "username",joinEntityClass = Userview.class)
    private String registrarName;

    @ApiModelProperty(value="登记人电话",name="registrarPhone",example="")
    @CnName("登记人电话")
    private String registrarPhone;

    @ApiModelProperty(value="故障编号",name="faultCode",example="")
    @CnName("故障编号")
    @Column(nullable = false)
    private String faultCode;

    @ApiModelProperty(value="处理人",name="handlerId",example="")
    @CnName("处理人")
    private Long handlerId;

    @ApiModelProperty(value="处理人名称",name="handlerName",example="")
    @CnName("处理人名称")
    private String handlerName;

    @ApiModelProperty(value="处理时间",name="handleTime",example="")
    @CnName("处理时间")
    private Date handleTime;

    @ApiModelProperty(value="处理状态",name="handleStatus",example="")
    @CnName("处理状态")// 0 ： 未处理；1：已处理
    private int handleStatus;

    public Long getFaultBillNo() {
        return faultBillNo;
    }

    public void setFaultBillNo(Long faultBillNo) {
        this.faultBillNo = faultBillNo;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

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

    public Integer getFaultCount() {
        return faultCount;
    }

    public void setFaultCount(Integer faultCount) {
        this.faultCount = faultCount;
    }

    public String getFaultInfo() {
        return faultInfo;
    }

    public void setFaultInfo(String faultInfo) {
        this.faultInfo = faultInfo;
    }

    public Date getFaultTime() {
        return faultTime;
    }

    public void setFaultTime(Date faultTime) {
        this.faultTime = faultTime;
    }

    public Long getRegistrarId() {
        return registrarId;
    }

    public void setRegistrarId(Long registrarId) {
        this.registrarId = registrarId;
    }

    public String getRegistrarPhone() {
        return registrarPhone;
    }

    public void setRegistrarPhone(String registrarPhone) {
        this.registrarPhone = registrarPhone;
    }

    public String getFaultCode() {
        return faultCode;
    }

    public void setFaultCode(String faultCode) {
        this.faultCode = faultCode;
    }

    public Long getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Long handlerId) {
        this.handlerId = handlerId;
    }

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public int getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(int handleStatus) {
        this.handleStatus = handleStatus;
    }

    public String getRegistrarName() {
        return registrarName;
    }

    public void setRegistrarName(String registrarName) {
        this.registrarName = registrarName;
    }

    public String getFaultBillCode() {
        return faultBillCode;
    }

    public void setFaultBillCode(String faultBillCode) {
        this.faultBillCode = faultBillCode;
    }
}
