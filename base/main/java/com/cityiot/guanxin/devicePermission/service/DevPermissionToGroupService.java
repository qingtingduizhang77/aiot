package com.cityiot.guanxin.devicePermission.service;

import com.cityiot.guanxin.devicePermission.entity.DevPermissionToGroup;
import com.cityiot.guanxin.devicePermission.entity.QDevPermissionToGroup;
import com.cityiot.guanxin.devicePermission.repository.DevPermissionToGroupRepository;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

@Service
public class DevPermissionToGroupService extends BaseService<DevPermissionToGroupRepository, DevPermissionToGroup> {


    public void deleteItemByPermissionId(Long permissionId){
        getRepsitory().deleteEntityByColumns(
                query -> query.where(QDevPermissionToGroup.devPermissionToGroup.devicePermissionId.eq(permissionId)));
    }
}
