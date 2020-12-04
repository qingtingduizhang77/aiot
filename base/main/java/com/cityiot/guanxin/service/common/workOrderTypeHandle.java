package com.cityiot.guanxin.service.common;

import org.springframework.stereotype.Component;

@Component
public class workOrderTypeHandle implements IUploadFileTypeHandle<Integer> {
    @Override
    public boolean support(Integer integer) {
        return integer == 4;
    }

    @Override
    public String getFileStroeDirectory() {
        return "work_order/";
    }
}
