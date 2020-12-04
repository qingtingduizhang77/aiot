package com.cityiot.guanxin.module.service;

import com.cityiot.guanxin.module.entity.Module;
import com.cityiot.guanxin.module.entity.QModule;
import com.cityiot.guanxin.module.repository.ModuleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import swallow.framework.exception.SwallowException;
import swallow.framework.service.BaseService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModuleService extends BaseService<ModuleRepository, Module> {
    private static final Logger log = LoggerFactory.getLogger(ModuleService.class);

}
