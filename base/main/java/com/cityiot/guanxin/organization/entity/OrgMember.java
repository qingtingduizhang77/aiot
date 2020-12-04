package com.cityiot.guanxin.organization.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import com.cityiot.guanxin.user.entity.Userview;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.jpaquery.repository.annotations.FieldPath;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Transient;

/**
 * @author zhengjc
 * 组织成员
 */
@ApiModel(value="组织成员")
@CnName("组织成员")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class OrgMember extends BaseEntity {

    @ApiModelProperty(value="组织ID",name="orgId",example="")
    @CnName("组织ID")
    @Column(nullable = false)
    @JoinEntity(name = "id",joinEntityClass = Organization.class)
    private long orgId;

    @ApiModelProperty(value="成员ID",name="userId",example="")
    @CnName("成员ID")
    @JoinEntity(name = "id",joinEntityClass = Userview.class)
    private long userId;

    @ApiModelProperty(value="成员名称",name="userName",example="")
    @CnName("成员名称")
    @Transient
    @FieldPath(name = "username",joinEntityClass = Userview.class)
    private String userName;

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
