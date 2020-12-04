package com.cityiot.guanxin.plan.control;

import com.cityiot.guanxin.log.annotation.ViLog;
import com.cityiot.guanxin.plan.entity.DeviceToPlan;
import com.cityiot.guanxin.plan.entity.Plan;
import com.cityiot.guanxin.plan.service.DeviceToPlanService;
import com.cityiot.guanxin.plan.service.PlanService;

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
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;
import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.List;


/**
 * 平面图控制层
 * @author Guoyz
 *
 */
@RestController
@Api(tags = "平面图控制层")
@RequestMapping("/api/plan")
public class PlanControl {
    private static final Logger log = LoggerFactory.getLogger(PlanControl.class);
    @Autowired
    private PlanService service;
    @Autowired
    private DeviceToPlanService deviceToPlanService;

    @ApiModel(value="平面图查询对象")
    static class QueryBean extends BasePageQueryBean {
        // 名称
    	@ApiModelProperty(value="名称",name="name",example="")
        @Like
        private String name;

    	@ApiModelProperty(value="修改时间",name="lastmodi",example="")
        private Date lastmodi;

    	@ApiModelProperty(value="创建时间",name="created",example="")
        private Date created;

        public Date getCreated() {
            return created;
        }

        public void setCreated(Date created) {
            this.created = created;
        }

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
    }
    /**
     * 新增一个平面图对象
     * @param item
     * @return
     */
    @RequiresPermissions(value={"002006001","SUPERADMIN"}, logical= Logical.OR)
    @ApiOperation(value = "新增一个平面图对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @ViLog(operEvent = "新增一个平面图对象", operType =1)//日志记录
    @PutMapping()
    @SuppressWarnings("unchecked")
    public ApiResult<Plan> saveNewPlan(@RequestBody Plan item) {
        try {

            return ApiResult.success(service.insertItem(item));
        } catch (SwallowException e) {
            log.error("新增平面图出错:"+e.getMessage(),e);
            return ApiResult.fail("新增平面图出错:"+e.getMessage());
        }
    }

    /**
     * 删除平面图
     * @param ids
     * @return
     */
    @RequiresPermissions(value={"002006002","SUPERADMIN"}, logical= Logical.OR)
    @ApiOperation(value = "删除平面图")
  	@ApiImplicitParams({
  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
  		@ApiImplicitParam(name = "ids", value = "平面图IDs数组", required = true,allowMultiple=true, dataType = "Long")
  		})
    @ViLog(operEvent = "删除平面图", operType =3)//日志记录
    @DeleteMapping
    public BaseApiResult deletePlan(@RequestBody long []ids) {
        try {
            for(var id:ids)
                service.deleteItemById(id);
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("删除平面图出错:"+e.getMessage(),e);
            return BaseApiResult.failResult(500,"删除平面图出错:"+e.getMessage());
        }
    }

    /**
     * 修改平面图分组
     * @param item
     * @return
     */
    @RequiresPermissions(value={"002006003","SUPERADMIN"}, logical= Logical.OR)
    @ApiOperation(value = "修改平面图分组")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @ViLog(operEvent = "修改平面图", operType =2)//日志记录
    @PostMapping
    @SuppressWarnings("unchecked")
    public ApiResult<Plan> savePlan(@RequestBody Plan item){
        try {
            return ApiResult.success(service.updateItem(item));
        } catch (SwallowException e) {
            log.error("修改平面图出错:"+e.getMessage(),e);
            return ApiResult.fail("修改平面图出错:"+e.getMessage());
        }
    }

    /**
     * 通过查询bean进行分页查询数据
     * @param query
     * @return
     */
    @RequiresPermissions(value={"002006004","SUPERADMIN"}, logical= Logical.OR)
    @ApiOperation(value = "通过查询bean进行分页查询数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @PostMapping("listpage")
    @SuppressWarnings("unchecked")
    public ApiResult<PageListData<Plan>> query(@RequestBody  QueryBean query){
        try {
            return ApiResult.success(service.getAllItemPageByQuerybean(query));
        } catch (Exception e) {
            log.error("查询平面图出错:"+e.getMessage(),e);
            return ApiResult.fail("查询平面图出错:"+e.getMessage());
        }
    }

    //获取指定平面图详细信息
    @ApiOperation(value = "获取指定平面图详细信息")
  	@ApiImplicitParams({
  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
  		@ApiImplicitParam(name = "id", value = "平面Id", required = true, dataType = "long")
  		})
    @RequestMapping("{id}")
    @SuppressWarnings("unchecked")
    public ApiResult<Plan> getItemById(@PathVariable long id){
        try {
            return ApiResult.success(service.getItemById01(id));
        } catch (Exception e) {
            log.error("查询平面图出错:"+e.getMessage(),e);
            return ApiResult.fail("查询平面图出错:"+e.getMessage());
        }
    }


    //获取指定平面图对应设备
    @ApiOperation(value = "获取指定平面图对应设备")
  	@ApiImplicitParams({
  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
  		@ApiImplicitParam(name = "planId", value = "平面Id", required = true, dataType = "long")
  		})
    @GetMapping("/deviceToPlan")
    @SuppressWarnings("unchecked")
    public ApiResult<PageListData<DeviceToPlan>> getDeviceToPlanByPlanId(long planId){
        try {
            return ApiResult.success(deviceToPlanService.getItemByPlanId(planId));
        } catch (Exception e) {
            log.error("查询平面图对应设备:"+e.getMessage(),e);
            return ApiResult.fail("平面图对应设备:"+e.getMessage());
        }
    }

    //更新指定平面图设备（包括添加和删除）（之前获取的设备会被覆盖）
    @RequiresPermissions(value={"002006003","SUPERADMIN"}, logical= Logical.OR)
    @ApiOperation(value = "更新指定平面图设备（包括添加和删除）（之前获取的设备会被覆盖）")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @ViLog(operEvent = "更新指定平面图设备", operType =2)//日志记录
    @PostMapping("/deviceToPlan")
    @SuppressWarnings("unchecked")
    public ApiResult<PageListData<DeviceToPlan>> updateDeviceToPlanByPlanId(HttpServletRequest request, @RequestBody DeviceToPlan[] item){
        try {
            long planId = Long.parseLong(request.getParameter("planId"));
            deviceToPlanService.updateDeviceToPlanByPlanId(planId,item);
            return ApiResult.success(deviceToPlanService.getItemByPlanId(planId));
        } catch (SwallowException e) {
            log.error("更新指定平面图设备出错:"+e.getMessage(),e);
            return ApiResult.fail("更新指定平面图设备出错:"+e.getMessage());
        }
    }


    //获取指定平面图设备具有可被查看权限的设备
    @ApiOperation(value = "获取指定平面图设备具有可被查看权限的设备")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "planId", value = "平面Id", required = true, dataType = "long")
    })
    @GetMapping("/deviceToPlanPower")
    @SuppressWarnings("unchecked")
    public ApiResult<List<DeviceToPlan>> getDeviceByPlanId(long planId){
        try {
            return ApiResult.success(deviceToPlanService.getListByPlanId(planId));
        } catch (Exception e) {
            log.error("查询平面图设备具有可被查看权限的设备:"+e.getMessage(),e);
            return ApiResult.fail("平面图设备具有可被查看权限的设备:"+e.getMessage());
        }
    }
}
