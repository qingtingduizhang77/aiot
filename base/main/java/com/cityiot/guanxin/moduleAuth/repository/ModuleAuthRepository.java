package com.cityiot.guanxin.moduleAuth.repository;

import org.springframework.stereotype.Service;

import com.cityiot.guanxin.moduleAuth.entity.ModuleAuth;

import swallow.framework.jpaquery.repository.SwallowRepository;
/**
 * 模块授权
 * @author ouwt
 *
 */
@Service
public class ModuleAuthRepository  extends SwallowRepository<ModuleAuth>{

}
