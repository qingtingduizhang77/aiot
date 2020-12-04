package com.cityiot.guanxin.control;


import com.cityiot.guanxin.entity.SystemVariableType;
import com.cityiot.guanxin.log.annotation.ViLog;
import com.cityiot.guanxin.service.SystemVariableTypeService;

import io.swagger.annotations.Api;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import swallow.framework.exception.SwallowException;
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

/**
 * 系统变量类型数据访问API接口
 * @author aohanhe
 *
 */
@Api(value = "/api/eSystemVariableType",tags = "系统变量类型数据访问API接口")
@RestController
@RequestMapping("/api/eSystemVariableType")
public class SystemVariableTypeControl {
	
	private static final Logger log = LoggerFactory.getLogger(SystemVariableTypeControl.class);

	@Autowired
	private SystemVariableTypeService service;
	
	/**
	 * 查询对象
	 * @author aohanhe
	 *
	 */
	static class QueryBean extends BasePageQueryBean{

		// 类型名
		@Like
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
	
	/**
	 * 新增一个新的系统变量类型对象
	 * @param item
	 * @return
	 */
	@ViLog(operEvent = "新增一个新的系统变量类型对象", operType =1)//日志记录
	@SuppressWarnings("unchecked")
	@PostMapping("add")
	@RequiresPermissions(value={"001006001","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<SystemVariableType> saveNewESystemVariableType(@RequestBody SystemVariableType item) {
		try {
			
			return ApiResult.success(service.insertItem(item));
		} catch (SwallowException e) {
			log.error("新增系统变量类型对象出错:"+e.getMessage(),e);
			return ApiResult.fail("新增工程对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 删除系统变量类型对象
	 * @param ids
	 * @return
	 */
	@ViLog(operEvent = "删除系统变量类型对象", operType =3)//日志记录
	@PostMapping("del")
	@RequiresPermissions(value={"001006002","SUPERADMIN"}, logical=Logical.OR)
	public BaseApiResult deleteESystemVariableType(@RequestBody long []ids) {
		try {
			for(var id:ids)
				service.deleteItemById(id);
			return BaseApiResult.successResult();
		} catch (Exception e) {
			log.error("删除系统变量类型对象出错:"+e.getMessage(),e);
			return BaseApiResult.failResult(500,"删除系统变量类型对象出错:"+e.getMessage());
		}		
	}
	
	/**
	 * 修改系统变量类型对象
	 * @param item
	 * @return
	 */
	@ViLog(operEvent = "修改系统变量类型对象", operType =2)//日志记录
	@PostMapping
	@SuppressWarnings("unchecked")
	@RequiresPermissions(value={"001006003","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<SystemVariableType> saveESystemVariableType(@RequestBody SystemVariableType item){
		try {
			return ApiResult.success(service.updateItem(item));
		} catch (SwallowException e) {
			log.error("修改系统变量类型对象出错:"+e.getMessage(),e);
			return ApiResult.fail("修改系统变量类型对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 通过查询bean进行分页查询数据
	 * @param query
	 * @return
	 */
	@PostMapping("listpage")
	@SuppressWarnings("unchecked")
	@RequiresPermissions(value={"001006004","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<PageListData<SystemVariableType>> query(@RequestBody  QueryBean query){
		try {			
			return ApiResult.success(service.getAllItemPageByQuerybean(query));
		} catch (Exception e) {
			log.error("查询系统变量类型对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询系统变量类型对象出错:"+e.getMessage());
		}
	}
	
	@RequestMapping("{id}")
	@SuppressWarnings("unchecked")
	@RequiresPermissions(value={"001006005","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<SystemVariableType> getItemById(@PathVariable long id){
		try {			
			return ApiResult.success(service.getItemById(id));
		} catch (Exception e) {
			log.error("查询代码模板对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询代码模板对象出错:"+e.getMessage());
		}
	}
	

}
