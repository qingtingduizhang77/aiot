package com.cityiot.guanxin.workOrder.inspection.repository;


import com.cityiot.guanxin.workOrder.inspection.entity.InspectionRecord;
import com.cityiot.guanxin.workOrder.inspection.entity.QInspectionRecord;
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
public class InspectionRecordRepository extends SwallowRepository<InspectionRecord>{
	@Override
	protected EntityPath<?> getMainTableExpression() {
         return QInspectionRecord.inspectionRecord;
	}

}
