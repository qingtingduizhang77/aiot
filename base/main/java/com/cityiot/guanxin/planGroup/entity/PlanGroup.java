package com.cityiot.guanxin.planGroup.entity;


import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Transient;

/**
 * @author Guoyz
 * 平面图分组
 */
@ApiModel(value="平面图分组")
@CnName("平面图分组")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class PlanGroup extends BaseEntity {
    public static final String T_PlanGroup_PlanGroup = "T_PlanGroup_PlanGroup";

    @ApiModelProperty(value="分组名",name="name",example="")
    @CnName("分组名")
    private String name;

    @ApiModelProperty(value="上级分组Id",name="parentId",example="")
    @CnName("上级分组Id")
    @JoinEntity(name = "id",joinEntityClass = PlanGroup.class,joinEntityAlias = T_PlanGroup_PlanGroup)
    private long parentId;

    @ApiModelProperty(value="级别",name="level",example="")
    @CnName("级别")
    private long level;//最高等级为1，最多9级

    @ApiModelProperty(value="排序号",name="orderNo",example="")
    @CnName("排序号")
    private long orderNo;//排序号

    @ApiModelProperty(value="父分组id数组",name="parentIdArr",example="")
    @CnName("父分组id数组")
    private String parentIdArr;//记录以上的父亲的id,例如：[1,2,3],这是第4层的分组，他的上一次id是3，3的上一层id是2

    @ApiModelProperty(value="备注",name="remark",example="")
    @CnName("备注")
    private String remark;

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }

    public String getParentIdArr() {
        return parentIdArr;
    }

    public void setParentIdArr(String parentIdArr) {
        this.parentIdArr = parentIdArr;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(long orderNo) {
        this.orderNo = orderNo;
    }
}
