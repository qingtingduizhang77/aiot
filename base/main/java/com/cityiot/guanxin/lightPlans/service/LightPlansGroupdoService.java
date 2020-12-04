package com.cityiot.guanxin.lightPlans.service;

import com.cityiot.guanxin.deviceInformation.entity.Deviceinformation;
import com.cityiot.guanxin.deviceInformation.entity.QDeviceinformation;
import com.cityiot.guanxin.deviceInformation.service.DeviceinformationService;
import com.cityiot.guanxin.lightPlans.entity.*;
import com.cityiot.guanxin.lightPlans.repository.LightPlansGroupdoRepository;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swallow.framework.exception.SwallowException;
import swallow.framework.service.BaseService;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Guoyz
 * createTime   2020/5/15 14:17
 */
@Service
public class LightPlansGroupdoService extends BaseService<LightPlansGroupdoRepository, LightPlansGroupdo> {
    private static final Logger log = LoggerFactory.getLogger(LightPlansGroupdoService.class);

    @Autowired
    private DeviceinformationService deviceinformationService;
    @Autowired
    private LightPlansService lightPlansService;

    //路灯设备列表
    public List<Deviceinformation> getDeviceListById(long id) {
        List<LightPlansGroupdo> list = this.getAllItems(
                query -> query.where(QLightPlansGroupdo.lightPlansGroupdo.lightPlansGroupId.eq(id)));
        Set<Long> deviceIds = list.stream().distinct().map(LightPlansGroupdo::getLightDeviceId).collect(Collectors.toSet());
        deviceIds.remove(null);
        return deviceinformationService.getAllItems(
                query -> query.where(QDeviceinformation.deviceinformation.id.in(deviceIds))
        );
    }
    //路灯策略列表
    public List<LightPlans> getLightPlansListById(long id) {
        List<LightPlansGroupdo> list = this.getAllItems(
                query -> query.where(QLightPlansGroupdo.lightPlansGroupdo.lightPlansGroupId.eq(id)));
        Set<Long> planIds = list.stream().distinct().map(LightPlansGroupdo::getLightPlansId).collect(Collectors.toSet());
        planIds.remove(null);
        return lightPlansService.getAllItems(
                query->query.where(QLightPlans.lightPlans.id.in(planIds))
        );
    }

    //添加路灯设备
    public void addLightDevice(long id,long lightDeviceId) throws SwallowException {
        LightPlansGroupdo lightPlansGroupdo = new LightPlansGroupdo();
        lightPlansGroupdo.setLightPlansGroupId(id);
        lightPlansGroupdo.setLightDeviceId(lightDeviceId);
        super.insertItem(lightPlansGroupdo);
    }
    //添加路灯计划策略
    public void addlightPlans(long id,long lightPlansId) throws SwallowException {
        LightPlansGroupdo lightPlansGroupdo = new LightPlansGroupdo();
        lightPlansGroupdo.setLightPlansGroupId(id);
        lightPlansGroupdo.setLightPlansId(lightPlansId);
        super.insertItem(lightPlansGroupdo);
    }

    //删除路灯设备
    public void delLightDevice(long id, Long[] deviceId) {
        List<Long> longs = Arrays.asList(deviceId);
        this.getRepsitory().deleteEntityByColumns(query->query.where(QLightPlansGroupdo.lightPlansGroupdo.lightPlansGroupId.eq(id).and(QLightPlansGroupdo.lightPlansGroupdo.lightDeviceId.in(longs))));
    }

    //删除路灯计划策略
    public void delLightPlans(long id, Long[] lightPlansIds) {
        List<Long> longs = Arrays.asList(lightPlansIds);
        this.getRepsitory().deleteEntityByColumns(query->query.where(QLightPlansGroupdo.lightPlansGroupdo.lightPlansGroupId.eq(id).and(QLightPlansGroupdo.lightPlansGroupdo.lightPlansId.in(longs))));
    }
    //根据分组id删除关联表数据
    public void deleteItemByGroupId(long id){
        this.getRepsitory().deleteEntityByColumns(qu->qu.where(QLightPlansGroupdo.lightPlansGroupdo.lightPlansGroupId.eq(id)));
    }
}
