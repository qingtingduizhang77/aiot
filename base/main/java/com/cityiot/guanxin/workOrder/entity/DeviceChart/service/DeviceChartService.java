package com.cityiot.guanxin.workOrder.entity.DeviceChart.service;


import com.cityiot.guanxin.common.utils.DateUtil;
import com.cityiot.guanxin.workOrder.entity.*;
import com.cityiot.guanxin.workOrder.entity.DeviceChart.DeviceChart;
import com.cityiot.guanxin.workOrder.service.EInspectionWorkOrderService;
import com.cityiot.guanxin.workOrder.service.EMaintenanceWorkOrderService;
import com.cityiot.guanxin.workOrder.service.ERepairWorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class DeviceChartService {
	@Autowired
	EMaintenanceWorkOrderService maintenanceWorkOrderService;

	@Autowired
	ERepairWorkOrderService repairWorkOrderService;

	@Autowired
	EInspectionWorkOrderService inspectionWorkOrderService;





	public DeviceChart deviceChart(Date startTime,Date endTime,long type){
		Map<String,Date> dateMap = format(startTime,endTime);
		DeviceChart deviceChart = new DeviceChart();
		//保养工单
		List<EMaintenanceWorkOrder> maintenanceWorkOrders = maintenanceWorkOrderService.getAllItems(query -> {
			return query.where(QEMaintenanceWorkOrder.eMaintenanceWorkOrder.created.gt(dateMap.get("start"))
					.and(QEMaintenanceWorkOrder.eMaintenanceWorkOrder.created.lt(dateMap.get("end"))));
		});
		//维修工单
		List<ERepairWorkOrder> repairWorkOrders = repairWorkOrderService.getAllItems(query -> {
			return query.where(QERepairWorkOrder.eRepairWorkOrder.created.gt(dateMap.get("start"))
					.and(QERepairWorkOrder.eRepairWorkOrder.created.lt(dateMap.get("end"))));
		});
		//
		List<EInspectionWorkOrder> inspectionWorkOrders = inspectionWorkOrderService.getAllItems(query -> {
			return query.where(QEInspectionWorkOrder.eInspectionWorkOrder.created.gt(dateMap.get("start"))
					.and(QEInspectionWorkOrder.eInspectionWorkOrder.created.lt(dateMap.get("end"))));
		});
		// <设备类型名称,<月份,数量>>
		Map<String, Map<String, Double>> map =new HashMap<>();
		if(type ==1) {
//			for (var item : maintenanceWorkOrders) {
//				String deviceType = item.getDeviceTypeName();
//				String time = DateUtil.format(DateUtil.PATTERN_MONTH, item.getCreated());
//
//				Map<String, Double> mapkey = map.get(deviceType);
//				// 插入数据
//				if (mapkey == null) {
//					mapkey = new HashMap<>();
//					String stime = DateUtil.format(DateUtil.PATTERN_MONTH, dateMap.get("start"));
//					Date timeEnd = dateMap.get("end");
//					while (DateUtil.parse(DateUtil.PATTERN_MONTH,stime).getTime() < timeEnd.getTime()){
//						// 创建月份
//						mapkey.put(stime, 0.0);
//						Date time1 = DateUtil.add(DateUtil.parse(DateUtil.PATTERN_MONTH,stime), Calendar.MONTH, 1);
//						stime = DateUtil.format(DateUtil.PATTERN_MONTH, time1);
//					}
//
//					mapkey.put(time, 1.0);
//					map.put(deviceType, mapkey);
//				} else {
//					Double num = mapkey.get(time);
//					if (num != null) {
//						num++;
//						mapkey.put(time, num);
//					} else {
//						mapkey.put(time, 1.0);
//					}
//					map.put(deviceType, mapkey);
//				}
//			}
//			deviceChart.setChartItem(map);
		}else if(type ==2){
			for (var item : repairWorkOrders) {
				String deviceType = item.getDeviceTypeName();
				String time = DateUtil.format(DateUtil.PATTERN_MONTH, item.getCreated());

				Map<String, Double> mapkey = map.get(deviceType);
				// 插入数据
				if (mapkey == null) {
					mapkey = new HashMap<>();
					String stime = DateUtil.format(DateUtil.PATTERN_MONTH, dateMap.get("start"));
					Date timeEnd = dateMap.get("end");
					while (DateUtil.parse(DateUtil.PATTERN_MONTH,stime).getTime() < timeEnd.getTime()){
						// 创建月份
						mapkey.put(stime,  0.0);
						Date time1 = DateUtil.add(DateUtil.parse(DateUtil.PATTERN_MONTH,stime), Calendar.MONTH, 1);
						stime = DateUtil.format(DateUtil.PATTERN_MONTH, time1);
					}
					//金额
					mapkey.put(time, item.getMoney());
					map.put(deviceType, mapkey);
				} else {
					Double num = mapkey.get(time);
					if (num != null) {
						num=num+item.getMoney();
						mapkey.put(time, num);
					} else {
						mapkey.put(time, item.getMoney());
					}
					map.put(deviceType, mapkey);
				}
			}
			deviceChart.setChartItem(map);
		}else if(type ==3){
			for (var item : repairWorkOrders) {
				String deviceType = item.getDeviceTypeName();
				String time = DateUtil.format(DateUtil.PATTERN_MONTH, item.getCreated());

				Map<String, Double> mapkey = map.get(deviceType);
				// 插入数据
				if (mapkey == null) {
					mapkey = new HashMap<>();
					String stime = DateUtil.format(DateUtil.PATTERN_MONTH, dateMap.get("start"));
					Date timeEnd = dateMap.get("end");
					while (DateUtil.parse(DateUtil.PATTERN_MONTH,stime).getTime() < timeEnd.getTime()){
						// 创建月份
						mapkey.put(stime, 0.0);
						Date time1 = DateUtil.add(DateUtil.parse(DateUtil.PATTERN_MONTH,stime), Calendar.MONTH, 1);
						stime = DateUtil.format(DateUtil.PATTERN_MONTH, time1);
					}
					mapkey.put(time, 1.0);
					map.put(deviceType, mapkey);
				} else {
					Double num = mapkey.get(time);
					if (num != null) {
						num++;
						mapkey.put(time, num);
					} else {
						mapkey.put(time, 1.0);
					}
					map.put(deviceType, mapkey);
				}
			}
			deviceChart.setChartItem(map);
		}
		return deviceChart;
	}




	private Map<String,Date> format(Date startTime,Date endTime) {
		// 获取最近六个月
//		Date startTime = startTime.getStartTime();
//		Date endTime = endTime.getEndTime();
		String pattern = DateUtil.PATTERN_MONTH;
		int field = Calendar.MONTH;
		if (StringUtils.isEmpty(startTime) && StringUtils.isEmpty(endTime)) {
			startTime = DateUtil.add(new Date(), field, -6);
			endTime = DateUtil.add(new Date(), field, 0);
		}
		if (!StringUtils.isEmpty(startTime) && StringUtils.isEmpty(endTime)) {
			endTime = DateUtil.add(startTime, field, 6);
		}
		if (StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
			startTime = DateUtil.add(endTime, field, -6);
		}
		Map<String,Date> map = new HashMap<>();
		map.put("start",startTime);
		map.put("end",endTime);
		return map;
	}
	}

