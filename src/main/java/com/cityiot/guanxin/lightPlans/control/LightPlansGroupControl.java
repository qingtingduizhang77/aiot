package com.cityiot.guanxin.lightPlans.control;

import com.cityiot.guanxin.deviceInformation.entity.Deviceinformation;
import com.cityiot.guanxin.lightPlans.entity.LightPlans;
import com.cityiot.guanxin.lightPlans.entity.LightPlansGroup;
import com.cityiot.guanxin.lightPlans.service.LightPlansGroupService;
import com.cityiot.guanxin.lightPlans.service.LightPlansGroupdoService;
import com.cityiot.guanxin.log.annotation.ViLog;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

import java.util.Date;
import java.util.List;

/**
 * 路灯分组控制管理接口
 * @author Guoyz
 * createTime   2020/5/13 10:33
 */
@RestController
@Api(tags = "路灯分组控制管理接口")
@RequestMapping("/api/lightPlansGroup")
public class LightPlansGroupControl {
    private static final Logger log = LoggerFactory.getLogger(LightPlansGroupControl.class);

    @Autowired
    private LightPlansGroupService service;
    @Autowired
    private LightPlansGroupdoService lightPlansGroupdoService;
    /**
     * 查询对象
     */
    @ApiModel(value="查询对象")
    static class QueryBean extends BasePageQueryBean {

        @Like
        @ApiModelProperty("分组名称")
        private String name;

        @CnName("是否启用")//1-启用，0-禁用
        @ApiModelProperty("是否启用")
        private Integer status;

        @CnName("修改时间")
        @ApiModelProperty("修改时间")
        private Date lastmodi;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Date getLastmodi() {
            return lastmodi;
        }

        public void setLastmodi(Date lastmodi) {
            this.lastmodi = lastmodi;
        }
    }

    /**
     * 新增路灯分组控制管理对象
     * @param item
     * @return
     */
    @ViLog(operEvent = "新增路灯分组控制管理对象", operType =1)//日志记录
    @ApiOperation(value = "新增路灯分组控制管理对象")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @SuppressWarnings("unchecked")
    @PutMapping()
    public ApiResult<LightPlansGroup> insertItem(@RequestBody LightPlansGroup item) {
        try {
            return ApiResult.success(service.insertItem(item));
        } catch (Exception e) {
            log.error("新增路灯分组控制管理对象出错:"+e.getMessage(),e);
            return ApiResult.fail("新增路灯分组控制管理对象出错:"+e.getMessage());
        }
    }

    /**
     * 删除路灯分组控制管理对象
     * @return
     */
    @ViLog(operEvent = "删除路灯分组控制管理对象", operType =3)//日志记录
    @ApiOperation(value = "删除路灯分组控制管理对象")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true),
            @ApiImplicitParam(name = "ids", value = "路灯分组控制管理Ids数组：long[]", required = true, dataType = "long[]")
    })
    @DeleteMapping
    @Transactional
    public BaseApiResult deleteItemById(@RequestBody long []ids) {
        try {
            for (long id : ids) {
                service.deleteItemById(id);
                lightPlansGroupdoService.deleteItemByGroupId(id);
            }
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("删除路灯分组控制管理对象出错:"+e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return BaseApiResult.failResult(500,"删除路灯分组控制管理对象出错:"+e.getMessage());
        }
    }
    /**
     * 修改路灯分组控制管理对象
     * @param item
     * @return
     */
    @ApiOperation(value = "修改路灯分组控制管理对象")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @ViLog(operEvent = "修改路灯分组控制管理对象", operType =2)//日志记录
    @PostMapping
    @SuppressWarnings("unchecked")
    public ApiResult<LightPlansGroup> updateItem(@RequestBody LightPlansGroup item){
        try {
            return ApiResult.success(service.updateItem(item));
        } catch (Exception e) {
            log.error("修改路灯分组控制管理对象出错:"+e.getMessage(),e);
            return ApiResult.fail("修改路灯分组控制管理对象出错:"+e.getMessage());
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
    public ApiResult<PageListData<LightPlansGroup>> query(@RequestBody QueryBean query){
        try {
            return ApiResult.success(service.getAllItemPageByQuerybean(query));
        } catch (Exception e) {
            log.error("查询路灯分组控制管理对象出错:"+e.getMessage(),e);
            return ApiResult.fail("查询路灯分组控制管理对象出错:"+e.getMessage());
        }
    }



    @ApiOperation(value = "通过Id进行查询对象数据")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "id", value = "Id", required = true, dataType = "long")
    })
    @RequestMapping("{id}")
    @SuppressWarnings("unchecked")
    public ApiResult<LightPlansGroup> getItemById(@PathVariable long id){
        try {
            return ApiResult.success(service.getItemById(id));
        } catch (Exception e) {
            log.error("查询路灯分组控制管理对象出错:"+e.getMessage(),e);
            return ApiResult.fail("查询路灯分组控制管理对象出错:"+e.getMessage());
        }
    }

    /**
     * 添加路灯设备
     * @return
     */
    @ApiOperation(value = "添加路灯设备")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "id", value = "路灯分组Id", required = true, dataType = "long"),
            @ApiImplicitParam(name = "deviceIds", value = "路灯设备ids数组", required = true,allowMultiple=true, dataType = "Long")
    })
    @ViLog(operEvent = "添加路灯设备", operType =1)//日志记录
    @SuppressWarnings("unchecked")
    @RequestMapping("/addLightDevice/{id}")
    @Transactional
    public BaseApiResult addLightDevice(@PathVariable long id, @RequestBody long []deviceIds) {
        try {
            for (var deviceId : deviceIds)
                lightPlansGroupdoService.addLightDevice(id, deviceId);
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("添加路灯设备出错:"+e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return BaseApiResult.failResult(500,"添加路灯设备出错:"+e.getMessage());
        }
    }

    /**
     * 删除路灯设备
     * @return
     */
    @ApiOperation(value = "删除路灯设备")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "id", value = "路灯分组Id", required = true, dataType = "long"),
            @ApiImplicitParam(name = "deviceIds", value = "设备IDs数组", required = true, allowMultiple=true, dataType = "Long")
    })
    @ViLog(operEvent = "删除路灯设备", operType =3)//日志记录
    @SuppressWarnings("unchecked")
    @RequestMapping("/delLightDevice/{id}")
    @Transactional
    public BaseApiResult delLightDevice(@PathVariable long id, @RequestBody Long []deviceIds) {
        try {
            lightPlansGroupdoService.delLightDevice(id,deviceIds);
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("删除路灯设备出错:"+e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return BaseApiResult.failResult(500,"删除路灯设备出错:"+e.getMessage());
        }
    }


    /**
     * 路灯设备列表
     * @return
     */
    @ApiOperation(value = "路灯设备列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "id", value = "设备分组Id", required = true, dataType = "long")
    })
    @SuppressWarnings("unchecked")
    @RequestMapping("/lightDeviceList/{id}")
    public ApiResult<List<Deviceinformation>> lightDeviceList(@PathVariable long id){
        try {
            return ApiResult.success(lightPlansGroupdoService.getDeviceListById(id));
        } catch (Exception e){
            log.error("查询路灯设备列表出错:"+e.getMessage(),e);
            return ApiResult.fail("查询路灯设备列表出错:"+e.getMessage());
        }
    }

    /**
     * 添加路灯计划策略
     * @return
     */
    @ApiOperation(value = "添加路灯计划策略")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "id", value = "路灯分组Id", required = true, dataType = "long"),
            @ApiImplicitParam(name = "lightPlansIds", value = "计划策略ids数组", required = true,allowMultiple=true, dataType = "Long")
    })
    @ViLog(operEvent = "添加路灯计划策略", operType =1)//日志记录
    @SuppressWarnings("unchecked")
    @RequestMapping("/addlightPlans/{id}")
    @Transactional
    public BaseApiResult addlightPlans(@PathVariable long id, @RequestBody long []lightPlansIds) {
        try {
            for (var lightPlansId : lightPlansIds)
                lightPlansGroupdoService.addlightPlans(id,lightPlansId);
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("添加路灯计划策略出错:"+e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return BaseApiResult.failResult(500,"添加路灯计划策略出错:"+e.getMessage());
        }
    }

    /**
     * 删除路灯计划策略
     * @return
     */
     @ApiOperation(value = "删除路灯计划策略")
     @ApiImplicitParams({
     @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
     @ApiImplicitParam(name = "id", value = "路灯分组Id", required = true, dataType = "long"),
     @ApiImplicitParam(name = "lightPlansIds", value = "路灯计划策略ids数组", required = true, allowMultiple=true, dataType = "Long")
     })
     @ViLog(operEvent = "删除路灯计划策略", operType =3)//日志记录
     @SuppressWarnings("unchecked")
     @RequestMapping("/delLightPlans/{id}")
     @Transactional
     public BaseApiResult delLightPlans(@PathVariable long id, @RequestBody Long []lightPlansIds) {
     try {
         lightPlansGroupdoService.delLightPlans(id, lightPlansIds);
     return BaseApiResult.successResult();
     } catch (Exception e) {
     log.error("删除路灯计划策略出错:"+e.getMessage(),e);
     TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
     return BaseApiResult.failResult(500,"删除路灯计划策略出错:"+e.getMessage());
     }
     }


    /**
     * 路灯计划策略列表
     * @return
     */
    @ApiOperation(value = "路灯计划策略列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "id", value = "设备分组Id", required = true, dataType = "long")
    })
    @SuppressWarnings("unchecked")
    @RequestMapping("/getLightPlansList/{id}")
    public ApiResult<List<LightPlans>> getLightPlansList(@PathVariable long id){
        try {
            return ApiResult.success(lightPlansGroupdoService.getLightPlansListById(id));
        } catch (Exception e){
            log.error("查询路灯计划策略列表出错:"+e.getMessage(),e);
            return ApiResult.fail("查询路灯计划策略列表出错:"+e.getMessage());
        }
    }
}
