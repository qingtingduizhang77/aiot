package com.cityiot.guanxin.devicePermission.service;

import com.cityiot.guanxin.devicePermission.entity.DevPermissionToAreaCode;
import com.cityiot.guanxin.devicePermission.entity.QDevPermissionToAreaCode;
import com.cityiot.guanxin.devicePermission.repository.DevPermissionToAreaCodeRepository;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

@Service
public class DevPermissionToAreaCodeService extends BaseService<DevPermissionToAreaCodeRepository, DevPermissionToAreaCode> {

    public void deleteItemByPermissionId(Long permissionId){
        getRepsitory().deleteEntityByColumns(
                query -> query.where(QDevPermissionToAreaCode.devPermissionToAreaCode.devicePermissionId.eq(permissionId)));
    }
}
