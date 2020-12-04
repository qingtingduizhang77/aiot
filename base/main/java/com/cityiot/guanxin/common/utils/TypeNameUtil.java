package com.cityiot.guanxin.common.utils;

import com.cityiot.guanxin.config.springboot.InitListenter;
import com.cityiot.guanxin.service.SystemVariableService;
import org.springframework.context.ApplicationContext;

public class TypeNameUtil {

    /**
     * 流程进度名称
     * @param workOrderType
     * @param workOrderStatus
     * @return
     */
    public static String getProcessTypeName(long workOrderType, int workOrderStatus){
        SystemVariableService service = InitListenter.getBean("systemVariableService", SystemVariableService.class);
        long varType = 5;
        if (workOrderType == 1) {
            varType = 101;
        }else if (workOrderType == 2){
            varType = 103;
        }else if (workOrderType == 3){
            varType = 105;
        }
        var bean = service.getVariableName(varType, workOrderStatus);
        if (bean != null){
            return bean.getName();
        }
        return "";
    }
}
