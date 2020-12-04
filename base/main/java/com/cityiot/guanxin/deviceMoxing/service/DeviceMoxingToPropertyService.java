package com.cityiot.guanxin.deviceMoxing.service;

import com.cityiot.guanxin.deviceGroup.entity.DeviceGroupRelation;
import com.cityiot.guanxin.deviceGroup.entity.QDeviceGroupRelation;
import com.cityiot.guanxin.deviceInformation.entity.Deviceinformation;
import com.cityiot.guanxin.deviceInformation.entity.QDeviceinformation;
import com.cityiot.guanxin.deviceMoxing.entity.DeviceMoxingProperty;
import com.cityiot.guanxin.deviceMoxing.entity.DeviceMoxingToProperty;
import com.cityiot.guanxin.deviceMoxing.entity.QDeviceMoxingProperty;
import com.cityiot.guanxin.deviceMoxing.entity.QDeviceMoxingToProperty;
import com.cityiot.guanxin.deviceMoxing.repository.DeviceMoxingToPropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swallow.framework.service.BaseService;

import java.util.List;

@Service
public class DeviceMoxingToPropertyService extends BaseService<DeviceMoxingToPropertyRepository, DeviceMoxingToProperty> {

    @Autowired
    private DeviceMoxingPropertyService deviceMoxingPropertyService;


    public void deleteItemByMoxingId(Long moxingId){
        getRepsitory().deleteEntityByColumns(
                query -> query.where(QDeviceMoxingToProperty.deviceMoxingToProperty.deviceMoxingId.eq(moxingId)));
    }

    @Transactional
    public void insertItem(long moxingId, DeviceMoxingProperty item) throws Exception{
        this.isRepeatNameOrTag(moxingId, item);
        DeviceMoxingProperty entity = deviceMoxingPropertyService.insertItem(item);
        DeviceMoxingToProperty model = new DeviceMoxingToProperty();
        model.setPropertyId(entity.getId());
        model.setDeviceMoxingId(moxingId);
        super.insertItem(model);
    }

    @Transactional
    public void editItem(long moxingId, DeviceMoxingProperty item) throws Exception{
        this.isRepeatNameOrTag(moxingId, item);
        deviceMoxingPropertyService.updateItem(item);
    }

    @Transactional
    public void deleteItemByMoxingIdAndPropertyId(long moxingId, long propertyId){
        getRepsitory().deleteEntityByColumns(
                query -> query.where(QDeviceMoxingToProperty.deviceMoxingToProperty.deviceMoxingId.eq(moxingId)
                        .and(QDeviceMoxingToProperty.deviceMoxingToProperty.propertyId.eq(propertyId))));
        deviceMoxingPropertyService.deleteItemById(propertyId);
    }

    public List<DeviceMoxingProperty> getPropertyListByMoxingId(long moxingId){
        QDeviceMoxingToProperty qDeviceMoxingToProperty = QDeviceMoxingToProperty.deviceMoxingToProperty;
        return deviceMoxingPropertyService.getAllItems(
                query -> query.leftJoin(qDeviceMoxingToProperty)
                        .on(qDeviceMoxingToProperty.propertyId.eq(QDeviceMoxingProperty.deviceMoxingProperty.id))
                        .where(qDeviceMoxingToProperty.deviceMoxingId.eq(moxingId)));
    }

    /**
     * 校验属性名称或标识符是否已被创建
     * @param item
     */
    public void isRepeatNameOrTag(long moxingId, DeviceMoxingProperty item) throws Exception{
        QDeviceMoxingProperty qDeviceMoxingProperty = QDeviceMoxingProperty.deviceMoxingProperty;
        QDeviceMoxingToProperty qDeviceMoxingToProperty = QDeviceMoxingToProperty.deviceMoxingToProperty;
        List<DeviceMoxingProperty> list = deviceMoxingPropertyService.getAllItems(query -> {
            query.leftJoin(qDeviceMoxingToProperty)
                    .on(qDeviceMoxingToProperty.propertyId.eq(qDeviceMoxingProperty.id))
                    .where(qDeviceMoxingProperty.name.eq(item.getName())
                    .or(qDeviceMoxingProperty.tag.eq(item.getTag())));
            query.where(qDeviceMoxingToProperty.deviceMoxingId.eq(moxingId));
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
