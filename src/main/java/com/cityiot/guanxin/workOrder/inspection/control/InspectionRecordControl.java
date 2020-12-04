package com.cityiot.guanxin.workOrder.inspection.control;


import com.cityiot.guanxin.deviceInformation.entity.DeviceModel;
import com.cityiot.guanxin.deviceInformation.entity.Deviceinformation;
import com.cityiot.guanxin.log.annotation.ViLog;
import com.cityiot.guanxin.workOrder.inspection.entity.Vo.AccessoriesVo;
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
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

import com.cityiot.guanxin.workOrder.inspection.entity.InspectionRecord;
import com.cityiot.guanxin.workOrder.inspection.service.InspectionRecordService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import java.util.Date;

/**
 * 设备巡检记录数据访问API接口
 * @author aohanhe
 *
 */
@RestController
@Api(tags = "设备巡检记录数据访问API接口")
@RequestMapping("/api/inspectionRecord")
public class InspectionRecordControl {
	
	private static final Logger log = LoggerFactory.getLogger(InspectionRecordControl.class);

	@Autowired
	private InspectionRecordService service;
	
	/**
	 * 查询对象
	 * @author aohanhe
	 *
	 */
	@ApiModel(value="设备巡检记录查询对象")
	@JoinEntity(fieldType = Long.class ,mainFiledName = "deviceModelId" , mainEnityClass = Deviceinformation.class,
			name = "id", joinEntityClass = DeviceModel.class)
	static class QueryBean extends BasePageQueryBean{
		
		@ApiModelProperty(value="设备id",name="deviceId",example="")
		@JoinEntity(name = "id",joinEntityClass = Deviceinformation.class)
		private Long deviceId;

		// 设备型号
		@ApiModelProperty(value="设备型号id",name="deviceModelId",example="")
		@FieldPath(name = "deviceModelId",joinEntityClass = Deviceinformation.class)
		private Long deviceModelId;

		// 设备类型
		@ApiModelProperty(value="设备类型id",name="deviceBrandId",example="")
		@FieldPath(name = "deviceBrandId",joinEntityClass = DeviceModel.class)
		private Long deviceBrandId;

		// 设备品牌
		@ApiModelProperty(value="设备品牌id",name="deviceTypeId",example="")
		@FieldPath(name = "deviceTypeId",joinEntityClass = DeviceModel.class)
		private Long deviceTypeId;

		@CnName("巡检时间")
		@ApiModelProperty(value="巡检时间",name="patrolTime",example="")
		private Date patrolTime;

		@CnName("修改时间")
		@ApiModelProperty(value="修改时间",name="lastmodi",example="")
		private Date lastmodi;

		@CnName("巡检状态")
		@ApiModelProperty(value="巡检状态",name="patrolStatus",example="")
		private Integer patrolStatus;

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

		public Date getPatrolTime() {
			return patrolTime;
		}

		public void setPatrolTime(Date patrolTime) {
			this.patrolTime = patrolTime;
		}

		public Date getLastmodi() {
			return lastmodi;
		}

		public void setLastmodi(Date lastmodi) {
			this.lastmodi = lastmodi;
		}

		public Integer getPatrolStatus() {
			return patrolStatus;
		}

		public void setPatrolStatus(Integer patrolStatus) {
			this.patrolStatus = patrolStatus;
		}
	}
	
	/**
	 * 新增一个新的设备巡检记录对象
	 * @param item
	 * @return
	 */
	@RequiresPermissions(value={"004006001","SUPERADMIN"}, logical= Logical.OR)
	@ApiOperation(value = "新增一个新的设备巡检记录对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "新增一个新的设备巡检记录对象", operType =1)//日志记录
	@SuppressWarnings("unchecked")
	@PutMapping()
	public ApiResult<InspectionRecord> saveNewInspectionRecord(@RequestBody AccessoriesVo item) {
		try {
			
			return ApiResult.success(service.insertItem(item.getInspectionRecord(),item.getPatrolPictures()));
		} catch (SwallowException e) {
			log.error("新增设备巡检记录对象出错:"+e.getMessage(),e);
			return ApiResult.fail("新增工程对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 删除设备巡检记录对象
	 * @param item
	 * @return
	 */
	@RequiresPermissions(value={"004006002","SUPERADMIN"}, logical= Logical.OR)
    @ApiOperation(value = "删除设备巡检记录对象")
	@ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "ids", value = "设备巡检记录ID", required = true,allowMultiple=true, dataType = "Long")
	  		})
	@ViLog(operEvent = "删除设备巡检记录对象", operType =3)//日志记录
	@DeleteMapping
	public BaseApiResult deleteInspectionRecord(@RequestBody long []ids) {
		try {
			for(var id:ids)
				service.deleteItemById(id);
			return BaseApiResult.successResult();
		} catch (Exception e) {
			log.error("删除设备巡检记录对象出错:"+e.getMessage(),e);
			return BaseApiResult.failResult(500,"删除设备巡检记录对象出错:"+e.getMessage());
		}		
	}
	
	/**
	 * 修改设备巡检记录对象
	 * @param item
	 * @return
	 */
	@RequiresPermissions(value={"004006003","SUPERADMIN"}, logical= Logical.OR)
    @ApiOperation(value = "修改设备巡检记录对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "修改设备巡检记录对象", operType =2)//日志记录
	@PostMapping
	@SuppressWarnings("unchecked")
	public ApiResult<InspectionRecord> saveInspectionRecord(@RequestBody AccessoriesVo item){
		try {
			return ApiResult.success(service.updateItem(item.getInspectionRecord()
					,item.getPatrolPictures()
					,item.getDeletepatrolPictures()
					));
		} catch (SwallowException e) {
			log.error("修改设备巡检记录对象出错:"+e.getMessage(),e);
			return ApiResult.fail("修改设备巡检记录对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 通过查询bean进行分页查询数据
	 * @param query
	 * @return
	 */
	@RequiresPermissions(value={"004006004","SUPERADMIN"}, logical= Logical.OR)
    @ApiOperation(value = "通过查询bean进行分页查询数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@PostMapping("listpage")
	@SuppressWarnings("unchecked")
	public ApiResult<PageListData<InspectionRecord>> query(@RequestBody  QueryBean query){
		try {			
			return ApiResult.success(service.getAllItemPageByQuerybean(query));
		} catch (Exception e) {
			log.error("查询设备巡检记录对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询设备巡检记录对象出错:"+e.getMessage());
		}
	}
	
    @ApiOperation(value = "根据id取设备巡检记录信息")
  	@ApiImplicitParams({
  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
  		@ApiImplicitParam(name = "id", value = "设备巡检记录Id", required = true, dataType = "long")
  		})
	@RequestMapping("{id}")
	@SuppressWarnings("unchecked")
	public ApiResult<InspectionRecord> getItemById(@PathVariable long id){
		try {			
			return ApiResult.success(service.getItemById(id));
		} catch (Exception e) {
			log.error("查询代码模板对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询代码模板对象出错:"+e.getMessage());
		}
	}

    @ApiOperation(value = "转换工单")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "修改设备巡检转换工单", operType =2)//日志记录
	@RequestMapping("/ConversionWorkOrder")
	@SuppressWarnings("unchecked")
	public ApiResult<InspectionRecord> ConversionWorkOrder(@RequestBody InspectionRecord item){
		try {
			return ApiResult.success(service.ConversionWorkOrder(item));
		} catch (Exception e) {
			log.error("转换工单出错:"+e.getMessage(),e);
			return ApiResult.fail("转换工单出错:"+e.getMessage());
		}
	}
	

}
