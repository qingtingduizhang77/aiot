package com.cityiot.guanxin.organization.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/**
 * @author zhengjc
 * 组织管理
 */
@ApiModel(value="组织")
@CnName("组织")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Organization extends BaseEntity {

    @ApiModelProperty(value="组织名称",name="name",example="")
    @CnName("组织名称")
    private String name;

    @ApiModelProperty(value="模块排序号",name="orderNumber",example="")
    @CnName("模块排序号")
    private int orderNumber;

    @ApiModelProperty(value="说明",name="description",example="")
    @CnName("说明")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
