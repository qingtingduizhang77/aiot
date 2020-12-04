package com.cityiot.guanxin.monitorDevice.control;

import com.cityiot.guanxin.log.annotation.ViLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cityiot.guanxin.deviceInformation.service.DeviceinformationService;
import com.cityiot.guanxin.monitorDevice.service.EChargingPileDeviceService;
import com.cityiot.guanxin.response.BaseResponse;
import com.cityiot.guanxin.response.RespCode;
import com.cityiot.guanxin.response.RespData;

import io.swagger.annotations.Api;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;

@RestController
@Api(value = "/guanxin-api/monitor",tags = "监控设备数据访问API接口")
@RequestMapping("/guanxin-api/monitor")
public class MonitorControl {

    private static final Logger log = LoggerFactory.getLogger(MonitorControl.class);

    @Autowired
    private EChargingPileDeviceService chargingPileDeviceService;
    @Autowired
    private DeviceinformationService deviceinformationService;


    /**
     * 监控设备数量统计
     * @return
     */
    @RequestMapping("/monitorDeviceCount")
    public BaseResponse monitorDeviceCount(){
        RespData data = new RespData();
        try {
            data.putData("deviceCount", this.chargingPileDeviceService.monitorDeviceCount());
            BaseResponse baseResponse = new BaseResponse(data);
            baseResponse.setState(RespCode.SUCCESS);
            return baseResponse;
        } catch (Exception e) {
            log.error("监控设备数量统计出错:"+e.getMessage(),e);
            return new BaseResponse(data, RespCode.ERROR, "监控设备数量统计出错");
        }
    }


    /**
     * 控制开关路灯设备
     * @param deviceId
     * @param switchStatus
     * @return
     */
    @ViLog(operEvent = "控制开关路灯设备", operType =2)//日志记录
    @RequestMapping("/switchLamp")
    public BaseResponse switchLamp(long deviceId, Integer switchStatus){
        RespData data = new RespData();
        try {
            this.chargingPileDeviceService.switchLamp(deviceId, switchStatus);
            BaseResponse baseResponse = new BaseResponse(data);
            baseResponse.setState(RespCode.SUCCESS);
            return baseResponse;
        } catch (Exception e) {
            log.error("控制开关路灯设备出错:"+e.getMessage(),e);
            return new BaseResponse(data, RespCode.ERROR, "控制开关路灯设备出错");
        }
    }

    /**
     * 查询地图设备列表
     * @param deviceType
     * @param keyword
     * @param lat
     * @param lng
     * @param scope
     * @return
     */
    @RequestMapping("/queryMonitorDevices")
    public BaseResponse queryMonitorDevices(Integer deviceType, String keyword, String lat, String lng, Double scope){
        RespData data = new RespData();
        try {
            data.putData("deviceList", chargingPileDeviceService.getDeviceMonitorList(deviceType, keyword, lat, lng, scope));
            BaseResponse baseResponse = new BaseResponse(data);
            baseResponse.setState(RespCode.SUCCESS);
            return baseResponse;
        } catch (Exception e) {
            log.error("查询地图设备列表出错:"+e.getMessage(),e);
            return new BaseResponse(data, RespCode.ERROR, "查询地图设备列表出错");
        }
    }

    /**
     * 校验是否有设备操作权限
     * @return
     */
    @PostMapping("/deviceIsOperated")
    @SuppressWarnings("unchecked")
    public BaseResponse deviceIsOperated(Long deviceId){
        RespData data = new RespData();
        try {
            data.putData("isOperated", chargingPileDeviceService.deviceIsOperated(deviceId));
            BaseResponse baseResponse = new BaseResponse(data);
            baseResponse.setState(RespCode.SUCCESS);
            return baseResponse;
        } catch (Exception e) {
            log.error("校验是否有设备操作权限出错:"+e.getMessage(),e);
            return new BaseResponse(data, RespCode.ERROR, "校验是否有设备操作权限出错");
        }
    }

    /**
     * 路灯设备详情
     * @param deviceId
     * @return
     */
    @RequestMapping("/lampDetail")
    public BaseResponse lampDetail(String lat, String lng, long deviceId){
        RespData data = new RespData();
        try {
            data.putData("lampDeviceInfo", deviceinformationService.lampDetail(deviceId, lat, lng));
            BaseResponse baseResponse = new BaseResponse(data);
            baseResponse.setState(RespCode.SUCCESS);
            return baseResponse;
        } catch (Exception e) {
            log.error("查询路灯设备详情出错:"+e.getMessage(),e);
            return new BaseResponse(data, RespCode.ERROR, "查询路灯设备详情出错");
        }
    }

    /**
     * 视频监控设备详情
     * @param lat
     * @param lng
     * @param deviceId
     * @return
     */
    @RequestMapping("/videoDeviceDetail")
    public BaseResponse videoDeviceDetail(String lat, String lng, long deviceId){
        RespData data = new RespData();
        try {
            data.putData("videoDeviceInfo", deviceinformationService.videoDeviceDetail(deviceId, lat, lng));
            BaseResponse baseResponse = new BaseResponse(data);
            baseResponse.setState(RespCode.SUCCESS);
            return baseResponse;
        } catch (Exception e) {
            log.error("查询视频监控设备详情出错:"+e.getMessage(),e);
            return new BaseResponse(data, RespCode.ERROR, "查询视频监控设备详情出错");
        }
    }


    /**
     * 充电桩设备详情
     * @param lat
     * @param lng
     * @param deviceId
     * @return
     */
    @RequestMapping("/chargingPileDetail")
    public BaseResponse chargingPileDetail(String lat, String lng, long deviceId){
        RespData data = new RespData();
        try {
            data.putData("chargingPileInfo", deviceinformationService.chargingPileDetail(deviceId, lat, lng));
            BaseResponse baseResponse = new BaseResponse(data);
            baseResponse.setState(RespCode.SUCCESS);
            return baseResponse;
        } catch (Exception e) {
            log.error("查询充电桩设备详情出错:"+e.getMessage(),e);
            return new BaseResponse(data, RespCode.ERROR, "查询充电桩设备详情出错");
        }
    }

    /**
     * 垃圾桶设备详情
     * @param lat
     * @param lng
     * @param deviceId
     * @return
     */
    @RequestMapping("/dustbinDetail")
    public BaseResponse dustbinDetail(String lat, String lng, long deviceId){
        RespData data = new RespData();
        try {
            data.putData("dustbinInfo", deviceinformationService.dustbinDetail(deviceId, lat, lng));
            BaseResponse baseResponse = new BaseResponse(data);
            baseResponse.setState(RespCode.SUCCESS);
            return baseResponse;
        } catch (Exception e) {
            log.error("查询垃圾桶设备详情出错:"+e.getMessage(),e);
            return new BaseResponse(data, RespCode.ERROR, "查询垃圾桶设备详情出错");
        }
    }
}
