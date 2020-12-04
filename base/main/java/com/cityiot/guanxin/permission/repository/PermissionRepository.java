package com.cityiot.guanxin.permission.repository;

import com.cityiot.guanxin.common.repository.BaseRepository;
import com.cityiot.guanxin.permission.entity.Permission;
import com.cityiot.guanxin.permission.entity.QPermission;
import com.querydsl.core.types.EntityPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PermissionRepository extends BaseRepository<Permission> {
    private static final Logger log = LoggerFactory.getLogger(PermissionRepository.class);

   
}
