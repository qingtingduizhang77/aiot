package com.cityiot.guanxin.workOrder.faultManagement.repository;


import com.cityiot.guanxin.workOrder.faultManagement.entity.QThresholdSetting;
import com.cityiot.guanxin.workOrder.faultManagement.entity.ThresholdSetting;
import com.querydsl.core.types.EntityPath;
import org.springframework.stereotype.Service;
import swallow.framework.jpaquery.repository.SwallowRepository;

/**
 * 阈值设定 的数据访问
 * 注意 因为使用的是自定义的SwallBaseRepository,使用@Repository无效
 * @author aohanhe
 *
 */
@Service
public class ThresholdSettingRepository extends SwallowRepository<ThresholdSetting>{



}
