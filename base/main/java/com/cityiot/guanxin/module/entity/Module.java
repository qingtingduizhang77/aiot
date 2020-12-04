package com.cityiot.guanxin.module.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/**
 * @author huangjinyong
 * 模块实体
 */
@ApiModel(value="模块")
@CnName("模块")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Module extends BaseEntity {
    @ApiModelProperty(value="模块名称",name="name",example="")
    @CnName("模块名称")
    private String name;
    @ApiModelProperty(value="父级模块Id",name="parentId",example="")
    @CnName("父级模块Id")
    private long parentId;
    @ApiModelProperty(value="模块编码",name="code",example="")
    @CnName("模块编码")
    private String code;
    @ApiModelProperty(value="模块排序号",name="orderNumber",example="")
    @CnName("模块排序号")
    private int orderNumber;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
}
