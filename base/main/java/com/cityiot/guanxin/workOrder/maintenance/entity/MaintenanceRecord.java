package com.cityiot.guanxin.workOrder.maintenance.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import com.cityiot.guanxin.deviceInformation.entity.DeviceBrand;
import com.cityiot.guanxin.deviceInformation.entity.DeviceModel;
import com.cityiot.guanxin.deviceInformation.entity.DeviceType;
import com.cityiot.guanxin.deviceInformation.entity.Deviceinformation;
import com.cityiot.guanxin.user.entity.Userview;
import com.cityiot.guanxin.workOrder.entity.EMaintenanceWorkOrder;
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

@ApiModel(value="设备保养记录")
@Entity
@CnName("设备保养记录")
@EntityListeners(AuditingEntityListener.class)
@JoinEntity(fieldType = Long.class ,mainFiledName = "deviceModelId" , mainEnityClass = Deviceinformation.class,
        name = "id", joinEntityClass = DeviceModel.class)
// 品牌和厂商
@JoinEntity(fieldType = Long.class ,mainFiledName = "deviceTypeId" , mainEnityClass = DeviceModel.class,
        name = "id", joinEntityClass = DeviceType.class)
@JoinEntity(fieldType = Long.class ,mainFiledName = "deviceBrandId" , mainEnityClass = DeviceModel.class,
        name = "id", joinEntityClass = DeviceBrand.class)
public class MaintenanceRecord extends BaseEntity {

    @ApiModelProperty(value="保养关联单号",name="maintainNo",example="")
    @CnName("保养关联单号")
    @JoinEntity(name = "id",joinEntityClass = EMaintenanceWorkOrder.class)
    private Long maintainNo;

    @ApiModelProperty(value="保养工单Code",name="maintainCode",example="")
    @CnName("保养工单Code")
    @Transient
    @FieldPath(name = "code",joinEntityClass = EMaintenanceWorkOrder.class)
    private String maintainCode;

    @ApiModelProperty(value="是否创建维修工单",name="isCreatedFault",example="")
    @CnName("是否创建维修工单")
    /**
     * 0:自动创建维修
     * 1:不创建维修工单
     */
    private int isCreatedFault;

    @ApiModelProperty(value="维修工单ID",name="faultBillNo",example="")
    @CnName("维修工单ID")
    @JoinEntity(name = "id", joinEntityClass = ERepairWorkOrder.class)
    private Long faultBillNo;

    @ApiModelProperty(value="维修工单Code",name="faultBillCode",example="")
    @CnName("维修工单Code")
    @Transient
    @FieldPath(name = "code",joinEntityClass = ERepairWorkOrder.class)
    private String faultBillCode;

    /**
     * 0:未保养
     * 1:保养中
     * 2:异常处理
     */
    @ApiModelProperty(value="保养状态",name="maintainStatus",example="")
    @CnName("保养状态")
    @Column(nullable = false)
    private Integer maintainStatus;

    @ApiModelProperty(value="设备id",name="deviceId",example="")
    @CnName("设备id")
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

    @ApiModelProperty(value="维保周期",name="maintenanceCycle",example="")
    @CnName("维保周期")
    @Transient
    @FieldPath(name = "maintenanceCycle",joinEntityClass = DeviceModel.class)
    private Integer maintenanceCycle;

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


    @ApiModelProperty(value="上次保养时间",name="lastMaintainTime",example="")
    @CnName("上次保养时间")
    private Date lastMaintainTime;

    @ApiModelProperty(value="保养周期",name="maintainCycle",example="")
    @CnName("保养周期")
    private Integer maintainCycle;

    @ApiModelProperty(value="处理人",name="operatorId",example="")
    @CnName("处理人")
    @JoinEntity(name = "id",joinEntityClass = Userview.class)
    private Long operatorId;

    @ApiModelProperty(value="处理人名称",name="operatorName",example="")
    @CnName("处理人名称")
    @Transient
    @FieldPath(name = "username",joinEntityClass = Userview.class)
    private String operatorName;

    @ApiModelProperty(value="处理时间",name="operatorTime",example="")
    @CnName("处理时间")
    private Date operatorTime;

    @ApiModelProperty(value="处理人电话",name="operatorPhone",example="")
    @CnName("处理人电话")
    private String operatorPhone;

    @ApiModelProperty(value="备注",name="remark",example="")
    @CnName("备注")
    private String remark;

    public Integer getMaintenanceCycle() {
        return maintenanceCycle;
    }

    public void setMaintenanceCycle(Integer maintenanceCycle) {
        this.maintenanceCycle = maintenanceCycle;
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

    public Long getMaintainNo() {
        return maintainNo;
    }

    public void setMaintainNo(Long maintainNo) {
        this.maintainNo = maintainNo;
    }

    public Integer getMaintainStatus() {
        return maintainStatus;
    }

    public void setMaintainStatus(Integer maintainStatus) {
        this.maintainStatus = maintainStatus;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Date getLastMaintainTime() {
        return lastMaintainTime;
    }

    public void setLastMaintainTime(Date lastMaintainTime) {
        this.lastMaintainTime = lastMaintainTime;
    }

    public Integer getMaintainCycle() {
        return maintainCycle;
    }

    public void setMaintainCycle(Integer maintainCycle) {
        this.maintainCycle = maintainCycle;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
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

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public int getIsCreatedFault() {
        return isCreatedFault;
    }

    public void setIsCreatedFault(int isCreatedFault) {
        this.isCreatedFault = isCreatedFault;
    }

    public Long getFaultBillNo() {
        return faultBillNo;
    }

    public void setFaultBillNo(Long faultBillNo) {
        this.faultBillNo = faultBillNo;
    }

    public String getMaintainCode() {
        return maintainCode;
    }

    public void setMaintainCode(String maintainCode) {
        this.maintainCode = maintainCode;
    }

    public String getFaultBillCode() {
        return faultBillCode;
    }

    public void setFaultBillCode(String faultBillCode) {
        this.faultBillCode = faultBillCode;
    }
}
