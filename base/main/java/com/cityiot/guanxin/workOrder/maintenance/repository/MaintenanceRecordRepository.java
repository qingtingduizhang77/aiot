package com.cityiot.guanxin.workOrder.maintenance.repository;


import com.cityiot.guanxin.workOrder.entity.DeviceChart.DeviceChart;
import com.cityiot.guanxin.workOrder.entity.DeviceChart.DeviceChartTime;
import com.cityiot.guanxin.workOrder.maintenance.entity.MaintenanceRecord;
import com.cityiot.guanxin.workOrder.maintenance.entity.QMaintenanceRecord;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Projections;
import org.springframework.stereotype.Service;
import swallow.framework.jpaquery.repository.SwallowRepository;

import java.util.List;

/**
 * 设备保养记录 的数据访问
 * 注意 因为使用的是自定义的SwallBaseRepository,使用@Repository无效
 * @author aohanhe
 *
 */
@Service
public class MaintenanceRecordRepository extends SwallowRepository<MaintenanceRecord>{




}
