package com.cityiot.guanxin.workOrder.control;


import com.cityiot.guanxin.workOrder.entity.EInspectionWorkOrder;
import com.cityiot.guanxin.workOrder.entity.EWorkOrderProgress;
import com.cityiot.guanxin.workOrder.entity.QEWorkOrderProgress;
import com.cityiot.guanxin.workOrder.service.EWorkOrderProgressService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BasePageQueryBean;

import java.util.List;

/**
 * 年检工单数据访问API接口
 * @author aohanhe
 *
 */
@RestController
@Api(tags = "年检工单数据访问API接口")
@RequestMapping("/api/eWorkOrderProgress")
public class EWorkOrderProgressControl {
	
	private static final Logger log = LoggerFactory.getLogger(EWorkOrderProgressControl.class);

	@Autowired
	private EWorkOrderProgressService service;
	
	/**
	 * 查询对象
	 * @author aohanhe
	 *
	 */
	static class QueryBean extends BasePageQueryBean{

	}
	

	
	@ApiOperation(value = "根据id取\"年检工单信息")
	@ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "id", value = "\"年检工单Id", required = true, dataType = "long")
	  		})
	@RequestMapping("{id}")
	@SuppressWarnings("unchecked")
	public ApiResult<EInspectionWorkOrder> getItemById(@PathVariable long id){
		try {			
			return ApiResult.success(service.getItemById(id));
		} catch (Exception e) {
			log.error("查询代码模板对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询代码模板对象出错:"+e.getMessage());
		}
	}

	 @ApiOperation(value = "设备分组添加关联设备")
	  	@ApiImplicitParams({
	  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
	  		@ApiImplicitParam(name = "workOrderType", value = "工单类型", required = true, dataType = "long"),
	  		@ApiImplicitParam(name = "id", value = "工单Id", required = true, dataType = "long")
	  		})
	@RequestMapping("getItemByWorkOrder/{workOrderType}/{id}")
	public ApiResult<List<EWorkOrderProgress>> getAllItems(@PathVariable int workOrderType,@PathVariable long id){
		try {
			return ApiResult.success(service.getAllItems(query -> {
				return query.where(QEWorkOrderProgress.eWorkOrderProgress.workOrderId.eq(id))
						.where(QEWorkOrderProgress.eWorkOrderProgress.workOrderType.eq(workOrderType))
						.orderBy(QEWorkOrderProgress.eWorkOrderProgress.time.asc());
			}));
		} catch (Exception e){
			log.error("查询代码模板对象出错:"+e.getMessage(),e);
			return ApiResult.fail("查询代码模板对象出错:"+e.getMessage());
		}
	}
	

}
