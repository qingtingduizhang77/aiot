package com.cityiot.guanxin.workOrder.control;


import com.cityiot.guanxin.workOrder.entity.DeviceChart.DeviceChart;
import com.cityiot.guanxin.workOrder.entity.DeviceChart.DeviceChartTime;
import com.cityiot.guanxin.workOrder.entity.DeviceChart.service.DeviceChartService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BasePageQueryBean;

/**
 * 设备日志数据访问API接口
 * @author aohanhe
 *
 */
@RestController
@Api(tags = "设备日志数据访问API接口")
@RequestMapping("/api/deviceEchart")
public class DeviceChartControl {
	
	private static final Logger log = LoggerFactory.getLogger(DeviceChartControl.class);

	@Autowired
	private DeviceChartService service;
	
	/**
	 * 查询对象
	 * @author aohanhe
	 *
	 */
	static class QueryBean extends BasePageQueryBean{

	}
//
//	/**
//	 * 新增一个新的设备日志对象
//	 * @param item
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	@PutMapping()
//	public ApiResult<DeviceLog> saveNewDeviceLog(@RequestBody DeviceLog item) {
//		try {
//
//			return ApiResult.success(service.insertItem(item));
//		} catch (SwallowException e) {
//			log.error("新增设备日志对象出错:"+e.getMessage(),e);
//			return ApiResult.fail("新增工程对象出错:"+e.getMessage());
//		}
//	}
//
//	/**
//	 * 删除设备日志对象
//	 * @param item
//	 * @return
//	 */
//	@DeleteMapping
//	public BaseApiResult deleteDeviceLog(@RequestBody long []ids) {
//		try {
//			for(var id:ids)
//				service.deleteItemById(id);
//			return BaseApiResult.successResult();
//		} catch (Exception e) {
//			log.error("删除设备日志对象出错:"+e.getMessage(),e);
//			return BaseApiResult.failResult(500,"删除设备日志对象出错:"+e.getMessage());
//		}
//	}
//
//	/**
//	 * 修改设备日志对象
//	 * @param item
//	 * @return
//	 */
//	@PostMapping
//	@SuppressWarnings("unchecked")
//	public ApiResult<DeviceLog> saveDeviceLog(@RequestBody DeviceLog item){
//		try {
//			return ApiResult.success(service.updateItem(item));
//		} catch (SwallowException e) {
//			log.error("修改设备日志对象出错:"+e.getMessage(),e);
//			return ApiResult.fail("修改设备日志对象出错:"+e.getMessage());
//		}
//	}
//
//	/**
//	 * 通过查询bean进行分页查询数据
//	 * @param query
//	 * @return
//	 */
//	@PostMapping("listpage")
//	@SuppressWarnings("unchecked")
//	public ApiResult<PageListData<DeviceLog>> query(@RequestBody  QueryBean query){
//		try {
//			return ApiResult.success(service.getAllItemPageByQuerybean(query));
//		} catch (Exception e) {
//			log.error("查询设备日志对象出错:"+e.getMessage(),e);
//			return ApiResult.fail("查询设备日志对象出错:"+e.getMessage());
//		}
//	}
//
//	@RequestMapping("{id}")
//	@SuppressWarnings("unchecked")
//	public ApiResult<DeviceLog> getItemById(@PathVariable long id){
//		try {
//			return ApiResult.success(service.getItemById(id));
//		} catch (Exception e) {
//			log.error("查询代码模板对象出错:"+e.getMessage(),e);
//			return ApiResult.fail("查询代码模板对象出错:"+e.getMessage());
//		}
//	}

	  @ApiOperation(value = "查询设备日志")
	  	@ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "deviceChartTime", value = "日期", required = true, dataType = "DeviceChartTime"),
	  		@ApiImplicitParam(name = "type", value = "设备类型", required = true,dataType = "Long")
	  		})
	@RequestMapping("chart/{type}")
	@SuppressWarnings("unchecked")
	public ApiResult<DeviceChart> deviceChart(@RequestBody DeviceChartTime deviceChartTime, @PathVariable long type){
		try {
			return ApiResult.success(service.deviceChart(deviceChartTime.getStartTime(),deviceChartTime.getEndTime(),type));
		} catch (Exception e) {
			log.error("转换工单出错:"+e.getMessage(),e);
			return ApiResult.fail("转换工单出错:"+e.getMessage());
		}
	}

}
