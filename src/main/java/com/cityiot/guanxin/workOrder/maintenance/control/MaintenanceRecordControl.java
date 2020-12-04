package com.cityiot.guanxin.workOrder.maintenance.control;


import com.cityiot.guanxin.deviceInformation.entity.DeviceModel;
import com.cityiot.guanxin.deviceInformation.entity.Deviceinformation;
import com.cityiot.guanxin.log.annotation.ViLog;
import com.cityiot.guanxin.workOrder.entity.DeviceChart.DeviceChart;
import com.cityiot.guanxin.workOrder.entity.DeviceChart.DeviceChartTime;
import com.cityiot.guanxin.workOrder.inspection.entity.InspectionRecord;
import com.cityiot.guanxin.workOrder.maintenance.entity.Vo.MaintenanceVo;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.jpaquery.repository.annotations.FieldPath;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

import com.cityiot.guanxin.workOrder.maintenance.entity.MaintenanceRecord;
import com.cityiot.guanxin.workOrder.maintenance.service.MaintenanceRecordService;

import java.util.Date;
import java.util.Map;

/**
 * 设备保养记录数据访问API接口
 * @author aohanhe
 *
 */
@RestController
@Api(tags = "设备保养记录数据访问API接口")
@RequestMapping("/api/maintenanceRecord")
public class MaintenanceRecordControl {
	
	private static final Logger log = LoggerFactory.getLogger(MaintenanceRecordControl.class);

	@Autowired
	private MaintenanceRecordService service;
	
	/**
	 * 查询对象
	 * @author aohanhe
	 *
	 */
	@ApiModel(value="设备保养记录查询对象")
	@JoinEntity(fieldType = Long.class ,mainFiledName = "deviceModelId" , mainEnityClass = Deviceinformation.class,
			name = "id", joinEntityClass = DeviceModel.class)
	static class QueryBean extends BasePageQueryBean{

		@JoinEntity(name = "id",joinEntityClass = Deviceinformation.class)
		private Long deviceId;

		// 设备型号
		@FieldPath(name = "deviceModelId",joinEntityClass = Deviceinformation.class)
		private Long deviceModelId;

		// 设备类型
		@FieldPath(name = "deviceBrandId",joinEntityClass = DeviceModel.class)
		private Long deviceBrandId;

		// 设备品牌
		@FieldPath(name = "deviceTypeId",joinEntityClass = DeviceModel.class)
		private Long deviceTypeId;

		// 保养状态
		private Integer maintainStatus;

		@CnName("处理时间")
		@ApiModelProperty(value="处理时间",name="operatorTime",example="")
		private Date operatorTime;

		@CnName("修改时间")
		@ApiModelProperty(value="修改时间",name="lastmodi",example="")
		private Date lastmodi;

		public Integer getMaintainStatus() {
			return maintainStatus;
		}

		public void setMaintainStatus(Integer maintainStatus) {
			this.maintainStatus = maintainStatus;
		}

		public Long getDeviceId() {
			return deviceId;
		}

		public void setDeviceId(Long deviceId) {
			this.deviceId = deviceId;
		}

		public Long getDeviceModelId() {
			return deviceModelId;
		}

		public void setDeviceModelId(Long deviceModelId) {
			this.deviceModelId = deviceModelId;
		}

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

		public Date getOperatorTime() {
			return operatorTime;
		}

		public void setOperatorTime(Date operatorTime) {
			this.operatorTime = operatorTime;
		}

		public Date getLastmodi() {
			return lastmodi;
		}

		public void setLastmodi(Date lastmodi) {
			this.lastmodi = lastmodi;
		}
	}
	
	/**
	 * 新增一个新的设备保养记录对象
	 * @param item
	 * @return
	 */
	@RequiresPermissions(value={"004004001","SUPERADMIN"}, logical= Logical.OR)
	@ApiOperation(value = "新增一个新的设备保养记录对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "新增一个新的设备保养记录对象", operType =1)//日志记录
	@SuppressWarnings("unchecked")
	@PutMapping()
	public ApiResult<MaintenanceRecord> saveNewMaintenanceRecord(@RequestBody MaintenanceVo item) {
		try {
			
			return ApiResult.success(service.insertItem(item.getMaintenanceRecord(),item.getMaintenancePictures()));
		} catch (SwallowException e) {
			log.error("新增设备保养记录对象出错:"+e.getMessage(),e);
			return ApiResult.fail("新增工程对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 删除设备保养记录对象
	 * @param item
	 * @return
	 */
	@RequiresPermissions(value={"004004002","SUPERADMIN"}, logical= Logical.OR)
	 @ApiOperation(value = " 删除设备保养记录对象")
	 @ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "ids", value = "设备保养记录ID", required = true,allowMultiple=true, dataType = "Long")
	  		})
	@ViLog(operEvent = "删除设备保养记录对象", operType =3)//日志记录
	@DeleteMapping
	public BaseApiResult deleteMaintenanceRecord(@RequestBody long []ids) {
		try {
			for(var id:ids)
				service.deleteItemById(id);
			return BaseApiResult.successResult();
		} catch (Exception e) {
			log.error("删除设备保养记录对象出错:"+e.getMessage(),e);
			return BaseApiResult.failResult(500,"删除设备保养记录对象出错:"+e.getMessage());
		}		
	}
	
	/**
	 * 修改设备保养记录对象
	 * @param item
	 * @return
	 */
	@RequiresPermissions(value={"004004003","SUPERADMIN"}, logical= Logical.OR)
	@ApiOperation(value = "修改设备保养记录对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "修改设备保养记录对象", operType =2)//日志记录
	@PostMapping
	@SuppressWarnings("unchecked")
	public ApiResult<MaintenanceRecord> saveMaintenanceRecord(@RequestBody MaintenanceVo item){
		try {
			return ApiResult.success(service.updateItem(item.getMaintenanceRecord()
					,item.getMaintenancePictures()
					,item.getDeleteMaintenancePictures()
			));
		} catch (SwallowException e) {
			log.error("修改设备保养记录对象出错:"+e.getMessage(),e);
			return ApiResult.fail("修改设备保养记录对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 通过查询bean进行分页查询数据
	 * @param query
	 * @return
	 */
	@RequiresPermissions(value={"004004004","SUPERADMIN"}, logical= Logical.OR)
	@ApiOperation(value = "通过查询bean进行分页查询数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@PostMapping("listpage")
	@SuppressWarnings("unchecked")
	public ApiResult<PageListData<MaintenanceRecord>> query(@RequestBody  QueryBean query){
		try {			
			return ApiResult.success(service.getAllItemPageByQuerybean(query));
		} catch (Exception e) {
			log.error("查询设备保养记录对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询设备保养记录对象出错:"+e.getMessage());
		}
	}
	
	 @ApiOperation(value = "根据id取设备保养记录信息")
	 @ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "id", value = "设备保养记录Id", required = true, dataType = "long")
	  		})
	@RequestMapping("{id}")
	@SuppressWarnings("unchecked")
	public ApiResult<MaintenanceRecord> getItemById(@PathVariable long id){
		try {			
			return ApiResult.success(service.getItemById(id));
		} catch (Exception e) {
			log.error("查询代码模板对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询代码模板对象出错:"+e.getMessage());
		}
	}

	@ApiOperation(value = "修改设备保养记录转换工单")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "修改设备保养记录转换工单", operType =2)//日志记录
	@RequestMapping("/ConversionWorkOrder")
	@SuppressWarnings("unchecked")
	public ApiResult<MaintenanceRecord> ConversionWorkOrder(@RequestBody MaintenanceRecord item){
		try {
			return ApiResult.success(service.ConversionWorkOrder(item));
		} catch (Exception e) {
			log.error("转换工单出错:"+e.getMessage(),e);
			return ApiResult.fail("转换工单出错:"+e.getMessage());
		}
	}

	

}
