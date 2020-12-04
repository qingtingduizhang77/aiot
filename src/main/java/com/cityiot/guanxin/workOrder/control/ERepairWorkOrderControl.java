package com.cityiot.guanxin.workOrder.control;


import com.cityiot.guanxin.deviceInformation.entity.DeviceModel;
import com.cityiot.guanxin.deviceInformation.entity.Deviceinformation;
import com.cityiot.guanxin.log.annotation.ViLog;
import com.cityiot.guanxin.workOrder.entity.Vo.RepairVo;
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

import com.cityiot.guanxin.workOrder.entity.ERepairWorkOrder;
import com.cityiot.guanxin.workOrder.service.ERepairWorkOrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import java.util.Date;

/**
 * 维修工单数据访问API接口
 * @author aohanhe
 *
 */
@RestController
@Api(tags = "维修工单数据访问API接口")
@RequestMapping("/api/eRepairWorkOrder")
public class ERepairWorkOrderControl {
	
	private static final Logger log = LoggerFactory.getLogger(ERepairWorkOrderControl.class);

	@Autowired
	private ERepairWorkOrderService service;
	
	/**
	 * 查询对象
	 * @author aohanhe
	 *
	 */
	@ApiModel(value="维修工单查询对象")
	@JoinEntity(fieldType = Long.class ,mainFiledName = "deviceModelId" , mainEnityClass = Deviceinformation.class,
			name = "id", joinEntityClass = DeviceModel.class)
	static class QueryBean extends BasePageQueryBean{
		
		@ApiModelProperty(value="设备信息id",name="deviceInfoId",example="")
		@CnName("设备信息id")
		@JoinEntity(name = "id",joinEntityClass = Deviceinformation.class)
		private Long deviceInfoId;


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

		// 设备型号
		@ApiModelProperty(value="设备型号",name="deviceModel",example="")
		@Like
		private String deviceModel;

		// 设备品牌
		@ApiModelProperty(value="设备品牌",name="deviceBrand",example="")
		@Like
		private String deviceBrand;

		// 设备类型
		@ApiModelProperty(value="角色名称",name="deviceType",example="")
		@Like
		private String deviceType;

		// 工单编号
		@ApiModelProperty(value="工单编号",name="deviceType",example="")
		@Like
		private String code;

		@ApiModelProperty(value="修改时间",name="lastmodi",example="")
		private Date lastmodi;

		@ApiModelProperty(value="处理时间时间",name="handleTime",example="")
		private Date handleTime;

		@CnName("处理状态")
		@ApiModelProperty(value="处理状态",name="lastmodi",example="")
		private Integer handleStatus;

		public Long getDeviceInfoId() {
			return deviceInfoId;
		}

		public void setDeviceInfoId(Long deviceInfoId) {
			this.deviceInfoId = deviceInfoId;
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

		public String getDeviceModel() {
			return deviceModel;
		}

		public void setDeviceModel(String deviceModel) {
			this.deviceModel = deviceModel;
		}
		public String getDeviceBrand() {
			return deviceBrand;
		}

		public void setDeviceBrand(String deviceBrand) {
			this.deviceBrand = deviceBrand;
		}
		public String getDeviceType() {
			return deviceType;
		}

		public void setDeviceType(String deviceType) {
			this.deviceType = deviceType;
		}

		public Date getLastmodi() {
			return lastmodi;
		}

		public void setLastmodi(Date lastmodi) {
			this.lastmodi = lastmodi;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public Date getHandleTime() {
			return handleTime;
		}

		public void setHandleTime(Date handleTime) {
			this.handleTime = handleTime;
		}

		public Integer getHandleStatus() {
			return handleStatus;
		}

		public void setHandleStatus(Integer handleStatus) {
			this.handleStatus = handleStatus;
		}
	}
	
	/**
	 * 新增一个新的维修工单对象
	 * @param item
	 * @return
	 */
	@RequiresPermissions(value={"004002001","SUPERADMIN"}, logical= Logical.OR)
	@ApiOperation(value = "新增一个新的维修工单对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "新增一个新的维修工单对象", operType =1)//日志记录
	@SuppressWarnings("unchecked")
	@PutMapping()
	public ApiResult<ERepairWorkOrder> saveNewERepairWorkOrder(@RequestBody RepairVo item) {
		try {
			
			return ApiResult.success(service.insertItem(item.getEntity(),item.getImages(), item.getIsProcess()));
		} catch (SwallowException e) {
			log.error("新增维修工单对象出错:"+e.getMessage(),e);
			return ApiResult.fail("新增工程对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 删除维修工单对象
	 * @return
	 */
	@RequiresPermissions(value={"004002002","SUPERADMIN"}, logical= Logical.OR)
	@ApiOperation(value = "删除维修工单对象")
	@ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "ids", value = "维修工单ID", required = true,allowMultiple=true, dataType = "Long")
	  		})
	@ViLog(operEvent = "删除维修工单对象", operType =3)//日志记录
	@DeleteMapping
	public BaseApiResult deleteERepairWorkOrder(@RequestBody long []ids) {
		try {
				service.deleteItemByIds(ids);
			return BaseApiResult.successResult();
		} catch (Exception e) {
			log.error("删除维修工单对象出错:"+e.getMessage(),e);
			return BaseApiResult.failResult(500,"删除维修工单对象出错:"+e.getMessage());
		}		
	}
	
	/**
	 * 修改维修工单对象
	 * @param item
	 * @return
	 */
	@RequiresPermissions(value={"004002003","SUPERADMIN"}, logical= Logical.OR)
	@ApiOperation(value = "修改维修工单对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "修改维修工单对象", operType =2)//日志记录
	@PostMapping
	@SuppressWarnings("unchecked")
	public ApiResult<ERepairWorkOrder> saveERepairWorkOrder(@RequestBody RepairVo item){
		try {
			return ApiResult.success(service.updateItem(item.getEntity(),item.getImages(),item.getDeleteImages(),item.getType(),item.getIsProcess()));
		} catch (SwallowException e) {
			log.error("修改维修工单对象出错:"+e.getMessage(),e);
			return ApiResult.fail("修改维修工单对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 通过查询bean进行分页查询数据
	 * @param query
	 * @return
	 */
	@RequiresPermissions(value={"004002004","SUPERADMIN"}, logical= Logical.OR)
	@ApiOperation(value = "通过查询bean进行分页查询数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@PostMapping("listpage")
	@SuppressWarnings("unchecked")
	public ApiResult<PageListData<ERepairWorkOrder>> query(@RequestBody  QueryBean query){
		try {			
			return ApiResult.success(service.getAllItemPageByQuerybean(query));
		} catch (Exception e) {
			log.error("查询维修工单对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询维修工单对象出错:"+e.getMessage());
		}
	}
	
	@ApiOperation(value = "根据id取维修工单")
	@ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "id", value = "维修工单Id", required = true, dataType = "long")
	  		})
	@RequestMapping("{id}")
	@SuppressWarnings("unchecked")
	public ApiResult<ERepairWorkOrder> getItemById(@PathVariable long id){
		try {			
			return ApiResult.success(service.getItemById(id));
		} catch (Exception e) {
			log.error("查询代码模板对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询代码模板对象出错:"+e.getMessage());
		}
	}

    /**
     * 更新维修工单状态
     * @param item
     * @return
     */
	@ApiOperation(value = " 更新维修工单状态")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "更新维修工单状态", operType =2)//日志记录
    @SuppressWarnings("unchecked")
    @PostMapping(value = "updateWorkOrderStatus")
    public ApiResult<ERepairWorkOrder> updateWorkOrderStatus(ERepairWorkOrder item) {
        try {
            return ApiResult.success(service.updateWorkOrderStatus(item));
        } catch (SwallowException e) {
            log.error("维修工单流程处理出错:"+e.getMessage(),e);
            return ApiResult.fail("维修工单流程处理出错:"+e.getMessage());
        }
    }
}
