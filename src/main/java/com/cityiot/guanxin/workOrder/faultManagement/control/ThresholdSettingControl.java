package com.cityiot.guanxin.workOrder.faultManagement.control;


import com.cityiot.guanxin.deviceInformation.entity.DeviceBrand;
import com.cityiot.guanxin.deviceInformation.entity.DeviceModel;
import com.cityiot.guanxin.deviceInformation.entity.DeviceType;
import com.cityiot.guanxin.log.annotation.ViLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import swallow.framework.exception.SwallowException;
import swallow.framework.jpaquery.repository.annotations.FieldPath;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

import com.cityiot.guanxin.workOrder.faultManagement.entity.ThresholdSetting;
import com.cityiot.guanxin.workOrder.faultManagement.service.ThresholdSettingService;

import io.swagger.annotations.Api;

/**
 * 阈值设定数据访问API接口
 * @author aohanhe
 *
 */
@RestController
@Api(tags = "阈值设定数据访问API接口")
@RequestMapping("/api/thresholdSetting")
public class ThresholdSettingControl {
	
	private static final Logger log = LoggerFactory.getLogger(ThresholdSettingControl.class);

	@Autowired
	private ThresholdSettingService service;
	
	/**
	 * 查询对象
	 * @author aohanhe
	 *
	 */
	static class QueryBean extends BasePageQueryBean{

		// 设备型号
		@JoinEntity(name = "id",joinEntityClass = DeviceModel.class)
		private Long deviceModelId;

		// 设备类型
		@FieldPath(name = "deviceBrandId",joinEntityClass = DeviceModel.class)
		private Long deviceBrandId;

		// 设备品牌
		@FieldPath(name = "deviceTypeId",joinEntityClass = DeviceModel.class)
		private Long deviceTypeId;


		public Long getDeviceBrandId() {
			return deviceBrandId;
		}

		public void setDeviceBrandId(Long deviceBrandId) {
			this.deviceBrandId = deviceBrandId;
		}

		public Long getDeviceTypeId() {
			return deviceTypeId;
		}

		public void setDeviceTypeId(Long deviceTypeId) {
			this.deviceTypeId = deviceTypeId;
		}

		public Long getDeviceModelId() {
			return deviceModelId;
		}

		public void setDeviceModelId(Long deviceModelId) {
			this.deviceModelId = deviceModelId;
		}
	}
	
	/**
	 * 新增一个新的阈值设定对象
	 * @param item
	 * @return
	 */
	@ViLog(operEvent = "新增一个新的阈值设定对象", operType =1)//日志记录
	@SuppressWarnings("unchecked")
	@PutMapping()
	public ApiResult<ThresholdSetting> saveNewThresholdSetting(@RequestBody ThresholdSetting item) {
		try {
			
			return ApiResult.success(service.insertItem(item));
		} catch (SwallowException e) {
			log.error("新增阈值设定对象出错:"+e.getMessage(),e);
			return ApiResult.fail("新增工程对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 删除阈值设定对象
	 * @param item
	 * @return
	 */
	@ViLog(operEvent = "删除阈值设定对象", operType =3)//日志记录
	@DeleteMapping
	public BaseApiResult deleteThresholdSetting(@RequestBody long []ids) {
		try {
			for(var id:ids)
				service.deleteItemById(id);
			return BaseApiResult.successResult();
		} catch (Exception e) {
			log.error("删除阈值设定对象出错:"+e.getMessage(),e);
			return BaseApiResult.failResult(500,"删除阈值设定对象出错:"+e.getMessage());
		}		
	}
	
	/**
	 * 修改阈值设定对象
	 * @param item
	 * @return
	 */
	@ViLog(operEvent = "修改阈值设定对象", operType =2)//日志记录
	@PostMapping
	@SuppressWarnings("unchecked")
	public ApiResult<ThresholdSetting> saveThresholdSetting(@RequestBody ThresholdSetting item){
		try {
			return ApiResult.success(service.updateItem(item));
		} catch (SwallowException e) {
			log.error("修改阈值设定对象出错:"+e.getMessage(),e);
			return ApiResult.fail("修改阈值设定对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 通过查询bean进行分页查询数据
	 * @param query
	 * @return
	 */
	@PostMapping("listpage")
	@SuppressWarnings("unchecked")
	public ApiResult<PageListData<ThresholdSetting>> query(@RequestBody  QueryBean query){
		try {			
			return ApiResult.success(service.getAllItemPageByQuerybean(query));
		} catch (Exception e) {
			log.error("查询阈值设定对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询阈值设定对象出错:"+e.getMessage());
		}
	}
	
	@RequestMapping("{id}")
	@SuppressWarnings("unchecked")
	public ApiResult<ThresholdSetting> getItemById(@PathVariable long id){
		try {			
			return ApiResult.success(service.getItemById(id));
		} catch (Exception e) {
			log.error("查询代码模板对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询代码模板对象出错:"+e.getMessage());
		}
	}
	

}
