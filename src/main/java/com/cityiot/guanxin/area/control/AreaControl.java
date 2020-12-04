package com.cityiot.guanxin.area.control;


import com.cityiot.guanxin.area.entity.Area;
import com.cityiot.guanxin.area.service.AreaService;

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
import org.springframework.web.bind.annotation.*;
import swallow.framework.exception.SwallowException;
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

import java.util.Date;
import java.util.regex.Pattern;

/**
 * 区域数据访问API接口
 * @author aohanhe
 *
 */
@RestController
@Api(tags = "区域数据访问API接口")
@RequestMapping("/api/area")
public class AreaControl {
	
	private static final Logger log = LoggerFactory.getLogger(AreaControl.class);

	@Autowired
	private AreaService service;
	
	/**
	 * 查询对象
	 * @author aohanhe
	 *
	 */
	@ApiModel(value="区域数据查询对象")
	static class QueryBean extends BasePageQueryBean{

		// 区域名称
		@ApiModelProperty(value="区域名称",name="name",example="")
		@Like
		private String name;

		// 区域代号
		@ApiModelProperty(value="区域代号",name="mark",example="")
		@Like
		private String mark;

		@ApiModelProperty(value="父区域id",name="parentId",example="")
		private Long parentId;

		@CnName("修改时间")
		@ApiModelProperty(value="修改时间",name="endTime",example="")
		private Date lastmodi;

		public Date getLastmodi() {
			return lastmodi;
		}

		public void setLastmodi(Date lastmodi) {
			this.lastmodi = lastmodi;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getMark() {
			return mark;
		}

		public void setMark(String mark) {
			this.mark = mark;
		}

		public long getParentId() {
			return parentId;
		}

		public void setParentId(long parentId) {
			this.parentId = parentId;
		}
	}
	
	/**
	 * 新增一个新的区域对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = "新增一个新的区域对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@SuppressWarnings("unchecked")
	@ViLog(operEvent = "新增一个新的区域对象", operType =1)//日志记录
	@PutMapping()
	@RequiresPermissions(value={"001005001","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<Area> saveNewArea(@RequestBody Area item) {
		try {
			return ApiResult.success(service.insertItem(item));
		} catch (SwallowException e) {
			log.error("新增区域对象出错:"+e.getMessage(),e);
			return ApiResult.fail("新增工程对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 删除区域对象
	 * @param ids
	 * @return
	 */
	@ApiOperation(value = "删除区域对象")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true),
		@ApiImplicitParam(name = "ids", value = "区域对象Ids数组：long[]", required = true, allowMultiple=true, dataType = "Long") 
		})
	@ViLog(operEvent = "删除区域对象", operType =3)//日志记录
	@DeleteMapping
	@RequiresPermissions(value={"001005002","SUPERADMIN"}, logical=Logical.OR)
	public BaseApiResult deleteArea(@RequestBody long []ids) {
		try {
			for(var id:ids)
				service.deleteItemById(id);
			return BaseApiResult.successResult();
		} catch (Exception e) {
			log.error("删除区域对象出错:"+e.getMessage(),e);
			return BaseApiResult.failResult(500,"删除区域对象出错:"+e.getMessage());
		}		
	}
	
	/**
	 * 修改区域对象
	 * @param item
	 * @return
	 */
	@ApiOperation(value = " 修改区域对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@ViLog(operEvent = "修改区域对象", operType =2)//日志记录
	@PostMapping
	@SuppressWarnings("unchecked")
	@RequiresPermissions(value={"001005003","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<Area> saveArea(@RequestBody Area item){
		try {
			return ApiResult.success(service.updateItem(item));
		} catch (SwallowException e) {
			log.error("修改区域对象出错:"+e.getMessage(),e);
			return ApiResult.fail("修改区域对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 通过查询bean进行分页查询数据
	 * @param query
	 * @return
	 */
	@ApiOperation(value = " 通过查询bean进行分页查询区域数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
	@PostMapping("listpage")
	@SuppressWarnings("unchecked")
	@RequiresPermissions(value={"001005004","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<PageListData<Area>> query(@RequestBody  QueryBean query){
		try {			
			return ApiResult.success(service.getAllItemPageByQuerybean(query));
		} catch (Exception e) {
			log.error("查询区域对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询区域对象出错:"+e.getMessage());
		}
	}
	
	@ApiOperation(value = "通过角色Id进行查询区域数据")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
		@ApiImplicitParam(name = "id", value = "区域Id", required = true, dataType = "long")
		})
	@RequestMapping("{id}")
	@SuppressWarnings("unchecked")
	@RequiresPermissions(value={"001005005","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<Area> getItemById(@PathVariable long id){
		try {			
			return ApiResult.success(service.getItemById(id));
		} catch (Exception e) {
			log.error("查询代码模板对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询代码模板对象出错:"+e.getMessage());
		}
	}
	

}
