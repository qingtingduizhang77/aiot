package com.cityiot.guanxin.lightPlans.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/**
 * 路灯分组控制管理
 * @author Guoyz
 * createTime   2020/5/12 10:06
 */
@ApiModel(value="路灯分组控制管理")
@CnName("路灯分组控制管理")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class LightPlansGroup extends BaseEntity {
    @ApiModelProperty(value="分组名称",name="name",example="")
    @CnName("分组名称")
    private String name;
    @ApiModelProperty(value="是否启用",name="remark",example="1-启用，0-禁用。")
    @CnName("是否启用")
    private Integer status;
    @ApiModelProperty(value="备注",name="remark",example="")
    @CnName("备注")
    private String remark;
    //@ApiModelProperty(value="分组路灯",name="lightGroupIds",example="[]")
    //@CnName("分组路灯")
    //private String lightGroupIds;
    //@ApiModelProperty(value="计划策略",name="lightPlansIds",example="[]")
    //@CnName("计划策略")
    //private String lightPlansIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
