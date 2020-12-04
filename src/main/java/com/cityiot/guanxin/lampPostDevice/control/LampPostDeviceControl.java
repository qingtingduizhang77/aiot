package com.cityiot.guanxin.lampPostDevice.control;

import com.cityiot.guanxin.common.service.JDBCDaoImp;
import com.cityiot.guanxin.common.utils.ImportExcelUtil;
import com.cityiot.guanxin.deviceInformation.service.DeviceinformationService;
import com.cityiot.guanxin.lampPostDevice.entity.LampPostDeviceStat;
import com.cityiot.guanxin.lampPostDevice.entity.QLampPostDevice;
import com.cityiot.guanxin.lampPostDevice.entity.vo.LampPostDeviceVo;
import com.cityiot.guanxin.lampPostDevice.service.LampPostDeviceService;
import com.cityiot.guanxin.lampPostDevice.service.LampPostDeviceStatService;
import io.swagger.annotations.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import swallow.framework.jpaquery.repository.annotations.CnName;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

import java.io.InputStream;
import java.util.*;

/**
 * 灯杆详情关联设备信息访问API接口
 * @author zhengjc
 *
 */
@RestController
@Api(tags = "灯杆详情关联设备信息访问API接口")
@RequestMapping("/api/lampPost")
public class LampPostDeviceControl {

    private static final Logger log = LoggerFactory.getLogger(LampPostDeviceControl.class);

    @Autowired
    private LampPostDeviceService service;

    @Autowired
    private LampPostDeviceStatService statService;

    @Autowired
    private DeviceinformationService deviceinformationService;

    @Autowired
    private JDBCDaoImp jdbcDaoImp;

    @ApiModel(value="灯杆查询对象")
    static class QueryBean extends BasePageQueryBean{

        private Date lastmodi;

        @CnName("设备名称")
        @ApiModelProperty("设备名称")
        private String deviceName;

        @CnName("区域编码")
        @ApiModelProperty("区域编码")
        private String areaCode;

        @CnName("地址")
        @ApiModelProperty("地址")
        private String area;

        @CnName("区域分组ID")
        @ApiModelProperty("区域分组ID")
        private Long deviceGroupId;

        @CnName("区域分组名称")
        @ApiModelProperty("区域分组名称")
        private String groupName;

        @CnName("设备数据保存列表")
        @ApiModelProperty("设备数据保存列表")
        private List<LampPostDeviceVo> deviceList;

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public Long getDeviceGroupId() {
            return deviceGroupId;
        }

        public void setDeviceGroupId(Long deviceGroupId) {
            this.deviceGroupId = deviceGroupId;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public Date getLastmodi() {
            return lastmodi;
        }

        public void setLastmodi(Date lastmodi) {
            this.lastmodi = lastmodi;
        }

        public List<LampPostDeviceVo> getDeviceList() {
            return deviceList;
        }

        public void setDeviceList(List<LampPostDeviceVo> deviceList) {
            this.deviceList = deviceList;
        }
    }

    /**
     * 灯杆详情关联设备列表
     * @return
     */
    @ApiOperation(value = "灯杆详情关联设备列表")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @PostMapping("/deviceList")
    @SuppressWarnings("unchecked")
    public ApiResult<List<LampPostDeviceVo>> query(Long deviceId, Long[] deviceTypeId){
        try {
            return ApiResult.success(service.findLampPostDeviceList(deviceId,deviceTypeId));
        } catch (Exception e) {
            log.error("查询灯杆设备列表出错:"+e.getMessage(),e);
            return ApiResult.fail("查询灯杆设备列表出错:"+e.getMessage());
        }
    }

    /**
     * 调节设备亮度
     * @param deviceId 设备ID
     * @param brightness 调节的亮度
     * @return
     */
    @ApiOperation(value = "调节设备亮度")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "deviceId", value = "设备ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "brightness", value = "亮度", required = true, dataType = "int")
    })
    @RequestMapping("/adjustBrightness/{deviceId}")
    @SuppressWarnings("unchecked")
    public BaseApiResult adjustBrightness(@PathVariable long deviceId, int brightness){
        try {
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("调节设备亮度出错:"+e.getMessage(),e);
            return BaseApiResult.failResult(500,"调节设备亮度出错:"+e.getMessage());
        }
    }

    /**
     * 灯杆分页列表
     * @return
     */
    @ApiOperation(value = "灯杆分页列表")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @PostMapping("listpage")
    @SuppressWarnings("unchecked")
    public ApiResult<PageListData<LampPostDeviceStat>> query(@RequestBody QueryBean query){
        try {
            return ApiResult.success(statService.findLampPostTypePageList(query));
        } catch (Exception e) {
            log.error("查询灯杆分页列表出错:"+e.getMessage(),e);
            return ApiResult.fail("查询灯杆分页列表出错:"+e.getMessage());
        }
    }

    /**
     * 灯杆单灯设备统计
     * @return
     */
    @ApiOperation(value = "灯杆单灯设备统计")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @PostMapping("/stat")
    @SuppressWarnings("unchecked")
    public ApiResult<Map<String,Object>> getDeviceStat(){
        try {
            Map<String,Object> map = new HashMap<>();
            map.put("lampPost",statService.getLampPostStat());
            map.put("light",statService.getLightStat());
            return ApiResult.success(map);
        } catch (Exception e) {
            log.error("查询灯杆单灯设备统计出错:"+e.getMessage(),e);
            return ApiResult.fail("查询灯杆单灯设备统计出错:"+e.getMessage());
        }
    }

    /**
     * 获取灯杆详情
     * @param id 设备ID
     * @return
     */
    @RequestMapping("{id}")
    @SuppressWarnings("unchecked")
    public ApiResult<Map<String, Object>> getItemById(@PathVariable long id){
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("lampPost", service.getItem(query -> query.where(QLampPostDevice.lampPostDevice.deviceId.eq(id))));
            data.put("lampPostInfo", statService.getItemById(id));
            data.put("deviceInfo", deviceinformationService.getItemById(id));
            return ApiResult.success(data);
        } catch (Exception e) {
            log.error("查询灯杆详情对象出错:"+e.getMessage(),e);
            return ApiResult.fail("查询灯杆对象出错:"+e.getMessage());
        }
    }

    /**
     * 获取设备详情
     * @param id 设备ID
     * @return
     */
    @RequestMapping("/device/{id}")
    @SuppressWarnings("unchecked")
    public ApiResult<Map<String, Object>> getDeviceById(@PathVariable long id){
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("deviceInfo", deviceinformationService.getItemById(id));
            data.put("otherDeviceInfo", service.getItem(query -> query.where(QLampPostDevice.lampPostDevice.deviceId.eq(id))));
            return ApiResult.success(data);
        } catch (Exception e) {
            log.error("查询设备详情对象出错:"+e.getMessage(),e);
            return ApiResult.fail("查询设备对象出错:"+e.getMessage());
        }
    }

    /**
     * 资产管理列表
     * @return
     */
    @ApiOperation(value = "资产管理列表")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @PostMapping("/asset/deviceList")
    @SuppressWarnings("unchecked")
    public ApiResult<PageListData<LampPostDeviceVo>> queryAsset(@RequestBody QueryBean queryBean){
        try {
            return ApiResult.success(service.findLampPostDeviceList(queryBean,queryBean.getDeviceName(),queryBean.getAreaCode(),
                    queryBean.getArea(),queryBean.getDeviceGroupId(), queryBean.getGroupName()));
        } catch (Exception e) {
            log.error("查询资产管理列表出错:"+e.getMessage(),e);
            return ApiResult.fail("查询资产管理列表出错:"+e.getMessage());
        }
    }

    /**
     * 批量保存更新灯杆下所属设备
     * @return
     */
    @ApiOperation(value = "批量保存更新灯杆下所属设备")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true)
    })
    @RequestMapping("/batch/update")
    @SuppressWarnings("unchecked")
    public BaseApiResult updateLampPost(@RequestBody QueryBean queryBean){
        try {
                service.batchUpdateLampPost(queryBean.getDeviceList());
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("保存修改设备出错:"+e.getMessage(),e);
            return BaseApiResult.failResult(500,"保存修改设备出错:"+e.getMessage());
        }
    }

    /**
     * 批量更新灯杆下所属设备信息
     * @param file
     * @return
     */
    @ApiOperation(value = "批量更新灯杆下所属设备信息",consumes="multipart/form-data")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true)
    })
    @RequestMapping(value = "/import/update")
    public BaseApiResult importLampPostUpdate(@RequestParam(value = "file") MultipartFile file) {
        log.info("----------start----------importLampPostUpdate");
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
                    sqlList.add(service.batchImportUpdateLampPost(list));
                }
                jdbcDaoImp.batchExecuteSql(sqlList);// 批量更新灯杆下所属设备信息
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
        log.info("----------end----------importLampPostUpdate");
        return ApiResult.success("导入成功！" + msg);
    }
}
