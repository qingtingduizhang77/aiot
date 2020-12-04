package com.cityiot.guanxin.projectTotal.control;

import com.cityiot.guanxin.log.annotation.ViLog;
import com.cityiot.guanxin.projectTotal.pojo.ProjectTotal;
import com.cityiot.guanxin.projectTotal.pojo.guanWang;
import com.cityiot.guanxin.projectTotal.service.ProjectTotalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import swallow.framework.exception.SwallowException;
import swallow.framework.web.ApiResult;

import java.util.List;

/**
 * 智慧工地统计数据访问API接口
 * @author Guoyz
 * createTime   2020/7/21 11:10
 */
@RestController
@Api(tags = "智慧工地统计数据访问API接口")
@RequestMapping("/api/projectTotal")
public class ProjectTotalControl {
    private static final Logger log = LoggerFactory.getLogger(ProjectTotalControl.class);
    @Autowired
    private ProjectTotalService service;

    /**
     * 查询智慧工地统计数据
     * @return
     */
    @GetMapping("getAllItems")
    @SuppressWarnings("unchecked")
    @ApiOperation(value = " 查询智慧工地统计数据")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true)})
    public ApiResult<List<ProjectTotal>> getAllItems(){
        try {
            return ApiResult.success(service.getAllItems());
        } catch (Exception e){
            log.error("查询智慧工地统计数据列表出错:"+e.getMessage(),e);
            return ApiResult.fail("查询智慧工地统计数据列表出错:"+e.getMessage());
        }
    }
    /**
     * 查询管网数据
     * @return
     */
    @GetMapping("guanWang")
    @SuppressWarnings("unchecked")
    @ApiOperation(value = " 查询管网数据")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true)})
    public ApiResult<List<guanWang>> getAllItemsGuanWang(){
        try {
            return ApiResult.success(service.getAllItemsGuanWang());
        } catch (Exception e){
            log.error("查询管网数据列表出错:"+e.getMessage(),e);
            return ApiResult.fail("查询管网数据列表出错:"+e.getMessage());
        }
    }
    /**
     * 更新管网数据
     * @param guanWangs 对象数组
     * @return
     */
    @PostMapping("guanWang")
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "更新管网数据")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true)  ,
            @ApiImplicitParam(name = "guanWangs", value = "对象数组", required = true,allowMultiple=true)
    })
    public ApiResult<List<guanWang>> savePermission(@RequestBody guanWang []guanWangs){
        try {
            return ApiResult.success(service.updateGuanWang(guanWangs));
        } catch (SwallowException e) {
            log.error("修改权限项对象出错:"+e.getMessage(),e);
            return ApiResult.fail("修改权限项对象出错:"+e.getMessage());
        }
    }
}
