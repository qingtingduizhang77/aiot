package com.cityiot.guanxin.service.common;

import org.springframework.stereotype.Component;

@Component
public class AppFileTypeHandle implements IUploadFileTypeHandle<Integer> {
    @Override
    public boolean support(Integer integer) {
        return integer==1;
    }

    @Override
    public String getFileStroeDirectory() {
        return "app/";
    }
}
