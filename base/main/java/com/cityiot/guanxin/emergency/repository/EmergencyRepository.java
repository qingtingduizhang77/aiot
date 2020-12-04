package com.cityiot.guanxin.emergency.repository;


import org.springframework.stereotype.Service;

import com.querydsl.core.types.EntityPath;

import swallow.framework.jpaquery.repository.SwallowRepository;
import com.cityiot.guanxin.emergency.entity.QEmergency;
import com.cityiot.guanxin.emergency.entity.Emergency;

/**
 * 告警 的数据访问
 * 注意 因为使用的是自定义的SwallBaseRepository,使用@Repository无效
 * @author aohanhe
 *
 */
@Service
public class EmergencyRepository extends SwallowRepository<Emergency>{

	@Override
	protected EntityPath<?> getMainTableExpression() {		
         return QEmergency.emergency;
	}

	public Long selectTotalNum() {
		QEmergency emergency = QEmergency.emergency;
		return this.factory.select(emergency).from(emergency).fetchCount();
	}

	public void readEmergency() {
		QEmergency emergency = QEmergency.emergency;
		this.factory.update(emergency).set(emergency.isReaded,2).execute();
	}
}
