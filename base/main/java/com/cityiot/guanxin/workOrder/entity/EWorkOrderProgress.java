package com.cityiot.guanxin.workOrder.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Transient;

import com.cityiot.guanxin.common.utils.TypeNameUtil;
import com.cityiot.guanxin.entity.QSystemVariable;
import com.cityiot.guanxin.entity.SystemVariableType;
import com.cityiot.guanxin.service.SystemVariableService;
import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.entity.OperatorOrCompanyManage;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.cityiot.guanxin.common.entity.BaseEntity;
import com.cityiot.guanxin.entity.SystemVariable;
import com.cityiot.guanxin.user.entity.Userview;

import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.jpaquery.repository.annotations.FieldPath;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;

@ApiModel(value="工单进度")
@Entity
@CnName("工单进度")
@EntityListeners(AuditingEntityListener.class)
@JoinEntity(fieldType = Long.class ,mainFiledName = "vartype" , mainEnityClass = SystemVariable.class,
        name = "id", joinEntityClass = SystemVariableType.class)
public class EWorkOrderProgress extends BaseEntity {

    static final String T_SystemVariable_progressType="T_SystemVariable_progressType";

    @ApiModelProperty(value="工单ID",name="workOrderId",example="")
    @CnName("工单ID")
    private long workOrderId;
    @ApiModelProperty(value="工单类型",name="workOrderType",example="")
    @CnName("工单类型")
    private int workOrderType; //1保养2巡检3故障;

    @ApiModelProperty(value="进度类型",name="progressType",example="")
    @CnName("进度类型")
    @JoinEntity(name = "variable",joinEntityClass =  SystemVariable.class,extOnMethod = "extendJoinStatus"
//            ,joinEntityAlias = T_SystemVariable_progressType
           )
    private int progressType; //1创建 2指派 3 完成 4确认工单 6退回

    @ApiModelProperty(value="进度类型名称",name="progressTypeValue",example="")
    @Transient
    @CnName("进度类型名称")
//    @FieldPath(name = "name",joinEntityClass =  SystemVariable.class
////            ,joinEntityAlias = T_SystemVariable_progressType
//    )
    private String progressTypeValue;
    @ApiModelProperty(value="时间",name="time",example="")
    @CnName("时间")
    private Date time;
    @ApiModelProperty(value="备注",name="remark",example="")
    @CnName("备注")
    private String remark;
    @ApiModelProperty(value="当前处理人id",name="operatorId",example="")
    @CnName("当前处理人id")
    @JoinEntity(name = "id", joinEntityClass = OperatorOrCompanyManage.class)
    private Long operatorId;
    @ApiModelProperty(value="进度内容",name="progressContent",example="")
    @CnName("进度内容")
    private String progressContent;

    @ApiModelProperty(value="当前后台处理人id",name="userId",example="")
    @CnName("当前后台处理人id")
    @JoinEntity(name = "id", joinEntityClass = Userview.class)
    private Long userId;

    @ApiModelProperty(value="姓名",name="name",example="")
    @CnName("姓名")
    @Transient
    @FieldPath(name = "username", joinEntityClass = Userview.class)
    private String name;

    @ApiModelProperty(value="当前处理人名称",name="operatorName",example="")
    @CnName("当前处理人名称")
    @Transient
    @FieldPath(name = "name", joinEntityClass = OperatorOrCompanyManage.class)
    private String operatorName;

    // 指定联结时只联结类型为WORK_ORDER_STATUS的值
    public static Predicate extendJoinStatus() {
        var corpType= QSystemVariable.systemVariable;
        return corpType.vartype.eq(SystemVariableService.PROGRESS_STATUS);
    }

    public String getProgressTypeValue() {
        return TypeNameUtil.getProcessTypeName(workOrderType, progressType);
    }

    public void setProgressTypeValue(String progressTypeValue) {
        this.progressTypeValue = progressTypeValue;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(long workOrderId) {
        this.workOrderId = workOrderId;
    }

    public int getWorkOrderType() {
        return workOrderType;
    }

    public void setWorkOrderType(int workOrderType) {
        this.workOrderType = workOrderType;
    }

    public int getProgressType() {
        return progressType;
    }

    public void setProgressType(int progressType) {
        this.progressType = progressType;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getProgressContent() {
        return progressContent;
    }

    public void setProgressContent(String progressContent) {
        this.progressContent = progressContent;
    }

}
