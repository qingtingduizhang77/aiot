package com.cityiot.guanxin.deviceLiandong.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import com.cityiot.guanxin.deviceInformation.entity.Deviceinformation;
import com.cityiot.guanxin.deviceMoxing.entity.DeviceMoxing;
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
 * 设备联动控制详情
 * @author Guoyz
 * createTime   2020/7/2 11:04
 */
@ApiModel(value="设备联动控制详情")
@CnName("设备联动控制详情")
@Entity
@EntityListeners(AuditingEntityListener.class)
@JoinEntity(mainEnityClass = DeviceLiandong.class,joinEntityClass = DeviceMoxing.class, mainFiledName = "sourceDeviceMoxingId",name = "id", mainEntityAlias = DeviceLiandongDetails.DeviceLiandong, joinEntityAlias = DeviceLiandongDetails.DeviceMoxing_Source)
@JoinEntity(mainEnityClass = DeviceLiandong.class,joinEntityClass = DeviceMoxing.class, mainFiledName = "targetDeviceMoxingId",name = "id", mainEntityAlias = DeviceLiandongDetails.DeviceLiandong, joinEntityAlias = DeviceLiandongDetails.DeviceMoxing_Target)
public class DeviceLiandongDetails extends BaseEntity {
    //关联设备联动表
    public static final String DeviceLiandong = "DeviceLiandong";

    //关联设备信息表
    public static final String Deviceinformation_Source = "Deviceinformation_Source";
    public static final String Deviceinformation_Target = "Deviceinformation_Target";

    //关联设备模型表
    public static final String DeviceMoxing_Source = "DeviceMoxing_Source";
    public static final String DeviceMoxing_Target = "DeviceMoxing_Target";


    @CnName("设备联动控制id")
    @ApiModelProperty(value="设备联动控制id",name="deviceLiandongId",example="")
    @JoinEntity(name = "id",joinEntityClass = DeviceLiandong.class,joinEntityAlias = DeviceLiandong)
    @Column(nullable = false)//不可为空
    private Integer deviceLiandongId;

    @Transient
    @ApiModelProperty(value="源设备型号ID",name="sourceDeviceModelIds",example="")
    @FieldPath(name = "deviceModelIds",joinEntityClass = DeviceMoxing.class,joinEntityAlias = DeviceMoxing_Source)
    private String sourceDeviceModelIds;

    @Transient
    @ApiModelProperty(value="目标设备型号ID",name="tagetDeviceModelIds",example="")
    @FieldPath(name = "deviceModelIds",joinEntityClass = DeviceMoxing.class,joinEntityAlias = DeviceMoxing_Target)
    private String tagetDeviceModelIds;

    @Transient
    @CnName("触发条件")
    @Column(columnDefinition="TEXT")
    @ApiModelProperty(value="触发条件",name="math",example="")
    @FieldPath(name = "math",joinEntityClass = DeviceLiandong.class,joinEntityAlias = DeviceLiandong)
    private String math;

    @Transient
    @CnName("触发动作")
    @ApiModelProperty(value="code",name="code",example="触发动作")
    @FieldPath(name = "code",joinEntityClass = DeviceLiandong.class,joinEntityAlias = DeviceLiandong)
    private String code;

    @CnName("源设备id")
    @ApiModelProperty(value="源设备id",name="sourceDeviceId",example="")
    @JoinEntity(name = "id",joinEntityClass = Deviceinformation.class,joinEntityAlias = Deviceinformation_Source)
    private Integer sourceDeviceId;

    @Transient
    @ApiModelProperty(value="源设备名称",name="sourceDeviceName",example="")
    @FieldPath(name = "deviceName",joinEntityClass = Deviceinformation.class,joinEntityAlias = Deviceinformation_Source)
    private String sourceDeviceName;

    @CnName("目标设备id")
    @ApiModelProperty(value="目标设备id",name="targetDeviceId",example="")
    @JoinEntity(name = "id",joinEntityClass = Deviceinformation.class,joinEntityAlias = Deviceinformation_Target)
    private Integer targetDeviceId;

    @Transient
    @ApiModelProperty(value="目标设备名称",name="targetDeviceName",example="")
    @FieldPath(name = "deviceName",joinEntityClass = Deviceinformation.class,joinEntityAlias = Deviceinformation_Target)
    private String targetDeviceName;


    public String getSourceDeviceModelIds() {
        return sourceDeviceModelIds;
    }

    public void setSourceDeviceModelIds(String sourceDeviceModelIds) {
        this.sourceDeviceModelIds = sourceDeviceModelIds;
    }

    public String getTagetDeviceModelIds() {
        return tagetDeviceModelIds;
    }

    public void setTagetDeviceModelIds(String tagetDeviceModelIds) {
        this.tagetDeviceModelIds = tagetDeviceModelIds;
    }

    public String getSourceDeviceName() {
        return sourceDeviceName;
    }

    public void setSourceDeviceName(String sourceDeviceName) {
        this.sourceDeviceName = sourceDeviceName;
    }

    public String getTargetDeviceName() {
        return targetDeviceName;
    }

    public void setTargetDeviceName(String targetDeviceName) {
        this.targetDeviceName = targetDeviceName;
    }

    public Integer getDeviceLiandongId() {
        return deviceLiandongId;
    }

    public void setDeviceLiandongId(Integer deviceLiandongId) {
        this.deviceLiandongId = deviceLiandongId;
    }

    public Integer getSourceDeviceId() {
        return sourceDeviceId;
    }

    public void setSourceDeviceId(Integer sourceDeviceId) {
        this.sourceDeviceId = sourceDeviceId;
    }

    public Integer getTargetDeviceId() {
        return targetDeviceId;
    }

    public void setTargetDeviceId(Integer targetDeviceId) {
        this.targetDeviceId = targetDeviceId;
    }

    public String getMath() {
        return math;
    }

    public void setMath(String math) {
        this.math = math;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
