package com.cityiot.guanxin.deviceMoxing.service;

import com.cityiot.guanxin.deviceMoxing.entity.DeviceMoxing;
import com.cityiot.guanxin.deviceMoxing.entity.DeviceMoxingToModel;
import com.cityiot.guanxin.deviceMoxing.entity.QDeviceMoxingToModel;
import com.cityiot.guanxin.deviceMoxing.repository.DeviceMoxingRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swallow.framework.service.BaseService;

import java.util.List;

@Service
public class DeviceMoxingService extends BaseService<DeviceMoxingRepository, DeviceMoxing> {

    // 日志对象
    private static final Logger log = LoggerFactory.getLogger(DeviceMoxingService.class);

    @Autowired
    private DeviceMoxingToModelService modelService;

    @Autowired
    private DeviceMoxingToPropertyService propertyService;

    public DeviceMoxing insertItem(DeviceMoxing item){
        DeviceMoxing entity = super.insertItem(item);

        this.insertMoxingInfo(entity);

        return entity;
    }

    private void insertMoxingInfo(DeviceMoxing item){
        // 设备型号
        if (StringUtils.isNotEmpty(item.getDeviceModelIds())) {
            String[] deviceModelIds = item.getDeviceModelIds().split(",");
            for (var i =0; i<deviceModelIds.length;i++) {
                DeviceMoxingToModel model = new DeviceMoxingToModel();
                model.setDeviceModelId(Long.valueOf(deviceModelIds[i]));
                model.setDeviceMoxingId(item.getId());
                modelService.insertItem(model);
            }
        }
    }

    public void checkModelIsRelated(DeviceMoxing item) throws Exception{
        if (StringUtils.isNotEmpty(item.getDeviceModelIds())) {
            String[] deviceModelIds = item.getDeviceModelIds().split(",");
            Long[] deviceModelIdsArr = new Long[deviceModelIds.length];
            for (int i = 0; i < deviceModelIds.length; i++) {
                deviceModelIdsArr[i] = Long.valueOf(deviceModelIds[i]);
            }
            List<DeviceMoxingToModel> list = modelService.getAllItems(query -> {
                query.where(QDeviceMoxingToModel.deviceMoxingToModel.deviceModelId.in(deviceModelIdsArr));
                if (item.getId() != null) {// 更新时校验
                    query.where(QDeviceMoxingToModel.deviceMoxingToModel.deviceMoxingId.ne(item.getId()));
                }
                return query;
            });
            if (list != null && list.size() > 0) {
                log.warn("设备型号不允许被重复关联！");
                throw new Exception("设备型号不允许被重复关联！");
            }
        }else{
            log.warn("设备型号不能为空！");
            throw new Exception("设备型号不能为空！");
        }
    }

    @Transactional
    public void deleteItemById(long[] ids){
        for (var id : ids) {
            super.deleteItemById(id);
            this.deleteItemByMoxingId(id);
        }
    }

    @Transactional
    public void deleteItemByMoxingId(long id){
        modelService.deleteItemByMoxingId(id);
        propertyService.deleteItemByMoxingId(id);
    }

    @Transactional
    public DeviceMoxing updateItem(DeviceMoxing item){
        modelService.deleteItemByMoxingId(item.getId());// 先删除关联表
        this.insertMoxingInfo(item);// 新增关联表数据
        return super.updateItem(item);
    }
}
