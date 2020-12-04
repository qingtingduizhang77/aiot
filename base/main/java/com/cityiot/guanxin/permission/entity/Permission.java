package com.cityiot.guanxin.permission.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/**
 * @author huangjinyong
 * 权限项实体
 */
@ApiModel(value="权限项")
@CnName("权限项")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Permission extends BaseEntity {
    @ApiModelProperty(value="权限名称",name="name",example="")
    @CnName("权限名称")
    private String name;
    @ApiModelProperty(value="权限唯一m码",name="code",example="")
    @CnName("权限唯一m码")
    private String code;
    @ApiModelProperty(value="模块Id",name="moduleId",example="")
    @CnName("模块Id")
    private long moduleId;
    @ApiModelProperty(value="权限项描述",name="description",example="")
    @CnName("权限项描述")
    private String description;
    @ApiModelProperty(value="权限类型",name="type",example="")
    @CnName("权限类型")
    // 1接口权限 2页面/菜单权限 3按钮权限
    private int type;
    @ApiModelProperty(value="权限对应的url前缀",name="urlPrefix",example="")
    @CnName("权限对应的url前缀")
    private String urlPrefix;
    @ApiModelProperty(value="权限对应url",name="url",example="")
    @CnName("权限对应url")
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getModuleId() {
        return moduleId;
    }

    public void setModuleId(long moduleId) {
        this.moduleId = moduleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlPrefix() {
        return urlPrefix;
    }

    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    public static interface PermissionType {
        int TYPE_API = 1;
        int TYPE_PAGE = 2;
        int TYPE_ELEMENT = 3;
    }
}
