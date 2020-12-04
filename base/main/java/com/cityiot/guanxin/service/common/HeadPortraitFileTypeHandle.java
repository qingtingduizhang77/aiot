package com.cityiot.guanxin.service.common;

import org.springframework.stereotype.Component;

/**
 * 头像上传
 */
@Component
public class HeadPortraitFileTypeHandle implements IUploadFileTypeHandle<Integer> {

    @Override
    public boolean support(Integer integer) {
        return integer == 2;
    }

    @Override
    public String getFileStroeDirectory() {
        return "head_protrait/";
    }
}
