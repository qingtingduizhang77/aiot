package com.cityiot.guanxin.workOrder.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import com.cityiot.guanxin.deviceInformation.entity.DeviceBrand;
import com.cityiot.guanxin.deviceInformation.entity.DeviceModel;
import com.cityiot.guanxin.deviceInformation.entity.DeviceType;
import com.cityiot.guanxin.deviceInformation.entity.Deviceinformation;
import com.cityiot.guanxin.entity.QSystemVariable;
import com.cityiot.guanxin.entity.SystemVariable;
import com.cityiot.guanxin.user.entity.Userview;
import com.cityiot.guanxin.workOrder.service.ERepairWorkOrderService;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.jpaquery.repository.annotations.FieldPath;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Transient;
import java.util.Date;

@ApiModel(value="维修工单")
@Entity
@CnName("维修工单")
@EntityListeners(AuditingEntityListener.class)
@JoinEntity(fieldType = Long.class ,mainFiledName = "deviceModelId" , mainEnityClass = Deviceinformation.class,
        name = "id", joinEntityClass = DeviceModel.class)
@JoinEntity(fieldType = Long.class ,mainFiledName = "deviceTypeId" , mainEnityClass = DeviceModel.class,
        name = "id", joinEntityClass = DeviceType.class)
@JoinEntity(fieldType = Long.class ,mainFiledName = "deviceBrandId" , mainEnityClass = DeviceModel.class,
        name = "id", joinEntityClass = DeviceBrand.class)
public class ERepairWorkOrder extends BaseEntity {
    @ApiModelProperty(value="维修工单编号",name="code",example="")
    @CnName("维修工单编号")
    private String code;
    @ApiModelProperty(value="故障/保养/巡检记录ID",name="recordId",example="")
    @CnName("故障/保养/巡检记录ID")
    private Long recordId;
    @ApiModelProperty(value="记录类型",name="recordType",example="")
    @CnName("记录类型")// 1故障；2保养；3巡检
    private int recordType;
    @ApiModelProperty(value="处理人ID",name="handlerId",example="")
    @CnName("处理人ID")
    @JoinEntity(name = "id", joinEntityClass = Userview.class)
    private Long handlerId;
    @ApiModelProperty(value="设备信息id",name="deviceInfoId",example="")
    @CnName("设备信息id")
    @JoinEntity(name = "id",joinEntityClass = Deviceinformation.class)
    private long deviceInfoId;
    @ApiModelProperty(value="工单状态",name="handleStatus",example="")
    @CnName("工单状态") //1待处理 2处理中 3已完成 4已退单
    @JoinEntity(name = "variable",joinEntityClass =  SystemVariable.class, extOnMethod = "extendJoinStatus")
    private int handleStatus;
    @ApiModelProperty(value="处理时间",name="handleTime",example="")
    @CnName("处理时间")
    private Date handleTime;
    @ApiModelProperty(value="备注",name="remark",example="")
    @CnName("备注")
    private String remark;
    @ApiModelProperty(value="设备编号",name="deviceCode",example="")
    @CnName("设备编号")
    @Transient
    @FieldPath(name = "deviceCode",joinEntityClass = Deviceinformation.class)
    private String deviceCode;
    @ApiModelProperty(value="设备信息名称",name="deviceName",example="")
    @CnName("设备信息名称")
    @Transient
    @FieldPath(name = "deviceName",joinEntityClass = Deviceinformation.class)
    private String deviceName;
    @ApiModelProperty(value="设备型号",name="deviceModel",example="")
    @CnName("设备型号")
    @Transient
    @FieldPath(name = "deviceModel",joinEntityClass = DeviceModel.class)
    private String deviceModel;
    @ApiModelProperty(value="设备类型",name="deviceType",example="")
    @CnName("设备类型")
    @Transient
    @FieldPath(name = "deviceTypeName",joinEntityClass = DeviceType.class)
    private String deviceType;
    @ApiModelProperty(value="设备类型名称",name="deviceTypeName",example="")
    @CnName("设备类型名称")
    @Transient
    @FieldPath(name = "deviceTypeName",joinEntityClass = DeviceType.class)
    private String deviceTypeName;
    @ApiModelProperty(value="设备厂家",name="deviceManufacturer",example="")
    // 品牌
    @CnName("设备厂家")
    @Transient
    @FieldPath(name = "deviceManufacturer",joinEntityClass = DeviceBrand.class)
    private String deviceManufacturer;

    @ApiModelProperty(value="设备品牌",name="deviceBrandName",example="")
    @CnName("设备品牌")
    @Transient
    @FieldPath(name = "deviceBrandName",joinEntityClass = DeviceBrand.class)
    private String deviceBrandName;
    @ApiModelProperty(value="所属区域",name="area",example="")
    @CnName("所属区域")
    @Transient
    @FieldPath(name = "area",joinEntityClass = Deviceinformation.class)
    private String area;
    @ApiModelProperty(value="维修描述",name="desContent",example="")
    @CnName("维修描述")
    private String desContent;
    @ApiModelProperty(value="维修项目",name="repairItems",example="")
    @CnName("维修项目")
    private String repairItems;
    @ApiModelProperty(value="经度",name="longitude",example="")
    @CnName("经度")
    @Transient
    @FieldPath(name = "longitude",joinEntityClass = Deviceinformation.class)
    private String longitude;
    @ApiModelProperty(value="纬度",name="latitude",example="")
    @CnName("纬度")
    @Transient
    @FieldPath(name = "latitude",joinEntityClass = Deviceinformation.class)
    private String latitude;

    @ApiModelProperty(value="维修金额",name="money",example="")
    @CnName("维修金额")
    private Double money;
    @ApiModelProperty(value="工单发起人",name="originator",example="")
    @CnName("工单发起人")
    private String originator;
    @ApiModelProperty(value="发起人电话",name="originatorPhone",example="")
    @CnName("发起人电话")
    private String originatorPhone;
    @ApiModelProperty(value="处理人姓名",name="handlerName",example="")
    @CnName("处理人姓名")
    @FieldPath(name = "username", joinEntityClass = Userview.class)
    @Transient
    private String handlerName;
    @ApiModelProperty(value="工单状态名称",name="handleStatusValue",example="")
    @CnName("工单状态名称")
    @Transient
    @FieldPath(name = "name", joinEntityClass = SystemVariable.class)
    private String handleStatusValue;

    // 工单状态变量
    public static Predicate extendJoinStatus() {
        var corpType=QSystemVariable.systemVariable;
        return corpType.vartype.eq(ERepairWorkOrderService.WORK_ORDER_STATUS);
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }

    public long getDeviceInfoId() {
        return deviceInfoId;
    }

    public void setDeviceInfoId(long deviceInfoId) {
        this.deviceInfoId = deviceInfoId;
    }

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator;
    }

    public String getOriginatorPhone() {
        return originatorPhone;
    }

    public void setOriginatorPhone(String originatorPhone) {
        this.originatorPhone = originatorPhone;
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

    public String getDesContent() {
        return desContent;
    }

    public void setDesContent(String desContent) {
        this.desContent = desContent;
    }

    public String getRepairItems() {
        return repairItems;
    }

    public void setRepairItems(String repairItems) {
        this.repairItems = repairItems;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
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


    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Long getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Long handlerId) {
        this.handlerId = handlerId;
    }

    public int getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(int handleStatus) {
        this.handleStatus = handleStatus;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }

    public String getHandleStatusValue() {
        return handleStatusValue;
    }

    public void setHandleStatusValue(String handleStatusValue) {
        this.handleStatusValue = handleStatusValue;
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

    public int getRecordType() {
        return recordType;
    }

    public void setRecordType(int recordType) {
        this.recordType = recordType;
    }
}
