package com.cityiot.guanxin.monitor.entity;

/**
 * 路灯状态数据
 * 状态： 1亮灯 2关灯 3单灯泡故障 4电源故障 5光源故障 6充电中 其他未知异常
 * 数值：当前能耗，当前亮度，当前已亮灯时间
 * 长时间不更新即异常状态
 */
public class LightInfo extends DeviceStatusInfo {
}
