package com.cityiot.guanxin.repository;


import com.cityiot.guanxin.entity.SystemVariableType;
import com.querydsl.core.types.EntityPath;
import org.springframework.stereotype.Service;
import swallow.framework.jpaquery.repository.SwallowRepository;

/**
 * 系统变量类型 的数据访问
 * 注意 因为使用的是自定义的SwallBaseRepository,使用@Repository无效
 * @author aohanhe
 *
 */
@Service
public class SystemVariableTypeRepository extends SwallowRepository<SystemVariableType>{



}
