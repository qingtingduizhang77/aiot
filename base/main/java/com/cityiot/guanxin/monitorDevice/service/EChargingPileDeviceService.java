package com.cityiot.guanxin.monitorDevice.service;


import com.cityiot.guanxin.devicePermission.service.DevicePermissionService;
import com.cityiot.guanxin.monitorDevice.vo.DeviceCountVo;
import com.cityiot.guanxin.monitorDevice.vo.DeviceVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swallow.framework.service.BaseService;

import com.cityiot.guanxin.monitorDevice.entity.EChargingPileDevice;
import com.cityiot.guanxin.monitorDevice.repository.EChargingPileDeviceRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * EChargingPileDevice 数据Service
 * @author aohanhe
 *
 */
@Service
public class EChargingPileDeviceService extends BaseService<EChargingPileDeviceRepository, EChargingPileDevice>{
	// 日志对象
	private static final Logger log = LoggerFactory.getLogger(EChargingPileDeviceService.class);

	@Autowired
	private EChargingPileDeviceRepository repository;

	@Autowired
	private DevicePermissionService devicePermissionService;

	public List<DeviceVo> queryMonitorDevices(Integer deviceType, String keyword, String lat, String lng, Double scope,Map<String,List<Long>> map) throws Exception {
		return repository.queryMonitorDevices(deviceType, keyword, lat, lng, scope, map);
	}

	// 获取当前用户角色下有（查看）权限的设备列表
	public List<DeviceVo> getDeviceMonitorList(Integer deviceType, String keyword, String lat, String lng, Double scope) throws Exception{
		if(lat == null || lng == null){
			throw new Exception("经度或纬度不能为空!");
		}

		Map<String,List<Long>> map = devicePermissionService.getDeviceInfo("view");

		if (map == null) {
			log.info("没有可查看的设备列表");
			return null;
		}else {
			if (map.containsKey("admin")) { //判断是否有管理员权限
				return this.queryMonitorDevices(deviceType, keyword, lat, lng, scope, null);
			}
		}
		return this.queryMonitorDevices(deviceType, keyword, lat, lng, scope, map);
	}

	// 获取当前用户角色下有（操作）权限的设备列表
	public List<DeviceVo> getOperDeviceList() throws Exception{

		Map<String,List<Long>> map = devicePermissionService.getDeviceInfo("oper");

		if (map == null) {
			log.info("没有可操作的设备列表");
			return null;
		}else {
			if (map.containsKey("admin")) { //判断是否有管理员权限
				return this.queryMonitorDevices(null, null, null, null, null, null);
			}
		}

		return this.queryMonitorDevices(null, null, null, null, null, map);
	}

	// 校验是否有设备的操作权限
	public boolean deviceIsOperated(Long deviceId) throws Exception{
		List<DeviceVo> deviceList = this.getOperDeviceList();

		if (deviceList != null && deviceList.size() > 0) {
			List<Long> deviceIds = deviceList.stream().map(DeviceVo::getDeviceId).distinct().collect(Collectors.toList());

			if (deviceIds.contains(deviceId)){
				return true;
			}
		}
		return false;
	}

	public List<DeviceCountVo> monitorDeviceCount() {
		return repository.monitorDeviceCount();
	}

	@Transactional(rollbackFor = Exception.class)
	public void switchLamp(long deviceId, Integer switchStatus) {
		this.repository.switchLamp(deviceId, switchStatus);
	}
}
