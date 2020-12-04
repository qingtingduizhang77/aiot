package com.cityiot.guanxin.planGroup.entity;


import com.cityiot.guanxin.common.entity.BaseEntity;
import com.cityiot.guanxin.plan.entity.Plan;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.jpaquery.repository.annotations.FieldPath;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Transient;


/**
 * 平面图与平面图分组关系表
 */
@ApiModel(value="平面图与平面图分组")
@Entity
@CnName("Plan与PlanGroup中间表")
@EntityListeners(AuditingEntityListener.class)
public class PlanGroupToPlan extends BaseEntity {
    public static final String Parent = "T_Parent_Group";
    @ApiModelProperty(value="平面图id",name="planId",example="")
    @CnName("平面图id")
    @JoinEntity(name = "id",joinEntityClass = Plan.class,joinEntityAlias = Parent)
    private long planId;

    @ApiModelProperty(value="平面图名称",name="planName",example="")
    @CnName("平面图名称")
    @Transient
    @FieldPath(name = "name",joinEntityClass = Plan.class,joinEntityAlias = Parent)
    private String planName;

    @ApiModelProperty(value="平面图备注",name="planRemark",example="")
    @CnName("平面图备注")
    @Transient
    @FieldPath(name = "remark",joinEntityClass = Plan.class,joinEntityAlias = Parent)
    private String planRemark;

    @ApiModelProperty(value="平面图分组id",name="planGroupId",example="")
    @CnName("平面图分组id")
    private long planGroupId;

    @ApiModelProperty(value="平面图序号",name="ordering",example="")
    @CnName("平面图序号")
    private long ordering;

    public static String getParent() {
        return Parent;
    }

    public long getPlanId() {
        return planId;
    }

    public void setPlanId(long planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanRemark() {
        return planRemark;
    }

    public void setPlanRemark(String planRemark) {
        this.planRemark = planRemark;
    }

    public long getPlanGroupId() {
        return planGroupId;
    }

    public void setPlanGroupId(long planGroupId) {
        this.planGroupId = planGroupId;
    }

    public long getOrdering() {
        return ordering;
    }

    public void setOrdering(long ordering) {
        this.ordering = ordering;
    }
}
