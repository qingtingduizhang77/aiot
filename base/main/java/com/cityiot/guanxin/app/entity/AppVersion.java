package com.cityiot.guanxin.app.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/**
 * @author huangjinyong
 * APP版本实体
 */
@ApiModel(value="APP版本")
@CnName("APP版本实体")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class AppVersion extends BaseEntity {
	
	//public static final QAppVersion qAppVersion = new QAppVersion("appVersion");
    @ApiModelProperty(value="APP平台类型",name="appPlatformType",example="")
    @CnName("APP平台类型")
    // 1Ios 2Android
    private int appPlatformType;
    @ApiModelProperty(value="版本号",name="appVersion",example="")
    @CnName("版本号")
    private String appVersion;
    @ApiModelProperty(value="内部版本号",name="appInnerVersoin",example="")
    @CnName("内部版本号")
    private int appInnerVersoin;
    @ApiModelProperty(value="APP类型",name="appType",example="")
    @CnName("APP类型")
    // 1客户端
    private int appType;
    @ApiModelProperty(value="是否强制更新",name="forceUpdate",example="")
    @CnName("是否强制更新")
    // 0否 1是
    private int forceUpdate;
    @ApiModelProperty(value="更新内容描述",name="updateDescription",example="")
    @CnName("更新内容描述")
    private String updateDescription;
    @ApiModelProperty(value="APP安装文件地址",name="appFileUrl",example="")
    @CnName("APP安装文件地址")
    private String appFileUrl;

    @ApiModelProperty(value="资源大小（M）",name="fileSize",example="")
    @CnName("资源大小（M）")
    private Double fileSize;

    @ApiModelProperty(value="更新模式",name="isHotUpdate",example="")
    @CnName("更新模式：0：外链整包更新，1：热更新")
    private int isHotUpdate;

    @ApiModelProperty(value="热更新包URL",name="hotUpdateUrl",example="")
    @CnName("热更新包URL")
    private String hotUpdateUrl;

    @ApiModelProperty(value="发布状态",name="publishStatus",example="")
    @CnName("发布状态;0：未发布；1：发布")
    private int publishStatus;

    public int getAppPlatformType() {
        return appPlatformType;
    }

    public void setAppPlatformType(int appPlatformType) {
        this.appPlatformType = appPlatformType;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public int getAppInnerVersoin() {
        return appInnerVersoin;
    }

    public void setAppInnerVersoin(int appInnerVersoin) {
        this.appInnerVersoin = appInnerVersoin;
    }

    public int getAppType() {
        return appType;
    }

    public void setAppType(int appType) {
        this.appType = appType;
    }

    public int getForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(int forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    public String getUpdateDescription() {
        return updateDescription;
    }

    public void setUpdateDescription(String updateDescription) {
        this.updateDescription = updateDescription;
    }

    public String getAppFileUrl() {
        return appFileUrl;
    }

    public void setAppFileUrl(String appFileUrl) {
        this.appFileUrl = appFileUrl;
    }

    public Double getFileSize() {
        return fileSize;
    }

    public void setFileSize(Double fileSize) {
        this.fileSize = fileSize;
    }

    public int getIsHotUpdate() {
        return isHotUpdate;
    }

    public void setIsHotUpdate(int isHotUpdate) {
        this.isHotUpdate = isHotUpdate;
    }

    public String getHotUpdateUrl() {
        return hotUpdateUrl;
    }

    public void setHotUpdateUrl(String hotUpdateUrl) {
        this.hotUpdateUrl = hotUpdateUrl;
    }

    public int getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(int publishStatus) {
        this.publishStatus = publishStatus;
    }
}
