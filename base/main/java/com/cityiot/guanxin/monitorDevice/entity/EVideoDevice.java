package com.cityiot.guanxin.monitorDevice.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import com.cityiot.guanxin.common.entity.OnlyIdEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

@ApiModel(value="视频设备")
@CnName("视频设备")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class EVideoDevice extends OnlyIdEntity {
    @ApiModelProperty(value="设备信息ID",name="deviceInfoId",example="")
    @CnName("设备信息ID")
    private long deviceInfoId;
    @ApiModelProperty(value="状态",name="VideoStatus",example="")
    @CnName("状态") //1 正常 2 离线
    private int VideoStatus;
    @ApiModelProperty(value="视频流地址",name="streamUrl",example="")
    @CnName("视频流地址")
    private String streamUrl;

    public long getDeviceInfoId() {
        return deviceInfoId;
    }

    public void setDeviceInfoId(long deviceInfoId) {
        this.deviceInfoId = deviceInfoId;
    }


    public String getStreamUrl() {
        return streamUrl;
    }

    public void setStreamUrl(String streamUrl) {
        this.streamUrl = streamUrl;
    }

    public int getVideoStatus() {
        return VideoStatus;
    }

    public void setVideoStatus(int videoStatus) {
        VideoStatus = videoStatus;
    }
}
