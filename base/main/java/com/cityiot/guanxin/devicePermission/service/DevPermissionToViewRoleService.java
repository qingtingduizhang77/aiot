package com.cityiot.guanxin.devicePermission.service;

import com.cityiot.guanxin.devicePermission.entity.DevPermissionToViewRole;
import com.cityiot.guanxin.devicePermission.entity.QDevPermissionToViewRole;
import com.cityiot.guanxin.devicePermission.repository.DevPermissionToViewRoleRepository;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

@Service
public class DevPermissionToViewRoleService extends BaseService<DevPermissionToViewRoleRepository, DevPermissionToViewRole> {

    public void deleteItemByPermissionId(Long permissionId){
        getRepsitory().deleteEntityByColumns(
                query -> query.where(QDevPermissionToViewRole.devPermissionToViewRole.devicePermissionId.eq(permissionId)));
    }
}
