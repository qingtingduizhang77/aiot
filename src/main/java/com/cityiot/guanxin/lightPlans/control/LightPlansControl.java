package com.cityiot.guanxin.lightPlans.control;

import com.cityiot.guanxin.lightPlans.entity.LightPlans;
import com.cityiot.guanxin.lightPlans.service.LightPlansService;
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

/**
 * 路灯计划策略管理接口
 * @author Guoyz
 * createTime   2020/5/13 10:33
 */
@RestController
@Api(tags = "路灯计划策略管理接口")
@RequestMapping("/api/lightPlans")
public class LightPlansControl {
    private static final Logger log = LoggerFactory.getLogger(LightPlansControl.class);

    @Autowired
    private LightPlansService service;
    /**
     * 查询对象
     */
    @ApiModel(value="查询对象")
    static class QueryBean extends BasePageQueryBean {

        @Like
        @ApiModelProperty("名称")
        private String name;

        @CnName("类型")//1-日常;2-节假日
        @ApiModelProperty("类型")
        private Integer dateType;

        @CnName("修改时间")
        @ApiModelProperty("修改时间")
        private Date lastmodi;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getDateType() {
            return dateType;
        }

        public void setDateType(Integer dateType) {
            this.dateType = dateType;
        }

        public Date getLastmodi() {
            return lastmodi;
        }

        public void setLastmodi(Date lastmodi) {
            this.lastmodi = lastmodi;
        }
    }

    /**
     * 新增路灯计划策略管理对象
     * @param item
     * @return
     */
    @ViLog(operEvent = "新增路灯计划策略管理对象", operType =1)//日志记录
    @ApiOperation(value = "新增路灯计划策略管理对象")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @SuppressWarnings("unchecked")
    @PutMapping()
    public ApiResult<LightPlans> insertItem(@RequestBody LightPlans item) {
        try {
            return ApiResult.success(service.insertItem(item));
        } catch (Exception e) {
            log.error("新增路灯计划策略管理对象出错:"+e.getMessage(),e);
            return ApiResult.fail("新增路灯计划策略管理对象出错:"+e.getMessage());
        }
    }

    /**
     * 删除路灯计划策略管理对象
     * @return
     */
    @ViLog(operEvent = "删除路灯计划策略管理对象", operType =3)//日志记录
    @ApiOperation(value = "删除路灯计划策略管理对象")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true),
            @ApiImplicitParam(name = "ids", value = "路灯计划策略管理Ids数组：long[]", required = true, dataType = "long[]")
    })
    @DeleteMapping
    @Transactional
    public BaseApiResult deleteItemById(@RequestBody long []ids) {
        try {
            for (long id : ids) {
                service.deleteItemById(id);
            }
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("删除路灯计划策略管理对象出错:"+e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return BaseApiResult.failResult(500,"删除路灯计划策略管理对象出错:"+e.getMessage());
        }
    }
    /**
     * 修改路灯计划策略管理对象
     * @param item
     * @return
     */
    @ApiOperation(value = "修改路灯计划策略管理对象")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @ViLog(operEvent = "修改路灯计划策略管理对象", operType =2)//日志记录
    @PostMapping
    @SuppressWarnings("unchecked")
    public ApiResult<LightPlans> updateItem(@RequestBody LightPlans item){
        try {
            return ApiResult.success(service.updateItem(item));
        } catch (Exception e) {
            log.error("修改路灯计划策略管理对象出错:"+e.getMessage(),e);
            return ApiResult.fail("修改路灯计划策略管理对象出错:"+e.getMessage());
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
    public ApiResult<PageListData<LightPlans>> query(@RequestBody QueryBean query){
        try {
            return ApiResult.success(service.getAllItemPageByQuerybean(query));
        } catch (Exception e) {
            log.error("查询路灯计划策略管理对象出错:"+e.getMessage(),e);
            return ApiResult.fail("查询路灯计划策略管理对象出错:"+e.getMessage());
        }
    }



    @ApiOperation(value = "通过Id进行查询对象数据")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "id", value = "Id", required = true, dataType = "long")
    })
    @RequestMapping("{id}")
    @SuppressWarnings("unchecked")
    public ApiResult<LightPlans> getItemById(@PathVariable long id){
        try {
            return ApiResult.success(service.getItemById(id));
        } catch (Exception e) {
            log.error("查询路灯计划策略管理对象出错:"+e.getMessage(),e);
            return ApiResult.fail("查询路灯计划策略管理对象出错:"+e.getMessage());
        }
    }
}
