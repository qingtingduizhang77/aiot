package com.cityiot.guanxin.control;


import com.cityiot.guanxin.log.annotation.ViLog;
import io.swagger.annotations.ApiModelProperty;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cityiot.guanxin.entity.SystemVariable;
import com.cityiot.guanxin.service.SystemVariableService;

import io.swagger.annotations.Api;
import swallow.framework.exception.SwallowException;
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

import java.util.Date;

/**
 * 系统变量配置数据访问API接口
 * @author aohanhe
 *
 */
@Api(value = "/api/systemVariable",tags = "系统变量配置数据访问API接口")
@RestController
@RequestMapping("/api/systemVariable")
public class SystemVariableControl {
	
	private static final Logger log = LoggerFactory.getLogger(SystemVariableControl.class);

	@Autowired
	private SystemVariableService service;
	
	/**
	 * 查询对象
	 * @author aohanhe
	 *
	 */
	static class QueryBean extends BasePageQueryBean{

		// 变量值
		private Integer variable;

		// 变量名称
		@Like
		private String name;

		// 变量类型		
		private Integer vartype;

		// version
		private Long version;

		@CnName("修改时间")
		private Date lastmodi;

		public Date getLastmodi() {
			return lastmodi;
		}

		public void setLastmodi(Date lastmodi) {
			this.lastmodi = lastmodi;
		}

		public Integer getVariable() {
			return variable;
		}

		public void setVariable(Integer variable) {
			this.variable = variable;
		}
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		public Integer getVartype() {
			return vartype;
		}

		public void setVartype(Integer vartype) {
			this.vartype = vartype;
		}
		public Long getVersion() {
			return version;
		}

		public void setVersion(Long version) {
			this.version = version;
		}
	}
	
	/**
	 * 新增一个新的系统变量配置对象
	 * @param item
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ViLog(operEvent = "新增一个新的系统变量配置对象", operType =1)//日志记录
	@PostMapping("add")
	@RequiresPermissions(value={"001006006","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<SystemVariable> saveNewSystemVariable(@RequestBody SystemVariable item) {
		try {
			
			return ApiResult.success(service.insertItem(item));
		} catch (SwallowException e) {
			log.error("新增系统变量配置对象出错:"+e.getMessage(),e);
			return ApiResult.fail("新增工程对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 删除系统变量配置对象
	 * @param ids
	 * @return
	 */
	@ViLog(operEvent = "删除系统变量配置对象", operType =3)//日志记录
	@PostMapping("del")
	@RequiresPermissions(value={"001006007","SUPERADMIN"}, logical=Logical.OR)
	public BaseApiResult deleteSystemVariable(@RequestBody long []ids) {
		try {
			for(var id:ids)
				service.deleteItemById(id);
			return BaseApiResult.successResult();
		} catch (Exception e) {
			log.error("删除系统变量配置对象出错:"+e.getMessage(),e);
			return BaseApiResult.failResult(500,"删除系统变量配置对象出错:"+e.getMessage());
		}		
	}
	
	/**
	 * 修改系统变量配置对象
	 * @param item
	 * @return
	 */
	@ViLog(operEvent = "修改系统变量配置对象", operType =2)//日志记录
	@PostMapping
	@SuppressWarnings("unchecked")
	@RequiresPermissions(value={"001006008","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<SystemVariable> saveSystemVariable(@RequestBody SystemVariable item){
		try {
			return ApiResult.success(service.updateItem(item));
		} catch (SwallowException e) {
			log.error("修改系统变量配置对象出错:"+e.getMessage(),e);
			return ApiResult.fail("修改系统变量配置对象出错:"+e.getMessage());
		}
	}
	
	/**
	 * 通过查询bean进行分页查询数据
	 * @param query
	 * @return
	 */
	@PostMapping("listpage")
	@SuppressWarnings("unchecked")
	@RequiresPermissions(value={"001006009","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<PageListData<SystemVariable>> query(@RequestBody  QueryBean query){
		try {			
			return ApiResult.success(service.getAllItemPageByQuerybean(query));
		} catch (Exception e) {
			log.error("查询系统变量配置对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询系统变量配置对象出错:"+e.getMessage());
		}
	}
	
	@RequestMapping("{id}")
	@SuppressWarnings("unchecked")
	@RequiresPermissions(value={"001006010","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<SystemVariable> getItemById(@PathVariable long id){
		try {			
			return ApiResult.success(service.getItemById(id));
		} catch (Exception e) {
			log.error("查询代码模板对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询代码模板对象出错:"+e.getMessage());
		}
	}




}
