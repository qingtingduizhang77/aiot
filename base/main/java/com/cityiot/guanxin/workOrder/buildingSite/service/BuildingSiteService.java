package com.cityiot.guanxin.workOrder.buildingSite.service;


import com.cityiot.guanxin.workOrder.buildingSite.entity.BuildingSite;
import com.cityiot.guanxin.workOrder.buildingSite.repository.BuildingSiteRepository;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * InspectionRecord 数据Service
 * @author aohanhe
 *
 */
@Service
public class BuildingSiteService extends BaseService<BuildingSiteRepository, BuildingSite>{
	// 日志对象
	private static final Logger log = LoggerFactory.getLogger(BuildingSiteService.class);
}
