package com.cityiot.guanxin.area.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
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
 * @author huangjinyong
 * 区域实体
 */
@ApiModel(value="区域")
@CnName("区域")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Area extends BaseEntity {
    public static final String Parent = "T_Parent_Area";
    @ApiModelProperty(value="区域名称",name="name",example="")
    @CnName("区域名称")
    private String name;
    @ApiModelProperty(value="上级区域Id",name="parentId",example="")
    @CnName("上级区域Id")
    @JoinEntity(name = "id",joinEntityClass = Area.class,joinEntityAlias = Parent)
    private long parentId;
    @ApiModelProperty(value="区域类型",name="areaType",example="")
    @CnName("区域类型")
    private int areaType;
    @ApiModelProperty(value="区域代号",name="mark",example="")
    @CnName("区域代号")
    private String mark;
    @ApiModelProperty(value="区域编号",name="code",example="")
    @CnName("区域编号")
    private long code;
    @ApiModelProperty(value="模块排序号",name="orderNumber",example="")
    @CnName("模块排序号")
    private int orderNumber;
    @ApiModelProperty(value="上级编码",name="parentCodeArr",example="")
    @CnName("上级编码，多个用逗号，隔开")
    private String parentCodeArr;
    @ApiModelProperty(value="上级区域名称",name="parentName",example="")
    @FieldPath(name = "name",joinEntityClass = Area.class,joinEntityAlias = Parent)
    @Transient
    private String parentName;

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

    public int getAreaType() {
        return areaType;
    }

    public void setAreaType(int areaType) {
        this.areaType = areaType;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentCodeArr() {
        return parentCodeArr;
    }

    public void setParentCodeArr(String parentCodeArr) {
        this.parentCodeArr = parentCodeArr;
    }
}
