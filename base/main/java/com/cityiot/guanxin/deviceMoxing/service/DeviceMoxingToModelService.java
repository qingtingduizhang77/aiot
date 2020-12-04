package com.cityiot.guanxin.deviceMoxing.service;

import com.cityiot.guanxin.deviceMoxing.entity.DeviceMoxingToModel;
import com.cityiot.guanxin.deviceMoxing.entity.QDeviceMoxingToModel;
import com.cityiot.guanxin.deviceMoxing.repository.DeviceMoxingToModelRepository;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

@Service
public class DeviceMoxingToModelService extends BaseService<DeviceMoxingToModelRepository, DeviceMoxingToModel>{

    public void deleteItemByMoxingId(Long moxingId){
        getRepsitory().deleteEntityByColumns(
                query -> query.where(QDeviceMoxingToModel.deviceMoxingToModel.deviceMoxingId.eq(moxingId)));
    }
}
