package com.cityiot.guanxin.common.ipush.entity;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import swallow.framework.jpaquery.repository.SwallowRepository;

@Service
public class IPushRepository extends SwallowRepository<IPush> {
}
