package com.cityiot.guanxin.workOrder.faultManagement.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.EntityPath;

import swallow.framework.jpaquery.repository.SwallowRepository;
import com.cityiot.guanxin.workOrder.faultManagement.entity.QFaultManagement;
import com.cityiot.guanxin.workOrder.faultManagement.entity.FaultManagement;

/**
 * 设备故障管理 的数据访问
 * 注意 因为使用的是自定义的SwallBaseRepository,使用@Repository无效
 * @author aohanhe
 *
 */
@Service
public class FaultManagementRepository extends SwallowRepository<FaultManagement>{

	

}
