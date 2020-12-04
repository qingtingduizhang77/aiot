package com.cityiot.guanxin.monitor.control;


import com.cityiot.guanxin.monitor.service.DeviceStatusInfoService;
import com.cityiot.guanxin.old.charts.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;

public class MonitorControl {
    @Autowired
    protected ChartService chartService;
    @Autowired
    protected DeviceStatusInfoService deviceStatusInfoService;
}
