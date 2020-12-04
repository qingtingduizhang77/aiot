package com.cityiot.guanxin.common.entity;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.cityiot.guanxin.user.entity.User;
import com.cityiot.guanxin.user.entity.Userview;

import io.swagger.annotations.ApiModelProperty;
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.jpaquery.repository.annotations.FieldPath;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;

@MappedSuperclass
public abstract class BaseEntity extends OnlyIdEntity {
    static final String T_MUser_MangerUser_Creator = "T_MUser_MangerUser_Creator";
    static final String T_MUser_MangerUser_Modifer = "T_MUser_MangerUser_Modifer";
    @ApiModelProperty(value="创建时间",name="created",example="")
    @CnName("创建时间")
    @CreatedDate
    private Date created;
    @ApiModelProperty(value="创建人",name="creator",example="")
    @CnName("创建人")
    @JoinEntity(name="id",joinEntityClass = Userview.class, joinEntityAlias = T_MUser_MangerUser_Creator)
    @CreatedBy
    private long creator;
    @ApiModelProperty(value="修改人",name="modifier",example="")
    @CnName("修改人")
    @JoinEntity(name="id",joinEntityClass = Userview.class, joinEntityAlias = T_MUser_MangerUser_Modifer)
    @LastModifiedBy
    private long modifier;
    @ApiModelProperty(value="最后修改时间",name="lastmodi",example="")
    @CnName("最后修改时间")
    @LastModifiedDate
    private Date lastmodi;
    @ApiModelProperty(value="创建人名称",name="creatorName",example="")
    @CnName("创建人名称")
    @Transient
    @FieldPath(name="username",joinEntityClass = Userview.class, joinEntityAlias = T_MUser_MangerUser_Creator)
    private String creatorName;
    @ApiModelProperty(value="最后修改人名称",name="modifierName",example="")
    @CnName("最后修改人名称")
    @Transient
    @FieldPath(name="username",joinEntityClass = Userview.class, joinEntityAlias = T_MUser_MangerUser_Modifer)
    private String modifierName;


    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
    public long getCreator() {
        return creator;
    }
    public void setCreator(long creator) {
        this.creator = creator;
    }
    public long getModifier() {
        return modifier;
    }
    public void setModifier(long modifier) {
        this.modifier = modifier;
    }
    public Date getLastmodi() {
        return lastmodi;
    }
    public void setLastmodi(Date lastmodi) {
        this.lastmodi = lastmodi;
    }
    public String getCreatorName() {
        return creatorName;
    }
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
    public String getModifierName() {
        return modifierName;
    }
    public void setModifierName(String modifierName) {
        this.modifierName = modifierName;
    }
}
