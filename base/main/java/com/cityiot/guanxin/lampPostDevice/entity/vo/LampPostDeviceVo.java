package com.cityiot.guanxin.lampPostDevice.entity.vo;

import swallow.framework.jpaquery.repository.annotations.CnName;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
@CnName("灯杆详情设备")
public class LampPostDeviceVo {

    @Id
    @CnName("ID主键")
    private long id;

    @CnName("设备ID")
    private long deviceId;

    @CnName("设备状态；0：异常；1：正常")
    private Integer status;

    @CnName("告警状态；0：正常；1：告警")
    private Integer alarmStatus;

    @CnName("设备名称")
    private String deviceName;

    @CnName("设备类型Id")
    private long deviceTypeId;

    @CnName("设备类型")
    private String deviceTypeName;

    @CnName("是否自有")
    private Integer isOwn;

    @CnName("是否租赁")
    private Integer isLease;

    @CnName("租赁方")
    private String lessor;

    @CnName("电压")
    private String voltage;

    @CnName("电流")
    private String electricCurrent;

    @CnName("功率")
    private String power;

    @CnName("亮度")
    private Integer brightness;

    @CnName("亮灯状态；0：灭灯；1：亮灯")
    private Integer switchStatus;

    @CnName("传输速率")
    private String transmissionRate;

    @CnName("连接数")
    private Integer conNum;

    @CnName("PM2.5")
    private String pm2point5;

    @CnName("PM10")
    private String pm10;

    @CnName("噪声")
    private String noise;

    @CnName("二氧化碳")
    @Transient
    private String co2;

    @CnName("一氧化碳")
    @Transient
    private String co;

    @CnName("一氧化氮")
    @Transient
    private String no;

    @CnName("温度")
    @Transient
    private String temperature;

    @CnName("湿度")
    @Transient
    private String humidity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(Integer alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    public Integer getIsOwn() {
        return isOwn;
    }

    public void setIsOwn(Integer isOwn) {
        this.isOwn = isOwn;
    }

    public Integer getIsLease() {
        return isLease;
    }

    public void setIsLease(Integer isLease) {
        this.isLease = isLease;
    }

    public String getLessor() {
        return lessor;
    }

    public void setLessor(String lessor) {
        this.lessor = lessor;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
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

    public Integer getStatus() {
        return status;
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
