package com.cityiot.guanxin.workOrder.carParking.control;


import com.cityiot.guanxin.log.annotation.ViLog;
import com.cityiot.guanxin.workOrder.carParking.entity.CarParking;
import com.cityiot.guanxin.workOrder.carParking.service.CarParkingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import swallow.framework.exception.SwallowException;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

/**
 * 停车场数据访问API接口
 * @author aohanhe
 *
 */
@RestController
@Api(tags = "停车场数据访问API接口")
@RequestMapping("/api/carParking")
public class CarParkingControl {
	
	private static final Logger log = LoggerFactory.getLogger(CarParkingControl.class);

	@Autowired
	private CarParkingService service;
	
	/**
	 * 查询对象
	 * @author aohanhe
	 *
	 */
	static class QueryBean extends BasePageQueryBean{
	}
	
	/**
	 * 新增一个新的停车场对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = "新增一个新的停车场对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "新增一个新的停车场对象", operType =1)//日志记录
	@SuppressWarnings("unchecked")
	@PutMapping()
	public ApiResult<CarParking> saveNewCarParking(@RequestBody CarParking item) {
		try {
			
			return ApiResult.success(service.insertItem(item));
		} catch (SwallowException e) {
			log.error("新增停车场对象出错:"+e.getMessage(),e);
			return ApiResult.fail("新增工程对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 删除停车场对象
	 * @param item
	 * @return
	 */
	  @ApiOperation(value = " 删除停车场对象")
	  @ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "deviceIds", value = "停车场ID", required = true,allowMultiple=true, dataType = "Long")
	  		})
	@ViLog(operEvent = "删除停车场对象", operType =3)//日志记录
	@DeleteMapping
	public BaseApiResult deleteCarParking(@RequestBody long []ids) {
		try {
			for(var id:ids)
				service.deleteItemById(id);
			return BaseApiResult.successResult();
		} catch (Exception e) {
			log.error("删除停车场对象出错:"+e.getMessage(),e);
			return BaseApiResult.failResult(500,"删除停车场对象出错:"+e.getMessage());
		}		
	}
	
	/**
	 * 修改停车场对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = "修改停车场对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "修改停车场对象", operType =2)//日志记录
	@PostMapping
	@SuppressWarnings("unchecked")
	public ApiResult<CarParking> saveCarParking(@RequestBody CarParking item){
		try {
			return ApiResult.success(service.updateItem(item));
		} catch (SwallowException e) {
			log.error("修改停车场对象出错:"+e.getMessage(),e);
			return ApiResult.fail("修改停车场对象出错:"+e.getMessage());
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
	public ApiResult<PageListData<CarParking>> query(@RequestBody  QueryBean query){
		try {			
			return ApiResult.success(service.getAllItemPageByQuerybean(query));
		} catch (Exception e) {
			log.error("查询停车场对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询停车场对象出错:"+e.getMessage());
		}
	}
	
	 @ApiOperation(value = "根据id取停车场信息")
	 @ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "id", value = "停车场Id", required = true, dataType = "long")
	  		})
	@RequestMapping("{id}")
	@SuppressWarnings("unchecked")
	public ApiResult<CarParking> getItemById(@PathVariable long id){
		try {			
			return ApiResult.success(service.getItemById(id));
		} catch (Exception e) {
			log.error("查询代码模板对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询代码模板对象出错:"+e.getMessage());
		}
	}


}
