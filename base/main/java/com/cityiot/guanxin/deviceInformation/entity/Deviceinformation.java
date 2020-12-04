package com.cityiot.guanxin.deviceInformation.entity;


import com.cityiot.guanxin.area.entity.Area;
import com.cityiot.guanxin.common.entity.BaseEntity;
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
import java.util.Date;

@ApiModel(value="设备信息")
@Entity
@CnName("设备信息")
@EntityListeners(AuditingEntityListener.class)
//@JoinEntity(fieldType = Long.class ,mainFiledName = "deviceTypeId" , mainEnityClass = DeviceModel.class,
//        name = "id", joinEntityClass = DeviceType.class)
@JoinEntity(fieldType = Long.class ,mainFiledName = "deviceBrandId" , mainEnityClass = DeviceModel.class,
        name = "id", joinEntityClass = DeviceBrand.class)
public class Deviceinformation extends BaseEntity {
    public static final String Parent = "T_Parent_Area";

    @ApiModelProperty(value="设备类型Id",name="deviceTypeId",example="")
    @CnName("设备类型Id")
    @JoinEntity(name = "id",joinEntityClass = DeviceType.class)
    private long deviceTypeId;

    @ApiModelProperty(value="设备名称",name="deviceName",example="")
    @CnName("设备名称")
    @Column(nullable = false)
    private String deviceName;

    @ApiModelProperty(value="设备型号ID",name="deviceModelId",example="")
    @CnName("设备型号ID")
    @Column(nullable = false)
    @JoinEntity(name = "id",joinEntityClass = DeviceModel.class)
    private Long deviceModelId;

    @ApiModelProperty(value="设备型号",name="deviceModel",example="")
    @CnName("设备型号")
    @Transient
    @FieldPath(name = "deviceModel",joinEntityClass = DeviceModel.class)
    private String deviceModel;

    // 品牌
    @ApiModelProperty(value="设备厂家",name="deviceManufacturer",example="")
    @CnName("设备厂家")
    @Transient
    @FieldPath(name = "deviceManufacturer",joinEntityClass = DeviceBrand.class)
    private String deviceManufacturer;

    @ApiModelProperty(value="设备品牌",name="deviceBrandName",example="")
    @CnName("设备品牌")
    @Transient
    @FieldPath(name = "deviceBrandName",joinEntityClass = DeviceBrand.class)
    private String deviceBrandName;

    // 类别
    @ApiModelProperty(value="设备类型",name="deviceTypeName",example="")
    @CnName("设备类型")
    @Transient
    @FieldPath(name = "deviceTypeName",joinEntityClass = DeviceType.class)
    private String deviceTypeName;

    @ApiModelProperty(value="地址",name="area",example="")
    @CnName("地址")
    @Column(nullable = false)
    private String area;

    @ApiModelProperty(value="所属区域Id",name="areaId",example="")
    @CnName("所属区域Id")
    @JoinEntity(name="id",joinEntityClass = Area.class)
    private Long areaId;

    @ApiModelProperty(value="所属区域Code",name="areaCode",example="")
    @CnName("所属区域Code")
    private Long areaCode;
    @ApiModelProperty(value="状态",name="status",example="")
    @CnName("状态") //1正常 2禁用 3故障
    private Integer status;

    @ApiModelProperty(value="设备编号",name="deviceCode",example="")
    @CnName("设备编号")
    @Column(nullable = false)
    private String deviceCode;

    @ApiModelProperty(value="坐标",name="coordinates",example="")
    @CnName("坐标")
    private String coordinates;

    @ApiModelProperty(value="上次维保时间",name="lastMaintenanceTime",example="")
    @CnName("上次维保时间")
    private Date lastMaintenanceTime;

    @ApiModelProperty(value="经度",name="longitude",example="")
    @CnName("经度")
    private String longitude;

    @ApiModelProperty(value="纬度",name="latitude",example="")
    @CnName("纬度")
    private String latitude;

    @ApiModelProperty(value="外部系统设备ID",name="cid",example="")
    @CnName("外部系统设备ID")
    private String cid;

    @ApiModelProperty(value="外部系统编码",name="appcode",example="")
    @CnName("外部系统编码")
    private String appcode;

    @ApiModelProperty(value="区域名称",name="areaName",example="")
    @Transient
    @FieldPath(name="name",joinEntityClass = Area.class)
    private String areaName;

    @ApiModelProperty(value="上级设备Id",name="parentId",example="")
    @CnName("上级设备Id")
    @JoinEntity(name = "id",joinEntityClass = Deviceinformation.class,joinEntityAlias = Parent)
    private long parentId;

    @ApiModelProperty(value="上级设备名称",name="parentName",example="")
    @Transient
    @FieldPath(name="deviceName",joinEntityClass = Deviceinformation.class,joinEntityAlias = Parent)
    private String parentName;

    @ApiModelProperty(value="告警状态；0：正常；1：告警",name="alarmStatus",example="")
    @CnName("告警状态；0：正常；1：告警")
    private int alarmStatus;

    @ApiModelProperty(value="地图显示",name="isShowMap",example="")
    @CnName("地图显示")
    private int isShowMap;

    @ApiModelProperty(value="最后通讯时间",name="lastComTime",example="")
    @CnName("最后通讯时间")
    private Date lastComTime;

    public Long getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(Long areaCode) {
        this.areaCode = areaCode;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Long getDeviceModelId() {
        return deviceModelId;
    }

    public void setDeviceModelId(Long deviceModelId) {
        this.deviceModelId = deviceModelId;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getDeviceManufacturer() {
        return deviceManufacturer;
    }

    public void setDeviceManufacturer(String deviceManufacturer) {
        this.deviceManufacturer = deviceManufacturer;
    }

    public String getDeviceBrandName() {
        return deviceBrandName;
    }

    public void setDeviceBrandName(String deviceBrandName) {
        this.deviceBrandName = deviceBrandName;
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public Date getLastMaintenanceTime() {
        return lastMaintenanceTime;
    }

    public void setLastMaintenanceTime(Date lastMaintenanceTime) {
        this.lastMaintenanceTime = lastMaintenanceTime;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public long getAreaId() {
        return areaId;
    }

    public void setAreaId(long areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public long getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(long deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getAppcode() {
		return appcode;
	}

	public void setAppcode(String appcode) {
		this.appcode = appcode;
	}

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public int getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(int alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    public int getIsShowMap() {
        return isShowMap;
    }

    public void setIsShowMap(int isShowMap) {
        this.isShowMap = isShowMap;
    }

    public Date getLastComTime() {
        return lastComTime;
    }

    public void setLastComTime(Date lastComTime) {
        this.lastComTime = lastComTime;
    }
}
