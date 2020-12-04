package com.cityiot.guanxin.org.control;

import com.cityiot.guanxin.log.annotation.ViLog;
import com.cityiot.guanxin.organization.entity.OrgAreaRelation;
import com.cityiot.guanxin.organization.entity.OrgMember;
import com.cityiot.guanxin.organization.entity.Organization;
import com.cityiot.guanxin.organization.entity.QOrganization;
import com.cityiot.guanxin.organization.entity.vo.AllocateOrgAreaVo;
import com.cityiot.guanxin.organization.entity.vo.OrganizationVo;
import com.cityiot.guanxin.organization.service.OrgAreaRelationService;
import com.cityiot.guanxin.organization.service.OrgMemberService;
import com.cityiot.guanxin.organization.service.OrganizationService;

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
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

import java.util.Date;
import java.util.List;
//@Transactional
/**
 * 组织管理访问API接口
 * @author zhengjc
 *
 */
@RestController
@Api(tags = " 组织管理访问API接口")
@RequestMapping("/api/org")
public class OrganizationControl {

    private static final Logger log = LoggerFactory.getLogger(OrganizationControl.class);

    @Autowired
    private OrganizationService service;

    @Autowired
    private OrgMemberService orgMemberService;

    @Autowired
    private OrgAreaRelationService orgAreaRelationService;

    /**
     * 查询对象
     * @author zhengjc
     *
     */
    @ApiModel(value="组织查询对象")
    static class QueryBean extends BasePageQueryBean {

    	@ApiModelProperty(value="组织名称",name="name",example="")
        @Like
        private String name;

        @CnName("修改时间")
        @ApiModelProperty(value="修改时间",name="endTime",example="")
        private Date lastmodi;

        public Date getLastmodi() {
            return lastmodi;
        }

        public void setLastmodi(Date lastmodi) {
            this.lastmodi = lastmodi;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    /**
     * 新增一个组织对象
     * @param item
     * @return
     */
    @ApiOperation(value = "新增一个组织对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @ViLog(operEvent = "新增一个组织对象", operType =1)//日志记录
    @RequiresPermissions(value={"001008001","SUPERADMIN"}, logical= Logical.OR)
    @SuppressWarnings("unchecked")
    @PutMapping()
    public ApiResult<Organization> saveOrganization(@RequestBody OrganizationVo item) {
        try {
            return ApiResult.success(service.insertItem(item.getOrganization(),item.getReadyGiveUserIds()));
        } catch (SwallowException e) {
            log.error("新增组织对象出错:"+e.getMessage(),e);
            return ApiResult.fail("新增组织对象出错:"+e.getMessage());
        }
    }


    /**
     * 删除组织对象
     * @return
     */
    @ApiOperation(value = "删除组织对象")
  	@ApiImplicitParams({
  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
  		@ApiImplicitParam(name = "ids", value = "组织IDs数组", required = true,allowMultiple=true, dataType = "Long")
  		})
    @ViLog(operEvent = "删除组织对象", operType =3)//日志记录
    @RequiresPermissions(value={"001008002","SUPERADMIN"}, logical= Logical.OR)
    @DeleteMapping
    public BaseApiResult deleteOrganization(@RequestBody long []ids) {
        try {
            for(var id:ids)
                service.deleteItemById(id);
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("删除组织对象出错:"+e.getMessage(),e);
            return BaseApiResult.failResult(500,"删除组织对象出错:"+e.getMessage());
        }
    }


    /**
     * 修改组织对象
     * @param item
     * @return
     */
    @ApiOperation(value = "修改组织对象")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @ViLog(operEvent = "修改组织对象", operType =2)//日志记录
    @PostMapping
    @RequiresPermissions(value={"001008003","SUPERADMIN"}, logical= Logical.OR)
    @SuppressWarnings("unchecked")
    public ApiResult<Organization> updateOrganization(@RequestBody OrganizationVo item){
        try {
            return ApiResult.success(service.updateItem(item.getOrganization(),item.getReadyGiveUserIds(),item.getReadyGiveUpUserIds()));
        } catch (SwallowException e) {
            log.error("修改组织对象出错:"+e.getMessage(),e);
            return ApiResult.fail("修改组织对象出错:"+e.getMessage());
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
    @RequiresPermissions(value={"001008004","SUPERADMIN"}, logical= Logical.OR)
    @SuppressWarnings("unchecked")
    public ApiResult<PageListData<Organization>> query(@RequestBody OrganizationControl.QueryBean queryBean){
        try {
            return ApiResult.success(service.getAllItemPageByQuerybean(queryBean, query ->
                    query.orderBy(QOrganization.organization.orderNumber.asc())));
        } catch (Exception e) {
            log.error("查询组织对象出错:"+e.getMessage(),e);
            return ApiResult.fail("查询组织对象出错:"+e.getMessage());
        }
    }

    /**
     * 查询组织对象
     * @param id
     * @return
     */
    @ApiOperation(value = "查询组织对象")
  	@ApiImplicitParams({
  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
  		@ApiImplicitParam(name = "id", value = "组织Id", required = true, dataType = "long")
  		})
    @RequestMapping("{id}")
    @SuppressWarnings("unchecked")
    public ApiResult<Organization> getItemById(@PathVariable long id){
        try {
            return ApiResult.success(service.getItemById(id));
        } catch (Exception e) {
            log.error("查询组织对象出错:"+e.getMessage(),e);
            return ApiResult.fail("查询组织对象出错:"+e.getMessage());
        }
    }


    /** 通过组织Id获取所有的成员用户
     * @param orgId
     * @return
     */
    @ApiOperation(value = "通过组织Id获取所有的成员用户")
  	@ApiImplicitParams({
  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
  		@ApiImplicitParam(name = "orgId", value = "组织Id", required = true, dataType = "long")
  		})
    @RequestMapping("getAllUserByOrgId/{orgId}")
    public ApiResult<List<OrgMember>> getAllUserByOrgId(@PathVariable long orgId) {
        return ApiResult.success(orgMemberService.getAllUserByOrgId(orgId));
    }


    /** 组织区域分配
     * @return
     */
    @ApiOperation(value = "组织区域分配")
  	@ApiImplicitParams({
  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true)
  		})
    @RequestMapping("allocateOrgArea")
    public ApiResult allocateOrgArea(@RequestBody AllocateOrgAreaVo allocateOrgAreaVo) {
        orgAreaRelationService.allocateOrgArea(allocateOrgAreaVo.getOrgId(),
                allocateOrgAreaVo.getReadyGiveUpAreaIds(),
                allocateOrgAreaVo.getReadyGiveAreaIds());
        return ApiResult.success("分配区域成功");
    }

    /** 通过组织Id获取关联的区域
     * @param orgId
     * @return
     */
    @ApiOperation(value = "通过组织Id获取关联的区域")
  	@ApiImplicitParams({
  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
  		@ApiImplicitParam(name = "orgId", value = "组织Id", required = true, dataType = "long")
  		})
    @RequestMapping("getAllAreaByOrgId")
    public ApiResult<List<OrgAreaRelation>> getAllAreaByOrgId(long orgId) {
        return ApiResult.success(orgAreaRelationService.getAllUserAreaRelationByOrgId(orgId));
    }
}
