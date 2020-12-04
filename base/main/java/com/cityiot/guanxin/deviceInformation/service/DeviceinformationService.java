package com.cityiot.guanxin.deviceInformation.service;


import com.cityiot.guanxin.area.entity.Area;
import com.cityiot.guanxin.area.service.AreaService;
import com.cityiot.guanxin.common.repository.CommonRepository;
import com.cityiot.guanxin.common.service.JDBCDaoImp;
import com.cityiot.guanxin.deviceInformation.entity.DeviceModel;
import com.cityiot.guanxin.deviceInformation.entity.Deviceinformation;
import com.cityiot.guanxin.deviceInformation.entity.QDeviceType;
import com.cityiot.guanxin.deviceInformation.entity.QDeviceinformation;
import com.cityiot.guanxin.deviceInformation.repository.DeviceinformationRepository;
import com.cityiot.guanxin.lampPostDevice.service.LampPostDeviceService;
import com.cityiot.guanxin.monitorDevice.vo.ChargingPileVo;
import com.cityiot.guanxin.monitorDevice.vo.DustbinDeviceVo;
import com.cityiot.guanxin.monitorDevice.vo.LampDeviceVo;
import com.cityiot.guanxin.monitorDevice.vo.VideoDeviceVo;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Deviceinformation 数据Service
 * @author aohanhe
 *
 */
@Service
public class DeviceinformationService extends BaseService<DeviceinformationRepository, Deviceinformation>{
	// 日志对象
	private static final Logger log = LoggerFactory.getLogger(DeviceinformationService.class);

	@Autowired
	private DeviceinformationRepository repository;
	@Autowired
	private DeviceModelService deviceModelService;
	@Autowired
	private CommonRepository commonRepository;
	@Autowired
	private AreaService areaService;
	@Autowired
    private MongoTemplate mongoTemplate;
	@Autowired
	private JDBCDaoImp jdbcDaoImp;
	@Autowired
	private LampPostDeviceService lampPostDeviceService;
	
	public String getLampStatus(String cid,String appcode) {
		
		 if(cid!=null && !cid.equals(""))
		 {
		
		    Query query = new Query();
    	    query.addCriteria(Criteria.where("c_ID").is(new Integer(cid)).and("appcode").is(appcode));
    	    Map<String,Object> remap= mongoTemplate.findOne(query, Map.class, "deviceStatus");
    	    if(remap!=null)
    	    {
    		   return (String)remap.get("status");
    	    }
   	 
		 }
    	 return null;
	}
	
	public Deviceinformation getItemByDeviceCode(String keyword) {
		QDeviceinformation df = QDeviceinformation.deviceinformation;
		return this.getItem(query -> query.where(df.deviceCode.eq(keyword)));
	}

	public Map<String,Object> selectCount() {
		return repository.selectCount();
	}

	public List<LampDeviceVo> lampDetail(long deviceId, String lat, String lng) throws Exception {
		if(lat == null || lng == null){
			throw new Exception("经度或纬度不能为空!");
		}
		return repository.lampDetail(deviceId, lat, lng);
	}

	public List<VideoDeviceVo> videoDeviceDetail(long deviceId, String lat, String lng) throws Exception{
		if(lat == null || lng == null){
			throw new Exception("经度或纬度不能为空!");
		}
		return repository.videoDeviceDetail(deviceId, lat, lng);
	}

	public List<ChargingPileVo> chargingPileDetail(long deviceId, String lat, String lng) throws Exception{
		if(lat == null || lng == null){
			throw new Exception("经度或纬度不能为空!");
		}
		return repository.chargingPileDetail(deviceId, lat, lng);
	}

	public List<DustbinDeviceVo> dustbinDetail(long deviceId, String lat, String lng) throws Exception {
		if(lat == null || lng == null){
			throw new Exception("经度或纬度不能为空!");
		}
		return repository.dustbinDetail(deviceId, lat, lng);
	}

	public Deviceinformation insertItem(Deviceinformation item)  {
		// 保存前根据code查找Area的Id
		Area queryArea = areaService.getAreaByCode(item.getAreaCode());
		if(queryArea!=null){
			item.setAreaId(queryArea.getId());
		}
		DeviceModel dm = deviceModelService.getItemById(item.getDeviceModelId());
		item.setDeviceTypeId(dm.getDeviceTypeId());

		item = super.insertItem(item);

		lampPostDeviceService.insertItem(item);// 添加设备拓展属性表记录

		return item;
	}


	public Deviceinformation updateItem(Deviceinformation item)  {
		// 保存前根据code查找Area的Id
		Area queryArea = areaService.getAreaByCode(item.getAreaCode());
		if(queryArea!=null){
			item.setAreaId(queryArea.getId());
		}
		DeviceModel dm = deviceModelService.getItemById(item.getDeviceModelId());
		item.setDeviceTypeId(dm.getDeviceTypeId());

		return super.updateItem(item);
	}

	/** 根据设备名查询查询某几种类型的设备
	 * @param deviceTypeString
	 * @return
	 */
	public List<Deviceinformation> getItemsOfDeviceTypes(String deviceTypeString) {
		QDeviceinformation qDeviceinformation = QDeviceinformation.deviceinformation;
		QDeviceType qDeviceType = QDeviceType.deviceType;
		List<Deviceinformation> deviceinformationList = null;
		JPAQuery jpaQuery = commonRepository
				.getFactory()
				.from(qDeviceinformation)
				.innerJoin(qDeviceType).on(qDeviceinformation.deviceTypeId.eq(qDeviceType.id));
		Predicate predicate = null;
		String[] deviceTypes = deviceTypeString.split(",");
		for (int i=0;i<deviceTypes.length;i++) {
			String deviceType = deviceTypes[i];
			if(i==0){
				predicate = qDeviceType.deviceTypeName.eq(deviceType);
			}
			else {
				predicate = ((BooleanExpression) predicate).or(qDeviceType.deviceTypeName.eq(deviceType));
			}
		}
		jpaQuery.where(predicate);
		deviceinformationList =jpaQuery.select(qDeviceinformation)
				.fetch();
		return deviceinformationList;
	}


	public String updateParentIdSql(String deviceName, String parentDeviceName){
		String sql = "UPDATE \n" +
				"`deviceinformation` a, \n" +
				"(SELECT d.id AS id FROM `deviceinformation` d WHERE d.`device_name` = '"+parentDeviceName+"') b \n" +
				"SET a.`parent_id` = b.id  \n" +
				"WHERE a.`device_name` = '"+deviceName+"'";

		return sql;
	}

	public List<Map<String, Object>> getAreaDeviceCountList(){
		String sql = "SELECT a.`name`,COUNT(1) AS count FROM deviceinformation d\n" +
				"LEFT JOIN area a ON a.id = d.area_id\n" +
				"GROUP BY a.id\n" +
				"ORDER BY COUNT desc";
		return jdbcDaoImp.queryForMap(sql, null);
	}

	public List<Map<String, Object>> getdeviceModelIdCount (String deviceModelId){
		List<Object> objs = new ArrayList<>();
		String sql ="SELECT \n" +
                "  dm.`device_model`,\n" +
                "  di.`device_model_id`,\n" +
                "  COUNT(1) AS count\n" +
                "FROM\n" +
                "  `deviceinformation` di \n" +
                "  LEFT JOIN `device_model` dm \n" +
                "    ON dm.`id` = di.`device_model_id` ";
		if (StringUtils.isNotEmpty(deviceModelId)) {
			sql += "  WHERE di.`device_model_id` in("+deviceModelId+")\n" ;
			objs.add(deviceModelId);
		}
		sql += "GROUP BY di.`device_model_id`";
		return jdbcDaoImp.queryForMap(sql, null);
	}

	public List<Map<String, Object>> getdeviceTypeIdCount(String deviceTypeId) {
		List<Object> objs = new ArrayList<>();
		String sql ="SELECT \n" +
				"  dt.`device_type_name`,\n" +
				"  di.`device_type_id`,\n" +
				"  COUNT(1) AS count\n" +
				"FROM\n" +
				"  `deviceinformation` di \n" +
				"  LEFT JOIN `device_type` dt \n" +
				"    ON dt.`id` = di.`device_type_id` ";
		if (StringUtils.isNotEmpty(deviceTypeId)) {
			sql += "  WHERE di.`device_type_id` in("+deviceTypeId+")\n" ;
			objs.add(deviceTypeId);
		}
		sql += "GROUP BY di.`device_type_id`";
		return jdbcDaoImp.queryForMap(sql, null);

	}
}
