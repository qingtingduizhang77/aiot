package com.cityiot.guanxin.devicePermission.control;

import com.cityiot.guanxin.deviceGroup.entity.DeviceGroup;
import com.cityiot.guanxin.deviceInformation.entity.DeviceBrand;
import com.cityiot.guanxin.deviceInformation.entity.DeviceModel;
import com.cityiot.guanxin.deviceInformation.entity.DeviceType;
import com.cityiot.guanxin.deviceInformation.entity.Deviceinformation;
import com.cityiot.guanxin.devicePermission.entity.DevicePermission;
import com.cityiot.guanxin.devicePermission.service.DevicePermissionService;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.jpaquery.repository.annotations.FieldPath;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.web.*;

import java.util.Date;
import java.util.List;

/**
 * 设备权限管理访问API接口
 * @author zhengjc
 *
 */
@RestController
@Api(tags = "设备权限管理访问API接口")
@RequestMapping("/api/devicePermission")
public class DevicePermissionControl {

    private static final Logger log = LoggerFactory.getLogger(DevicePermissionControl.class);


    @Autowired
    private DevicePermissionService service;


    /**
     * 查询对象
     * @author aohanhe
     *
     */
    @ApiModel(value="设备权限查询对象")
    static class QueryBean extends BasePageQueryBean {

        // 设备型号名称
    	@ApiModelProperty(value="设备型号名称",name="deviceModelName",example="")
        @Like
        private String deviceModelName;

        // 区域名称
    	@ApiModelProperty(value="区域名称",name="areaName",example="")
        @Like
        private String areaName;

    	@ApiModelProperty(value="修改时间",name="lastmodi",example="")
        private Date lastmodi;

        public String getDeviceModelName() {
            return deviceModelName;
        }

        public void setDeviceModelName(String deviceModelName) {
            this.deviceModelName = deviceModelName;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public Date getLastmodi() {
            return lastmodi;
        }

        public void setLastmodi(Date lastmodi) {
            this.lastmodi = lastmodi;
        }
    }

    /**
     * 查询对象
     * @author aohanhe
     *
     */
    @ApiModel(value="查询对象")
    @JoinEntity(fieldType = Long.class ,mainFiledName = "deviceBrandId" , mainEnityClass = DeviceModel.class,
            name = "id", joinEntityClass = DeviceBrand.class)
    static class QueryBeanDevice extends BasePageQueryBean{

    	@ApiModelProperty(value="设备名称",name="deviceName",example="")
        @Like
        private String deviceName;

    	@ApiModelProperty(value="设备编码",name="deviceCode",example="")
        private String deviceCode;

        // 设备型号ID
    	@ApiModelProperty(value="设备型号ID",name="deviceModelId",example="")
        @JoinEntity(name="id",joinEntityClass = DeviceModel.class)
        private Long deviceModelId;

    	@ApiModelProperty(value="设备品牌ID",name="deviceBrandId",example="")
        @FieldPath(name = "deviceBrandId",joinEntityClass = DeviceModel.class)
        private Long deviceBrandId;

    	@ApiModelProperty(value="设备工厂",name="deviceManufacturer",example="")
        @FieldPath(name = "deviceManufacturer",joinEntityClass = DeviceBrand.class)
        private String deviceManufacturer;

    	@ApiModelProperty(value="设备品牌名称",name="deviceBrandName",example="")
        @FieldPath(name = "deviceBrandName",joinEntityClass = DeviceBrand.class)
        private String deviceBrandName;

    	@ApiModelProperty(value="设备类型名称",name="deviceTypeName",example="")
        @FieldPath(name = "deviceTypeName",joinEntityClass = DeviceType.class)
        private String deviceTypeName;

        //		@FieldPath(name = "deviceTypeId",joinEntityClass = DeviceModel.class)
    	@ApiModelProperty(value="设备类型ID",name="deviceTypeId",example="")
        @JoinEntity(name = "id",joinEntityClass = DeviceType.class)
        private Long deviceTypeId;

    	@ApiModelProperty(value="设备型号名称",name="deviceModel",example="")
        @FieldPath(name = "deviceModel",joinEntityClass = DeviceModel.class)
        private String deviceModel;

        // 地址
        @Like
        private String area;

        private Date lastmodi;

        @ApiModelProperty(value="设备类型ID数组",name="deviceTypeIds",example="")
        private Long []deviceTypeIds;

        @ApiModelProperty(value="区域Id",name="areaId",example="")
        private Long areaId;

        @ApiModelProperty(value="分组Id",name="groupId",example="")
        private Long groupId;

        @ApiModelProperty(value="是否显示地图",name="isShowMap",example="0：否；1：是")
        private Integer isShowMap;

        //来自DeviceMoxing表
        @ApiModelProperty(value="设备型号ID数组",name="deviceModelIds",example="")
        @CnName("设备型号ID数组")// 多个用逗号，隔开
        private Long []deviceModelIds;

        public Long[] getDeviceModelIds() {
            return deviceModelIds;
        }

        public void setDeviceModelIds(Long[] deviceModelIds) {
            this.deviceModelIds = deviceModelIds;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getDeviceCode() {
            return deviceCode;
        }

        public void setDeviceCode(String deviceCode) {
            this.deviceCode = deviceCode;
        }

        public String getDeviceManufacturer() {
            return deviceManufacturer;
        }

        public void setDeviceManufacturer(String deviceManufacturer) {
            this.deviceManufacturer = deviceManufacturer;
        }

        public String getDeviceBrandName() {
            return deviceBrandName;
        }

        public void setDeviceBrandName(String deviceBrandName) {
            this.deviceBrandName = deviceBrandName;
        }

        public String getDeviceTypeName() {
            return deviceTypeName;
        }

        public void setDeviceTypeName(String deviceTypeName) {
            this.deviceTypeName = deviceTypeName;
        }

        public String getDeviceModel() {
            return deviceModel;
        }

        public void setDeviceModel(String deviceModel) {
            this.deviceModel = deviceModel;
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

        public Long getDeviceBrandId() {
            return deviceBrandId;
        }

        public void setDeviceBrandId(Long deviceBrandId) {
            this.deviceBrandId = deviceBrandId;
        }

        public Long getDeviceTypeId() {
            return deviceTypeId;
        }

        public void setDeviceTypeId(Long deviceTypeId) {
            this.deviceTypeId = deviceTypeId;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public Long[] getDeviceTypeIds() {
            return deviceTypeIds;
        }

        public void setDeviceTypeIds(Long[] deviceTypeIds) {
            this.deviceTypeIds = deviceTypeIds;
        }

        public Long getAreaId() {
            return areaId;
        }

        public void setAreaId(Long areaId) {
            this.areaId = areaId;
        }

        public Long getGroupId() {
            return groupId;
        }

        public void setGroupId(Long groupId) {
            this.groupId = groupId;
        }

        public Integer getIsShowMap() {
            return isShowMap;
        }

        public void setIsShowMap(Integer isShowMap) {
            this.isShowMap = isShowMap;
        }
    }

    /**
     * 查询对象
     * @author aohanhe
     *
     */
    @ApiModel(value="查询对象")
    @JoinEntity(fieldType = Long.class ,mainFiledName = "deviceBrandId" , mainEnityClass = DeviceModel.class,
            name = "id", joinEntityClass = DeviceBrand.class)
    static class QueryBeanDevice1 extends BaseQueryBean {

        @ApiModelProperty(value="设备名称",name="deviceName",example="")
        @Like
        private String deviceName;

        @ApiModelProperty(value="设备编码",name="deviceCode",example="")
        private String deviceCode;

        // 设备型号ID
        @ApiModelProperty(value="设备型号ID",name="deviceModelId",example="")
        @JoinEntity(name="id",joinEntityClass = DeviceModel.class)
        private Long deviceModelId;

        @ApiModelProperty(value="设备品牌ID",name="deviceBrandId",example="")
        @FieldPath(name = "deviceBrandId",joinEntityClass = DeviceModel.class)
        private Long deviceBrandId;

        @ApiModelProperty(value="设备工厂",name="deviceManufacturer",example="")
        @FieldPath(name = "deviceManufacturer",joinEntityClass = DeviceBrand.class)
        private String deviceManufacturer;

        @ApiModelProperty(value="设备品牌名称",name="deviceBrandName",example="")
        @FieldPath(name = "deviceBrandName",joinEntityClass = DeviceBrand.class)
        private String deviceBrandName;

        @ApiModelProperty(value="设备类型名称",name="deviceTypeName",example="")
        @FieldPath(name = "deviceTypeName",joinEntityClass = DeviceType.class)
        private String deviceTypeName;

        //		@FieldPath(name = "deviceTypeId",joinEntityClass = DeviceModel.class)
        @ApiModelProperty(value="设备类型ID",name="deviceTypeId",example="")
        @JoinEntity(name = "id",joinEntityClass = DeviceType.class)
        private Long deviceTypeId;

        @ApiModelProperty(value="设备型号名称",name="deviceModel",example="")
        @FieldPath(name = "deviceModel",joinEntityClass = DeviceModel.class)
        private String deviceModel;

        // 地址
        @Like
        private String area;

        private Date lastmodi;

        @ApiModelProperty(value="设备类型ID数组",name="deviceTypeIds",example="")
        private Long []deviceTypeIds;

        @ApiModelProperty(value="区域Id",name="areaId",example="")
        private Long areaId;

        @ApiModelProperty(value="分组Id",name="groupId",example="")
        private Long groupId;

        @ApiModelProperty(value="是否显示地图",name="isShowMap",example="0：否；1：是")
        private Integer isShowMap;

        //来自DeviceMoxing表
        @ApiModelProperty(value="设备型号ID数组",name="deviceModelIds",example="")
        @CnName("设备型号ID数组")// 多个用逗号，隔开
        private Long []deviceModelIds;

        public Long[] getDeviceModelIds() {
            return deviceModelIds;
        }

        public void setDeviceModelIds(Long[] deviceModelIds) {
            this.deviceModelIds = deviceModelIds;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getDeviceCode() {
            return deviceCode;
        }

        public void setDeviceCode(String deviceCode) {
            this.deviceCode = deviceCode;
        }

        public String getDeviceManufacturer() {
            return deviceManufacturer;
        }

        public void setDeviceManufacturer(String deviceManufacturer) {
            this.deviceManufacturer = deviceManufacturer;
        }

        public String getDeviceBrandName() {
            return deviceBrandName;
        }

        public void setDeviceBrandName(String deviceBrandName) {
            this.deviceBrandName = deviceBrandName;
        }

        public String getDeviceTypeName() {
            return deviceTypeName;
        }

        public void setDeviceTypeName(String deviceTypeName) {
            this.deviceTypeName = deviceTypeName;
        }

        public String getDeviceModel() {
            return deviceModel;
        }

        public void setDeviceModel(String deviceModel) {
            this.deviceModel = deviceModel;
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

        public Long getDeviceBrandId() {
            return deviceBrandId;
        }

        public void setDeviceBrandId(Long deviceBrandId) {
            this.deviceBrandId = deviceBrandId;
        }

        public Long getDeviceTypeId() {
            return deviceTypeId;
        }

        public void setDeviceTypeId(Long deviceTypeId) {
            this.deviceTypeId = deviceTypeId;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public Long[] getDeviceTypeIds() {
            return deviceTypeIds;
        }

        public void setDeviceTypeIds(Long[] deviceTypeIds) {
            this.deviceTypeIds = deviceTypeIds;
        }

        public Long getAreaId() {
            return areaId;
        }

        public void setAreaId(Long areaId) {
            this.areaId = areaId;
        }

        public Long getGroupId() {
            return groupId;
        }

        public void setGroupId(Long groupId) {
            this.groupId = groupId;
        }

        public Integer getIsShowMap() {
            return isShowMap;
        }

        public void setIsShowMap(Integer isShowMap) {
            this.isShowMap = isShowMap;
        }
    }


    /**
     * 新增一个新的设备权限对象
     * @param item
     * @return
     */
    @RequiresPermissions(value={"002007001","SUPERADMIN"}, logical= Logical.OR)
    @ApiOperation(value = "新增一个新的设备权限对象")
   	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @ViLog(operEvent = "新增一个新的设备权限对象", operType =1)//日志记录
    @SuppressWarnings("unchecked")
    @PutMapping()
    public ApiResult<DevicePermission> saveNewDevicePermission(@RequestBody DevicePermission item) {
        try {
            return ApiResult.success(service.insertItem(item));
        } catch (Exception e) {
            log.error("新增设备权限对象出错:"+e.getMessage(),e);
            return ApiResult.fail("新增设备权限出错:"+e.getMessage());
        }
    }

    /**
     * 删除设备权限对象
     * @return
     */
    @RequiresPermissions(value={"002007002","SUPERADMIN"}, logical= Logical.OR)
    @ApiOperation(value = "删除设备权限对象")
  	@ApiImplicitParams({
  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
  		@ApiImplicitParam(name = "ids", value = "设备权限IDs数组", required = true, dataType = "long[]")
  		})
    @ViLog(operEvent = "删除设备权限对象", operType =3)//日志记录
    @DeleteMapping
    @Transactional
    public BaseApiResult deleteDevicePermission(@RequestBody long []ids) {
        try {
                service.deleteItemById(ids);
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("删除设备权限对象出错:"+e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return BaseApiResult.failResult(500,"删除设备权限对象出错:"+e.getMessage());
        }
    }


    /**
     * 修改设备权限对象
     * @param item
     * @return
     */
    @RequiresPermissions(value={"002007003","SUPERADMIN"}, logical= Logical.OR)
    @ApiOperation(value = "修改设备权限对象")
   	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @ViLog(operEvent = "修改设备权限对象", operType =2)//日志记录
    @PostMapping
    @SuppressWarnings("unchecked")
    public ApiResult<DevicePermission> saveDevicePermission(@RequestBody DevicePermission item){
        try {
            return ApiResult.success(service.updateItem(item));
        } catch (Exception e) {
            log.error("修改设备权限对象出错:"+e.getMessage(),e);
            return ApiResult.fail("修改设备权限对象出错:"+e.getMessage());
        }
    }

    /**
     * 通过查询bean进行分页查询数据
     * @param query
     * @return
     */
    @RequiresPermissions(value={"002007004","SUPERADMIN"}, logical= Logical.OR)
    @ApiOperation(value = "通过查询bean进行分页查询数据")
   	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @PostMapping("listpage")
    @SuppressWarnings("unchecked")
    public ApiResult<PageListData<DevicePermission>> query(@RequestBody QueryBean query){
        try {
            return ApiResult.success(service.getAllItemPageByQuerybean(query));
        } catch (Exception e) {
            log.error("查询设备权限对象出错:"+e.getMessage(),e);
            return ApiResult.fail("查询设备权限对象出错:"+e.getMessage());
        }
    }

    @RequestMapping("{id}")
    @SuppressWarnings("unchecked")
    public ApiResult<DevicePermission> getItemById(@PathVariable long id){
        try {
            return ApiResult.success(service.getItemById(id));
        } catch (Exception e) {
            log.error("查询设备权限对象出错:"+e.getMessage(),e);
            return ApiResult.fail("查询设备权限对象出错:"+e.getMessage());
        }
    }

    /**
     * 获取当前用户角色下有查看权限的设备列表
     * @param query
     * @return
     */
    @ApiOperation(value = "获取当前用户角色下有查看权限的设备列表")
   	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @PostMapping("/device/listpage")
    @SuppressWarnings("unchecked")
    public ApiResult<PageListData<Deviceinformation>> queryDevicePage(@RequestBody QueryBeanDevice query){
        try {
            // 设置为null时，querybean不做此字段查询不报错（报错因Deviceinformation无此属性字段）
            var deviceTypeIds = query.getDeviceTypeIds();
            var groupId = query.getGroupId();
            var areaId = query.getAreaId();
            var deviceModelIds = query.getDeviceModelIds();
            query.setDeviceModelIds(null);
            query.setDeviceTypeIds(null);
            query.setGroupId(null);
            query.setAreaId(null);
            return ApiResult.success(service.getDevicePageList(query, deviceTypeIds, areaId, groupId, deviceModelIds));
        } catch (Exception e) {
            log.error("查询可查看的设备列表出错:"+e.getMessage(),e);
            return ApiResult.fail("查询可查看的设备列表出错:"+e.getMessage());
        }
    }

    /**
     * 获取当前用户角色下有查看权限的设备列表
     * @return
     */
    @ApiOperation(value = " 获取当前用户角色下有查看权限的设备列表")
   	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true)})
    @PostMapping("/device/list")
    @SuppressWarnings("unchecked")
    public ApiResult<List<Deviceinformation>> queryDeviceList(@RequestBody QueryBeanDevice1 query){
        try {
            // 设置为null时，querybean不做此字段查询不报错（报错因Deviceinformation无此属性字段）
            var deviceTypeIds = query.getDeviceTypeIds();
            var groupId = query.getGroupId();
            var areaId = query.getAreaId();
            var deviceModelIds = query.getDeviceModelIds();
            query.setDeviceModelIds(null);
            query.setDeviceTypeIds(null);
            query.setGroupId(null);
            query.setAreaId(null);
            return ApiResult.success(service.queryDeviceList(query, deviceTypeIds, areaId, groupId, deviceModelIds));
        } catch (Exception e) {
            log.error("查询可查看的设备列表出错:"+e.getMessage(),e);
            return ApiResult.fail("查询可查看的设备列表出错:"+e.getMessage());
        }
    }

    /**
     * 校验是否有设备操作权限
     * @return
     */
    @ApiOperation(value = "校验是否有设备操作权限")
  	@ApiImplicitParams({
  		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
  		@ApiImplicitParam(name = "deviceId", value = "设备Id", required = true, dataType = "long")
  		})
    @PostMapping("/checkIsOperation")
    @SuppressWarnings("unchecked")
    public BaseApiResult checkIsOperation(Long deviceId){
        try {
            return ApiResult.success(service.isOperationDevice(deviceId));
        } catch (Exception e) {
            log.error("校验是否有设备操作权限出错:"+e.getMessage(),e);
            return ApiResult.fail("校验是否有设备操作权限出错:"+e.getMessage());
        }
    }
}
