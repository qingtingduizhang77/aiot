package com.cityiot.guanxin.planGroup.control;

import com.cityiot.guanxin.log.annotation.ViLog;
import com.cityiot.guanxin.planGroup.entity.PlanGroup;
import com.cityiot.guanxin.planGroup.entity.PlanGroupToPlan;
import com.cityiot.guanxin.planGroup.entity.QPlanGroup;
import com.cityiot.guanxin.planGroup.service.PlanGroupService;
import com.cityiot.guanxin.planGroup.service.PlanGroupToPlanService;

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
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import swallow.framework.exception.SwallowException;
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;
import java.util.Date;
import java.util.List;

/**
 * 平面图分组控制层
 * @author Guoyz
 *
 */
@RestController
@Api(tags = "平面图分组控制层")
@RequestMapping("/api/planGroup")
public class PlanGroupControl {
    private static final Logger log = LoggerFactory.getLogger(PlanGroupControl.class);
    @Autowired
    private PlanGroupService service;
    @Autowired
    private PlanGroupToPlanService PTPService;

    @ApiModel(value="平面图分组查询对象")
    static class QueryBean extends BasePageQueryBean {
        // 名称
    	@ApiModelProperty(value="名称",name="name",example="")
        @Like
        private String name;
        // 分组父ID
    	@ApiModelProperty(value="分组父ID",name="parentId",example="")
        private Long parentId;

    	@ApiModelProperty(value="修改时间",name="lastmodi",example="")
        private Date lastmodi;

        // 排序号
        @ApiModelProperty(value="排序号",name="orderNo",example="")
        private Long orderNo;

        public Long getParentId() {
            return parentId;
        }

        public void setParentId(Long parentId) {
            this.parentId = parentId;
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

        public Long getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(Long orderNo) {
            this.orderNo = orderNo;
        }
    }
    /**
     * 新增一个平面图分组对象
     * @param item
     * @return
     */
    @RequiresPermissions(value={"002005001","SUPERADMIN"}, logical= Logical.OR)
    @ApiOperation(value = "新增一个平面图分组对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @ViLog(operEvent = "新增一个平面图分组对象", operType =1)//日志记录
    @PutMapping()
    @SuppressWarnings("unchecked")
    public ApiResult<PlanGroup> saveNewPlanGroup(@RequestBody PlanGroup item) {
        try {
            service.checkIsGtLevel(item);// 检查是否超过9层
            return ApiResult.success(service.insertItem(item));
        } catch (Exception e) {
            log.error("新增平面图分组出错:"+e.getMessage(),e);
            return ApiResult.fail("新增平面图分组出错:"+e.getMessage());
        }
    }

    /**
     * 删除平面图分组
     * @param ids
     * @return
     */
    @RequiresPermissions(value={"002005002","SUPERADMIN"}, logical= Logical.OR)
    @ApiOperation(value = " 删除平面图分组")
  	@ApiImplicitParams({
  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
  		@ApiImplicitParam(name = "ids", value = "平面图分组IDs数组", required = true,allowMultiple=true, dataType = "Long")
  		})
    @ViLog(operEvent = "删除平面图分组", operType =3)//日志记录
    @DeleteMapping
    public BaseApiResult deletePlanGroup(@RequestBody long []ids) {
        try {
            for(var id:ids)
                if (service.getPlanGroupListByParentId(id).size() > 0) {
                    throw new Exception("有子级分组，不予以删除！");
                }else {
                service.deleteItemById(id);}
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("删除平面图分组出错:"+e.getMessage(),e);
            return BaseApiResult.failResult(500,"删除平面图分组出错:"+e.getMessage());
        }
    }

    /**
     * 修改平面图分组
     * @param item
     * @return
     */
    @RequiresPermissions(value={"002005003","SUPERADMIN"}, logical= Logical.OR)
    @ApiOperation(value = "修改平面图分组")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @ViLog(operEvent = "修改平面图分组", operType =2)//日志记录
    @PostMapping
    @SuppressWarnings("unchecked")
    public ApiResult<PlanGroup> savePlanGroup(@RequestBody PlanGroup item){
        try {
            PlanGroup group = service.getItemById(item.getParentId());// 父节点
            if (item.getParentId() == item.getId() || (group != null && group.getParentIdArr() != null &&
                    (group.getParentIdArr().contains("'"+item.getId()+"',") || group.getParentId() == item.getId()))) {
                return ApiResult.fail("节点不可移动到子节点或本节点分组上！");
            }
            service.checkIsGtLevel(item);// 检查是否超过9层
            return ApiResult.success(service.updateItem(item));
        } catch (Exception e) {
            log.error("修改平面图分组出错:"+e.getMessage(),e);
            return ApiResult.fail("修改平面图分组出错:"+e.getMessage());
        }
    }

    /**
     * 通过查询bean进行分页查询数据
     * @param query
     * @return
     */
    @RequiresPermissions(value={"002005004","SUPERADMIN"}, logical= Logical.OR)
    @ApiOperation(value = "通过查询bean进行分页查询数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @PostMapping("listpage")
    @SuppressWarnings("unchecked")
    public ApiResult<PageListData<PlanGroup>> query(@RequestBody  QueryBean query){
        try {
            return ApiResult.success(service.getAllItemPageByQuerybean(query));
        } catch (Exception e) {
            log.error("查询平面图分组出错:"+e.getMessage(),e);
            return ApiResult.fail("查询平面图分组出错:"+e.getMessage());
        }
    }

    @ApiOperation(value = "根据id取平面图分组信息")
  	@ApiImplicitParams({
  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
  		@ApiImplicitParam(name = "id", value = "平面图分组Id", required = true, dataType = "long")
  		})
    @RequestMapping("{id}")
    @SuppressWarnings("unchecked")
    public ApiResult<PlanGroup> getItemById(@PathVariable long id){
        try {
            return ApiResult.success(service.getItemById(id));
        } catch (Exception e) {
            log.error("查询平面图分组出错:"+e.getMessage(),e);
            return ApiResult.fail("查询平面图分组出错:"+e.getMessage());
        }
    }
    /**
     * 平面图分组分组列表（不分页）
     * @return
     */
    @ApiOperation(value = "平面图分组分组列表（不分页）")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @SuppressWarnings("unchecked")
    @RequestMapping("getAllItems")
    public ApiResult<List<PlanGroup>> getAllItems(){
        try {
            return ApiResult.success(service.getAllItems(query -> query.orderBy(QPlanGroup.planGroup.parentId.asc(),QPlanGroup.planGroup.orderNo.asc())));
        } catch (Exception e){
            log.error("查询平面图分组列表出错:"+e.getMessage(),e);
            return ApiResult.fail("查询平面图分组列表出错:"+e.getMessage());
        }
    }

    /**
     * 平面图分组关联平面图列表（不分页）
     * @return
     */
    @ApiOperation(value = " 平面图分组关联平面图列表（不分页）")
  	@ApiImplicitParams({
  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
  		@ApiImplicitParam(name = "id", value = "平面图分组Id", required = true, dataType = "long")
  		})
    @SuppressWarnings("unchecked")
    @RequestMapping("getPlanList/{id}")
    public ApiResult<List<PlanGroupToPlan>> getAllItems(@PathVariable long id){
        try {
            return ApiResult.success(PTPService.getPlanGroupToPlanListByGroupId(id));
        } catch (Exception e){
            log.error("查询平面图分组关联平面图列表出错:"+e.getMessage(),e);
            return ApiResult.fail("查询平面图分组关联平面图列表出错:"+e.getMessage());
        }
    }

    /**
     * 平面图分组添加关联平面图
     * @return
     */
    @ApiOperation(value = " 平面图分组关联平面图列表（不分页）")
  	@ApiImplicitParams({
  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
  		@ApiImplicitParam(name = "planGroupToPlanlist", value = "平面图与平面图分组", required = true, allowMultiple=true,dataType = "PlanGroupToPlan")
  		})
    @SuppressWarnings("unchecked")
    @RequestMapping("/add/plan")
    public BaseApiResult addPlan(@RequestBody PlanGroupToPlan[] planGroupToPlanlist) {
        try {
            for (PlanGroupToPlan planGroupToPlan : planGroupToPlanlist) {
                PTPService.insertItem(planGroupToPlan);
            }
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("平面图分组添加关联平面图出错:"+e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return BaseApiResult.failResult(500,"平面图分组添加关联平面图出错:"+e.getMessage());
        }
    }

    /**
     * 平面图分组取消关联平面图
     * @return
     */
    @ApiOperation(value = "平面图分组取消关联平面图")
  	@ApiImplicitParams({
  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
  		@ApiImplicitParam(name = "ids", value = "平面图分组IDs数组", required = true,allowMultiple=true, dataType = "Long")
  		})
    @SuppressWarnings("unchecked")
    @RequestMapping("/cancel/plan")
    public BaseApiResult cancelPlan(@RequestBody long []ids) {
        try {
            for (long id : ids) {
                PTPService.deleteItemById(id);
            }
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("平面图分组取消关联设备出错:"+e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return BaseApiResult.failResult(500,"平面图分组取消关联设备出错:"+e.getMessage());
        }
    }

    /**
     * 更新指定平图数组中的平面图序号
     * @return
     */
    @ApiOperation(value = " 更新指定平图数组中的平面图序号")
  	@ApiImplicitParams({
  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
  		@ApiImplicitParam(name = "planGroupToPlanlist", value = "平面图与平面图分组", required = true, allowMultiple=true,dataType = "PlanGroupToPlan")
  		})
    @SuppressWarnings("unchecked")
    @RequestMapping("/updataPlanList")
    public BaseApiResult updataPlanList(@RequestBody PlanGroupToPlan[] planGroupToPlanlist) {
        try {
            for (PlanGroupToPlan planGroupToPlan : planGroupToPlanlist) {
                PTPService.updateItem(planGroupToPlan);
            }
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("更新平面图序号出错:"+e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return BaseApiResult.failResult(500,"更新平面图序号出错:"+e.getMessage());
        }
    }



}
