package com.cityiot.guanxin.workOrder.buildingSite.control;


import com.cityiot.guanxin.log.annotation.ViLog;
import com.cityiot.guanxin.workOrder.buildingSite.entity.BuildingSite;
import com.cityiot.guanxin.workOrder.buildingSite.service.BuildingSiteService;

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
 * 工地信息数据访问API接口
 * @author aohanhe
 *
 */
@RestController
@Api(tags = "工地信息数据访问API接口")
@RequestMapping("/api/buildingSite")
public class BuildingSiteControl {
	
	private static final Logger log = LoggerFactory.getLogger(BuildingSiteControl.class);

	@Autowired
	private BuildingSiteService service;
	
	/**
	 * 查询对象
	 * @author aohanhe
	 *
	 */
	static class QueryBean extends BasePageQueryBean{
	}
	
	/**
	 * 新增一个新的工地信息对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = "新增一个新的工地信息对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "新增一个新的工地信息对象", operType =1)//日志记录
	@SuppressWarnings("unchecked")
	@PutMapping()
	public ApiResult<BuildingSite> saveNewbuildingSite(@RequestBody BuildingSite item) {
		try {
			
			return ApiResult.success(service.insertItem(item));
		} catch (SwallowException e) {
			log.error("新增工地信息对象出错:"+e.getMessage(),e);
			return ApiResult.fail("新增工程对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 删除工地信息对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = "删除工地信息对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	@ApiImplicitParam(name = "ids", value = "工地信息IDs", required = true,allowMultiple=true, dataType = "Long")
	})
	@ViLog(operEvent = "删除工地信息对象", operType =3)//日志记录
	@DeleteMapping
	public BaseApiResult deletebuildingSite(@RequestBody long []ids) {
		try {
			for(var id:ids)
				service.deleteItemById(id);
			return BaseApiResult.successResult();
		} catch (Exception e) {
			log.error("删除工地信息对象出错:"+e.getMessage(),e);
			return BaseApiResult.failResult(500,"删除工地信息对象出错:"+e.getMessage());
		}		
	}
	
	/**
	 * 修改工地信息对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = " 修改工地信息对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "修改工地信息对象", operType =2)//日志记录
	@PostMapping
	@SuppressWarnings("unchecked")
	public ApiResult<BuildingSite> savebuildingSite(@RequestBody BuildingSite item){
		try {
			return ApiResult.success(service.updateItem(item));
		} catch (SwallowException e) {
			log.error("修改工地信息对象出错:"+e.getMessage(),e);
			return ApiResult.fail("修改工地信息对象出错:"+e.getMessage());
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
	public ApiResult<PageListData<BuildingSite>> query(@RequestBody  QueryBean query){
		try {			
			return ApiResult.success(service.getAllItemPageByQuerybean(query));
		} catch (Exception e) {
			log.error("查询工地信息对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询工地信息对象出错:"+e.getMessage());
		}
	}
	
	  @ApiOperation(value = "根据id取工地信息信息")
	  	@ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "id", value = "工地信息Id", required = true, dataType = "long")
	  		})
	@RequestMapping("{id}")
	@SuppressWarnings("unchecked")
	public ApiResult<BuildingSite> getItemById(@PathVariable long id){
		try {			
			return ApiResult.success(service.getItemById(id));
		} catch (Exception e) {
			log.error("查询代码模板对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询代码模板对象出错:"+e.getMessage());
		}
	}


}
