package com.cityiot.guanxin.log.control;

import com.cityiot.guanxin.common.utils.DateUtil;
import com.cityiot.guanxin.log.entity.LogOperation;
import com.cityiot.guanxin.log.entity.QLogOperation;
import com.cityiot.guanxin.log.service.LogOperationService;

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
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

import java.util.Date;

/**
 * 操作日志访问API接口
 * @author Guoyz
 *
 */
@RestController
@Api(tags = "操作日志访问API接口")
@RequestMapping("/api/LogOperation")
public class LogOperationControl {
    private static final Logger log = LoggerFactory.getLogger(LogOperationControl.class);
    @Autowired
    private LogOperationService service;

    @ApiModel(value="操作日志查询对象")
    static class QueryBean extends BasePageQueryBean {

        // 操作人账号
    	@ApiModelProperty(value="操作人账号",name="identity",example="")
        private String identity;
        //请求类型
    	@ApiModelProperty(value="请求类型",name="reqType",example="")
        private String reqType;
        //操作的url
    	@ApiModelProperty(value="操作的url",name="operUrl",example="")
        private String operUrl;
        //开始时间
    	@ApiModelProperty(value="开始时间",name="startTime",example="")
        private Date startTime;
        //截至时间
    	@ApiModelProperty(value="截至时间",name="endTime",example="")
        private Date endTime;

        @CnName("修改时间")
        @ApiModelProperty(value="修改时间",name="endTime",example="")
        private Date lastmodi;

        public Date getLastmodi() {
            return lastmodi;
        }

        public void setLastmodi(Date lastmodi) {
            this.lastmodi = lastmodi;
        }

        public String getOperUrl() {
            return operUrl;
        }

        public void setOperUrl(String operUrl) {
            this.operUrl = operUrl;
        }

        public String getIdentity() {
            return identity;
        }

        public void setIdentity(String identity) {
            this.identity = identity;
        }

        public String getReqType() {
            return reqType;
        }

        public void setReqType(String reqType) {
            this.reqType = reqType;
        }

        public Date getStartTime() {
            return startTime;
        }

        public void setStartTime(Date startTime) {
            this.startTime = startTime;
        }

        public Date getEndTime() {
            return endTime;
        }

        public void setEndTime(Date endTime) {
            this.endTime = endTime;
        }
    }



    /**
     * 删除操作日志
     * @param ids
     * @return
     */
    @ApiOperation(value = "删除操作日志")
  	@ApiImplicitParams({
  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
  		@ApiImplicitParam(name = "ids", value = "操作日志IDs数组", required = true,allowMultiple=true, dataType = "Long")
  		})
    @DeleteMapping
    @RequiresPermissions(value={"001005002","SUPERADMIN"}, logical=Logical.OR)
    public BaseApiResult deleteArea(@RequestBody long []ids) {
        try {
            for(var id:ids)
                service.deleteItemById(id);
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("删除操作日志出错:"+e.getMessage(),e);
            return BaseApiResult.failResult(500,"删除操作日志出错:"+e.getMessage());
        }
    }

    /**
     * 通过查询bean进行分页查询数据
     * @param queryBean
     * @return
     */
    @ApiOperation(value = "通过查询bean进行分页查询数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @PostMapping("listpage")
    @SuppressWarnings("unchecked")
    @RequiresPermissions(value={"001005004","SUPERADMIN"}, logical=Logical.OR)
    public ApiResult<PageListData<LogOperation>> query(@RequestBody LogOperationControl.QueryBean queryBean){
        try {
            //查询对象
            var qm = QLogOperation.logOperation;
            Date startTime = queryBean.getStartTime();
            Date endTime = queryBean.getEndTime();
            queryBean.setStartTime(null);
            queryBean.setEndTime(null);
            //返回结果
            var bean =service.getRepsitory().getAllItemPageByQuerybean(queryBean,
                    query ->{//条件查询
                        //操作人账号
                        if(queryBean.getIdentity()!=null&&queryBean.getIdentity()!=""){
                            query.where(qm.identity.eq(queryBean.getIdentity()));
                        }
                        //请求类型(get,post,put,delete)
                        if(queryBean.getReqType()!=null&&queryBean.getReqType()!=""){
                            query.where(qm.reqType.eq(queryBean.getReqType()));
                        }
                        //操作URL
                        if(queryBean.getOperUrl()!=null&&queryBean.getOperUrl()!=""){
                            query.where(qm.operUrl.eq(queryBean.getOperUrl()));
                        }
                        //开始和结束字段
                        if(startTime != null && endTime != null ){
                            query.where(qm.operTime.between(startTime, endTime));
                        }else if(startTime!=null){
                            query.where(qm.operTime.after(startTime));
                        }else if(endTime!=null){
                            query.where(qm.operTime.before(endTime));
                        }
                        return query;
                    }
            );
            return ApiResult.success(bean);
            //return ApiResult.success(service.getAllItemPageByQuerybean(query));
        } catch (Exception e) {
            log.error("查询操作日志出错:"+e.getMessage(),e);
            return ApiResult.fail("查询操作日志出错:"+e.getMessage());
        }
    }

    @ApiOperation(value = "根据id取操作日信息")
  	@ApiImplicitParams({
  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
  		@ApiImplicitParam(name = "id", value = "操作日Id", required = true, dataType = "long")
  		})
    @RequestMapping("{id}")
    @SuppressWarnings("unchecked")
    @RequiresPermissions(value={"001005005","SUPERADMIN"}, logical=Logical.OR)
    public ApiResult<LogOperation> getItemById(@PathVariable long id){
        try {
            return ApiResult.success(service.getItemById(id));
        } catch (Exception e) {
            log.error("查询操作日志出错:"+e.getMessage(),e);
            return ApiResult.fail("查询操作日志出错:"+e.getMessage());
        }
    }

}
