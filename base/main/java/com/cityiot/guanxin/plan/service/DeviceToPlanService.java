package com.cityiot.guanxin.plan.service;

import com.cityiot.guanxin.deviceInformation.entity.Deviceinformation;
import com.cityiot.guanxin.deviceInformation.entity.QDeviceinformation;
import com.cityiot.guanxin.devicePermission.service.DevicePermissionService;
import com.cityiot.guanxin.plan.entity.DeviceToPlan;
import com.cityiot.guanxin.plan.entity.QDeviceToPlan;
import com.cityiot.guanxin.plan.repository.DeviceToPlanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swallow.framework.service.BaseService;
import swallow.framework.web.BaseQueryBean;

import java.util.List;
import java.util.Map;

@Service
public class DeviceToPlanService extends BaseService<DeviceToPlanRepository, DeviceToPlan> {
    // 日志对象
    private static final Logger log = LoggerFactory.getLogger(DeviceToPlanService.class);

    @Autowired
    private DevicePermissionService devicePermissionService;

    //获取指定平面图对应设备
    public List<DeviceToPlan> getItemByPlanId(long planId) {
        return this.getAllItems(query -> query.where(QDeviceToPlan.deviceToPlan.planId.eq(planId)));
    }

    ////更新指定平面图设备（包括添加和删除）（之前获取的设备会被覆盖）
    @Transactional
    public void updateDeviceToPlanByPlanId(long planId, DeviceToPlan[] item) {
            this.deleteItemByPlanId(planId);
        if(item.length>0){
            for (DeviceToPlan deviceToPlan : item) {
                deviceToPlan.setId(null);
                deviceToPlan.setPlanId(planId);
                this.insertItem(deviceToPlan);
            }
        }
    }

    //删除指定平面图关联的设备
    private void deleteItemByPlanId(long planId) {
        getRepsitory().deleteEntityByColumns(query -> query.where(QDeviceToPlan.deviceToPlan.planId.eq(planId)));
    }

    public List<DeviceToPlan> getListByPlanId(long planId) throws Exception{

        Map<String,List<Long>> map = devicePermissionService.getDeviceInfo("view");

        if (map == null) {
            log.info("没有可查看的设备列表");
            return null;
        }else {
            if (map.containsKey("admin")) { //判断是否有管理员权限
                return this.getAdminDeviceList(planId);
            }
        }

        return this.getDeviceList(planId, map.get("modelIds"), map.get("areaCodes"), map.get("deviceIds"));
    }

    public List<DeviceToPlan> getDeviceList (long planId, List<Long> modelIds, List<Long> areaCodes, List<Long> deviceIds) {
        QDeviceinformation qDeviceinformation = QDeviceinformation.deviceinformation;
        return this.getAllItems(query -> {
                    query.leftJoin(qDeviceinformation).on(qDeviceinformation.id.eq(QDeviceToPlan.deviceToPlan.deviceId))
                            .where(QDeviceToPlan.deviceToPlan.planId.eq(planId));
                    if (modelIds != null && modelIds.size() > 0) {
                        query.where(qDeviceinformation.deviceModelId.in(modelIds));
                    }
                    if (areaCodes != null && areaCodes.size() > 0) {
                        query.where(qDeviceinformation.areaCode.in(areaCodes));
                    }
                    if (deviceIds != null && deviceIds.size() > 0) {
                        query.where(qDeviceinformation.id.in(deviceIds));
                    }
                    return query;
        });
    }

    public List<DeviceToPlan> getAdminDeviceList(long planId){
        QDeviceinformation qDeviceinformation = QDeviceinformation.deviceinformation;
        return this.getAllItems(query -> query.leftJoin(qDeviceinformation).on(qDeviceinformation.id.eq(QDeviceToPlan.deviceToPlan.deviceId))
                .where(QDeviceToPlan.deviceToPlan.planId.eq(planId)));
    }
}
