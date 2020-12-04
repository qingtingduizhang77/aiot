package com.cityiot.guanxin.monitorDevice.repository;


import com.cityiot.guanxin.monitorDevice.entity.QELampDevice;
import com.cityiot.guanxin.monitorDevice.vo.DeviceCountVo;
import com.cityiot.guanxin.monitorDevice.vo.DeviceVo;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.EntityPath;

import swallow.framework.jpaquery.repository.SwallowRepository;
import com.cityiot.guanxin.monitorDevice.entity.QEChargingPileDevice;
import com.cityiot.guanxin.monitorDevice.entity.EChargingPileDevice;

import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * 充电桩设备 的数据访问
 * 注意 因为使用的是自定义的SwallBaseRepository,使用@Repository无效
 * @author aohanhe
 *
 */
@Service
public class EChargingPileDeviceRepository extends SwallowRepository<EChargingPileDevice>{


	public List<DeviceVo> queryMonitorDevices(Integer deviceType, String keyword, String lat, String lng, Double scope, Map<String,List<Long>> map) {
		String sql = "SELECT\n" +
				"\t\t\t\tdeviceinformation.id AS device_id,\n" +
				"\t\t\t\tdeviceinformation.device_name AS device_name,\n" +
				"\t\t\t\tdeviceinformation.device_code AS device_code,\n" +
				"\t\t\t\tdeviceinformation.latitude AS lat,\n" +
				"\t\t\t\tdeviceinformation.longitude AS lng,\n" +
				"\t\t\t\tdeviceinformation.status AS status,\n" +
				"\t\t\t\tdeviceinformation.lastmodi AS update_time,\n" +
				"\t\t\t\tdeviceinformation.area AS area,\n" +
				"\t\t\t\tdeviceinformation.cid AS cid,\n" +
				"\t\t\t\tdeviceinformation.appcode AS appcode,\n" +
				"\t\t\t\tdeviceinformation.alarm_status AS alarm_status,\n" +
				"\t\t\t\tdevice_type.device_type_name AS device_type_name,\n" +
				"\t\t\t\tGET_DISTANCE(:lat, :lng, deviceinformation.latitude, deviceinformation.longitude) AS distance,\n" +
				"\t\t\t\tdevice_type.id AS device_type\n" +
				"\t\t\t\tFROM\n" +
				"\t\t\t\tdeviceinformation\n" +
				"\t\t\t\tLEFT JOIN device_model ON deviceinformation.device_model_id = device_model.id\n" +
				"\t\t\t\tLEFT JOIN device_type ON device_model.device_type_id = device_type.id\n" +
				"WHERE 1 = 1 AND deviceinformation.is_show_map = 1\n";
		if(keyword != null){
			sql = sql + " and device_name like :keyword";
		}
		if(scope != null){
			sql = sql + " and GET_DISTANCE(:lat, :lng, deviceinformation.latitude, deviceinformation.longitude) < :scope";
		}
		if(deviceType != null){
			sql = sql + " and device_type.id  = :deviceType \n";
		}
		if (map != null) {
			if (map.get("modelIds") != null && map.get("modelIds").size() > 0) {
				sql = sql + " AND deviceinformation.device_model_id in (:modelIds)";
			}
			if (map.get("areaCodes") != null && map.get("areaCodes").size() > 0) {
				sql = sql + " AND deviceinformation.area_code in (:areaCodes)";
			}
			if (map.get("deviceIds") != null && map.get("deviceIds").size() > 0) {
				sql = sql + " AND deviceinformation.id in (:deviceIds)";
			}
		}


		Query query = this.em.createNativeQuery(sql, DeviceVo.class);
		query.setParameter("lat", lat);
		query.setParameter("lng", lng);
		if(deviceType != null){
			query.setParameter("deviceType", deviceType);
		}
		if(scope != null){
			query.setParameter("scope", scope);
		}
		if(keyword != null){
			query.setParameter("keyword", "%"+keyword+"%");
		}
		if (map != null) {
			if (map.get("modelIds") != null && map.get("modelIds").size() > 0) {
				query.setParameter("modelIds", map.get("modelIds"));
			}
			if (map.get("areaCodes") != null && map.get("areaCodes").size() > 0) {
				query.setParameter("areaCodes", map.get("areaCodes"));
			}
			if (map.get("deviceIds") != null && map.get("deviceIds").size() > 0) {
				query.setParameter("deviceIds", map.get("deviceIds"));
			}
		}

		return query.getResultList();
	}

	public List<DeviceCountVo> monitorDeviceCount() {
		String sql = "SELECT\n" +
				"\tdevice_type.id AS id,\n" +
				"\tcount(*) AS device_count,\n" +
				"\tdevice_type.device_type_name,\n" +
				"\tCASE\n" +
				"\t\tWHEN device_type.device_type_name = '路灯' THEN 1\n" +
				"\t\tWHEN device_type.device_type_name = '视频监控' THEN 2\n" +
				"\t\tWHEN device_type.device_type_name = '充电桩' THEN 3\n" +
				"\t\tWHEN device_type.device_type_name = '垃圾桶' THEN 4\n" +
				"\t  ELSE 0\n" +
				"\tEND as device_type\n" +
				"FROM\n" +
				"\tdeviceinformation\n" +
				"LEFT JOIN device_model ON deviceinformation.device_model_id = device_model.id\n" +
				"LEFT JOIN device_type ON device_model.device_type_id = device_type.id\n" +
				"GROUP BY\n" +
				"\tdevice_type.device_type_name";
		return this.em.createNativeQuery(sql, DeviceCountVo.class).getResultList();
	}


	public void switchLamp(long deviceId, Integer switchStatus) {
		QELampDevice eLampDevice = QELampDevice.eLampDevice;
		this.factory.update(eLampDevice).set(eLampDevice.switchStatus, switchStatus).where(eLampDevice.deviceInfoId.eq(deviceId)).execute();
	}
}
