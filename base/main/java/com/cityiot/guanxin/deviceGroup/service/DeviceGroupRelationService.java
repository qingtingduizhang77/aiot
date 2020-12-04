package com.cityiot.guanxin.deviceGroup.service;

import com.cityiot.guanxin.deviceGroup.entity.DeviceGroupRelation;
import com.cityiot.guanxin.deviceGroup.entity.QDeviceGroupRelation;
import com.cityiot.guanxin.deviceGroup.repository.DeviceGroupRelationRepository;
import com.cityiot.guanxin.deviceInformation.entity.Deviceinformation;
import com.cityiot.guanxin.deviceInformation.entity.QDeviceinformation;
import com.cityiot.guanxin.deviceInformation.service.DeviceinformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swallow.framework.service.BaseService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceGroupRelationService extends BaseService<DeviceGroupRelationRepository, DeviceGroupRelation> {

    @Autowired
    private DeviceinformationService deviceinformationService;

    public void deleteItemByGroupId(Long groupId){
        getRepsitory().deleteEntityByColumns(
                query -> query.where(QDeviceGroupRelation.deviceGroupRelation.deviceGroupId.eq(groupId)));
    }

    @Transactional
    public DeviceGroupRelation insertItem(long groupId, long deviceId){
        DeviceGroupRelation deviceGroupRelation = new DeviceGroupRelation();
        deviceGroupRelation.setDeviceGroupId(groupId);
        deviceGroupRelation.setDeviceId(deviceId);
        return this.insertItem(deviceGroupRelation);
    }

    @Transactional
    public void deleteItemByGroupIdAndDeviceId(long groupId, long deviceId){
        getRepsitory().deleteEntityByColumns(
                query -> query.where(QDeviceGroupRelation.deviceGroupRelation.deviceGroupId.eq(groupId)
                        .and(QDeviceGroupRelation.deviceGroupRelation.deviceId.eq(deviceId))));
    }

    public List<Deviceinformation> getDeviceInfoListByGroupId(long groupId){
        List<DeviceGroupRelation> list = this.getAllItems(
                query -> query.where(QDeviceGroupRelation.deviceGroupRelation.deviceGroupId.eq(groupId)));

        List<Long> deviceIds = list.stream().distinct().map(DeviceGroupRelation::getDeviceId).collect(Collectors.toList());

        return deviceinformationService.getAllItems(
                query -> query.where(QDeviceinformation.deviceinformation.id.in(deviceIds))
        );
    }
}
