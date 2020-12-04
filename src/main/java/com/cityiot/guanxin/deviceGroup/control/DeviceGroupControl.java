package com.cityiot.guanxin.deviceGroup.control;

import com.cityiot.guanxin.deviceGroup.entity.DeviceGroup;
import com.cityiot.guanxin.deviceGroup.entity.QDeviceGroup;
import com.cityiot.guanxin.deviceGroup.service.DeviceGroupRelationService;
import com.cityiot.guanxin.deviceGroup.service.DeviceGroupService;
import com.cityiot.guanxin.deviceInformation.entity.Deviceinformation;
import com.cityiot.guanxin.log.annotation.ViLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
 * 设备分组数据访问API接口
 * @author zhengjc
 *
 */
@RestController
@Api(value = "/api/deviceGroup",tags = "设备分组数据访问API接口")
@RequestMapping("/api/deviceGroup")
public class DeviceGroupControl {

    private static final Logger log = LoggerFactory.getLogger(DeviceGroupControl.class);

    @Autowired
    private DeviceGroupService service;

    @Autowired
    private DeviceGroupRelationService relationService;

    /**
     * 查询对象
     * @author aohanhe
     *
     */
    @ApiModel(value="设备分组数据查询对象")
    static class QueryBean extends BasePageQueryBean {

        // 分组名称
    	@ApiModelProperty(value="分组名称",name="name",example="")
        @Like
        private String name;

        // 分组父ID
    	@ApiModelProperty(value="分组父ID",name="parentId",example="")
        private Long parentId;

    	@ApiModelProperty(value="修改日期",name="lastmodi",example="")
        private Date lastmodi;

        // 排序号
        @ApiModelProperty(value="排序号",name="orderNo",example="")
        private Long orderNo;

        @ApiModelProperty(value="区域编码",name="areaCode",example="")
        private Long areaCode;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

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

        public Long getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(Long orderNo) {
            this.orderNo = orderNo;
        }

        public Long getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(Long areaCode) {
            this.areaCode = areaCode;
        }
    }

    /**
     * 新增一个新的设备分组对象
     * @param item
     * @return
     */
    @RequiresPermissions(value={"002009001","SUPERADMIN"}, logical= Logical.OR)
    @ApiOperation(value = "新增一个新的设备分组对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @ViLog(operEvent = "新增一个新的设备分组对象", operType =1)//日志记录
    @SuppressWarnings("unchecked")
    @PutMapping()
    public ApiResult<DeviceGroup> saveNewDeviceGroup(@RequestBody DeviceGroup item) {
        try {
                service.checkIsGtLevel(item);// 检查是否超过9层
            return ApiResult.success(service.insertItem(item));
        } catch (Exception e) {
            log.error("新增设备分组对象出错:"+e.getMessage(),e);
            return ApiResult.fail("新增设备分组出错:"+e.getMessage());
        }
    }

    /**
     * 删除设备分组对象
     * @return
     */
    @RequiresPermissions(value={"002009002","SUPERADMIN"}, logical= Logical.OR)
    @ApiOperation(value = " 删除设备分组对象")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true),
		@ApiImplicitParam(name = "ids", value = "设备分组Ids数组：long[]", required = true, dataType = "long[]") 
		})
    @ViLog(operEvent = "删除设备分组对象", operType =3)//日志记录
    @DeleteMapping
    @Transactional
    public BaseApiResult deleteDeviceGroup(@RequestBody long []ids) {
        try {
                service.deleteItemById(ids);
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("删除设备分组对象出错:"+e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return BaseApiResult.failResult(500,"删除设备分组对象出错:"+e.getMessage());
        }
    }

    /**
     * 修改设备分组对象
     * @param item
     * @return
     */
    @RequiresPermissions(value={"002009003","SUPERADMIN"}, logical= Logical.OR)
    @ApiOperation(value = "修改设备分组对象")
   	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @ViLog(operEvent = "修改设备分组对象", operType =2)//日志记录
    @PostMapping
    @SuppressWarnings("unchecked")
    public ApiResult<DeviceGroup> saveDeviceGroup(@RequestBody DeviceGroup item){
        try {
            DeviceGroup group = service.getItemById(item.getParentId());// 父节点
            if (item.getParentId() == item.getId() || (group != null && group.getParentIdArr() != null &&
                    (group.getParentIdArr().contains("'"+item.getId()+"',") || group.getParentId() == item.getId()))) {
                return ApiResult.fail("节点不可移动到子节点或本节点分组上！");
            }
                service.checkIsGtLevel(item);// 检查是否超过9层
            return ApiResult.success(service.updateItem(item));
        } catch (Exception e) {
            log.error("修改设备分组对象出错:"+e.getMessage(),e);
            return ApiResult.fail("修改设备分组对象出错:"+e.getMessage());
        }
    }

    /**
     * 通过查询bean进行分页查询数据
     * @param query
     * @return
     */
    @RequiresPermissions(value={"002009004","SUPERADMIN"}, logical= Logical.OR)
    @ApiOperation(value = "通过查询bean进行分页查询数据")
   	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @PostMapping("listpage")
    @SuppressWarnings("unchecked")
    public ApiResult<PageListData<DeviceGroup>> query(@RequestBody QueryBean query){
        try {
            return ApiResult.success(service.getAllItemPageByQuerybean(query));
        } catch (Exception e) {
            log.error("查询设备分组对象出错:"+e.getMessage(),e);
            return ApiResult.fail("查询设备分组对象出错:"+e.getMessage());
        }
    }

    @ApiOperation(value = "通过设备分组Id进行查询角色数据")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
		@ApiImplicitParam(name = "id", value = "设备分组Id", required = true, dataType = "long")
		})
    @RequestMapping("{id}")
    @SuppressWarnings("unchecked")
    public ApiResult<DeviceGroup> getItemById(@PathVariable long id){
        try {
            return ApiResult.success(service.getItemById(id));
        } catch (Exception e) {
            log.error("查询设备分组对象出错:"+e.getMessage(),e);
            return ApiResult.fail("查询设备分组对象出错:"+e.getMessage());
        }
    }

    /**
     * 设备分组列表
     * @return
     */
    @ApiOperation(value = "设备分组列表，不分页。列表控件")
   	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @SuppressWarnings("unchecked")
    @RequestMapping("getAllItems")
    public ApiResult<List<DeviceGroup>> getAllItems(@RequestBody QueryBean queryBean){
        try {
            return ApiResult.success(service.getAllItems(query -> {
                    if (queryBean.getAreaCode() != null) {
                        query.where(QDeviceGroup.deviceGroup.areaCode.eq(queryBean.getAreaCode()));
                    }
                    query.orderBy(QDeviceGroup.deviceGroup.parentId.asc(),QDeviceGroup.deviceGroup.orderNo.asc());
                    return query;
            }));
        } catch (Exception e){
            log.error("查询设备分组列表出错:"+e.getMessage(),e);
            return ApiResult.fail("查询设备分组列表出错:"+e.getMessage());
        }
    }

    /**
     * 设备分组添加关联设备
     * @return
     */
    @ApiOperation(value = "设备分组添加关联设备")
  	@ApiImplicitParams({
  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
  		@ApiImplicitParam(name = "id", value = "设备分组Id", required = true, dataType = "long"),
  		@ApiImplicitParam(name = "deviceIds", value = "设备ID", required = true,allowMultiple=true, dataType = "Long")
  		})
    @ViLog(operEvent = "新增一个新的设备关联", operType =1)//日志记录
    @SuppressWarnings("unchecked")
    @RequestMapping("/add/device/{id}")
    @Transactional
    public BaseApiResult addDevice(@PathVariable long id, @RequestBody long []deviceIds) {
        try {
            for (var deviceId : deviceIds)
                relationService.insertItem(id, deviceId);
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("设备分组添加关联设备出错:"+e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return BaseApiResult.failResult(500,"设备分组添加关联设备出错:"+e.getMessage());
        }
    }

    /**
     * 设备分组取消关联设备
     * @return
     */
    @ApiOperation(value = "设备分组取消关联设备")
  	@ApiImplicitParams({
  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
  		@ApiImplicitParam(name = "id", value = "设备分组Id", required = true, dataType = "long"),
  		@ApiImplicitParam(name = "deviceIds", value = "设备IDs数组", required = true, allowMultiple=true, dataType = "Long")
  		})
    @ViLog(operEvent = "取消设备关联对象", operType =3)//日志记录
    @SuppressWarnings("unchecked")
    @RequestMapping("/cancel/device/{id}")
    @Transactional
    public BaseApiResult cancelDevice(@PathVariable long id, @RequestBody long []deviceIds) {
        try {
            for (var deviceId : deviceIds)
                relationService.deleteItemByGroupIdAndDeviceId(id, deviceId);
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("设备分组取消关联设备出错:"+e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return BaseApiResult.failResult(500,"设备分组取消关联设备出错:"+e.getMessage());
        }
    }


    /**
     * 设备分组关联设备列表
     * @return
     */
    @ApiOperation(value = "设备分组关联设备列表")
  	@ApiImplicitParams({
  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
  		@ApiImplicitParam(name = "id", value = "设备分组Id", required = true, dataType = "long")
  		})
    @SuppressWarnings("unchecked")
    @RequestMapping("getDeviceList/{id}")
    public ApiResult<List<Deviceinformation>> getAllItems(@PathVariable long id){
        try {
            return ApiResult.success(relationService.getDeviceInfoListByGroupId(id));
        } catch (Exception e){
            log.error("查询设备分组关联设备列表出错:"+e.getMessage(),e);
            return ApiResult.fail("查询设备分组关联设备列表出错:"+e.getMessage());
        }
    }
}
