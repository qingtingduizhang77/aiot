package com.cityiot.guanxin.deviceMoxing.control;

import com.cityiot.guanxin.deviceInformation.entity.DeviceModel;
import com.cityiot.guanxin.deviceInformation.entity.Deviceinformation;
import com.cityiot.guanxin.deviceMoxing.entity.DeviceMoxing;
import com.cityiot.guanxin.deviceMoxing.entity.DeviceMoxingProperty;
import com.cityiot.guanxin.deviceMoxing.entity.DeviceMoxingToModel;
import com.cityiot.guanxin.deviceMoxing.service.DeviceMoxingService;
import com.cityiot.guanxin.deviceMoxing.service.DeviceMoxingToPropertyService;
import com.cityiot.guanxin.log.annotation.ViLog;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import swallow.framework.jpaquery.repository.annotations.FieldPath;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

import java.util.Date;
import java.util.List;

/**
 * 设备模型数据访问API接口
 * @author zhengjc
 *
 */
@RestController
@Api(value = "/api/deviceMoxing",tags = "设备模型数据访问API接口")
@RequestMapping("/api/deviceMoxing")
public class DeviceMoxingControl {

    private static final Logger log = LoggerFactory.getLogger(DeviceMoxingControl.class);

    @Autowired
    private DeviceMoxingService service;

    @Autowired
    private DeviceMoxingToPropertyService propertyService;

    /**
     * 查询对象
     * @author aohanhe
     *
     */
    @ApiModel(value="设备模型数据查询对象")
    @JoinEntity(fieldType = Long.class ,mainFiledName = "deviceModelId" , mainEnityClass = DeviceMoxingToModel.class,
            name = "id", joinEntityClass = DeviceModel.class)
    @JoinEntity(fieldType = Long.class ,mainFiledName = "deviceMoxingId" , mainEnityClass = DeviceMoxingToModel.class,
            name = "id", joinEntityClass = DeviceMoxing.class)
    static class QueryBean extends BasePageQueryBean {

        // 分组名称
        @ApiModelProperty(value="模型名称",name="name",example="")
        @Like
        private String name;

        // 设备型号
        @ApiModelProperty(value="设备型号（多个逗号，隔开）",name="name",example="")
        @Like
        private String deviceModels;

        // 设备型号ID
        @ApiModelProperty(value="设备型号ID",name="deviceModelId",example="")
        @JoinEntity(name="id",joinEntityClass = DeviceModel.class)
        private Long deviceModelId;

        @ApiModelProperty(value="修改日期",name="lastmodi",example="")
        private Date lastmodi;

        @ApiModelProperty(value="设备设备型号",name="deviceModel",example="")
        @FieldPath(name = "deviceModel",joinEntityClass = DeviceModel.class)
        private String deviceModel;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getLastmodi() {
            return lastmodi;
        }

        public void setLastmodi(Date lastmodi) {
            this.lastmodi = lastmodi;
        }

        public Long getDeviceModelId() {
            return deviceModelId;
        }

        public void setDeviceModelId(Long deviceModelId) {
            this.deviceModelId = deviceModelId;
        }

        public String getDeviceModel() {
            return deviceModel;
        }

        public void setDeviceModel(String deviceModel) {
            this.deviceModel = deviceModel;
        }

        public String getDeviceModels() {
            return deviceModels;
        }

        public void setDeviceModels(String deviceModels) {
            this.deviceModels = deviceModels;
        }
    }

    /**
     * 新增一个新的设备模型对象
     * @param item
     * @return
     */
    @ApiOperation(value = "新增一个新的设备模型对象")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @ViLog(operEvent = "新增一个新的设备模型对象", operType =1)//日志记录
    @SuppressWarnings("unchecked")
    @PutMapping()
    public ApiResult<DeviceMoxing> saveNewDeviceMoxing(@RequestBody DeviceMoxing item) {
        try {
                service.checkModelIsRelated(item);
            return ApiResult.success(service.insertItem(item));
        } catch (Exception e) {
            log.error("新增设备模型对象出错:"+e.getMessage(),e);
            return ApiResult.fail("新增设备模型出错:"+e.getMessage());
        }
    }

    /**
     * 删除设备模型对象
     * @return
     */
    @ApiOperation(value = " 删除设备模型对象")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true),
            @ApiImplicitParam(name = "ids", value = "设备模型Ids数组：long[]", required = true, dataType = "long[]")
    })
    @ViLog(operEvent = "删除设备模型对象", operType =3)//日志记录
    @DeleteMapping
    @Transactional
    public BaseApiResult deleteDeviceMoxing(@RequestBody long []ids) {
        try {
                service.deleteItemById(ids);
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("删除设备模型对象出错:"+e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return BaseApiResult.failResult(500,"删除设备模型对象出错:"+e.getMessage());
        }
    }

    /**
     * 修改设备模型对象
     * @param item
     * @return
     */
    @ApiOperation(value = "修改设备模型对象")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @ViLog(operEvent = "修改设备模型对象", operType =2)//日志记录
    @PostMapping
    @SuppressWarnings("unchecked")
    public ApiResult<DeviceMoxing> saveDeviceMoxing(@RequestBody DeviceMoxing item){
        try {
                service.checkModelIsRelated(item);
            return ApiResult.success(service.updateItem(item));
        } catch (Exception e) {
            log.error("修改设备模型对象出错:"+e.getMessage(),e);
            return ApiResult.fail("修改设备模型对象出错:"+e.getMessage());
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
    public ApiResult<PageListData<DeviceMoxing>> query(@RequestBody QueryBean query){
        try {
            return ApiResult.success(service.getAllItemPageByQuerybean(query));
        } catch (Exception e) {
            log.error("查询设备模型对象出错:"+e.getMessage(),e);
            return ApiResult.fail("查询设备模型对象出错:"+e.getMessage());
        }
    }

    @ApiOperation(value = "通过设备模型Id进行查询对象数据")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "id", value = "设备模型Id", required = true, dataType = "long")
    })
    @RequestMapping("{id}")
    @SuppressWarnings("unchecked")
    public ApiResult<DeviceMoxing> getItemById(@PathVariable long id){
        try {
            return ApiResult.success(service.getItemById(id));
        } catch (Exception e) {
            log.error("查询设备模型对象出错:"+e.getMessage(),e);
            return ApiResult.fail("查询设备模型对象出错:"+e.getMessage());
        }
    }


    /**
     * 设备模型添加关联属性
     * @return
     */
    @ApiOperation(value = "设备模型添加关联属性")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "id", value = "设备模型Id", required = true, dataType = "long")
    })
    @ViLog(operEvent = "设备模型添加关联属性", operType =1)//日志记录
    @SuppressWarnings("unchecked")
    @RequestMapping("/add/property/{id}")
    @Transactional
    public BaseApiResult addProperty(@PathVariable long id, @RequestBody DeviceMoxingProperty item) {
        try {
                propertyService.insertItem(id, item);
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("设备模型添加关联属性出错:"+e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return BaseApiResult.failResult(500,"设备模型添加关联属性出错:"+e.getMessage());
        }
    }


    /**
     * 设备模型编辑关联属性
     * @return
     */
    @ApiOperation(value = "设备模型编辑关联属性")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "id", value = "设备模型Id", required = true, dataType = "long")
    })
    @ViLog(operEvent = "设备模型编辑关联属性", operType =1)//日志记录
    @SuppressWarnings("unchecked")
    @RequestMapping("/edit/property/{id}")
    @Transactional
    public BaseApiResult editProperty(@PathVariable long id, @RequestBody DeviceMoxingProperty item) {
        try {
                propertyService.editItem(id, item);
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("设备模型编辑关联属性出错:"+e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return BaseApiResult.failResult(500,"设备模型编辑关联属性出错:"+e.getMessage());
        }
    }

    /**
     * 设备模型取消关联属性
     * @return
     */
    @ApiOperation(value = "设备模型取消关联属性")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "id", value = "设备模型Id", required = true, dataType = "long"),
            @ApiImplicitParam(name = "propertyIds", value = "属性IDs数组", required = true, allowMultiple=true, dataType = "Long")
    })
    @ViLog(operEvent = "设备模型取消关联属性", operType =3)//日志记录
    @SuppressWarnings("unchecked")
    @RequestMapping("/cancel/property/{id}")
    @Transactional
    public BaseApiResult cancelProperty(@PathVariable long id, @RequestBody long []propertyIds) {
        try {
            for (var propertyId : propertyIds)
                propertyService.deleteItemByMoxingIdAndPropertyId(id, propertyId);
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("设备模型取消关联属性出错:"+e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return BaseApiResult.failResult(500,"设备模型取消关联属性出错:"+e.getMessage());
        }
    }

    /**
     * 设备模型关联属性列表
     * @return
     */
    @ApiOperation(value = "设备模型关联属性列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "id", value = "设备模型Id", required = true, dataType = "long")
    })
    @SuppressWarnings("unchecked")
    @RequestMapping("getPropertyList/{id}")
    public ApiResult<List<DeviceMoxingProperty>> getAllItems(@PathVariable long id){
        try {
            return ApiResult.success(propertyService.getPropertyListByMoxingId(id));
        } catch (Exception e){
            log.error("查询设备模型关联属性列表出错:"+e.getMessage(),e);
            return ApiResult.fail("查询设备模型关联属性列表出错:"+e.getMessage());
        }
    }
}
