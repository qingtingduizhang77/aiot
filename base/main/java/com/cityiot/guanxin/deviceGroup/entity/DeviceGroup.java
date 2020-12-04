package com.cityiot.guanxin.deviceGroup.entity;

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
 * @author zhengjc
 * 设备分组
 */
@ApiModel(value="设备分组")
@CnName("设备分组")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class DeviceGroup extends BaseEntity {
    public static final String Parent = "T_Parent_Group";

    @ApiModelProperty(value="分组名称",name="name",example="")
    @CnName("分组名称")
    private String name;

    @ApiModelProperty(value="父分组id",name="parentId",example="")
    @CnName("父分组id")
    @JoinEntity(name = "id",joinEntityClass = DeviceGroup.class,joinEntityAlias = Parent)
    private long parentId;

    @ApiModelProperty(value="备注",name="remark",example="")
    @CnName("备注")
    private String remark;

    @ApiModelProperty(value="级别",name="level",example="")
    @CnName("级别")
    private int level;

    @ApiModelProperty(value="父分组id数组",name="parentIdArr",example="")
    @CnName("父分组id数组")
    private String parentIdArr;

    @ApiModelProperty(value="排序号",name="orderNo",example="")
    @CnName("排序号")
    private long orderNo;//排序号

    @ApiModelProperty(value="父分组名称",name="parentName",example="")
    @FieldPath(name = "name",joinEntityClass = DeviceGroup.class,joinEntityAlias = Parent)
    @Transient
    private String parentName;

    @ApiModelProperty(value="区域ID",name="areaId",example="")
    @CnName("区域ID")
    private long areaId;

    @ApiModelProperty(value="区域编码",name="areaCode",example="")
    @CnName("区域编码")
    private long areaCode;

    @ApiModelProperty(value="区域名称",name="areaName",example="")
    @CnName("区域名称")
    private String areaName;

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getParentIdArr() {
        return parentIdArr;
    }

    public void setParentIdArr(String parentIdArr) {
        this.parentIdArr = parentIdArr;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(long orderNo) {
        this.orderNo = orderNo;
    }

    public long getAreaId() {
        return areaId;
    }

    public void setAreaId(long areaId) {
        this.areaId = areaId;
    }

    public long getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(long areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
