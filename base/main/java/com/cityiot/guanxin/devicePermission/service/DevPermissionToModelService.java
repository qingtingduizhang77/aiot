package com.cityiot.guanxin.devicePermission.service;

import com.cityiot.guanxin.devicePermission.entity.DevPermissionToModel;
import com.cityiot.guanxin.devicePermission.entity.QDevPermissionToModel;
import com.cityiot.guanxin.devicePermission.repository.DevPermissionToModelRepository;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

@Service
public class DevPermissionToModelService extends BaseService<DevPermissionToModelRepository, DevPermissionToModel> {

    public void deleteItemByPermissionId(Long permissionId){
        getRepsitory().deleteEntityByColumns(
                query -> query.where(QDevPermissionToModel.devPermissionToModel.devicePermissionId.eq(permissionId)));
    }
}
