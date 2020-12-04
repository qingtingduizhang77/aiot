package com.cityiot.guanxin.workOrder.service;


import java.util.List;

import com.cityiot.guanxin.planGroup.entity.QPlanGroupToPlan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cityiot.guanxin.workOrder.entity.EWorkOrder;
import com.cityiot.guanxin.workOrder.entity.QEWorkOrder;
import com.cityiot.guanxin.workOrder.repository.EWorkOrderRepository;
import com.cityiot.guanxin.workOrder.vo.WorkNumInfoVo;
import com.cityiot.guanxin.workOrder.vo.WorkOrderStatisticVo;

import swallow.framework.service.BaseService;
import swallow.framework.web.BasePageQueryBean;

/**
 * EWorkOrder 数据Service
 * @author aohanhe
 *
 */
@Service
public class EWorkOrderService extends BaseService<EWorkOrderRepository, EWorkOrder>{
	// 日志对象
	private static final Logger log = LoggerFactory.getLogger(EWorkOrderService.class);

	@Autowired
    private EWorkOrderRepository workOrderRepository;

    public WorkNumInfoVo queryMyWorkNum(long id) {
        return workOrderRepository.queryMyWorkNum(id);
    }
    
    
  
    
    public EWorkOrder getEWorkOrder(long workOrderId,int workOrderType) {
    	QEWorkOrder qeWorkOrder=QEWorkOrder.eWorkOrder;
    	
    	List<EWorkOrder>   list=getRepsitory().findEntitysByColumns(query -> {
            return query.where(qeWorkOrder.recordId.eq(workOrderId),qeWorkOrder.workOrderType.eq(workOrderType)).orderBy(qeWorkOrder.workOrderType.desc());
        });
    	if(list!=null && list.size()>0)
    	{
    		return list.get(0);
    	}
    	
    	return null;
    }
    
    
    
    
    

    public List<WorkOrderStatisticVo> workOrderStatistic(Integer scopeType,Long userId) {
        return workOrderRepository.workOrderStatistic(scopeType,userId);
    }
    
    
    public void deleteItemByRecordId(long recordId, int workOrderType){
        getRepsitory().deleteEntityByColumns(
                query -> query.where(QEWorkOrder.eWorkOrder.recordId.eq(recordId)
                        .and(QEWorkOrder.eWorkOrder.workOrderType.eq(workOrderType))));
    }
    
}
