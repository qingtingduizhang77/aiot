package com.cityiot.guanxin.workOrder.repository;


import org.springframework.stereotype.Service;

import com.querydsl.core.types.EntityPath;

import swallow.framework.jpaquery.repository.SwallowRepository;
import com.cityiot.guanxin.workOrder.entity.QEWorkOrderImages;
import com.cityiot.guanxin.workOrder.entity.EWorkOrderImages;

/**
 * 工单图片 的数据访问
 * 注意 因为使用的是自定义的SwallBaseRepository,使用@Repository无效
 * @author aohanhe
 *
 */
@Service
public class EWorkOrderImagesRepository extends SwallowRepository<EWorkOrderImages>{

	
}
