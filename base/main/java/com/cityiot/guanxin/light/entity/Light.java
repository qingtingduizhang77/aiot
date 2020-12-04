package com.cityiot.guanxin.light.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import com.cityiot.guanxin.deviceInformation.entity.Deviceinformation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.jpaquery.repository.annotations.FieldPath;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Transient;

/**
 * @author huangjinyong
 * 路灯实体
 */
@ApiModel(value="路灯实体")
@CnName("路灯实体")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Light extends BaseEntity {
    @ApiModelProperty(value="分组Id",name="groupId",example="")
    @CnName("分组Id")
    private long groupId;
    @ApiModelProperty(value="路灯设备编号",name="deviceCode",example="")
    @CnName("路灯设备编号")
    private String deviceCode;
    @ApiModelProperty(value="设备Id",name="deviceId",example="")
    @CnName("设备Id")
    @JoinEntity(name = "id",joinEntityClass = Deviceinformation.class)
    private long deviceId;
    @ApiModelProperty(value="路灯名称",name="name",example="")
    @Transient
    @CnName("路灯名称")
    @FieldPath(name = "deviceName",joinEntityClass = Deviceinformation.class)
    private String name;

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
