package com.cityiot.guanxin.deviceInformation.control;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.cityiot.guanxin.common.service.JDBCDaoImp;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cityiot.guanxin.area.entity.Area;
import com.cityiot.guanxin.area.entity.QArea;
import com.cityiot.guanxin.area.service.AreaService;
import com.cityiot.guanxin.common.utils.ImportExcelUtil;
import com.cityiot.guanxin.common.utils.NumberUtil;
import com.cityiot.guanxin.deviceInformation.entity.DeviceBrand;
import com.cityiot.guanxin.deviceInformation.entity.DeviceModel;
import com.cityiot.guanxin.deviceInformation.entity.DeviceType;
import com.cityiot.guanxin.deviceInformation.entity.Deviceinformation;
import com.cityiot.guanxin.deviceInformation.entity.QDeviceBrand;
import com.cityiot.guanxin.deviceInformation.entity.QDeviceModel;
import com.cityiot.guanxin.deviceInformation.entity.QDeviceType;
import com.cityiot.guanxin.deviceInformation.service.DeviceBrandService;
import com.cityiot.guanxin.deviceInformation.service.DeviceModelService;
import com.cityiot.guanxin.deviceInformation.service.DeviceTypeService;
import com.cityiot.guanxin.deviceInformation.service.DeviceinformationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;

/**
 * 设备导入功能API接口
 * @author zhengjc
 *
 */
@RestController
@Api(tags = " 设备导入功能API接口")
@RequestMapping("/api/import")
public class DeviceImportControl {

    private static final Logger log = LoggerFactory.getLogger(DeviceImportControl.class);

    @Autowired
    private DeviceinformationService service;

    @Autowired
    private DeviceBrandService deviceBrandService;

    @Autowired
    private DeviceTypeService deviceTypeService;

    @Autowired
    private DeviceModelService deviceModelService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private JDBCDaoImp jdbcDaoImp;


    /**
     * 设备批量导入
     * @param file
     * @return
     */
    @RequiresPermissions(value={"002004006","SUPERADMIN"}, logical= Logical.OR)
    @ApiOperation(value = "设备批量导入",consumes="multipart/form-data")
   	@ApiImplicitParams({
   		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) 
   		})
    @RequestMapping(value = "/device/info")
    public BaseApiResult importDeviceInfo(@RequestParam(value = "file") MultipartFile file) {
        log.info("----------start----------importDeviceInfo");
        if (file == null) {
            return BaseApiResult.failResult(500,"请重新上传文件！");
        }
        boolean flag = true;
        String msg = "";
        try {
            // 读取内容
            InputStream in = file.getInputStream();
            Map<String, Object> objMap= new ImportExcelUtil().getMapByExcel(in, file.getOriginalFilename(), false);

            @SuppressWarnings("unchecked")
            List<ArrayList<Object>> objList = (List<ArrayList<Object>>) objMap.get("list");
            if (objList != null && objList.size() > 1) {
                List<Deviceinformation> deviceList = new ArrayList<>();
                for (int i = 1; i < objList.size(); i++) {
                    List<Object> list = objList.get(i);
                    if (list.size() == 0 || StringUtils.isEmpty(list.get(0).toString())
                            || StringUtils.isEmpty(list.get(1).toString()) || StringUtils.isEmpty(list.get(2).toString())
                            || StringUtils.isEmpty(list.get(3).toString()) || StringUtils.isEmpty(list.get(4).toString())
                            || StringUtils.isEmpty(list.get(5).toString()) || StringUtils.isEmpty(list.get(6).toString())) {
                        msg = StringUtils.isEmpty(msg) ? "第" + i  : msg + "、" + i;
                        continue;
                    }
                    Deviceinformation deviceinformation = new Deviceinformation();
                    deviceinformation.setDeviceName(list.get(0).toString());// 设备名称
                    List<DeviceModel> deviceModelList = deviceModelService.getAllItems(
                            query -> query.where(QDeviceModel.deviceModel1.deviceModel.eq(list.get(1).toString())));//通过设备型号名称查询
                    if (deviceModelList!= null && deviceModelList.size() > 0) {
                        deviceinformation.setDeviceModelId(deviceModelList.get(0).getId());// 设备型号ID
                        deviceinformation.setDeviceTypeId(deviceModelList.get(0).getDeviceTypeId());// 设备类型ID
                    }else {
                        msg = StringUtils.isEmpty(msg) ? "第" + i  : msg + "、" + i;
                        continue;
                    }
                    deviceinformation.setAreaCode(Long.parseLong(list.get(2).toString()));// 所属区域
                    List<Area> areaList = areaService.getAllItems(
                            query -> query.where(QArea.area.code.eq(Long.parseLong(list.get(2).toString()))));//通过区域编码查询
                    if (areaList!= null && areaList.size() > 0) {
                        deviceinformation.setAreaId(areaList.get(0).getId());// 所属区域ID
                    }else {
                        msg = StringUtils.isEmpty(msg) ? "第" + i  : msg + "、" + i;
                        continue;
                    }
                    DecimalFormat df = new DecimalFormat("0.000000"); // 格式化数字
                    deviceinformation.setArea(list.get(3).toString());// 地址
                    deviceinformation.setLongitude(df.format(Double.parseDouble(list.get(4).toString())));//经度
                    deviceinformation.setLatitude(df.format(Double.parseDouble(list.get(5).toString())));//纬度
                    deviceinformation.setCoordinates(df.format(Double.parseDouble(list.get(4).toString())) + "," + df.format(Double.parseDouble(list.get(5).toString())));//坐标
                    deviceinformation.setDeviceCode(list.get(6).toString());//设备编号
                    deviceinformation.setIsShowMap(Integer.parseInt(list.get(7).toString()));
                    deviceinformation.setStatus(1);
                    deviceList.add(deviceinformation);
                }
                for (Deviceinformation deviceinformation : deviceList){
                    service.insertItem(deviceinformation);
                }
            } else {
                flag = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return BaseApiResult.failResult(500,"导入失败！" + e.getMessage());
        }
        if (!flag) {
            return BaseApiResult.failResult(500,"导入失败！");
        }
        msg = StringUtils.isEmpty(msg) ? ""  : msg + "条数据添加失败！" ;
        log.info("----------end----------importDeviceInfo");
        return ApiResult.success("导入成功！" + msg);

    }

    /**
     * 设备品牌批量导入
     * @param file
     * @return
     */
    @RequiresPermissions(value={"002001006","SUPERADMIN"}, logical= Logical.OR)
    @ApiOperation(value = "设备品牌批量导入",consumes="multipart/form-data")
   	@ApiImplicitParams({
   		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) 
   		})
    @RequestMapping(value = "/device/brand")
    public BaseApiResult importDeviceBrand(@RequestParam(value = "file") MultipartFile file) {
        log.info("----------start----------importDeviceBrand");
        if (file == null) {
            return BaseApiResult.failResult(500,"请重新上传文件！");
        }
        boolean flag = true;
        String msg = "";
        try {
            // 读取内容
            InputStream in = file.getInputStream();
            Map<String, Object> objMap = new ImportExcelUtil().getMapByExcel(in, file.getOriginalFilename(), false);

            @SuppressWarnings("unchecked")
            List<ArrayList<Object>> objList = (List<ArrayList<Object>>) objMap.get("list");
            if (objList != null && objList.size() > 1) {
                List<DeviceBrand> deviceList = new ArrayList<>();
                for (int i = 1; i < objList.size(); i++) {
                    List<Object> list = objList.get(i);
                    if (list.size() == 0 || StringUtils.isEmpty(list.get(0).toString())
                            || StringUtils.isEmpty(list.get(1).toString())) {
                        msg = StringUtils.isEmpty(msg) ? "第" + i  : msg + "、" + i;
                        continue;
                    }
                    DeviceBrand deviceBrand = new DeviceBrand();
                    deviceBrand.setDeviceManufacturer(list.get(0) == null ? "" : list.get(0).toString());// 设备厂家
                    deviceBrand.setDeviceBrandName(list.get(1) == null ? "" : list.get(1).toString());// 设备品牌
                    if (deviceBrandService.checkForPresenceInfo(deviceBrand)) {// 校验数据库是否存在相同数据
                        msg = StringUtils.isEmpty(msg) ? "第" + i  : msg + "、" + i;
                        continue;
                    }
                    deviceList.add(deviceBrand);
                }
                // 导入数据去重
                List<DeviceBrand> deviceList1 = deviceList.stream().collect(Collectors
                        .collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> {
                            // 按品牌、厂家 去重
                            return o.getDeviceBrandName() + "," + o.getDeviceManufacturer();
                 }))), ArrayList::new));
                for (DeviceBrand deviceBrand : deviceList1){
                    deviceBrandService.insertItem(deviceBrand);
                }
            } else {
                flag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BaseApiResult.failResult(500,"导入失败！" + e.getMessage());
        }
        if (!flag) {
            return BaseApiResult.failResult(500,"导入失败！");
        }
        msg = StringUtils.isEmpty(msg) ? ""  : msg + "条数据添加失败！" ;
        log.info("----------end----------importDeviceBrand");
        return ApiResult.success("导入成功！" + msg);
    }

    /**
     * 设备类型批量导入
     * @param file
     * @return
     */
    @RequiresPermissions(value={"002002006","SUPERADMIN"}, logical= Logical.OR)
    @ApiOperation(value = "设备类型批量导入",consumes="multipart/form-data")
   	@ApiImplicitParams({
   		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) 
   		})
    @RequestMapping(value = "/device/type")
    public BaseApiResult importDeviceType(@RequestParam(value = "file") MultipartFile file) {
        log.info("----------start----------importDeviceType");
        if (file == null) {
            return BaseApiResult.failResult(500,"请重新上传文件！");
        }
        boolean flag = true;
        String msg = "";
        try {
            // 读取内容
            InputStream in = file.getInputStream();
            Map<String, Object>  objMap = new ImportExcelUtil().getMapByExcel(in, file.getOriginalFilename(), false);

            @SuppressWarnings("unchecked")
            List<ArrayList<Object>> objList = (List<ArrayList<Object>>) objMap.get("list");
            if (objList != null && objList.size() > 1) {
                List<DeviceType> deviceTypeList = new ArrayList<>();
                for (int i = 1; i < objList.size(); i++) {
                    List<Object> list = objList.get(i);
                    if (list.size() == 0 || StringUtils.isEmpty(list.get(0).toString())) {
                        msg = StringUtils.isEmpty(msg) ? "第" + i  : msg + "、" + i;
                        continue;
                    }
                    DeviceType deviceType = new DeviceType();
                    deviceType.setDeviceTypeName(list.get(0).toString());// 设备类型
                    if (deviceTypeService.checkForPresenceInfo(deviceType)) {// 校验数据库是否存在相同数据
                        msg = StringUtils.isEmpty(msg) ? "第" + i  : msg + "、" + i;
                        continue;
                    }
                    deviceTypeList.add(deviceType);
                }
                // 导入数据去重
                List<DeviceType> deviceTypeList1 = deviceTypeList.stream().collect(Collectors
                        .collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> {
                            // 按设备类型去重
                            return o.getDeviceTypeName();
                }))), ArrayList::new));
                for (DeviceType deviceType : deviceTypeList1){
                    deviceTypeService.insertItem(deviceType);
                }
            } else {
                flag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BaseApiResult.failResult(500,"导入失败！" + e.getMessage());
        }
        if (!flag) {
            return BaseApiResult.failResult(500,"导入失败！" + "数据为空");
        }
        msg = StringUtils.isEmpty(msg) ? ""  : msg + "条数据添加失败！" ;
        log.info("----------end----------importDeviceType");
        return ApiResult.success("导入成功！" + msg);
    }

    /**
     * 设备型号批量导入
     * @param file
     * @return
     */
    @RequiresPermissions(value={"002003006","SUPERADMIN"}, logical= Logical.OR)
    @ApiOperation(value = "设备型号批量导入",consumes="multipart/form-data")
   	@ApiImplicitParams({
   		@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true)
   		})
    @RequestMapping(value = "/device/model")
    public BaseApiResult importDeviceModel(@RequestParam(value = "file") MultipartFile file) {
        log.info("----------start----------importDeviceModel");
        if (file == null) {
            return BaseApiResult.failResult(500,"请重新上传文件！");
        }
        boolean flag = true;
        String msg = "";
        try {
            // 读取内容
            InputStream in = file.getInputStream();
            Map<String, Object> objMap = new ImportExcelUtil().getMapByExcel(in, file.getOriginalFilename(), false);

            @SuppressWarnings("unchecked")
            List<ArrayList<Object>> objList = (List<ArrayList<Object>>) objMap.get("list");
            if (objList != null && objList.size() > 1) {
                List<DeviceModel> deviceModelList = new ArrayList<>();
                for (int i = 1; i < objList.size(); i++) {
                    List<Object> list = objList.get(i);
                    if (list.size() == 0 || StringUtils.isEmpty(list.get(0).toString())
                            || StringUtils.isEmpty(list.get(1).toString()) || !NumberUtil.isNumeric(list.get(3))) {
                        msg = StringUtils.isEmpty(msg) ? "第" + i  : msg + "、" + i;
                        continue;
                    }
                    DeviceModel deviceModel = new DeviceModel();
                    List<DeviceType> deviceTypeList = deviceTypeService.getAllItems(
                            query -> query.where(QDeviceType.deviceType.deviceTypeName.eq(list.get(0).toString())));
                    if (deviceTypeList!= null && deviceTypeList.size() > 0) {
                        deviceModel.setDeviceTypeId(deviceTypeList.get(0).getId());// 设备类型ID
                    }else {
                        msg = StringUtils.isEmpty(msg) ? "第" + i  : msg + "、" + i;
                        continue;
                    }
                    List<DeviceBrand> deviceBrandList = deviceBrandService.getAllItems(
                            query -> query.where(QDeviceBrand.deviceBrand.deviceBrandName.eq(list.get(1).toString())));
                    if (deviceBrandList!= null && deviceBrandList.size() > 0) {
                        deviceModel.setDeviceBrandId(deviceBrandList.get(0).getId());// 设备品牌ID
                    }else {
                        msg = StringUtils.isEmpty(msg) ? "第" + i  : msg + "、" + i;
                        continue;
                    }
                    deviceModel.setDeviceModel(list.get(2).toString());// 设备类型
                    deviceModel.setMaintenanceCycle(Integer.parseInt(list.get(3).toString()));//维保周期
                    if(deviceModelService.checkForPresenceInfo(deviceModel)){// 校验数据库是否存在相同数据
                        msg = StringUtils.isEmpty(msg) ? "第" + i  : msg + "、" + i;
                        continue;
                    }
                    deviceModelList.add(deviceModel);
                }
                // 导入数据去重
                List<DeviceModel> deviceModelList1 = deviceModelList.stream().collect(Collectors
                        .collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> {
                            // 按设备类型、品牌、型号去重
                            return o.getDeviceTypeName()+ "," + o.getDeviceBrandName() + "," + o.getDeviceModel();
                }))), ArrayList::new));
                for (DeviceModel deviceModel : deviceModelList1){
                    deviceModelService.insertItem(deviceModel);
                }
            } else {
                flag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BaseApiResult.failResult(500,"导入失败！" + e.getMessage());
        }
        if (!flag) {
            return BaseApiResult.failResult(500,"导入失败！");
        }
        msg = StringUtils.isEmpty(msg) ? ""  : msg + "条数据添加失败！" ;
        log.info("----------end----------importDeviceModel");
        return ApiResult.success("导入成功！" + msg);
    }


    /**
     * 批量更新device旧数据设值parentId
     * @param file
     * @return
     */
    @ApiOperation(value = "批量更新device旧数据设值parentId",consumes="multipart/form-data")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true)
    })
    @RequestMapping(value = "/device/update")
    public BaseApiResult importDeviceUpdate(@RequestParam(value = "file") MultipartFile file) {
        log.info("----------start----------importDeviceUpdate");
        if (file == null) {
            return BaseApiResult.failResult(500,"请重新上传文件！");
        }
        boolean flag = true;
        String msg = "";
        try {
            // 读取内容
            InputStream in = file.getInputStream();
            Map<String, Object>  objMap = new ImportExcelUtil().getMapByExcel(in, file.getOriginalFilename(), false);

            @SuppressWarnings("unchecked")
            List<ArrayList<Object>> objList = (List<ArrayList<Object>>) objMap.get("list");
            if (objList != null && objList.size() > 1) {
                List<String> sqlList = new ArrayList<>();
                for (int i = 1; i < objList.size(); i++) {
                    List<Object> list = objList.get(i);
                    if (list.size() == 0 || StringUtils.isEmpty(list.get(0).toString())
                            || StringUtils.isEmpty(list.get(1).toString())) {
                        msg = StringUtils.isEmpty(msg) ? "第" + i  : msg + "、" + i;
                        continue;
                    }
                    sqlList.add(service.updateParentIdSql(list.get(0).toString(), list.get(1).toString()));
                }
                jdbcDaoImp.batchExecuteSql(sqlList);// 批量更新所属设备parentId
            } else {
                flag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BaseApiResult.failResult(500,"导入失败！" + e.getMessage());
        }
        if (!flag) {
            return BaseApiResult.failResult(500,"导入失败！" + "数据为空");
        }
        msg = StringUtils.isEmpty(msg) ? ""  : msg + "条数据添加失败！" ;
        log.info("----------end----------importDeviceUpdate");
        return ApiResult.success("导入成功！" + msg);
    }
}
