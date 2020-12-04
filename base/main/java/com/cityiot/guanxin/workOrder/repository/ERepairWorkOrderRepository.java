package com.cityiot.guanxin.workOrder.repository;


import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cityiot.guanxin.workOrder.entity.ERepairWorkOrder;
import com.cityiot.guanxin.workOrder.entity.QERepairWorkOrder;

import swallow.framework.jpaquery.repository.SwallowRepository;

/**
 * 维修工单 的数据访问
 * 注意 因为使用的是自定义的SwallBaseRepository,使用@Repository无效
 * @author aohanhe
 *
 */
@Service
public class ERepairWorkOrderRepository extends SwallowRepository<ERepairWorkOrder>{

	

    public void updateNewItem(long workOrderId, int status) {
        QERepairWorkOrder eRepairWorkOrder = QERepairWorkOrder.eRepairWorkOrder;
        this.factory.update(eRepairWorkOrder)
                .set(eRepairWorkOrder.handleStatus,status)
                .set(eRepairWorkOrder.handleTime, new Date())
                .where(eRepairWorkOrder.id.eq(workOrderId)).execute();
    }
    
    public List<ERepairWorkOrder> getERepairWorkOrderList(long handlerId) {
    	QERepairWorkOrder eRepairWorkOrder = QERepairWorkOrder.eRepairWorkOrder;
        return getQuery().select(eRepairWorkOrder)
             .from(eRepairWorkOrder)
             .where(eRepairWorkOrder.handlerId.eq(handlerId),eRepairWorkOrder.handleStatus.ne(40) ,eRepairWorkOrder.handleStatus.ne(900))
             .fetch();
   }
}
