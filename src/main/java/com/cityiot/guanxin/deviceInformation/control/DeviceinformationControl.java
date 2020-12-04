package com.cityiot.guanxin.deviceInformation.control;


import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cityiot.guanxin.user.entity.Userview;
import com.cityiot.guanxin.workOrder.deviceLog.service.DeviceLogService;
import com.gexin.fastjson.JSON;
import com.gexin.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cityiot.guanxin.deviceInformation.entity.DeviceBrand;
import com.cityiot.guanxin.deviceInformation.entity.DeviceModel;
import com.cityiot.guanxin.deviceInformation.entity.DeviceType;
import com.cityiot.guanxin.deviceInformation.entity.Deviceinformation;
import com.cityiot.guanxin.deviceInformation.service.DeviceinformationService;
import com.cityiot.guanxin.lampPostDevice.service.LampPostDeviceService;
import com.cityiot.guanxin.log.annotation.ViLog;
import com.cityiot.guanxin.serviceClient.ServiceClientUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import swallow.framework.exception.SwallowException;
import swallow.framework.jpaquery.repository.annotations.FieldPath;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

/**
 * 设备信息数据访问API接口
 * @author aohanhe
 *
 */
@RestController
@Api(tags = "设备信息数据访问API接口")
@RequestMapping("/api/deviceinformation")
public class DeviceinformationControl {
	
	private static final Logger log = LoggerFactory.getLogger(DeviceinformationControl.class);

	@Autowired
	private DeviceinformationService service;
	 @Autowired
	 private ServiceClientUtil serviceClientUtil;
	 
	 @Autowired
	 private LampPostDeviceService lampPostDeviceService;

	 @Autowired
	 private DeviceLogService deviceLogService;
	
	/**
	 * 查询对象
	 * @author aohanhe
	 *
	 */
	 @ApiModel(value="设备信息查询对象")
	@JoinEntity(fieldType = Long.class ,mainFiledName = "deviceBrandId" , mainEnityClass = DeviceModel.class,
			name = "id", joinEntityClass = DeviceBrand.class)
	static class QueryBean extends BasePageQueryBean{

		@ApiModelProperty(value="设备名称",name="deviceName",example="")
		@Like
		private String deviceName;

		@ApiModelProperty(value="设备编号",name="deviceCode",example="")
		private String deviceCode;

		// 设备型号ID
		@ApiModelProperty(value="设备型号ID",name="deviceModelId",example="")
		@JoinEntity(name="id",joinEntityClass = DeviceModel.class)
		private Long deviceModelId;

		@ApiModelProperty(value="设备品牌ID",name="deviceBrandId",example="")
		@FieldPath(name = "deviceBrandId",joinEntityClass = DeviceModel.class)
		private Long deviceBrandId;

		@ApiModelProperty(value="设备工厂",name="deviceManufacturer",example="")
		@FieldPath(name = "deviceManufacturer",joinEntityClass = DeviceBrand.class)
		private String deviceManufacturer;

		@ApiModelProperty(value="设备品牌名称",name="deviceBrandName",example="")
		@FieldPath(name = "deviceBrandName",joinEntityClass = DeviceBrand.class)
		private String deviceBrandName;

		@ApiModelProperty(value="设备型名称",name="deviceTypeName",example="")
		@FieldPath(name = "deviceTypeName",joinEntityClass = DeviceType.class)
		private String deviceTypeName;

//		@FieldPath(name = "deviceTypeId",joinEntityClass = DeviceModel.class)
		@ApiModelProperty(value="设备类型ID",name="deviceTypeId",example="")
		@JoinEntity(name = "id",joinEntityClass = DeviceType.class)
		private Long deviceTypeId;

		@ApiModelProperty(value="设备设备型号",name="deviceModel",example="")
		@FieldPath(name = "deviceModel",joinEntityClass = DeviceModel.class)
		private String deviceModel;

		// 地址
		@ApiModelProperty(value="地址",name="area",example="")
		@Like
		private String area;

		@ApiModelProperty(value="修改时间",name="lastmodi",example="")
		private Date lastmodi;

		public String getDeviceName() {
			return deviceName;
		}

		public void setDeviceName(String deviceName) {
			this.deviceName = deviceName;
		}

		public String getDeviceCode() {
			return deviceCode;
		}

		public void setDeviceCode(String deviceCode) {
			this.deviceCode = deviceCode;
		}

		public String getDeviceManufacturer() {
			return deviceManufacturer;
		}

		public void setDeviceManufacturer(String deviceManufacturer) {
			this.deviceManufacturer = deviceManufacturer;
		}

		public String getDeviceBrandName() {
			return deviceBrandName;
		}

		public void setDeviceBrandName(String deviceBrandName) {
			this.deviceBrandName = deviceBrandName;
		}

		public String getDeviceTypeName() {
			return deviceTypeName;
		}

		public void setDeviceTypeName(String deviceTypeName) {
			this.deviceTypeName = deviceTypeName;
		}

		public String getDeviceModel() {
			return deviceModel;
		}

		public void setDeviceModel(String deviceModel) {
			this.deviceModel = deviceModel;
		}

		public Date getLastmodi() {
			return lastmodi;
		}

		public void setLastmodi(Date lastmodi) {
			this.lastmodi = lastmodi;
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

		public String getArea() {
			return area;
		}

		public void setArea(String area) {
			this.area = area;
		}
	}
	
	/**
	 * 新增一个新的设备信息对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = "新增一个新的设备信息对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "新增一个新的设备信息对象", operType =1)//日志记录
	@SuppressWarnings("unchecked")
	@PutMapping()
	public ApiResult<Deviceinformation> saveNewDeviceinformation(@RequestBody Deviceinformation item) {
		try {
			
			return ApiResult.success(service.insertItem(item));
		} catch (SwallowException e) {
			log.error("新增设备信息对象出错:"+e.getMessage(),e);
			return ApiResult.fail("新增工程对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 删除设备信息对象
	 * @param ids
	 * @return
	 */
	@ApiOperation(value = "新增一个新的设备信息对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true),
		@ApiImplicitParam(name = "ids", value = "设备IDs数组", required = true, allowMultiple=true, dataType = "Long")
	})
	@ViLog(operEvent = "删除设备信息对象", operType =3)//日志记录
	@DeleteMapping
	public BaseApiResult deleteDeviceinformation(@RequestBody long []ids) {
		try {
			for(var id:ids)
				service.deleteItemById(id);
			return BaseApiResult.successResult();
		} catch (Exception e) {
			log.error("删除设备信息对象出错:"+e.getMessage(),e);
			return BaseApiResult.failResult(500,"删除设备信息对象出错:"+e.getMessage());
		}		
	}
	
	/**
	 * 修改设备信息对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = "修改设备信息对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "修改设备信息对象", operType =2)//日志记录
	@PostMapping
	@SuppressWarnings("unchecked")
	public ApiResult<Deviceinformation> saveDeviceinformation(@RequestBody Deviceinformation item){
		try {
			return ApiResult.success(service.updateItem(item));
		} catch (SwallowException e) {
			log.error("修改设备信息对象出错:"+e.getMessage(),e);
			return ApiResult.fail("修改设备信息对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 通过查询bean进行分页查询数据
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "通过查询bean进行分页查询数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@PostMapping("listpage")
	@SuppressWarnings("unchecked")
	public ApiResult<PageListData<Deviceinformation>> query(@RequestBody  QueryBean query){
		try {			
			return ApiResult.success(service.getAllItemPageByQuerybean(query));
		} catch (Exception e) {
			log.error("查询设备信息对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询设备信息对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 通过查询bean进行分页查询数据
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "通过查询bean进行分页查询数据控件")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@PostMapping("queryComp")
	@SuppressWarnings("unchecked")
	public ApiResult<PageListData<Deviceinformation>> queryComp(@RequestBody  QueryBean query){
		try {			
			return ApiResult.success(service.getAllItemPageByQuerybean(query));
		} catch (Exception e) {
			log.error("查询设备信息对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询设备信息对象出错:"+e.getMessage());
		}
	}
	
	 @ApiOperation(value = "根据id取设备信息")
	 @ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "id", value = "设备分组Id", required = true, dataType = "long")
	  		})
	@RequestMapping("{id}")
	@SuppressWarnings("unchecked")
	public ApiResult<Deviceinformation> getItemById(@PathVariable long id){
		try {			
			return ApiResult.success(service.getItemById(id));
		} catch (Exception e) {
			log.error("查询代码模板对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询代码模板对象出错:"+e.getMessage());
		}
	}
	
	/** 根据设备名查询查询某几种类型的设备
	 * @param deviceTypes
	 * @return
	 */
	 @ApiOperation(value = "根据设备名查询查询某几种类型的设备")
	 @ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "deviceTypes", value = "设备类型", required = true, dataType = "String")
	  		})
	@GetMapping("itemsOfDeviceTypes")
	public ApiResult<List<Deviceinformation>> getItemsOfDeviceTypes(String deviceTypes) {
		List<Deviceinformation> deviceinformationList = service.getItemsOfDeviceTypes(deviceTypes);
		return ApiResult.success(deviceinformationList);
	}
	
	/**
	 * 远和开灯关灯
	 * @param type 1开灯,2:关灯
	 * @param cid
	 * @param appcode
	 * @return
	 */
	 @ApiOperation(value = "远和开灯关灯")
	 @ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "type", value = "type 1开灯,2:关灯", required = true, dataType = "int"),
	  		@ApiImplicitParam(name = "cid", value = "设备cid", required = true, dataType = "String"),
	  		@ApiImplicitParam(name = "appcode", value = "设备appcode", required = true, dataType = "String")
	  		})
	@GetMapping("closeOropenLamp")
	@SuppressWarnings("unchecked")
	public ApiResult<String> closeOropenLamp(int type,String cid,String appcode){
		try {
			String param=null;
			if(type==1)//开灯
			{
				param="{\"function\":\"deviceControl\",\"type\":\"openlamp\",\"appcode\":\""+appcode+"\",\"cid\":\""+cid+"\"}";
			}
			else if(type==2)//关灯
			{
				param="{\"function\":\"deviceControl\",\"type\":\"closelamp\",\"appcode\":\""+appcode+"\",\"cid\":\""+cid+"\"}";
				
			}
			else
			{
				return ApiResult.fail("");
			}
		
			String re=serviceClientUtil.csExtend(param);
			
			return ApiResult.success(re);
		} catch (Exception e) {
			log.error("查询代码模板对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询代码模板对象出错:"+e.getMessage());
		}
	}
	 
	 
	 
	    /**
		 * 灯杆操作（开灯、关灯、智能分流调光）
		 * @param type 1开灯,2:关灯,3:智能分流调光开启,4:智能分流调光关闭
		 * @param id 设备灯杆id
		 * @param cid
		 * @param appcode
		 * @return
		 */
		 @ApiOperation(value = "灯杆操作（开灯、关灯、智能分流调光）")
		 @ApiImplicitParams({
		  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
		  		@ApiImplicitParam(name = "type", value = "type 1开灯,2:关灯,3:智能分流调光开启,4:智能分流调光关闭", required = true, dataType = "int"),
		  		@ApiImplicitParam(name = "id", value = "设备灯杆id", required = true, dataType = "Long"),
		  		@ApiImplicitParam(name = "cid", value = "设备cid", required = true, dataType = "String"),
		  		@ApiImplicitParam(name = "appcode", value = "设备appcode", required = true, dataType = "String")
		  		})
		@GetMapping("closeLamppost")
		@SuppressWarnings("unchecked")
		public ApiResult<String> closeLamppost(int type,Long id,String cid,String appcode){
			try {
				Userview userview = (Userview) SecurityUtils.getSubject().getPrincipal();
				String param=null;
				if(type==1)//开灯
				{
					param="{\"function\":\"deviceControl\",\"type\":\"openLamppost\",\"appcode\":\""+appcode+"\",\"cid\":\""+cid+"\",\"id\":\""+id+"\"}";
				}
				else if(type==2)//关灯
				{
					param="{\"function\":\"deviceControl\",\"type\":\"closeLamppost\",\"appcode\":\""+appcode+"\",\"cid\":\""+cid+"\",\"id\":\""+id+"\"}";
				}
				else if(type==3)//智能分流调光开启
				{
					param="{\"function\":\"deviceControl\",\"type\":\"openDimmingLamppost\",\"appcode\":\""+appcode+"\",\"cid\":\""+cid+"\",\"id\":\""+id+"\"}";
				}
				else if(type==4)//智能分流调光关闭
				{
					param="{\"function\":\"deviceControl\",\"type\":\"closeDimmingLamppost\",\"appcode\":\""+appcode+"\",\"cid\":\""+cid+"\",\"id\":\""+id+"\"}";
				}
				else
				{
					return ApiResult.fail("");
				}
			
				String re=serviceClientUtil.csExtend(param);
				if(re!=null)
				{
					JSONObject jsonObject = JSON.parseObject(re);
					if(jsonObject.getIntValue("Code")==0)//
					{
						lampPostDeviceService.updchildrenLampPostDevice(type, id, 0);
						// 设备操作日志
						deviceLogService.insertDeviceLog(type, id, 0, userview.getId());
					}
				}
				
				
				
				return ApiResult.success(re);
			} catch (Exception e) {
				log.error("灯杆操作（开灯、关灯、智能分流调光）出错:"+e.getMessage(),e);
				return ApiResult.fail("灯杆操作（开灯、关灯、智能分流调光）出错:"+e.getMessage());
			}
		}
		 
		 
		  /**
			 * 灯杆调光
			 * @param brightness 亮度
			 * @param id  设备灯杆id
			 * @param cid
			 * @param appcode
			 * @return
			 */
			 @ApiOperation(value = "灯杆操作（开灯、关灯、调光、智能分流调光）")
			 @ApiImplicitParams({
			  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
			  		@ApiImplicitParam(name = "brightness", value = "亮度", required = true, dataType = "int"),
			  		@ApiImplicitParam(name = "id", value = "设备灯杆id", required = true, dataType = "Long"),
			  		@ApiImplicitParam(name = "cid", value = "设备cid", required = true, dataType = "String"),
			  		@ApiImplicitParam(name = "appcode", value = "设备appcode", required = true, dataType = "String")
			  		})
			@GetMapping("dimmingLamppost")
			@SuppressWarnings("unchecked")
			public ApiResult<String> dimmingLamppost(int brightness,Long id,String cid,String appcode){
				try {
					Userview userview = (Userview) SecurityUtils.getSubject().getPrincipal();
					String param="{\"function\":\"deviceControl\",\"type\":\"dimmingLamppost\",\"appcode\":\""+appcode+"\",\"cid\":\""+cid+"\",\"id\":\""+id+"\",\"brightness\":\""+brightness+"\"}";
					String re=serviceClientUtil.csExtend(param);
					
					if(re!=null)
					{
						JSONObject jsonObject =  JSON.parseObject(re);
						if(jsonObject.getIntValue("Code")==0)//
						{
							lampPostDeviceService.updchildrenLampPostDevice(5, id, brightness);
							// 设备操作日志
							deviceLogService.insertDeviceLog(5, id, brightness, userview.getId());
						}
					}
					
					return ApiResult.success(re);
				} catch (Exception e) {
					log.error("灯杆调光出错:"+e.getMessage(),e);
					return ApiResult.fail("灯杆调光出错:"+e.getMessage());
				}
			}
		 
		 
	 
	
	/**
	 * 取设备当前状态
	 * @param cid
	 * @param appcode
	 * @return
	 */
	 @ApiOperation(value = " 取设备当前状态")
	 @ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "cid", value = "设备cid", required = true, dataType = "String"),
	  		@ApiImplicitParam(name = "appcode", value = "设备appcode", required = true, dataType = "String")
	  		})
	@GetMapping("getLampStatus")
	@SuppressWarnings("unchecked")
	public ApiResult<String> getLampStatus(String cid,String appcode){
		try {
			String re=service.getLampStatus(cid, appcode);
			return ApiResult.success(re);
		} catch (Exception e) {
			log.error("查询代码模板对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询代码模板对象出错:"+e.getMessage());
		}
	}

    /** 查询区域下的设备数量列表
     * @return
     */
    @ApiOperation(value = "查询区域下的设备数量列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true)
    })
    @RequestMapping("getAreaDeviceCountList")
    @SuppressWarnings("unchecked")
    public ApiResult<List<Map<String, Object>>> getAreaDeviceCountList() {
        return ApiResult.success(service.getAreaDeviceCountList());
    }

	/** 查询指定-设备型号-的设备数量，不传参则返回所有设备型号数量
	 * @return
	 */
	@ApiOperation(value = "查询指定-设备型号-的设备数量")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true),
			@ApiImplicitParam(name = "deviceModelId", value = "设备模型ids,多个用逗号分隔", required = true, dataType = "String")
	})
	@RequestMapping("getdeviceModelIdCount")
	@SuppressWarnings("unchecked")
	public ApiResult<List<Map<String, Object>>> getdeviceModelIdCount(String deviceModelId) {
		return ApiResult.success(service.getdeviceModelIdCount(deviceModelId));
	}

	/**
	 * 查询指定-设备类型-的设备数量，不传参则返回所有设备类型数量
	 * @return
	 */
	@ApiOperation(value = "查询指定-设备类型-的设备数量")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true),
			@ApiImplicitParam(name = "deviceTypeId", value = "设备类型ids,多个用逗号分隔", required = true, dataType = "String")
	})
	@RequestMapping("getdeviceTypeIdCount")
	@SuppressWarnings("unchecked")
	public ApiResult<List<Map<String, Object>>> getdeviceTypeIdCount(String deviceTypeId) {
		return ApiResult.success(service.getdeviceTypeIdCount(deviceTypeId));
	}

	/**
	 * 批量灯杆操作（开灯、关灯、智能分流调光、调光）
	 * @param type 1开灯,2:关灯,3:智能分流调光开启,4:智能分流调光关闭，5：调光
	 * @param ids 多个设备灯杆id
	 * @param cid
	 * @param appcode
	 * @return
	 */
	@ApiOperation(value = "批量灯杆操作（开灯、关灯、智能分流调光）")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
			@ApiImplicitParam(name = "type", value = "type 1开灯,2:关灯,3:智能分流调光开启,4:智能分流调光关闭,5：调光", required = true, dataType = "int"),
			@ApiImplicitParam(name = "ids", value = "设备灯杆ids", required = true, dataType = "Long[]"),
			@ApiImplicitParam(name = "cid", value = "设备cid", required = true, dataType = "String[]"),
			@ApiImplicitParam(name = "appcode", value = "设备appcode", required = true, dataType = "String[]")
	})
	@RequestMapping("batchOperLamppost")
	@SuppressWarnings("unchecked")
	public ApiResult<String> batchOperLamppost(Integer type,String ids,String cid,String appcode,
					@RequestParam(value = "brightness", defaultValue = "0",required = false)int brightness){
		try {
			Userview userview = (Userview) SecurityUtils.getSubject().getPrincipal();
			String re = null;
			String[] idsArr = ids.split(",");
			String[] cidArr = cid.split(",");
			String[] appcodeArr = appcode.split(",");
			if(idsArr!= null && idsArr.length > 0) {
				for (int i=0;i<idsArr.length;i++){
					String param=null;
					if(type==1)//开灯
					{
						param="{\"function\":\"deviceControl\",\"type\":\"openLamppost\",\"appcode\":\""+appcodeArr[i]+"\",\"cid\":\""+cidArr[i]+"\",\"id\":\""+idsArr[i]+"\"}";
					}
					else if(type==2)//关灯
					{
						param="{\"function\":\"deviceControl\",\"type\":\"closeLamppost\",\"appcode\":\""+appcodeArr[i]+"\",\"cid\":\""+cidArr[i]+"\",\"id\":\""+idsArr[i]+"\"}";
					}
					else if(type==3)//智能分流调光开启
					{
						param="{\"function\":\"deviceControl\",\"type\":\"openDimmingLamppost\",\"appcode\":\""+appcodeArr[i]+"\",\"cid\":\""+cidArr[i]+"\",\"id\":\""+idsArr[i]+"\"}";
					}
					else if(type==4)//智能分流调光关闭
					{
						param="{\"function\":\"deviceControl\",\"type\":\"closeDimmingLamppost\",\"appcode\":\""+appcodeArr[i]+"\",\"cid\":\""+cidArr[i]+"\",\"id\":\""+idsArr[i]+"\"}";
					}
					else if (type==5){// 调光
						param="{\"function\":\"deviceControl\",\"type\":\"dimmingLamppost\",\"appcode\":\""+appcodeArr[i]+"\",\"cid\":\""+cidArr[i]+"\",\"id\":\""+idsArr[i]+"\",\"brightness\":\""+brightness+"\"}";
					}
					else
					{
						return ApiResult.fail("");
					}

					re=serviceClientUtil.csExtend(param);
					if(re!=null)
					{
						JSONObject jsonObject = JSON.parseObject(re);
						if(jsonObject.getIntValue("Code")==0)//
						{
							lampPostDeviceService.updchildrenLampPostDevice(type, Long.valueOf(idsArr[i]), brightness);
							// 设备操作日志
							deviceLogService.insertDeviceLog(type, Long.valueOf(idsArr[i]), brightness, userview.getId());
						}
					}

				}
			}

			return ApiResult.success(re);
		} catch (Exception e) {
			log.error("灯杆操作（开灯、关灯、智能分流调光,调光）出错:"+e.getMessage(),e);
			return ApiResult.fail("灯杆操作（开灯、关灯、智能分流调光，调光）出错:"+e.getMessage());
		}
	}
}
