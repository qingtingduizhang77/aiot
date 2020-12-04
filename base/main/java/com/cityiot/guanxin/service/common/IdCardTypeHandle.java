package com.cityiot.guanxin.service.common;

import org.springframework.stereotype.Component;

@Component
public class IdCardTypeHandle implements IUploadFileTypeHandle<Integer> {
    @Override
    public boolean support(Integer integer) {
        return integer == 5;
    }

    @Override
    public String getFileStroeDirectory() {
        return "Id_Card/";
    }
}
