package com.cityiot.guanxin.workOrder.entity.DeviceChart;

import java.util.Date;

public class DeviceChartTime {

    private Date startTime;


    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

//    public String getStartTime(Integer type){
//        if(!StringUtils.isEmpty(this.startTime)&&type==2){
//            return startTime+"-01";
//        }
//        return startTime;
//    }
//    public String getEndTime(Integer type){
//        if(!StringUtils.isEmpty(this.endTime)&&type==2){
//            return endTime+"-01";
//        }
//        return endTime;
//    }
}
