package com.cityiot.guanxin.workOrder.inspection.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import com.cityiot.guanxin.deviceInformation.entity.DeviceBrand;
import com.cityiot.guanxin.deviceInformation.entity.DeviceModel;
import com.cityiot.guanxin.deviceInformation.entity.DeviceType;
import com.cityiot.guanxin.deviceInformation.entity.Deviceinformation;
import com.cityiot.guanxin.user.entity.Userview;
import com.cityiot.guanxin.workOrder.entity.EInspectionWorkOrder;
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

@ApiModel(value="设备巡检记录")
@Entity
@CnName("设备巡检记录")
@EntityListeners(AuditingEntityListener.class)
@JoinEntity(fieldType = Long.class ,mainFiledName = "deviceModelId" , mainEnityClass = Deviceinformation.class,
        name = "id", joinEntityClass = DeviceModel.class)
// 品牌和厂商
@JoinEntity(fieldType = Long.class ,mainFiledName = "deviceTypeId" , mainEnityClass = DeviceModel.class,
        name = "id", joinEntityClass = DeviceType.class)
@JoinEntity(fieldType = Long.class ,mainFiledName = "deviceBrandId" , mainEnityClass = DeviceModel.class,
        name = "id", joinEntityClass = DeviceBrand.class)
public class InspectionRecord extends BaseEntity {

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

    @ApiModelProperty(value="上次巡检时间",name="lastPatrolTime",example="")
    @CnName("上次巡检时间")
    private Date lastPatrolTime;

    /**
     * 0:未巡检
     * 1:已巡检
     * 2:异常处理
     */
    @ApiModelProperty(value="巡检状态",name="patrolStatus",example="")
    @CnName("巡检状态")
    @Column(nullable = false)
    private Integer patrolStatus;

    @ApiModelProperty(value="巡检人",name="patroller",example="")
    @CnName("巡检人")
    @JoinEntity(name = "id",joinEntityClass = Userview.class)
    private Long patroller;

    @ApiModelProperty(value="巡检人名称",name="patrollerName",example="")
    @CnName("巡检人名称")
    @Transient
    @FieldPath(name = "username",joinEntityClass = Userview.class)
    private String patrollerName;

    @ApiModelProperty(value="巡检人电话",name="patrollerPhone",example="")
    @CnName("巡检人电话")
    private String patrollerPhone;

    @ApiModelProperty(value="巡检时间",name="patrolTime",example="")
    @CnName("巡检时间")
    private Date patrolTime;

    @ApiModelProperty(value="巡检关联单号",name="patrollerNo",example="")
    @CnName("巡检关联单号")
    @JoinEntity(name = "id", joinEntityClass = EInspectionWorkOrder.class)
    private Long patrollerNo;

    @ApiModelProperty(value="巡检工单Code",name="patrollerCode",example="")
    @CnName("巡检工单Code")
    @Transient
    @FieldPath(name = "code",joinEntityClass = EInspectionWorkOrder.class)
    private String patrollerCode;

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

    @ApiModelProperty(value="备注",name="remark",example="")
    @CnName("备注")
    private String remark;

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

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Date getLastPatrolTime() {
        return lastPatrolTime;
    }

    public void setLastPatrolTime(Date lastPatrolTime) {
        this.lastPatrolTime = lastPatrolTime;
    }

    public Integer getPatrolStatus() {
        return patrolStatus;
    }

    public void setPatrolStatus(Integer patrolStatus) {
        this.patrolStatus = patrolStatus;
    }

    public Long getPatroller() {
        return patroller;
    }

    public void setPatroller(Long patroller) {
        this.patroller = patroller;
    }

    public String getPatrollerName() {
        return patrollerName;
    }

    public void setPatrollerName(String patrollerName) {
        this.patrollerName = patrollerName;
    }

    public String getPatrollerPhone() {
        return patrollerPhone;
    }

    public void setPatrollerPhone(String patrollerPhone) {
        this.patrollerPhone = patrollerPhone;
    }

    public Date getPatrolTime() {
        return patrolTime;
    }

    public void setPatrolTime(Date patrolTime) {
        this.patrolTime = patrolTime;
    }

    public Long getPatrollerNo() {
        return patrollerNo;
    }

    public void setPatrollerNo(Long patrollerNo) {
        this.patrollerNo = patrollerNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
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

    public String getPatrollerCode() {
        return patrollerCode;
    }

    public void setPatrollerCode(String patrollerCode) {
        this.patrollerCode = patrollerCode;
    }

    public String getFaultBillCode() {
        return faultBillCode;
    }

    public void setFaultBillCode(String faultBillCode) {
        this.faultBillCode = faultBillCode;
    }
}
