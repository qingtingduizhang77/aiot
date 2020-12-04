package com.cityiot.guanxin.workOrder.maintenance.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.EntityPath;

import swallow.framework.jpaquery.repository.SwallowRepository;
import com.cityiot.guanxin.workOrder.maintenance.entity.QMaintenanceAccessories;
import com.cityiot.guanxin.workOrder.maintenance.entity.MaintenanceAccessories;

/**
 * 设备保养附件 的数据访问
 * 注意 因为使用的是自定义的SwallBaseRepository,使用@Repository无效
 * @author aohanhe
 *
 */
@Service
public class MaintenanceAccessoriesRepository extends SwallowRepository<MaintenanceAccessories>{



}
