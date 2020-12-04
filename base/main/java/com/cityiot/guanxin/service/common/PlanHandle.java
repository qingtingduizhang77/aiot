package com.cityiot.guanxin.service.common;

import org.springframework.stereotype.Component;

/**
 * 平面图上传
 */
@Component
public class PlanHandle implements IUploadFileTypeHandle<Integer> {

    @Override
    public boolean support(Integer integer) {
        return integer == 7;
    }

    @Override
    public String getFileStroeDirectory() {
        return "plan/";
    }
}
