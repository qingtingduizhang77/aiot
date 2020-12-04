package com.cityiot.guanxin.deviceInformation.control;

import com.cityiot.guanxin.common.utils.ExcelUtils;
import com.cityiot.guanxin.deviceInformation.entity.DeviceBrand;
import com.cityiot.guanxin.deviceInformation.entity.DeviceModel;
import com.cityiot.guanxin.deviceInformation.entity.DeviceType;
import com.cityiot.guanxin.deviceInformation.entity.Deviceinformation;
import com.cityiot.guanxin.deviceInformation.service.DeviceBrandService;
import com.cityiot.guanxin.deviceInformation.service.DeviceModelService;
import com.cityiot.guanxin.deviceInformation.service.DeviceTypeService;
import com.cityiot.guanxin.deviceInformation.service.DeviceinformationService;
import com.cityiot.guanxin.devicePermission.service.DevicePermissionService;
import com.cityiot.guanxin.user.entity.User;
import com.cityiot.guanxin.user.service.UserService;
import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.entity.OperatorOrCompanyManage;
import com.cityiot.guanxin.workOrder.operatorOrCompanyManage.service.OperatorOrCompanyManageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swallow.framework.jpaquery.repository.annotations.FieldPath;
import swallow.framework.jpaquery.repository.annotations.JoinEntity;
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.BaseQueryBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 设备导出功能API接口
 * @author zhengjc
 *
 */
@RestController
@Api(tags = "设备导出功能API接口")
@RequestMapping("/api/export")
public class DeviceExportControl {


    private static final Logger log = LoggerFactory.getLogger(DeviceExportControl.class);

    @Autowired
    private DeviceinformationService service;

    @Autowired
    private DeviceBrandService deviceBrandService;

    @Autowired
    private DeviceTypeService deviceTypeService;

    @Autowired
    private DeviceModelService deviceModelService;

    @Autowired
    private UserService userService;

    @Autowired
    private OperatorOrCompanyManageService operatorOrCompanyManageService;

    @Autowired
    private DevicePermissionService devicePermissionService;

    /**
     * 查询对象
     *
     */
    @ApiModel(value="设备品牌批量导出查询对象")
    static class QueryBeanBrand extends BaseQueryBean{

    	@ApiModelProperty(value="设备厂家",name="deviceManufacturer",example="")
        @Like
        private String deviceManufacturer;

    	@ApiModelProperty(value="设备品牌",name="deviceBrandName",example="")
        @Like
        private String deviceBrandName;

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
    }

    /**
     * 查询对象
     *
     */
    @ApiModel(value="设备类型批量导出查询对象")
    static class QueryBeanType extends BaseQueryBean{

    	@ApiModelProperty(value="设备类型",name="deviceTypeName",example="")
        @Like
        private String deviceTypeName;

        public String getDeviceTypeName() {
            return deviceTypeName;
        }

        public void setDeviceTypeName(String deviceTypeName) {
            this.deviceTypeName = deviceTypeName;
        }
    }

    /**
     * 查询对象
     *
     */
    @ApiModel(value="设备型号批量导出查询对象")
    static class QueryBeanModel extends BaseQueryBean{

    	
        // 设备类型ID
    	@ApiModelProperty(value="设备类型ID",name="deviceTypeId",example="")
        private Long deviceTypeId;

        // 设备品牌ID
    	@ApiModelProperty(value="设备品牌ID",name="deviceBrandId",example="")
        private Long deviceBrandId;

        // 设备型号
    	@ApiModelProperty(value="设备型号",name="deviceModel",example="")
        @Like
        private String deviceModel;

        public Long getDeviceTypeId() {
            return deviceTypeId;
        }

        public void setDeviceTypeId(Long deviceTypeId) {
            this.deviceTypeId = deviceTypeId;
        }
        public Long getDeviceBrandId() {
            return deviceBrandId;
        }

        public void setDeviceBrandId(Long deviceBrandId) {
            this.deviceBrandId = deviceBrandId;
        }
        public String getDeviceModel() {
            return deviceModel;
        }

        public void setDeviceModel(String deviceModel) {
            this.deviceModel = deviceModel;
        }
    }

    /**
     * 查询对象
     *
     */
    @ApiModel(value="设备信息批量导出查询对象")
    static class QueryBean extends BaseQueryBean {


        // 设备型号ID
    	@ApiModelProperty(value="设备型号ID",name="deviceModelId",example="")
        @JoinEntity(name="id",joinEntityClass = DeviceModel.class)
        private Long deviceModelId;

    	@ApiModelProperty(value="设备品牌ID",name="deviceBrandId",example="")
        @FieldPath(name = "deviceBrandId",joinEntityClass = DeviceModel.class)
        private Long deviceBrandId;

        //		@FieldPath(name = "deviceTypeId",joinEntityClass = DeviceModel.class)
    	@ApiModelProperty(value="设备类型ID",name="deviceTypeId",example="")
        private Long deviceTypeId;

        // 地址
    	@ApiModelProperty(value="地址",name="area",example="")
        @Like
        private String area;

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
    }

    /**
     * 查询对象
     *
     */
    @ApiModel(value="用户信息批量导出查询对象")
    static class QueryBeanUser extends BaseQueryBean{

        // 姓名
    	@ApiModelProperty(value="姓名",name="name",example="")
        @Like
        private String name;

        // 用户名
    	@ApiModelProperty(value="用户名",name="username",example="")
        @Like
        private String username;

        // 用户名
    	@ApiModelProperty(value="电话",name="phone",example="")
        @Like
        private String phone;

        // 是否禁用
    	@ApiModelProperty(value="是否禁用",name="disable",example="")
        private Integer disable;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
        public Integer getDisable() {
            return disable;
        }

        public void setDisable(Integer disable) {
            this.disable = disable;
        }

    }

    /**
     * 查询对象
     *
     */
    @ApiModel(value="运维人员公司管理批量导出查询对象")
    static class QueryBeanOperatorOrComp extends BaseQueryBean{

        // 账户
    	@ApiModelProperty(value="账户",name="account",example="")
        private String account;

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }
    }

    /**
     * 设备品牌批量导出
     * @return
     */
    @ApiOperation(value = "设备品牌批量导出")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping(value = "/device/brand")
    public void exportDeviceBrand(HttpServletRequest request, HttpServletResponse response,@RequestBody QueryBeanBrand bean) {
        log.info("----------start----------exportDeviceBrand");
        try {
            List<DeviceBrand> deviceBrands = deviceBrandService.getAllItemByQuerybean(bean);
            List<Map<String, Object>> rowlist = new ArrayList<>();
            for (DeviceBrand deviceBrand : deviceBrands){
                rowlist.add(ExcelUtils.getObjectToMap(deviceBrand));
            }

            String[] titles = { "设备厂家", "设备品牌"};
            String[] columnKey = { "deviceManufacturer", "deviceBrandName"};

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); // 日期格式化
            request.setCharacterEncoding("utf-8");
            String fileName = "device_brand_" + sdf.format(new Date()) + ".xls";
            fileName = new String(fileName.getBytes("gbk"), "iso8859-1");
            HSSFWorkbook workbook = ExcelUtils.eqEcel(rowlist, titles, "设备品牌", columnKey);
            response.reset();
            response.setContentType("contentType=application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Connection", "close");
            workbook.write(response.getOutputStream());
            response.flushBuffer();

        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("----------end----------exportDeviceBrand");
    }

    /**
     * 设备类型批量导出
     * @return
     */
    @ApiOperation(value = "设备类型批量导出")
   	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping(value = "/device/type")
    public void exportDeviceType(HttpServletRequest request, HttpServletResponse response, @RequestBody QueryBeanType bean) {
        log.info("----------start----------exportDeviceType");
        try {
            List<DeviceType> deviceTypes = deviceTypeService.getAllItemByQuerybean(bean);
            List<Map<String, Object>> rowlist = new ArrayList<>();
            for (DeviceType deviceType : deviceTypes){
                rowlist.add(ExcelUtils.getObjectToMap(deviceType));
            }

            String[] titles = { "设备类型", "归属板块 （1市政 2智慧安防 3城市环境 4智慧工地 5智慧停车场）"};
            String[] columnKey = { "deviceTypeName", "belongModule"};

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); // 日期格式化
            request.setCharacterEncoding("utf-8");
            String fileName = "device_type_" + sdf.format(new Date()) + ".xls";
            fileName = new String(fileName.getBytes("gbk"), "iso8859-1");
            HSSFWorkbook workbook = ExcelUtils.eqEcel(rowlist, titles, "设备类型", columnKey);
            response.reset();
            response.setContentType("contentType=application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Connection", "close");
            workbook.write(response.getOutputStream());
            response.flushBuffer();

        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("----------end----------exportDeviceType");
    }


    /**
     * 设备型号批量导出
     * @return
     */
    @ApiOperation(value = "设备型号批量导出")
   	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping(value = "/device/model")
    public void exportDeviceModel(HttpServletRequest request, HttpServletResponse response, @RequestBody QueryBeanModel bean) {
        log.info("----------start----------exportDeviceModel");
        try {
            List<DeviceModel> deviceModels = deviceModelService.getAllItemByQuerybean(bean);
            List<Map<String, Object>> rowlist = new ArrayList<>();
            for (DeviceModel deviceModel : deviceModels){
                rowlist.add(ExcelUtils.getObjectToMap(deviceModel));
            }

            String[] titles = { "设备类型", "设备品牌", "设备型号", "维保周期"};
            String[] columnKey = { "deviceTypeName", "deviceBrandName", "deviceModel", "maintenanceCycle"};

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); // 日期格式化
            request.setCharacterEncoding("utf-8");
            String fileName = "device_model_" + sdf.format(new Date()) + ".xls";
            fileName = new String(fileName.getBytes("gbk"), "iso8859-1");
            HSSFWorkbook workbook = ExcelUtils.eqEcel(rowlist, titles, "设备型号", columnKey);
            response.reset();
            response.setContentType("contentType=application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Connection", "close");
            workbook.write(response.getOutputStream());
            response.flushBuffer();

        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("----------end----------exportDeviceModel");
    }

    /**
     * 设备信息批量导出
     * @return
     */
    @ApiOperation(value = "设备信息批量导出")
   	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping(value = "/device/info")
    public void exportDeviceInfo(HttpServletRequest request, HttpServletResponse response,@RequestBody QueryBean bean) {
        log.info("----------start----------exportDeviceInfo");
        try {
            List<Deviceinformation> devices = devicePermissionService.getDeviceList(bean);
            List<Map<String, Object>> rowlist = new ArrayList<>();
            for (Deviceinformation device : devices){
                rowlist.add(ExcelUtils.getObjectToMap(device));
            }

            String[] titles = {"设备名称", "设备型号", "所属区域", "地址", "经度(高德系longitude)", "维度（高德系latitude）", "设备编号"};
            String[] columnKey = {"deviceName", "deviceModel", "areaCode", "area", "longitude", "latitude", "deviceCode"};

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); // 日期格式化
            request.setCharacterEncoding("utf-8");
            String fileName = "device_info_" + sdf.format(new Date()) + ".xls";
            fileName = new String(fileName.getBytes("gbk"), "iso8859-1");
            HSSFWorkbook workbook = ExcelUtils.eqEcel(rowlist, titles, "设备", columnKey);
            response.reset();
            response.setContentType("contentType=application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Connection", "close");
            workbook.write(response.getOutputStream());
            response.flushBuffer();

        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("----------end----------exportDeviceInfo");
    }


    /**
     * 用户信息批量导出
     * @return
     */
    @ApiOperation(value = "用户信息批量导出")
   	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping(value = "/user/info")
    public void exportUserInfo(HttpServletRequest request, HttpServletResponse response,@RequestBody QueryBeanUser bean) {
        log.info("----------start----------exportUserInfo");
        try {
            List<User> users = userService.getAllItemByQuerybean(bean);
            List<Map<String, Object>> rowlist = new ArrayList<>();
            for (User user : users){
                rowlist.add(ExcelUtils.getObjectToMap(user));
            }

            String[] titles = { "用户名", "姓名", "手机号码", "是否禁用(0不禁用1禁用)", "性别(0为女 1为男)"};
            String[] columnKey = { "username", "name", "phone", "disable", "sex"};

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); // 日期格式化
            request.setCharacterEncoding("utf-8");
            String fileName = "user_info_" + sdf.format(new Date()) + ".xls";
            fileName = new String(fileName.getBytes("gbk"), "iso8859-1");
            HSSFWorkbook workbook = ExcelUtils.eqEcel(rowlist, titles, "用户信息", columnKey);
            response.reset();
            response.setContentType("contentType=application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Connection", "close");
            workbook.write(response.getOutputStream());
            response.flushBuffer();

        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("----------end----------exportUserInfo");
    }

    /**
     * 运维人员公司管理批量导出
     * @return
     */
    /**
     * 用户信息批量导出
     * @return
     */
    @ApiOperation(value = " 运维人员公司管理批量导出")
   	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping(value = "/operator/comp")
    public void exportOperatorOrComp(HttpServletRequest request, HttpServletResponse response,@RequestBody QueryBeanOperatorOrComp bean) {
        log.info("----------start----------exportOperatorOrComp");
        try {
            List<OperatorOrCompanyManage> models = operatorOrCompanyManageService.getAllItemByQuerybean(bean);
            List<Map<String, Object>> rowlist = new ArrayList<>();
            for (OperatorOrCompanyManage model : models){
                rowlist.add(ExcelUtils.getObjectToMap(model));
            }

            String[] titles = { "姓名", "账户", "性别(1男2女)", "联系电话", "在岗状态(1在职2离职)", "禁用状态(1启动2禁用)","类型(1员工2公司)","备注"};
            String[] columnKey = { "name", "account", "gender", "phone", "status", "disable", "operatorType", "remark"};

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); // 日期格式化
            request.setCharacterEncoding("utf-8");
            String fileName = "operator_or_comp_" + sdf.format(new Date()) + ".xls";
            fileName = new String(fileName.getBytes("gbk"), "iso8859-1");
            HSSFWorkbook workbook = ExcelUtils.eqEcel(rowlist, titles, "运维人员公司管理", columnKey);
            response.reset();
            response.setContentType("contentType=application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Connection", "close");
            workbook.write(response.getOutputStream());
            response.flushBuffer();

        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("----------end----------exportOperatorOrComp");
    }
}
