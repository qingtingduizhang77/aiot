package com.cityiot.guanxin.branchLeader.entity;

import com.cityiot.guanxin.area.entity.Area;
import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.jpaquery.repository.annotations.FieldPath;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * yingzhao
 * 区域与负责人配置表
 */
@ApiModel(value="区域与负责人")
@Entity
@CnName("area与branch中间表")
public class BranchToArea extends BaseEntity {
    public static final String T_Area_BranchToArea = "T_Area_BranchToArea";

    @ApiModelProperty(value="负责人id",name="branchId",example="")
    @CnName("负责人id")
    private Long branchId;
    @ApiModelProperty(value="负责区域id",name="areaId",example="")
    @CnName("负责区域id")
    @JoinEntity(name = "id",joinEntityClass = Area.class,joinEntityAlias = T_Area_BranchToArea)
    private Long areaId;
    @ApiModelProperty(value="负责区域名称",name="areaName",example="")
    @CnName("负责区域名称")
    @Transient
    @FieldPath(name = "name",joinEntityClass = Area.class,joinEntityAlias = T_Area_BranchToArea)
    private String areaName;

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
