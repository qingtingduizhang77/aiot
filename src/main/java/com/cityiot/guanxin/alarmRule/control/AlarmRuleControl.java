package com.cityiot.guanxin.alarmRule.control;


import com.cityiot.guanxin.alarmRule.entity.AlarmRule;
import com.cityiot.guanxin.alarmRule.service.AlarmRuleService;
import com.cityiot.guanxin.deviceMoxing.entity.DeviceMoxing;
import com.cityiot.guanxin.log.annotation.ViLog;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.jpaquery.repository.annotations.FieldPath;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 告警规则配置接口
 * @author Guoyz
 *
 */
@RestController
@Api(tags = "告警规则配置接口")
@RequestMapping("/api/alarmRule")
public class AlarmRuleControl {
    private static final Logger log = LoggerFactory.getLogger(AlarmRuleControl.class);

    @Autowired
    private AlarmRuleService service;

    /**
     * 查询对象
     */
    @ApiModel(value="查询对象")
    @JoinEntity(mainEnityClass = AlarmRule.class,joinEntityClass = DeviceMoxing.class, mainFiledName = "deviceParameterId",name = "id",fieldType = Long.class)
    static class QueryBean extends BasePageQueryBean {

        @Like
        @ApiModelProperty("告警规则名称")
        private String name;

        @Like
        @ApiModelProperty("设备模型名称")
        @FieldPath(joinEntityClass = DeviceMoxing.class,name = "name",joinEntityAlias = AlarmRule.T_DeviceMoxing_AlarmRule)
        private String deviceParameterName;

        //@JoinEntity(name = "id",joinEntityClass = DeviceMoxing.class)
        //private Long deviceParameterId;

        @CnName("告警类型")//10:故障,20:警报,30:其他
        @ApiModelProperty("告警类型")
        private Long alarmType;

        @CnName("告警级别")//10:紧急,20:重要,30:一般
        @ApiModelProperty("告警级别")
        private Long alarmLevel;

        @CnName("修改时间")
        @ApiModelProperty("修改时间")
        private Date lastmodi;

        @ApiModelProperty
        @CnName("告警状态")//1-有效，0-禁用。默认1有效
        private Integer status;

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

        public String getDeviceParameterName() {
            return deviceParameterName;
        }

        public void setDeviceParameterName(String deviceParameterName) {
            this.deviceParameterName = deviceParameterName;
        }

        public Long getAlarmType() {
            return alarmType;
        }

        public void setAlarmType(Long alarmType) {
            this.alarmType = alarmType;
        }

        public Long getAlarmLevel() {
            return alarmLevel;
        }

        public void setAlarmLevel(Long alarmLevel) {
            this.alarmLevel = alarmLevel;
        }

        public Date getLastmodi() {
            return lastmodi;
        }

        public void setLastmodi(Date lastmodi) {
            this.lastmodi = lastmodi;
        }
    }

    /**
     * 新增告警规则对象
     * @param item
     * @return
     */
    @ViLog(operEvent = "新增告警规则对象", operType =1)//日志记录
    @ApiOperation(value = "新增告警规则对象")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @SuppressWarnings("unchecked")
    @PutMapping()
    public ApiResult<AlarmRule> insertItem(@RequestBody AlarmRule item) {
        try {
            return ApiResult.success(service.insertItem(item));
        } catch (Exception e) {
            log.error("新增告警规则对象出错:"+e.getMessage(),e);
            return ApiResult.fail("新增告警规则对象出错:"+e.getMessage());
        }
    }

    /**
     * 删除告警规则对象
     * @return
     */
    @ViLog(operEvent = "删除告警规则对象", operType =3)//日志记录
    @ApiOperation(value = "删除告警规则对象")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true),
            @ApiImplicitParam(name = "ids", value = "告警规则Ids数组：long[]", required = true, dataType = "long[]")
    })
    @DeleteMapping
    @Transactional
    public BaseApiResult deleteItemById(@RequestBody long []ids) {
        try {
            for (long id : ids) {
                service.deleteItemByAlarmRuleId(id);
            }
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("删除告警规则对象出错:"+e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return BaseApiResult.failResult(500,"删除告警规则对象出错:"+e.getMessage());
        }
    }


    /**
     * 修改告警规则对象
     * @param item
     * @return
     */
    @ApiOperation(value = "修改告警规则对象")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @ViLog(operEvent = "修改告警规则对象", operType =2)//日志记录
    @PostMapping
    @SuppressWarnings("unchecked")
    public ApiResult<AlarmRule> updateItem(@RequestBody AlarmRule item){
        try {
            return ApiResult.success(service.updateItem(item));
        } catch (Exception e) {
            log.error("修改告警规则对象出错:"+e.getMessage(),e);
            return ApiResult.fail("修改告警规则对象出错:"+e.getMessage());
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
    public ApiResult<PageListData<AlarmRule>> query(@RequestBody QueryBean query){
        try {
            return ApiResult.success(service.getAllItemPageByQuerybean(query));
        } catch (Exception e) {
            log.error("查询告警规则对象出错:"+e.getMessage(),e);
            return ApiResult.fail("查询告警规则对象出错:"+e.getMessage());
        }
    }

    @ApiOperation(value = "通过告警规则Id进行查询对象数据")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "id", value = "告警规则Id", required = true, dataType = "long")
    })
    @RequestMapping("{id}")
    @SuppressWarnings("unchecked")
    public ApiResult<AlarmRule> getItemById(@PathVariable long id){
        try {
            return ApiResult.success(service.getItemById(id));
        } catch (Exception e) {
            log.error("查询告警规则对象出错:"+e.getMessage(),e);
            return ApiResult.fail("查询告警规则对象出错:"+e.getMessage());
        }
    }

    /** 查询以code为分组的告警信息数量
     * @return
     */
    @ApiOperation(value = "查询以code为分组的告警信息数量")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true),
            @ApiImplicitParam(name = "code", value = "code", required = true, dataType = "String")
    })
    @RequestMapping("/getCodeCountWarning")
    @SuppressWarnings("unchecked")
    public ApiResult<List<Map<String, Object>>> getCodeCountWarning(String code) {
        return ApiResult.success(service.getCodeCountWarning(code));
    }
}
