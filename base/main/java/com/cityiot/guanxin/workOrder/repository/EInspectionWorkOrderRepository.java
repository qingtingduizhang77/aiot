package com.cityiot.guanxin.workOrder.repository;


import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cityiot.guanxin.workOrder.entity.EInspectionWorkOrder;
import com.cityiot.guanxin.workOrder.entity.QEInspectionWorkOrder;

import swallow.framework.jpaquery.repository.SwallowRepository;

/**
 * 年检工单 的数据访问
 * 注意 因为使用的是自定义的SwallBaseRepository,使用@Repository无效
 * @author aohanhe
 *
 */
@Service
public class EInspectionWorkOrderRepository extends SwallowRepository<EInspectionWorkOrder>{

	

    public void updateNewItem(long workOrderId, int status) {
        QEInspectionWorkOrder eInspectionWorkOrder = QEInspectionWorkOrder.eInspectionWorkOrder;
        this.factory.update(eInspectionWorkOrder)
                .set(eInspectionWorkOrder.handleStatus,status)
                .set(eInspectionWorkOrder.handleTime, new Date())
                .where(eInspectionWorkOrder.id.eq(workOrderId)).execute();
    }

    public void updateWorkOrderHandler(Long nextOperator, long workOrderId) {
        QEInspectionWorkOrder eInspectionWorkOrder = QEInspectionWorkOrder.eInspectionWorkOrder;
        this.factory.update(eInspectionWorkOrder)
                .set(eInspectionWorkOrder.handlerId, nextOperator)
                .where(eInspectionWorkOrder.id.eq(workOrderId)).execute();
    }
    
    
    public List<EInspectionWorkOrder> getEInspectionWorkOrderList(long handlerId) {
        QEInspectionWorkOrder eInspectionWorkOrder = QEInspectionWorkOrder.eInspectionWorkOrder;
        return getQuery().select(eInspectionWorkOrder)
                .from(eInspectionWorkOrder)
                .where(eInspectionWorkOrder.handlerId.eq(handlerId),eInspectionWorkOrder.handleStatus.ne(40) ,eInspectionWorkOrder.handleStatus.ne(900))
                .fetch();
    }
    
    
}
