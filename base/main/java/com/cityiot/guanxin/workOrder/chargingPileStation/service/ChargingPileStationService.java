package com.cityiot.guanxin.workOrder.chargingPileStation.service;


import com.cityiot.guanxin.workOrder.chargingPileStation.entity.ChargingPileStation;
import com.cityiot.guanxin.workOrder.chargingPileStation.repository.ChargingPileStationRepository;
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
public class ChargingPileStationService extends BaseService<ChargingPileStationRepository, ChargingPileStation>{
	// 日志对象
	private static final Logger log = LoggerFactory.getLogger(ChargingPileStationService.class);
}
