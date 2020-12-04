package com.cityiot.guanxin.devicePermission.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/**
 * @author zhengjc
 * 设备权限与区域编码权限关联表
 */
@ApiModel(value="设备权限与区域编码")
@CnName("设备权限与区域编码权限关联表")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class DevPermissionToAreaCode extends BaseEntity {

    @ApiModelProperty(value="设备权限ID",name="devicePermissionId",example="")
    @CnName("设备权限ID")
    private long devicePermissionId;

    @ApiModelProperty(value="区域ID",name="areaId",example="")
    @CnName("区域ID")
    private long areaId;

    @ApiModelProperty(value="区域编码",name="areaCode",example="")
    @CnName("区域编码")
    private long areaCode;

    public long getDevicePermissionId() {
        return devicePermissionId;
    }

    public void setDevicePermissionId(long devicePermissionId) {
        this.devicePermissionId = devicePermissionId;
    }

    public long getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(long areaCode) {
        this.areaCode = areaCode;
    }

    public long getAreaId() {
        return areaId;
    }

    public void setAreaId(long areaId) {
        this.areaId = areaId;
    }
}
