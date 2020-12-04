package com.cityiot.guanxin.devicePermission.service;

import com.cityiot.guanxin.devicePermission.entity.DevPermissionToOperRole;
import com.cityiot.guanxin.devicePermission.entity.QDevPermissionToOperRole;
import com.cityiot.guanxin.devicePermission.repository.DevPermissionToOperRoleRepository;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

@Service
public class DevPermissionToOperRoleService extends BaseService<DevPermissionToOperRoleRepository, DevPermissionToOperRole> {

    public void deleteItemByPermissionId(Long permissionId){
        getRepsitory().deleteEntityByColumns(
                query -> query.where(QDevPermissionToOperRole.devPermissionToOperRole.devicePermissionId.eq(permissionId)));
    }
}
