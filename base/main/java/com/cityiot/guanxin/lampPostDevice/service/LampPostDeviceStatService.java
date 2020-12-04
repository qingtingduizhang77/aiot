package com.cityiot.guanxin.lampPostDevice.service;

import com.cityiot.guanxin.deviceInformation.entity.Deviceinformation;
import com.cityiot.guanxin.devicePermission.service.DevicePermissionService;
import com.cityiot.guanxin.lampPostDevice.entity.LampPostDeviceStat;
import com.cityiot.guanxin.lampPostDevice.entity.QLampPostDeviceStat;
import com.cityiot.guanxin.lampPostDevice.repository.LampPostDeviceStatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LampPostDeviceStatService extends BaseService<LampPostDeviceStatRepository, LampPostDeviceStat> {
    @Autowired
    private DevicePermissionService devicePermissionService;

    public PageListData<LampPostDeviceStat> findLampPostTypePageList(BasePageQueryBean queryBean) throws Exception{
        // 当前角色可查看的设备列表
        List<Deviceinformation> deviceList = devicePermissionService.getDeviceList(null);
        if (deviceList == null || deviceList.isEmpty())
            return null;

        List<Long> deviceIds = deviceList.stream().map(Deviceinformation::getId).distinct().collect(Collectors.toList());

        return this.getAllItemPageByQuerybean(queryBean, query -> {
            query.where(QLampPostDeviceStat.lampPostDeviceStat.deviceTypeId.in(1,11));
            if (deviceIds.size() > 0) {
                query.where(QLampPostDeviceStat.lampPostDeviceStat.id.in(deviceIds));
            }
            return query;
        });
    }

    // 灯杆统计
    public Map<String, Object> getLampPostStat(){
        Map<String, Object> map = new HashMap<>();
        Long[] deviceTypeIds = new Long[]{1L,11L};
        map.put("sum", this.getRepsitory().getDeviceSum(deviceTypeIds));
        map.put("alarmCountSum", this.getAlarmCountSum(deviceTypeIds));
        map.put("lightUpCountSum", this.getLightUpCountSum(deviceTypeIds));
        return map;
    }

    //单灯统计
    public Map<String, Object> getLightStat(){
        Map<String, Object> map = new HashMap<>();
        Long[] deviceTypeIds = new Long[]{14L};
        map.put("sum", this.getRepsitory().getDeviceSum(deviceTypeIds));
        map.put("alarmCountSum", this.getAlarmCountSum(deviceTypeIds));
        map.put("lightUpCountSum", this.getLightUpCountSum(deviceTypeIds));
        return map;
    }

    private long getAlarmCountSum(Long[] deviceTypeIds){
        List<LampPostDeviceStat> list = this.getAllItems(query -> query.where(
                QLampPostDeviceStat.lampPostDeviceStat.deviceTypeId.in(deviceTypeIds)
        ));
        return list.stream().mapToInt(LampPostDeviceStat::getAlarmCount).sum();
    }

    private long getLightUpCountSum(Long[] deviceTypeIds){
        List<LampPostDeviceStat> list = this.getAllItems(query -> query.where(
                QLampPostDeviceStat.lampPostDeviceStat.deviceTypeId.in(deviceTypeIds)
        ));
        return list.stream().mapToInt(LampPostDeviceStat::getLightUpCount).sum();
    }
}
