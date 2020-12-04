package com.cityiot.guanxin.deviceInformation.entity;


import com.cityiot.guanxin.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

@ApiModel(value="设备类型")
@Entity
@CnName("设备类型")
@EntityListeners(AuditingEntityListener.class)
public class DeviceType extends BaseEntity {

    @ApiModelProperty(value="设备类型",name="deviceTypeName",example="")
    @CnName("设备类型")
    @Column(nullable = false)
    private String deviceTypeName;

    @ApiModelProperty(value="归属板块",name="belongModule",example="")
    @CnName("归属板块")
    private Integer belongModule;

    public Integer getBelongModule() {
        return belongModule;
    }

    public void setBelongModule(Integer belongModule) {
        this.belongModule = belongModule;
    }

    public static String getDeviceType(Integer deviceType){
        switch (deviceType){
            case 1:return "路灯";
            case 2:return "视频监控";
            case 3:return "充电桩";
            case 4:return "垃圾桶";
            default:return null;
        }
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }
}
