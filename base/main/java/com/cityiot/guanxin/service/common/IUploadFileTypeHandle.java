package com.cityiot.guanxin.service.common;

public interface IUploadFileTypeHandle<T> {
    boolean support(T t);
    String getFileStroeDirectory();
}
