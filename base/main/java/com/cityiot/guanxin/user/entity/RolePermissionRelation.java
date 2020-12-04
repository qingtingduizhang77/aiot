package com.cityiot.guanxin.user.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;

/**
 * @author huangjinyong
 * 角色和权限的关系
 */
@ApiModel(value="角色和权限")
@CnName("角色和权限的关系")
@Entity
public class RolePermissionRelation extends BaseEntity {
    @ApiModelProperty(value="角色Id",name="roleId",example="")
    // 角色Id
    private long roleId;
    @ApiModelProperty(value="权限Id",name="permissionId",example="")
    // 权限Id
    private long permissionId;

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(long permissionId) {
        this.permissionId = permissionId;
    }
}
