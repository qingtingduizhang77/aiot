package com.cityiot.guanxin.app.control;


import com.cityiot.guanxin.log.annotation.ViLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

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

import com.cityiot.guanxin.app.entity.AppVersion;
import com.cityiot.guanxin.app.service.AppVersionService;

import swallow.framework.exception.SwallowException;
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

import java.util.Date;
import java.util.Map;

/**
 * APP版本实体数据访问API接口
 * @author aohanhe
 *
 */
@RestController
@Api(tags = "APP版本实体数据访问API接口")
@RequestMapping("/api/appVersion")
public class AppVersionControl {
	
	private static final Logger log = LoggerFactory.getLogger(AppVersionControl.class);

	@Autowired
	private AppVersionService service;
	
	/**
	 * 查询对象
	 * @author aohanhe
	 *
	 */
	@ApiModel(value="APP版本查询对象")
	static class QueryBean extends BasePageQueryBean{

		// APP平台类型
		@ApiModelProperty(value="APP平台类型",name="appPlatformType",example="")
		private Integer appPlatformType;

		@CnName("修改时间")
		@ApiModelProperty(value="修改时间",name="endTime",example="")
		private Date lastmodi;

		public Date getLastmodi() {
			return lastmodi;
		}

		public void setLastmodi(Date lastmodi) {
			this.lastmodi = lastmodi;
		}

		public Integer getAppPlatformType() {
			return appPlatformType;
		}

		public void setAppPlatformType(Integer appPlatformType) {
			this.appPlatformType = appPlatformType;
		}
	}
	
	/**
	 * 新增一个新的APP版本实体对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = "新增一个新的APP版本实体对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "新增一个新的APP版本实体对象", operType =1)//日志记录
	@SuppressWarnings("unchecked")
	@PutMapping()
	@RequiresPermissions(value={"001004001","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<AppVersion> saveNewAppVersion(@RequestBody AppVersion item) {
		try {
			AppVersion saveAppVersion = service.insertItem(item);
			if(saveAppVersion.getPublishStatus()==1){
				// 调用发布
				service.publishApp(saveAppVersion.getId(), saveAppVersion.getAppPlatformType());
			}
			return ApiResult.success(saveAppVersion);
		} catch (SwallowException e) {
			log.error("新增APP版本实体对象出错:"+e.getMessage(),e);
			return ApiResult.fail("新增工程对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 删除APP版本实体对象
	 * @param ids
	 * @return
	 */
	@ApiOperation(value = "删除APP版本实体对象")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true),
		@ApiImplicitParam(name = "ids", value = "APP版本Ids数组：long[]", required = true, allowMultiple=true, dataType = "Long") 
		})
	@ViLog(operEvent = "删除APP版本实体对象", operType =3)//日志记录
	@DeleteMapping
	@RequiresPermissions(value={"001004002","SUPERADMIN"}, logical=Logical.OR)
	public BaseApiResult deleteAppVersion(@RequestBody long []ids) {
		try {
			for(var id:ids)
				service.deleteItemById(id);
			return BaseApiResult.successResult();
		} catch (Exception e) {
			log.error("删除APP版本实体对象出错:"+e.getMessage(),e);
			return BaseApiResult.failResult(500,"删除APP版本实体对象出错:"+e.getMessage());
		}		
	}
	
	/**
	 * 修改APP版本实体对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = "修改APP版本实体对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "修改APP版本实体对象", operType =2)//日志记录
	@PostMapping
	@SuppressWarnings("unchecked")
	@RequiresPermissions(value={"001004003","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<AppVersion> saveAppVersion(@RequestBody AppVersion item){
		try {
			AppVersion editAppVersion = service.updateItem(item);
			if(editAppVersion.getPublishStatus()==1){
				// 调用发布
				service.publishApp(editAppVersion.getId(), editAppVersion.getAppPlatformType());
			}
			return ApiResult.success(editAppVersion);
		} catch (SwallowException e) {
			log.error("修改APP版本实体对象出错:"+e.getMessage(),e);
			return ApiResult.fail("修改APP版本实体对象出错:"+e.getMessage());
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
	@RequiresPermissions(value={"001004004","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<PageListData<AppVersion>> query(@RequestBody  QueryBean query){
		try {
			return ApiResult.success(service.getAllItemPageByQuerybean(query));
		} catch (Exception e) {
			log.error("查询APP版本实体对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询APP版本实体对象出错:"+e.getMessage());
		}
	}
	
	
	@ApiOperation(value = "通过角色Id进行查询角色数据")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
		@ApiImplicitParam(name = "id", value = "APP版本Id", required = true, dataType = "long")
		})
	@RequestMapping("{id}")
	@SuppressWarnings("unchecked")
	@RequiresPermissions(value={"001004005","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<AppVersion> getItemById(@PathVariable long id){
		try {			
			return ApiResult.success(service.getItemById(id));
		} catch (Exception e) {
			log.error("查询代码模板对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询代码模板对象出错:"+e.getMessage());
		}
	}

	/**
	 * APP发布
	 * @param id
	 * @param appPlatformType
	 * @return
	 */
	@ApiOperation(value = "APP发布")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
		@ApiImplicitParam(name = "appPlatformType", value = "APP平台类型", required = true, dataType = "int"),
		@ApiImplicitParam(name = "id", value = "APP版本Id", required = true, dataType = "long")
		})
	@RequestMapping("/publish/{appPlatformType}/{id}")
	@SuppressWarnings("unchecked")
	public BaseApiResult publishApp(@PathVariable long id,@PathVariable int appPlatformType){
		try {
			service.publishApp(id, appPlatformType);
			return ApiResult.successResult();
		} catch (Exception e) {
			log.error("发布出错:"+e.getMessage(),e);
			return ApiResult.fail("发布出错:"+e.getMessage());
		}
	}

	/**
	 * 发布列表
	 * @return
	 */
	@ApiOperation(value = "发布列表")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@RequestMapping("/publish/list")
	@SuppressWarnings("unchecked")
	public ApiResult<Map<String, Object>> getPublishList(){
		try {
			return ApiResult.success(service.getPublishList());
		} catch (Exception e) {
			log.error("查询发布信息出错:"+e.getMessage(),e);
			return ApiResult.fail("查询发布信息出错:"+e.getMessage());
		}
	}
}
