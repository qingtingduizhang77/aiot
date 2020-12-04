package com.cityiot.guanxin.lampPostDevice.service;

import com.cityiot.guanxin.common.service.JDBCDaoImp;
import com.cityiot.guanxin.deviceInformation.entity.Deviceinformation;
import com.cityiot.guanxin.devicePermission.service.DevicePermissionService;
import com.cityiot.guanxin.lampPostDevice.entity.LampPostDevice;
import com.cityiot.guanxin.lampPostDevice.entity.vo.DeviceAssetVo;
import com.cityiot.guanxin.lampPostDevice.entity.vo.LampPostDeviceVo;
import com.cityiot.guanxin.lampPostDevice.repository.LampPostDeviceRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LampPostDeviceService extends BaseService<LampPostDeviceRepository, LampPostDevice> {

    @Autowired
    private DevicePermissionService devicePermissionService;

    @Autowired
    private JDBCDaoImp jdbcDaoImp;

    public List<LampPostDeviceVo> findLampPostDeviceList(Long deviceId, Long[] deviceTypeId) throws Exception{
        // 当前角色可查看的设备列表
        List<Deviceinformation> deviceList = devicePermissionService.getDeviceList(null);
        if (deviceList == null || deviceList.isEmpty())
            return null;

        List<Long> deviceIds = deviceList.stream().map(Deviceinformation::getId).distinct().collect(Collectors.toList());

        Map<String, Object> args  = new HashMap<>();

        String sql = "SELECT d.id AS id, d.id AS deviceId, d.device_name AS deviceName, lpd.is_own AS isOwn," +
                "lpd.is_lease AS isLease, lpd.lessor AS lessor, d.status AS status, d.alarm_status AS alarmStatus," +
                "lpd.brightness AS brightness, lpd.con_num AS conNum, d.device_type_id AS deviceTypeId," +
                "dt.device_type_name AS deviceTypeName, lpd.electric_current AS electricCurrent, lpd.noise AS noise," +
                "lpd.pm10 AS pm10, lpd.pm2point5 AS pm2point5, lpd.power AS power, lpd.switch_status AS switchStatus," +
                "lpd.co2 as co2,lpd.co as co,lpd.no as no,lpd.temperature as temperature,lpd.humidity as humidity,"+
                "lpd.transmission_rate AS transmissionRate, lpd.voltage AS voltage FROM " +
                "deviceinformation d " +
                "LEFT JOIN lamp_post_device lpd ON lpd.device_id = d.id " +
                "LEFT JOIN device_type dt ON d.device_type_id = dt.id "+
                "WHERE 1 = 1 ";
        if(deviceId != null){
            sql = sql + " and d.parent_id = :deviceId ";
            args.put("deviceId",deviceId);
        }
        if(deviceTypeId != null && deviceTypeId.length > 0 && !Arrays.asList(deviceTypeId).contains(0L)){
            sql = sql + " and d.device_type_id in (:deviceTypeId) ";
            args.put("deviceTypeId", Arrays.asList(deviceTypeId));
        }
        if (deviceIds.size() > 0) {
            sql = sql + " AND d.id in (:deviceIds) ";
            args.put("deviceIds",deviceIds);
        }else {// 无设备查看权限
            sql = sql + " AND d.id = 0 ";
        }
        sql = sql + " order by d.device_type_id asc,d.device_name desc";
        RowMapper<LampPostDeviceVo> rm = BeanPropertyRowMapper.newInstance(LampPostDeviceVo.class);
        return jdbcDaoImp.getListBySql(sql, rm, args);
    }
    
    
    public List<LampPostDevice>  getchildrenLampPostDevice(Long deviceId)
    {
    	return this.getRepsitory().getchildrenLampPostDevice(deviceId);
    }
    /**
     * 
     * @param type 1开灯,2:关灯,3:智能分流调光开启,4:智能分流调光关闭  5调光
     * @param deviceId 设备灯杆id
     * @param brightness 亮度
     * @return
     */
    public boolean  updchildrenLampPostDevice(int type,Long deviceId,int brightness)
    {
    	if(type==1)
    	{
    		jdbcDaoImp.executeSql("UPDATE  lamp_post_device  SET switch_status=?  WHERE device_id IN( SELECT `id` FROM  deviceinformation  WHERE parent_id=?)", new Object[] {1,deviceId});
    	}
    	else if(type==2)
    	{
    		jdbcDaoImp.executeSql("UPDATE  lamp_post_device  SET switch_status=?  WHERE device_id IN( SELECT `id` FROM  deviceinformation  WHERE parent_id=?)", new Object[] {0,deviceId});
    	}
    	else if(type==3)
    	{
    		jdbcDaoImp.executeSql("UPDATE  lamp_post_device  SET dimming_status=?  WHERE device_id =? ", new Object[] {1,deviceId});
    	}
    	else if(type==4)
    	{
    		jdbcDaoImp.executeSql("UPDATE  lamp_post_device  SET dimming_status=?  WHERE device_id=? ", new Object[] {0,deviceId});
    	}
    	
    	else if(type==5)
    	{
    		jdbcDaoImp.executeSql("UPDATE  lamp_post_device  SET switch_status=?,brightness=?  WHERE device_id IN( SELECT `id` FROM  deviceinformation  WHERE parent_id=?)", new Object[] {1,brightness,deviceId});
    	}
    	return true;
    }


    public PageListData<DeviceAssetVo> findLampPostDeviceList(BasePageQueryBean queryBean, String deviceName, String areaCode, String area,Long deviceGroupId, String groupName) throws Exception{
        int pageNo= queryBean.getPage() > 0 ? queryBean.getPage() : 0;
        int pageSize= queryBean.getPageSize() > 0 ? queryBean.getPageSize() : 10;
        // 当前角色可查看的设备列表
        List<Deviceinformation> deviceList = devicePermissionService.getDeviceList(null);
        if (deviceList == null || deviceList.isEmpty())
            return null;

        List<Long> deviceIds = deviceList.stream().map(Deviceinformation::getId).distinct().collect(Collectors.toList());

        Map<String, Object> args  = new HashMap<>();

        String sql = "SELECT d.id as id,d.id as deviceId,d.device_name as deviceName,d.device_type_id as deviceTypeId," +
                "t.device_type_name as deviceTypeName,d.area as area,a.`code` as areaCode,a.`name` as areaName," +
                "group_concat(re.device_group_id) as deviceGroupId,group_concat(g.`name`) as groupName,d.device_code as deviceCode, " +
                "d.cid as cid,d.appcode as appcode "+
                "FROM deviceinformation d\n" +
                "LEFT JOIN lamp_post_device l ON l.device_id = d.id \n" +
                "LEFT JOIN device_type t ON t.id = d.device_type_id\n" +
                "LEFT JOIN area a ON a.id = d.area_id\n" +
                "LEFT JOIN device_group_relation re ON re.device_id = d.id\n" +
                "LEFT JOIN device_group g on g.id = re.device_group_id\n" +
                "WHERE d.device_type_id in (1,11) \n";
        if (StringUtils.isNotEmpty(deviceName)) {
            sql += " and d.device_name like :deviceName ";
            args.put("deviceName", "%"+deviceName+"%");
        }
        if (StringUtils.isNotEmpty(areaCode)) {
            sql += " and (a.parent_code_arr like :areaCodes OR a.`code` = :areaCode ) ";
            args.put("areaCodes", "%"+areaCode+"%");
            args.put("areaCode", areaCode);
        }
        if (StringUtils.isNotEmpty(area)) {
            sql += " and d.area like :area ";
            args.put("area", "%"+area+"%");
        }
        if (deviceGroupId != null) {
            sql += " and g.id = :deviceGroupId ";
            args.put("deviceGroupId", deviceGroupId);
        }
        if (StringUtils.isNotEmpty(groupName)) {
            sql += " and g.`name` like :groupName ";
            args.put("groupName", "%"+groupName+"%");
        }
        if (deviceIds.size() > 0) {
            sql = sql + " AND d.id in (:deviceIds) ";
            args.put("deviceIds",deviceIds);
        }else {// 无设备查看权限
            sql = sql + " AND d.id = 0 ";
        }

        sql += " group by d.id";

        String sql1 = "select count(1) from (" + sql +") tt";

        int total = jdbcDaoImp.queryForInt(sql1, args);

        sql += " limit "+(pageNo - 1) * pageSize+","+ pageNo * pageSize ;

        RowMapper<DeviceAssetVo> rm = BeanPropertyRowMapper.newInstance(DeviceAssetVo.class);

        return new PageListData<>(pageSize, total, jdbcDaoImp.getListBySql(sql, rm, args));
    }

    public boolean batchUpdateLampPost(List<LampPostDeviceVo> lists){
        List<String> sqlList = new ArrayList<>();
        for (LampPostDeviceVo vo : lists){
            String sql = "update lamp_post_device set  is_own = "+vo.getIsOwn()+",is_lease="+vo.getIsLease()+",lessor='"+(StringUtils.isNotEmpty(vo.getLessor()) ? vo.getLessor(): "")+"' where device_id = " + vo.getDeviceId();
            sqlList.add(sql);
        }
        if (sqlList.size() > 0) {
            return jdbcDaoImp.batchExecuteSql(sqlList);
        }
        return true;
    }

    public String batchImportUpdateLampPost(List<Object> list){
        String sql = "update deviceinformation d " +
                " left join deviceinformation d1 ON d.parent_id = d1.id " +
                " LEFT JOIN lamp_post_device l ON l.device_id = d.id " +
                " set l.is_own = "+("是".equals(list.get(2).toString()) ? 1 : 0)+
                ",l.is_lease="+("是".equals(list.get(3).toString()) ? 1 : 0)+
                ",l.lessor= '"+(StringUtils.isNotEmpty(list.get(4).toString()) ? list.get(4).toString() : "") +
                "' where d.device_name = '"+list.get(1).toString()+"' and d1.device_name = '" + list.get(0).toString()+"'";
        return sql;
    }

    public LampPostDevice insertItem(Deviceinformation deviceinformation){
        LampPostDevice lampPostDevice = new LampPostDevice();
        lampPostDevice.setDeviceId(deviceinformation.getId());
        lampPostDevice.setDeviceTypeId(deviceinformation.getDeviceTypeId());
        return super.insertItem(lampPostDevice);
    }
}
