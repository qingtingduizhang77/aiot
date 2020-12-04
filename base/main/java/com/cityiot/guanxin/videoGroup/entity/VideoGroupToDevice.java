package com.cityiot.guanxin.videoGroup.entity;

import com.cityiot.guanxin.common.entity.OnlyIdEntity;
import com.cityiot.guanxin.deviceInformation.entity.Deviceinformation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.FieldPath;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Transient;

/**
 * 视频分组关联设备表
 * @author Guoyz
 * createTime   2020/7/31 14:48
 */
@Entity
@ApiModel(value="视频分组关联设备表")
@EntityListeners(AuditingEntityListener.class)
public class VideoGroupToDevice extends OnlyIdEntity {

    public static final String T_Deviceinformation = "T_Deviceinformation";

    @Column(nullable = false)
    @ApiModelProperty(value="设备id",name="deviceId",example="")
    @JoinEntity(name = "id",joinEntityClass = Deviceinformation.class,joinEntityAlias = T_Deviceinformation)
    private long deviceId;

    @Transient
    @ApiModelProperty(value="设备名称",name="deviceName",example="")
    @FieldPath(name = "deviceName",joinEntityClass = Deviceinformation.class,joinEntityAlias = T_Deviceinformation)
    private String deviceName;

    @ApiModelProperty(value="视频分组id",name="videoGroupId",example="")
    private long videoGroupId;

    @ApiModelProperty(value="视频序号",name="ordering",example="")
    private long ordering;

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public long getVideoGroupId() {
        return videoGroupId;
    }

    public void setVideoGroupId(long videoGroupId) {
        this.videoGroupId = videoGroupId;
    }

    public long getOrdering() {
        return ordering;
    }

    public void setOrdering(long ordering) {
        this.ordering = ordering;
    }
}
