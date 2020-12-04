package com.cityiot.guanxin.workOrder.entity;


import com.cityiot.guanxin.common.entity.BaseEntity;
import com.cityiot.guanxin.entity.QSystemVariable;
import com.cityiot.guanxin.entity.SystemVariable;
import com.cityiot.guanxin.service.SystemVariableService;
import com.cityiot.guanxin.user.entity.Userview;
import com.cityiot.guanxin.workOrder.service.EMaintenanceWorkOrderService;
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

@ApiModel(value="保养工单")
@Entity
@CnName("保养工单")
@EntityListeners(AuditingEntityListener.class)
public class EMaintenanceWorkOrder extends BaseEntity {
    @ApiModelProperty(value="保养工单编号",name="code",example="")
    @CnName("保养工单编号")
    private String code;
    @ApiModelProperty(value="处理人ID",name="handlerId",example="")
    @CnName("处理人ID")
    @JoinEntity(name = "id", joinEntityClass = Userview.class)
    private Long handlerId;
    @ApiModelProperty(value="设备信息id",name="deviceIds",example="")
    @CnName("设备信息id")
    private String deviceIds;
    @ApiModelProperty(value="工单状态",name="handleStatus",example="")
    @CnName("工单状态") //10待发布 20待处理 30处理中 40处理完成 900已退单
    @JoinEntity(name = "variable",joinEntityClass =  SystemVariable.class, extOnMethod = "extendJoinStatus")
    private int handleStatus;
    @ApiModelProperty(value="处理时间",name="handleTime",example="")
    @CnName("处理时间")
    private Date handleTime;
    @ApiModelProperty(value="备注",name="remark",example="")
    @CnName("备注")
    private String remark;
    @ApiModelProperty(value="设备信息名称",name="deviceName",example="")
    @CnName("设备信息名称")
    private String deviceName;
    @ApiModelProperty(value="保养描述",name="desContent",example="")
    @CnName("保养描述")
    private String desContent;
    @ApiModelProperty(value="保养项目",name="maintainenceItems",example="")
    @CnName("保养项目")
    private String maintainenceItems;
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

    // 通过扩展联结的方式，指定联结时只联结类型为WORK_ORDER_STATUS的值
    public static Predicate extendJoinStatus() {
        var corpType=QSystemVariable.systemVariable;
        return corpType.vartype.eq(EMaintenanceWorkOrderService.WORK_ORDER_STATUS);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Long handlerId) {
        this.handlerId = handlerId;
    }

    public String getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(String deviceIds) {
        this.deviceIds = deviceIds;
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

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDesContent() {
        return desContent;
    }

    public void setDesContent(String desContent) {
        this.desContent = desContent;
    }

    public String getMaintainenceItems() {
        return maintainenceItems;
    }

    public void setMaintainenceItems(String maintainenceItems) {
        this.maintainenceItems = maintainenceItems;
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
}
