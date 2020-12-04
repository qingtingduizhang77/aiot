package com.cityiot.guanxin.common.service;

import com.cityiot.guanxin.common.repository.BaseMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseMongoService<T extends BaseMongoRepository> {
    @Autowired
    protected T repository;
    public T getRepository() {
        return repository;
    }
}
