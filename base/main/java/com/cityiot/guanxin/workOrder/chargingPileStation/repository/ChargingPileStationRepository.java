package com.cityiot.guanxin.workOrder.chargingPileStation.repository;


import com.cityiot.guanxin.workOrder.chargingPileStation.entity.ChargingPileStation;
import com.cityiot.guanxin.workOrder.chargingPileStation.entity.QChargingPileStation;
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
public class ChargingPileStationRepository extends SwallowRepository<ChargingPileStation>{
	
}
