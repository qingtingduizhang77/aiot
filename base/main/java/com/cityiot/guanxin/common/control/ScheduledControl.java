package com.cityiot.guanxin.common.control;

import com.cityiot.guanxin.workOrder.service.EInspectionWorkOrderService;
import com.cityiot.guanxin.workOrder.service.EMaintenanceWorkOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * 定时任务
 */
@Component
@Configuration
@EnableScheduling
public class ScheduledControl {

    private static final Logger log = LoggerFactory.getLogger(ScheduledControl.class);

    @Autowired
    private EInspectionWorkOrderService eInspectionWorkOrderService;
    @Autowired
    private EMaintenanceWorkOrderService eMaintenanceWorkOrderService;

    /**
     * 定时新增巡检工单（每月1号零点）
     */
    @Scheduled(cron = "0 0 0 1 * ?")
    public void createEInspectionWorkOrder(){
        log.info("start add eInspectionWorkOrder");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        eInspectionWorkOrderService.addWorkOrder();
        stopWatch.stop();
        log.info("end add eInspectionWorkOrder");
        log.info("消耗时间：" + stopWatch.getTotalTimeMillis() + "ms");
    }

    /**
     * 定时新增保养工单（每月1号零点）
     */
    @Scheduled(cron = "0 0 0 1 * ?")
    public void createEMaintenanceWorkOrder(){
        log.info("start add eMaintenanceWorkOrder");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        eMaintenanceWorkOrderService.addWorkOrder();
        stopWatch.stop();
        log.info("end add eMaintenanceWorkOrder");
        log.info("消耗时间：" + stopWatch.getTotalTimeMillis() + "ms");
    }
}
