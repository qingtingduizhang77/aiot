package com.cityiot.guanxin.deviceInformation.repository;


import com.cityiot.guanxin.deviceInformation.entity.Deviceinformation;
import com.cityiot.guanxin.monitorDevice.vo.ChargingPileVo;
import com.cityiot.guanxin.monitorDevice.vo.DustbinDeviceVo;
import com.cityiot.guanxin.monitorDevice.vo.LampDeviceVo;
import com.cityiot.guanxin.monitorDevice.vo.VideoDeviceVo;
import org.springframework.stereotype.Service;
import swallow.framework.jpaquery.repository.SwallowRepository;

import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设备信息 的数据访问
 * 注意 因为使用的是自定义的SwallBaseRepository,使用@Repository无效
 * @author aohanhe
 *
 */
@Service
public class DeviceinformationRepository extends SwallowRepository<Deviceinformation>{

	

    public Map<String,Object> selectCount() {
        String sql = "SELECT \n" +
                "\tCOUNT(*) AS totalDeviceNum,\n" +
                "\tnormalTable.normalDeviceNum AS normalDeviceNum,\n" +
                "\tbreakTable.breakdownDeviceNum AS breakdownDeviceNum\n" +
                "FROM deviceinformation\n" +
                "JOIN \n" +
                "(SELECT COUNT(*) AS normalDeviceNum FROM deviceinformation WHERE status = 1) AS normalTable\n" +
                "JOIN\n" +
                "(SELECT COUNT(*) AS breakdownDeviceNum FROM deviceinformation WHERE status = 0) AS breakTable";
        Query query = this.em.createNativeQuery(sql);
        List resultList = query.getResultList();
        Map<String,Object> map = new HashMap<>();
        map.put("totalDeviceNum", ((Object[])resultList.get(0))[0]);
        map.put("normalDeviceNum", ((Object[])resultList.get(0))[1]);
        map.put("breakdownDeviceNum", ((Object[])resultList.get(0))[2]);
        return map;
    }

    public List<LampDeviceVo> lampDetail(long deviceId, String lat, String lng) {
	    String sql = "SELECT\n" +
                "\tdeviceinformation.area AS area,\n" +
                "\tdeviceinformation.id AS device_id,\n" +
                "\tdeviceinformation.device_name AS device_name,\n" +
                "\tdeviceinformation.device_code AS device_code,\n" +
                "\tdeviceinformation.latitude AS lat,\n" +
                "\tdeviceinformation.longitude AS lng,\n" +
                "\telamp_device.brightness AS brightness,\n" +
                "\telamp_device.switch_status AS switch_status,\n" +
                "\tdeviceinformation.lastmodi AS update_time,\n" +
                "\tGET_DISTANCE(:lat, :lng, deviceinformation.latitude, deviceinformation.longitude) AS distance\n" +
                "FROM\n" +
                "\tdeviceinformation\n" +
                "LEFT JOIN device_model ON deviceinformation.device_model_id = device_model.id\n" +
                "LEFT JOIN device_type ON device_model.device_type_id = device_type.id\n" +
                "LEFT JOIN elamp_device ON elamp_device.device_info_id = deviceinformation.id\n" +
                "WHERE device_type.device_type_name = '路灯' AND deviceinformation.id = :deviceId";
        Query query = this.em.createNativeQuery(sql, LampDeviceVo.class)
                .setParameter("lat", lat).setParameter("lng", lng).setParameter("deviceId", deviceId);
        return query.getResultList();
    }

    public List<VideoDeviceVo> videoDeviceDetail(long deviceId, String lat, String lng) {
        String sql = "SELECT\n" +
                "\tdeviceinformation.area AS area,\n" +
                "\tdeviceinformation.id AS device_id,\n" +
                "\tdeviceinformation.device_name AS device_name,\n" +
                "\tdeviceinformation.device_code AS device_code,\n" +
                "\tdeviceinformation.latitude AS lat,\n" +
                "\tdeviceinformation.longitude AS lng,\n" +
                "\tdeviceinformation.lastmodi AS update_time,\n" +
                "\tevideo_device.video_status AS video_status,\n" +
                "\tevideo_device.stream_url AS stream_url,\n" +
                "\tGET_DISTANCE(:lat, :lng, deviceinformation.latitude, deviceinformation.longitude) AS distance\n" +
                "FROM\n" +
                "\tdeviceinformation\n" +
                "LEFT JOIN device_model ON deviceinformation.device_model_id = device_model.id\n" +
                "LEFT JOIN device_type ON device_model.device_type_id = device_type.id\n" +
                "LEFT JOIN evideo_device ON evideo_device.device_info_id = deviceinformation.id\n" +
                "WHERE device_type.device_type_name = '视频监控' AND deviceinformation.id = :deviceId";
        Query query = this.em.createNativeQuery(sql, VideoDeviceVo.class)
                .setParameter("lat", lat).setParameter("lng", lng).setParameter("deviceId", deviceId);
        return query.getResultList();
    }

    public List<ChargingPileVo> chargingPileDetail(long deviceId, String lat, String lng) {
        String sql = "SELECT\n" +
                "\tdeviceinformation.area AS area,\n" +
                "\tdeviceinformation.id AS device_id,\n" +
                "\tdeviceinformation.device_name AS device_name,\n" +
                "\tdeviceinformation.device_code AS device_code,\n" +
                "\tdeviceinformation.latitude AS lat,\n" +
                "\tdeviceinformation.longitude AS lng,\n" +
                "\tdeviceinformation.lastmodi AS update_time,\n" +
                "\techarging_pile_device.use_status AS use_status,\n" +
                "\techarging_pile_device.charging_status AS charging_status,\n" +
                "\tGET_DISTANCE(:lat, :lng, deviceinformation.latitude, deviceinformation.longitude) AS distance\n" +
                "FROM\n" +
                "\tdeviceinformation\n" +
                "LEFT JOIN device_model ON deviceinformation.device_model_id = device_model.id\n" +
                "LEFT JOIN device_type ON device_model.device_type_id = device_type.id\n" +
                "LEFT JOIN echarging_pile_device ON echarging_pile_device.device_info_id = deviceinformation.id\n" +
                "WHERE device_type.device_type_name = '充电桩' AND deviceinformation.id = :deviceId";
        Query query = this.em.createNativeQuery(sql, ChargingPileVo.class)
                .setParameter("lat", lat).setParameter("lng", lng).setParameter("deviceId", deviceId);
        return query.getResultList();
    }

    public List<DustbinDeviceVo> dustbinDetail(long deviceId, String lat, String lng) {
        String sql = "SELECT\n" +
                "\tdeviceinformation.area AS area,\n" +
                "\tdeviceinformation.id AS device_id,\n" +
                "\tdeviceinformation.device_name AS device_name,\n" +
                "\tdeviceinformation.device_code AS device_code,\n" +
                "\tdeviceinformation.latitude AS lat,\n" +
                "\tdeviceinformation.longitude AS lng,\n" +
                "\tdeviceinformation.lastmodi AS update_time,\n" +
                "\tedustbin_device.use_status AS use_status,\n" +
                "\tedustbin_device.dustbin_status AS dustbin_status,\n" +
                "\tGET_DISTANCE(:lat, :lng, deviceinformation.latitude, deviceinformation.longitude) AS distance\n" +
                "FROM\n" +
                "\tdeviceinformation\n" +
                "LEFT JOIN device_model ON deviceinformation.device_model_id = device_model.id\n" +
                "LEFT JOIN device_type ON device_model.device_type_id = device_type.id\n" +
                "LEFT JOIN edustbin_device ON edustbin_device.device_info_id = deviceinformation.id\n" +
                "WHERE device_type.device_type_name = '垃圾桶' AND deviceinformation.id = :deviceId";
        Query query = this.em.createNativeQuery(sql, DustbinDeviceVo.class)
                .setParameter("lat", lat).setParameter("lng", lng).setParameter("deviceId", deviceId);
        return query.getResultList();
    }
}
