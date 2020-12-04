package com.cityiot.guanxin.service;


import com.cityiot.guanxin.entity.QSystemVariable;
import com.cityiot.guanxin.entity.QSystemVariableType;
import com.cityiot.guanxin.entity.SystemVariable;
import com.cityiot.guanxin.repository.SystemVariableRepository;
import com.cityiot.guanxin.workOrder.service.EMaintenanceWorkOrderService;
import com.cityiot.guanxin.workOrder.service.EWorkOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;
import swallow.framework.web.ApiResult;

import java.util.List;

/**
 * ESystemVariable 数据Service
 * @author aohanhe
 *
 */
@Service
public class SystemVariableService extends BaseService<SystemVariableRepository, SystemVariable> {

	// 日志对象
	private static final Logger log = LoggerFactory.getLogger(SystemVariableRepository.class);

	// 工单状态值
    public static final long WORK_ORDER_STATUS = 4;
    // 工单进度状态值
    public static final long PROGRESS_STATUS = 5;

	@Autowired
    private SystemVariableRepository repository;

    public List<SystemVariable> queryAllRepairByCode(String dictionaryCode) {
        QSystemVariable qSystemVariable = QSystemVariable.systemVariable;
        QSystemVariableType qSystemVariableType = QSystemVariableType.systemVariableType;
        return this.getAllItems(query -> query
                .leftJoin(qSystemVariableType).on(qSystemVariable.vartype.eq(qSystemVariableType.id))
                .where(qSystemVariableType.name.eq(dictionaryCode))
                .orderBy(qSystemVariable.variable.desc()));
    }

    public List<SystemVariable> getAllByName(String typeName, String repairItems) {
        String[] repairArr = repairItems.split(",");
        Number[] arrNum = new Number[repairArr.length];
        for (int i = 0; i < repairArr.length; i++) {
            arrNum[i] = Integer.valueOf(repairArr[i]);
        }
        return repository.getAllName(typeName, arrNum);
    }

    public SystemVariable getVariableName(long varType, int workOrderStatus) {
        QSystemVariable qSystemVariable = QSystemVariable.systemVariable;
        QSystemVariableType qSystemVariableType = QSystemVariableType.systemVariableType;
        return this.getItem(query -> query
                .leftJoin(qSystemVariableType).on(qSystemVariable.vartype.eq(qSystemVariableType.id))
                .where(qSystemVariable.vartype.eq(varType)
                        .and(qSystemVariable.variable.eq(workOrderStatus))));
    }
}
