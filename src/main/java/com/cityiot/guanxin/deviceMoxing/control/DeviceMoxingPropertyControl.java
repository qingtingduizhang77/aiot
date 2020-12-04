package com.cityiot.guanxin.deviceMoxing.control;

import com.cityiot.guanxin.deviceMoxing.entity.DeviceMoxingProperty;
import com.cityiot.guanxin.deviceMoxing.service.DeviceMoxingPropertyService;
import com.cityiot.guanxin.log.annotation.ViLog;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

/**
 * 设备模型属性数据访问API接口
 * @author zhengjc
 *
 */
@RestController
@Api(value = "/api/deviceMoxingProperty",tags = "设备模型属性数据访问API接口")
@RequestMapping("/api/deviceMoxingProperty")
public class DeviceMoxingPropertyControl {

    private static final Logger log = LoggerFactory.getLogger(DeviceMoxingPropertyControl.class);

    @Autowired
    private DeviceMoxingPropertyService service;

    /**
     * 查询对象
     * @author aohanhe
     *
     */
    @ApiModel(value="设备模型属性数据查询对象")
    static class QueryBean extends BasePageQueryBean {

    }


    /**
     * 新增一个新的设备模型属性对象
     * @param item
     * @return
     */
    @ApiOperation(value = "新增一个新的设备模型属性对象")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @ViLog(operEvent = "新增一个新的设备模型属性对象", operType =1)//日志记录
    @SuppressWarnings("unchecked")
    @PutMapping()
    public ApiResult<DeviceMoxingProperty> saveNewDeviceMoxingProperty(@RequestBody DeviceMoxingProperty item) {
        try {
                service.isRepeatNameOrTag(item);
            return ApiResult.success(service.insertItem(item));
        } catch (Exception e) {
            log.error("新增设备模型属性对象出错:"+e.getMessage(),e);
            return ApiResult.fail("新增设备模型属性出错:"+e.getMessage());
        }
    }

    /**
     * 删除设备模型属性对象
     * @return
     */
    @ApiOperation(value = " 删除设备模型属性对象")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true),
            @ApiImplicitParam(name = "ids", value = "设备模型属性Ids数组：long[]", required = true, dataType = "long[]")
    })
    @ViLog(operEvent = "删除设备模型属性对象", operType =3)//日志记录
    @DeleteMapping
    @Transactional
    public BaseApiResult deleteDeviceMoxingProperty(@RequestBody long []ids) {
        try {
            for (var id : ids)
                service.deleteItemById(id);
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("删除设备模型属性对象出错:"+e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return BaseApiResult.failResult(500,"删除设备模型属性对象出错:"+e.getMessage());
        }
    }

    /**
     * 修改设备模型属性对象
     * @param item
     * @return
     */
    @ApiOperation(value = "修改设备模型属性对象")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @ViLog(operEvent = "修改设备模型属性对象", operType =2)//日志记录
    @PostMapping
    @SuppressWarnings("unchecked")
    public ApiResult<DeviceMoxingProperty> saveDeviceMoxingProperty(@RequestBody DeviceMoxingProperty item){
        try {
                service.isRepeatNameOrTag(item);
            return ApiResult.success(service.updateItem(item));
        } catch (Exception e) {
            log.error("修改设备模型属性对象出错:"+e.getMessage(),e);
            return ApiResult.fail("修改设备模型属性对象出错:"+e.getMessage());
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
    public ApiResult<PageListData<DeviceMoxingProperty>> query(@RequestBody QueryBean query){
        try {
            return ApiResult.success(service.getAllItemPageByQuerybean(query));
        } catch (Exception e) {
            log.error("查询设备模型属性对象出错:"+e.getMessage(),e);
            return ApiResult.fail("查询设备模型属性对象出错:"+e.getMessage());
        }
    }

    @ApiOperation(value = "通过设备模型属性Id进行查询对象数据")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "id", value = "设备模型属性Id", required = true, dataType = "long")
    })
    @RequestMapping("{id}")
    @SuppressWarnings("unchecked")
    public ApiResult<DeviceMoxingProperty> getItemById(@PathVariable long id){
        try {
            return ApiResult.success(service.getItemById(id));
        } catch (Exception e) {
            log.error("查询设备模型属性对象出错:"+e.getMessage(),e);
            return ApiResult.fail("查询设备模型属性对象出错:"+e.getMessage());
        }
    }
}
