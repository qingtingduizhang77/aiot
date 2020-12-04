package com.cityiot.guanxin.deviceMoxing.service;

import com.cityiot.guanxin.deviceMoxing.entity.DeviceMoxingProperty;
import com.cityiot.guanxin.deviceMoxing.entity.QDeviceMoxingProperty;
import com.cityiot.guanxin.deviceMoxing.repository.DeviceMoxingPropertyRepository;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

import java.util.List;

@Service
public class DeviceMoxingPropertyService extends BaseService<DeviceMoxingPropertyRepository, DeviceMoxingProperty> {

    /**
     * 校验属性名称或标识符是否已被创建
     * @param item
     */
    public void isRepeatNameOrTag(DeviceMoxingProperty item) throws Exception{
        QDeviceMoxingProperty qDeviceMoxingProperty = QDeviceMoxingProperty.deviceMoxingProperty;
        List<DeviceMoxingProperty> list = this.getAllItems(query -> {
            query.where(qDeviceMoxingProperty.name.eq(item.getName())
                    .or(qDeviceMoxingProperty.tag.eq(item.getTag())));
            if (item.getId() != null) {
                query.where(qDeviceMoxingProperty.id.ne(item.getId()));
            }
            return query;
        });
        if (list != null && list.size() > 0) {
            throw new Exception("属性名称或标识符不可重复创建！");
        }
    }
}
