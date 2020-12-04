package com.cityiot.guanxin.lampPostDevice.entity;

import com.cityiot.guanxin.common.entity.BaseEntity;
import com.cityiot.guanxin.deviceInformation.entity.DeviceType;
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
 * @author zhengjc
 * 灯杆详情关联设备信息
 */
@ApiModel(value="灯杆详情关联设备信息")
@CnName("灯杆详情关联设备信息")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class LampPostDevice extends BaseEntity {

    @ApiModelProperty(value="设备ID",name="deviceId",example="")
    @CnName("设备ID")
    @JoinEntity(name = "id",joinEntityClass = Deviceinformation.class)
    private long deviceId;

    @ApiModelProperty(value="设备状态；0：异常；1：正常",name="deviceStatus",example="")
    @CnName("设备状态；0：异常；1：正常")
    @Transient
    @FieldPath(name="status",joinEntityClass = Deviceinformation.class)
    private int status;

    @ApiModelProperty(value="告警状态；0：正常；1：告警",name="alarmStatus",example="")
    @CnName("告警状态；0：正常；1：告警")
    @Transient
    @FieldPath(name="alarmStatus",joinEntityClass = Deviceinformation.class)
    private int alarmStatus;

    @ApiModelProperty(value="设备名称",name="deviceName",example="")
    @Transient
    @FieldPath(name="deviceName",joinEntityClass = Deviceinformation.class)
    private String deviceName;

    @ApiModelProperty(value="设备类型Id",name="deviceTypeId",example="")
    @CnName("设备类型Id")
    @JoinEntity(name = "id",joinEntityClass = DeviceType.class)
    private long deviceTypeId;

    @ApiModelProperty(value="设备类型",name="deviceTypeName",example="")
    @CnName("设备类型")
    @Transient
    @FieldPath(name = "deviceTypeName",joinEntityClass = DeviceType.class)
    private String deviceTypeName;

    @ApiModelProperty(value="是否自有",name="isOwn",example="")
    @CnName("是否自有")
    private int isOwn;

    @ApiModelProperty(value="是否租赁",name="isLease",example="")
    @CnName("是否租赁")
    private int isLease;

    @ApiModelProperty(value="租赁方",name="lessor",example="")
    @CnName("租赁方")
    private String lessor;

    @ApiModelProperty(value="电压",name="voltage",example="")
    @CnName("电压")
    private String voltage;

    @ApiModelProperty(value="电流",name="electricCurrent",example="")
    @CnName("电流")
    private String electricCurrent;

    @ApiModelProperty(value="功率",name="power",example="")
    @CnName("功率")
    private String power;

    @ApiModelProperty(value="亮度",name="brightness",example="")
    @CnName("亮度")
    private Integer brightness;

    @ApiModelProperty(value="亮灯状态；0：灭灯；1：亮灯",name="switchStatus",example="")
    @CnName("亮灯状态；0：灭灯；1：亮灯")
    private Integer switchStatus;

    @ApiModelProperty(value="传输速率",name="transmissionRate",example="")
    @CnName("传输速率")
    private String transmissionRate;

    @ApiModelProperty(value="连接数",name="conNum",example="")
    @CnName("连接数")
    private Integer conNum;

    @ApiModelProperty(value="PM2.5",name="pm2point5",example="")
    @CnName("PM2.5")
    private String pm2point5;

    @ApiModelProperty(value="PM10",name="pm10",example="")
    @CnName("PM10")
    private String pm10;

    @ApiModelProperty(value="噪声",name="noise",example="")
    @CnName("噪声")
    private String noise;

    @ApiModelProperty(value="智能分流调光状态，0-关闭，1-开启",name="dimmingStatus",example="")
    @CnName("智能分流调光状态，0-关闭，1-开启")
    private Integer dimmingStatus;

    @ApiModelProperty(value="报文解析JSON",name="msgParsingJson",example="")
    @CnName("报文解析JSON")
    private String msgParsingJson;

    @ApiModelProperty(value="二氧化碳",name="co2",example="")
    @CnName("二氧化碳")
    private String co2;

    @ApiModelProperty(value="一氧化碳",name="co",example="")
    @CnName("一氧化碳")
    private String co;

    @ApiModelProperty(value="一氧化氮",name="no",example="")
    @CnName("一氧化氮")
    private String no;

    @ApiModelProperty(value="温度",name="temperature",example="")
    @CnName("温度")
    private String temperature;

    @ApiModelProperty(value="湿度",name="humidity",example="")
    @CnName("湿度")
    private String humidity;

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public int getIsOwn() {
        return isOwn;
    }

    public void setIsOwn(int isOwn) {
        this.isOwn = isOwn;
    }

    public int getIsLease() {
        return isLease;
    }

    public void setIsLease(int isLease) {
        this.isLease = isLease;
    }

    public String getLessor() {
        return lessor;
    }

    public void setLessor(String lessor) {
        this.lessor = lessor;
    }

    public String getElectricCurrent() {
        return electricCurrent;
    }

    public void setElectricCurrent(String electricCurrent) {
        this.electricCurrent = electricCurrent;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public Integer getBrightness() {
        return brightness;
    }

    public void setBrightness(Integer brightness) {
        this.brightness = brightness;
    }

    public Integer getSwitchStatus() {
        return switchStatus;
    }

    public void setSwitchStatus(Integer switchStatus) {
        this.switchStatus = switchStatus;
    }

    public String getTransmissionRate() {
        return transmissionRate;
    }

    public void setTransmissionRate(String transmissionRate) {
        this.transmissionRate = transmissionRate;
    }

    public Integer getConNum() {
        return conNum;
    }

    public void setConNum(Integer conNum) {
        this.conNum = conNum;
    }

    public String getPm2point5() {
        return pm2point5;
    }

    public void setPm2point5(String pm2point5) {
        this.pm2point5 = pm2point5;
    }

    public String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    public String getNoise() {
        return noise;
    }

    public void setNoise(String noise) {
        this.noise = noise;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public long getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(long deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(int alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public Integer getDimmingStatus() {
        return dimmingStatus;
    }

    public void setDimmingStatus(Integer dimmingStatus) {
        this.dimmingStatus = dimmingStatus;
    }

    public String getMsgParsingJson() {
        return msgParsingJson;
    }

    public void setMsgParsingJson(String msgParsingJson) {
        this.msgParsingJson = msgParsingJson;
    }

    public String getCo2() {
        return co2;
    }

    public void setCo2(String co2) {
        this.co2 = co2;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}
