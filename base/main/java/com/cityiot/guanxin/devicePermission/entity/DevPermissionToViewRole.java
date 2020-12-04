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
 * 设备权限与查看角色权限关联表
 */
@ApiModel(value="设备权限与查看角色")
@CnName("设备权限与查看角色权限关联表")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class DevPermissionToViewRole extends BaseEntity {

    @ApiModelProperty(value="设备权限ID",name="devicePermissionId",example="")
    @CnName("设备权限ID")
    private long devicePermissionId;

    @ApiModelProperty(value="查看角色ID",name="viewRoleId",example="")
    @CnName("查看角色ID")
    private long viewRoleId;

    public long getDevicePermissionId() {
        return devicePermissionId;
    }

    public void setDevicePermissionId(long devicePermissionId) {
        this.devicePermissionId = devicePermissionId;
    }

    public long getViewRoleId() {
        return viewRoleId;
    }

    public void setViewRoleId(long viewRoleId) {
        this.viewRoleId = viewRoleId;
    }
}
