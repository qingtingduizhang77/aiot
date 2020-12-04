package com.cityiot.guanxin.workOrder.repository;


import com.cityiot.guanxin.common.repository.BaseRepository;
import com.cityiot.guanxin.workOrder.entity.QEWorkOrderProgress;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.EntityPath;

import swallow.framework.jpaquery.repository.SwallowRepository;
import com.cityiot.guanxin.workOrder.entity.EWorkOrderProgress;

/**
 * 工单进度 的数据访问
 * 注意 因为使用的是自定义的SwallBaseRepository,使用@Repository无效
 * @author aohanhe
 *
 */
@Service
public class EWorkOrderProgressRepository extends BaseRepository<EWorkOrderProgress> {



}
