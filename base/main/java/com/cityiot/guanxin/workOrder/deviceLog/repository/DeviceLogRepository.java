package com.cityiot.guanxin.workOrder.deviceLog.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.EntityPath;

import swallow.framework.jpaquery.repository.SwallowRepository;
import com.cityiot.guanxin.workOrder.deviceLog.entity.QDeviceLog;
import com.cityiot.guanxin.workOrder.deviceLog.entity.DeviceLog;

/**
 * 设备日志 的数据访问
 * 注意 因为使用的是自定义的SwallBaseRepository,使用@Repository无效
 * @author aohanhe
 *
 */
@Service
public class DeviceLogRepository extends SwallowRepository<DeviceLog>{



}
