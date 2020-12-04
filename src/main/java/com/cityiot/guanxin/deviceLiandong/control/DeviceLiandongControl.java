package com.cityiot.guanxin.deviceLiandong.control;

import com.cityiot.guanxin.deviceLiandong.entity.DeviceLiandong;
import com.cityiot.guanxin.deviceLiandong.entity.DeviceLiandongDetails;
import com.cityiot.guanxin.deviceLiandong.entity.QDeviceLiandongDetails;
import com.cityiot.guanxin.deviceLiandong.service.DeviceLiandongDetailsService;
import com.cityiot.guanxin.deviceLiandong.service.DeviceLiandongService;
import com.cityiot.guanxin.log.annotation.ViLog;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

import java.util.Date;
import java.util.List;

/**
 * 设备联动控制之控制层
 * @author Guoyz
 * createTime   2020/7/2 14:52
 */
@RestController
@Api(tags = "设备联动控制之控制层")
@RequestMapping("/api/deviceLiandong")
public class DeviceLiandongControl {
    private static final Logger log = LoggerFactory.getLogger(DeviceLiandongControl.class);
    @Autowired
    private DeviceLiandongService service;
    @Autowired
    private DeviceLiandongDetailsService DetailsServer;

    @ApiModel(value="设备联动控制查询对象")
    static class QueryBean extends BasePageQueryBean {
        // 名称
        @ApiModelProperty(value="名称",name="name",example="")
        @Like
        private String name;

        @ApiModelProperty(value="状态",name="status",example="1-启用，0-禁用")
        private Integer status;

        @ApiModelProperty(value="修改时间",name="lastmodi",example="")
        private Date lastmodi;

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

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
    }
    /**
     * 新增一个设备联动控制对象
     * @param item
     * @return
     */
    @ApiOperation(value = "新增一个设备联动控制对象")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @ViLog(operEvent = "新增一个设备联动控制对象", operType =1)//日志记录
    @PutMapping()
    @SuppressWarnings("unchecked")
    public ApiResult<DeviceLiandong> insertItem(@RequestBody DeviceLiandong item) {
        try {
            return ApiResult.success(service.insertItem(item));
        } catch (Exception e) {
            log.error("新增设备联动控制出错:"+e.getMessage(),e);
            return ApiResult.fail("新增设备联动控制出错:"+e.getMessage());
        }
    }

    /**
     * 删除设备联动控制
     * @param ids
     * @return
     */
    @ApiOperation(value = " 删除设备联动控制")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "ids", value = "设备联动控制IDs数组", required = true,allowMultiple=true, dataType = "Long")
    })
    @ViLog(operEvent = "删除设备联动控制", operType =3)//日志记录
    @DeleteMapping
    public BaseApiResult deleteItemById(@RequestBody long []ids) {
        try {
            for(var id:ids)
                service.deleteItemById(id);
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("删除设备联动控制出错:"+e.getMessage(),e);
            return BaseApiResult.failResult(500,"删除设备联动控制出错:"+e.getMessage());
        }
    }

    /**
     * 修改设备联动控制
     * @param item
     * @return
     */
    @ApiOperation(value = "修改设备联动控制")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @ViLog(operEvent = "修改设备联动控制", operType =2)//日志记录
    @PostMapping
    @SuppressWarnings("unchecked")
    public ApiResult<DeviceLiandong> updateItem(@RequestBody DeviceLiandong item){
        try {
            return ApiResult.success(service.updateItem(item));
        } catch (Exception e) {
            log.error("修改设备联动控制出错:"+e.getMessage(),e);
            return ApiResult.fail("修改设备联动控制出错:"+e.getMessage());
        }
    }

    /**
     * 通过查询bean进行分页查询数据
     * @param query
     * @return
     */
    @ApiOperation(value = "通过查询bean进行分页查询数据")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @PostMapping("/listpage")
    @SuppressWarnings("unchecked")
    public ApiResult<PageListData<DeviceLiandong>> query(@RequestBody  QueryBean query){
        try {
            return ApiResult.success(service.getAllItemPageByQuerybean(query));
        } catch (Exception e) {
            log.error("查询设备联动控制出错:"+e.getMessage(),e);
            return ApiResult.fail("查询设备联动控制出错:"+e.getMessage());
        }
    }

    @ApiOperation(value = "根据id取设备联动控制信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "id", value = "设备联动控制Id", required = true, dataType = "long")
    })
    @RequestMapping("{id}")
    @SuppressWarnings("unchecked")
    public ApiResult<DeviceLiandong> getItemById(@PathVariable long id){
        try {
            return ApiResult.success(service.getItemById(id));
        } catch (Exception e) {
            log.error("查询设备联动控制出错:"+e.getMessage(),e);
            return ApiResult.fail("查询设备联动控制出错:"+e.getMessage());
        }
    }
    /**
     * 查询源设备与目标设备列表
     * @return
     */
    @ApiOperation(value = "查询源设备与目标设备列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true),
            @ApiImplicitParam(name = "id", value = "设备联动控制Id", required = true, dataType = "Integer")
    })
    @RequestMapping("/getAllDevice/{id}")
    @SuppressWarnings("unchecked")
    public ApiResult<List<DeviceLiandongDetails>> getAllDevice(@PathVariable Integer id){
        try {
            List<DeviceLiandongDetails> allItems = DetailsServer.getAllItems(query -> query.where(QDeviceLiandongDetails.deviceLiandongDetails.deviceLiandongId.eq(id)));
            return ApiResult.success(allItems);
        } catch (Exception e){
            log.error("查询源设备与目标设备列表出错:"+e.getMessage(),e);
            return ApiResult.fail("查询源设备与目标设备列表出错:"+e.getMessage());
        }
    }


    /**
     * 添加源设备与目标设备
     * @return
     */
    @ApiOperation(value = " 添加源设备与目标设备")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "DeviceLiandongDetails", value = "源设备与目标设备", required = true, allowMultiple=true,dataType = "DeviceLiandongDetails")
    })
    @RequestMapping("/addDevice")
    @SuppressWarnings("unchecked")
    public BaseApiResult addPlan(@RequestBody DeviceLiandongDetails deviceLiandongDetails) {
        try {
            DetailsServer.insertItem(deviceLiandongDetails);
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("添加源设备与目标设备出错:"+e.getMessage(),e);
            return BaseApiResult.failResult(500,"添加源设备与目标设备出错:"+e.getMessage());
        }
    }

    /**
     * 删除源设备与目标设备
     * @return
     */
    @ApiOperation(value = "删除源设备与目标设备")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "ids", value = "设备联动控制IDs数组", required = true,allowMultiple=true, dataType = "Long")
    })
    @SuppressWarnings("unchecked")
    @RequestMapping("/cancelDevice")
    public BaseApiResult cancelPlan(@RequestBody long []ids) {
        try {
            for (long id : ids) {
                DetailsServer.deleteItemById(id);
            }
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("设备联动控制取消关联设备出错:"+e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return BaseApiResult.failResult(500,"设备联动控制取消关联设备出错:"+e.getMessage());
        }
    }



}
