package com.cityiot.guanxin.user.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/**
 * 用户和角色关系
 * @author huangjinyong
 */
@ApiModel(value="用户和角色")
@CnName("用户和角色关系")
@Entity
public class UserRoleRelation extends BaseEntity {
    @ApiModelProperty(value="用户Id",name="userId",example="")
    // 用户Id
    private long userId;
    @ApiModelProperty(value="角色Id",name="roleId",example="")
    // 角色Id
    private long roleId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}
