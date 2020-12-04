package com.cityiot.guanxin.warning.service;

import java.util.*;

import com.cityiot.guanxin.common.service.JDBCDaoImp;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cityiot.guanxin.warning.entity.DeviceWarningMsg;
import com.cityiot.guanxin.warning.entity.QDeviceWarningMsg;
import com.cityiot.guanxin.warning.repository.DeviceWarningRepository;

import swallow.framework.service.BaseService;

@Service
public class DeviceWarningService  extends BaseService<DeviceWarningRepository, DeviceWarningMsg> {

	@Autowired
	private JDBCDaoImp jdbcDaoImp;

	/**
	 * 设备监控大屏统计查询
	 * @return
	 */
	public Map<String,Object> getIndexDeviceWarningStatistics(){
		
		return this.getRepsitory().getIndexDeviceWarningStatistics();
		
	}
	
	/**
	 * 移动APP告警管理统计
	 * @return
	 */
	public Map<String,Object> getAppDeviceWarningStatistics(){
		
		return this.getRepsitory().getAppDeviceWarningStatistics();
	}
	
	
	public List<DeviceWarningMsg> getDeviceWarningMsgByState(Integer state)
	{
		
		QDeviceWarningMsg qDeviceWarningMsg=QDeviceWarningMsg.deviceWarningMsg;
		
		List<DeviceWarningMsg> list= this.getAllItems(query ->{
			return query.where(qDeviceWarningMsg.state.eq(state));
	    });
		
		
//		List<DeviceWarningMsg> list= this.getAllItems(query -> query );
//		
		//query -> query
		
		return list;
	}
	
	public List<Map<String, Object>> getWarningEvelStatByYear(String year){
		List<Object> objs = new ArrayList<>();
		String sql = "SELECT\n" +
				"\twarning_evel as warningEvel,\n" +
				"\tCOUNT(1) as count\n" +
				"\tFROM\n" +
				"\tdevice_warning_msg\n" +
				"\tWHERE 1=1 \n";
		if (StringUtils.isNotEmpty(year)) {
			sql += "and DATE_FORMAT(warning_time, '%Y') = ?\n";
			objs.add(year);
		}
		sql += "\tGROUP BY\n" +
				"\twarning_evel ";
		return jdbcDaoImp.queryForMap(sql, objs.toArray());
	}


	public List<Map<String, Object>> getWarningTypeStatByYear(String year){
		List<Object> objs = new ArrayList<>();
		String sql = "SELECT\n" +
				"\twarning_type as warningType,\n" +
				"\tCOUNT(1) as count\n" +
				"\tFROM\n" +
				"\tdevice_warning_msg\n" +
				"\tWHERE 1=1 \n";
		if (StringUtils.isNotEmpty(year)) {
			sql += "and DATE_FORMAT(warning_time, '%Y') = ?\n";
			objs.add(year);
		}
		sql += "\tGROUP BY\n" +
				"\twarning_type ";
		return jdbcDaoImp.queryForMap(sql, objs.toArray());
	}


	public long getWarningCountByDeviceId(Long deviceId){
		return this.getRepsitory().count(QDeviceWarningMsg.deviceWarningMsg.deviceId.eq(deviceId)
				.and(QDeviceWarningMsg.deviceWarningMsg.state.eq(1)));
	}

	public List<Map<String, Object>> getWarningMsgStatByYearAndCode(String year, String[] codes){
		Map<String, Object> args  = new HashMap<>();
		String sql = "SELECT\n" +
				"\tCOUNT(1) as num,\n" +
				"\ta.id as id,\n" +
				"\ta.`code` as code,\n" +
				"\tDATE_FORMAT(m.warning_time, '%Y') as year,\n" +
				"\ta.alarm_level as alarmLevel,\n" +
				"\ta.alarm_type as alarmType\n" +
				" FROM\n" +
				"\tdevice_warning_msg m " +
				" LEFT JOIN alarm_rule a ON m.rule_id = a.id " +
				" where 1=1 ";
		if (StringUtils.isNotEmpty(year)) {
			sql += "and DATE_FORMAT(warning_time, '%Y') = :year \n";
			args.put("year", year);
		}
		if (codes != null && codes.length > 0) {
			sql += "and a.`code` in (:codes)\n";
			args.put("codes",Arrays.asList(codes));
		}
		sql += "\tGROUP BY\n" +
				"\ta.`code`,\n" +
				"\ta.alarm_level,\n" +
				"\ta.alarm_type,\n" +
				"\tDATE_FORMAT(m.warning_time, '%Y') ";
		return jdbcDaoImp.queryForMaps(sql, args);
	}
}
