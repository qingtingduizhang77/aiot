package com.cityiot.guanxin.workOrder.buildingSite.repository;


import com.cityiot.guanxin.workOrder.buildingSite.entity.BuildingSite;
import com.cityiot.guanxin.workOrder.buildingSite.entity.QBuildingSite;
import com.querydsl.core.types.EntityPath;
import org.springframework.stereotype.Service;
import swallow.framework.jpaquery.repository.SwallowRepository;

/**
 * 设备巡检记录 的数据访问
 * 注意 因为使用的是自定义的SwallBaseRepository,使用@Repository无效
 * @author aohanhe
 *
 */
@Service
public class BuildingSiteRepository extends SwallowRepository<BuildingSite>{


}
