package com.cityiot.guanxin.branchLeader.control;


import com.cityiot.guanxin.branchLeader.entity.BranchLeader;
import com.cityiot.guanxin.branchLeader.entity.vo.BranchLeaderVo;
import com.cityiot.guanxin.branchLeader.service.BranchLeaderService;
import com.cityiot.guanxin.log.annotation.ViLog;

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
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

import java.util.Date;


/**
 * @author guoyingzhao
 * 型号区域负责人配置表(前端称它为：设备运维配置)
 */
@RestController
@Api(tags = "型号区域负责人配置表接口")
@RequestMapping("/api/typeAreaOperator")
public class BranchLeaderControl {
    private static final Logger log = LoggerFactory.getLogger(BranchLeaderControl.class);

    @Autowired
    private BranchLeaderService service;

    /**
     * 查询对象
     * @author aohanhe
     *
     */
    @ApiModel(value="型号区域负责人配置查询对象")
    static class QueryBean extends BasePageQueryBean{

        // 设备型号Id
    	@ApiModelProperty(value="设备型号Id",name="deviceModelId",example="")
        private Long deviceModelId;

        @CnName("修改时间")
        @ApiModelProperty(value="修改时间",name="lastmodi",example="")
        private Date lastmodi;

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
    }

    /**
     * 新增一个型号区域负责人配置表对象
     * @param item
     * @return
     */
    @RequiresPermissions(value={"002008001","SUPERADMIN"}, logical= Logical.OR)
    @ApiOperation(value = "新增一个型号区域负责人配置表对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @ViLog(operEvent = "新增一个型号区域负责人配置表对象", operType =1)//日志记录
    @SuppressWarnings("unchecked")
    @PutMapping()
    public ApiResult<BranchLeader> saveNewOperatorOrCompanyManage(@RequestBody BranchLeaderVo item) {
        try {
            return ApiResult.success(service.insertItem01(item));
        } catch (SwallowException e) {
            log.error("新增型号区域负责人配置表出错:"+e.getMessage(),e);
            return ApiResult.fail("新增型号区域负责人配置表出错:"+e.getMessage());
        }
    }
    /**
     * 删除型号区域负责人配置表对象
     * @return
     */
    @RequiresPermissions(value={"002008002","SUPERADMIN"}, logical= Logical.OR)
    @ApiOperation(value = "删除型号区域负责人配置表对象")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true),
		@ApiImplicitParam(name = "ids", value = "型号区域负责人配置Ids数组：long[]", required = true, allowMultiple=true, dataType = "Long") 
		})
    @ViLog(operEvent = "删除型号区域负责人配置表对象", operType =3)//日志记录
    @DeleteMapping
    public BaseApiResult deleteBranchLeader(@RequestBody long []ids) {
        try {
            for(var id:ids){
                service.deleteItemById01(id);
            }

            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("删除型号区域负责人配置表出错:"+e.getMessage(),e);
            return BaseApiResult.failResult(500,"删除型号区域负责人配置表出错:"+e.getMessage());
        }
    }
    /**
     * 修改型号区域负责人配置表
     * @param item
     * @return
     */
    @RequiresPermissions(value={"002008003","SUPERADMIN"}, logical= Logical.OR)
    @ApiOperation(value = "修改型号区域负责人配置表")
   	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @ViLog(operEvent = "修改型号区域负责人配置表", operType =2)//日志记录
    @PostMapping
    @SuppressWarnings("unchecked")
    public ApiResult<BranchLeader> updateBranchLeader(@RequestBody BranchLeaderVo item){
        try {
            return ApiResult.success(service.updateItem01(item));
        } catch (SwallowException e) {
            log.error("修改型号区域负责人配置表出错:"+e.getMessage(),e);
            return ApiResult.fail("修改型号区域负责人配置表出错:"+e.getMessage());
        }
    }
    /**
     * 通过查询bean进行分页查询数据
     * @param queryBean
     * @return
     */
    @RequiresPermissions(value={"002008004","SUPERADMIN"}, logical= Logical.OR)
    @ApiOperation(value = "通过查询bean进行分页查询数据")
   	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @PostMapping("/listpage")
    @SuppressWarnings("unchecked")
    public ApiResult<PageListData<BranchLeader>> query(@RequestBody  QueryBean queryBean){
        try {
            if(queryBean.deviceModelId != null){
                Long aLong = Long.valueOf(queryBean.deviceModelId);
                queryBean.setDeviceModelId(null);
                return ApiResult.success(service.getAllByDeviceModel(queryBean,aLong));
            }else {
                return ApiResult.success(service.getAllItemPageByQuerybean(queryBean));
            }

        } catch (Exception e) {
            log.error("查询型号区域负责人配置表对象出错:"+e.getMessage(),e);
            return ApiResult.fail("查询型号区域负责人配置表对象出错:"+e.getMessage());
        }
    }

    /**
     * 根据branchId查询对象
     */
    @ApiOperation(value = " 根据branchId查询对象")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
		@ApiImplicitParam(name = "id", value = "型号区域负责人配置Id", required = true, dataType = "long")
		})
    @GetMapping
    @SuppressWarnings("unchecked")
    public ApiResult<BranchLeaderVo> getItemById(long id){
        try {
            return ApiResult.success(service.getItemById01(id));

        } catch (Exception e) {
            log.error("查询型号区域负责人配置表对象出错:"+e.getMessage(),e);
            return ApiResult.fail("查询型号区域负责人配置表对象出错:"+e.getMessage());
        }

    }


}
