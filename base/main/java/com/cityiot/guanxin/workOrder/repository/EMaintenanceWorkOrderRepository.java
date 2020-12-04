package com.cityiot.guanxin.workOrder.repository;


import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cityiot.guanxin.workOrder.entity.EMaintenanceWorkOrder;
import com.cityiot.guanxin.workOrder.entity.QEMaintenanceWorkOrder;

import swallow.framework.jpaquery.repository.SwallowRepository;

/**
 * 保养工单 的数据访问
 * 注意 因为使用的是自定义的SwallBaseRepository,使用@Repository无效
 * @author aohanhe
 *
 */
@Service
public class EMaintenanceWorkOrderRepository extends SwallowRepository<EMaintenanceWorkOrder>{

	

    public void updateNewItem(long workOrderId, int status) {
        QEMaintenanceWorkOrder eMaintenanceWorkOrder = QEMaintenanceWorkOrder.eMaintenanceWorkOrder;
        this.factory.update(eMaintenanceWorkOrder)
                .set(eMaintenanceWorkOrder.handleStatus,status)
                .set(eMaintenanceWorkOrder.handleTime, new Date())
                .where(eMaintenanceWorkOrder.id.eq(workOrderId)).execute();
    }
    
    
    public List<EMaintenanceWorkOrder> getEMaintenanceWorkOrderList(long handlerId) {
    	   QEMaintenanceWorkOrder eMaintenanceWorkOrder = QEMaintenanceWorkOrder.eMaintenanceWorkOrder;
           return getQuery().select(eMaintenanceWorkOrder)
                .from(eMaintenanceWorkOrder)
                .where(eMaintenanceWorkOrder.handlerId.eq(handlerId),eMaintenanceWorkOrder.handleStatus.ne(40) ,eMaintenanceWorkOrder.handleStatus.ne(900))
                .fetch();
    }
    
}
