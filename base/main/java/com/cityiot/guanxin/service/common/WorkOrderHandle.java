package com.cityiot.guanxin.service.common;

import org.springframework.stereotype.Component;

/**
 * 工单上传
 */
@Component
public class WorkOrderHandle implements IUploadFileTypeHandle<Integer> {

    @Override
    public boolean support(Integer integer) {
        return integer == 6;
    }

    @Override
    public String getFileStroeDirectory() {
        return "workOrder/";
    }
}
