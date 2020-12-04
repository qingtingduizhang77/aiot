package com.cityiot.guanxin.monitor.control;


import com.cityiot.guanxin.monitor.entity.DeviceStatusInfo;
import com.cityiot.guanxin.old.charts.entity.Chart;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swallow.framework.web.ApiResult;

import java.util.Collections;

/**
 * @author hjy
 * 安防统计数据
 */
@RestController
@Api(tags = "安防统计数据接口")
@RequestMapping("/api/security")
public class SecurityControl extends MonitorControl {
    /** 视频统计数据
     * @return
     */
	@ApiOperation(value = "视频统计数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @GetMapping("video")
    public ApiResult getLudengStatisticsData() {
        Chart chart = chartService.getChartByChartName("video");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

    /** 消防统计数据
     * @return
     */
	@ApiOperation(value = "消防统计数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @GetMapping("xiaofang")
    public ApiResult getXiaofangStatisticsData() {
        Chart chart = chartService.getChartByChartName("xiaofang");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

    /** 烟感装置统计数据
     * @return
     */
	@ApiOperation(value = "烟感装置统计数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @GetMapping("yangan")
    public ApiResult getYanganStatisticsData() {
        Chart chart = chartService.getChartByChartName("yangan");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

    /** 应急报警统计数据
     * @return
     */
	@ApiOperation(value = " 应急报警统计数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @GetMapping("yingjibaojing")
    public ApiResult getYingjibaojingStatisticsData() {
        Chart chart = chartService.getChartByChartName("yingjibaojing");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

	@ApiOperation(value = "视频Ex统计")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @GetMapping("videoExCount")
    public ApiResult getVideoExceptionCount() {
        long videoExCount = deviceStatusInfoService.countForStatus("camera_real_data", DeviceStatusInfo.DeviceStatus.EXCEPTION);
        return ApiResult.success(videoExCount);
    }
	@ApiOperation(value = "消防Ex统计 ")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @GetMapping("fireplugExCount")
    public ApiResult getFireplugExceptionCount() {
        long fireplugExCount = deviceStatusInfoService.countForStatus("firepug_real_data", DeviceStatusInfo.DeviceStatus.EXCEPTION);
        return ApiResult.success(fireplugExCount);
    }
	
	@ApiOperation(value = "烟感Ex统计")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @GetMapping("smokeExCount")
    public ApiResult getSmokeExceptionCount() {
        long smokeExCount = deviceStatusInfoService.countForStatus("smokeDetector_real_data", DeviceStatusInfo.DeviceStatus.EXCEPTION);
        return ApiResult.success(smokeExCount);
    }

    /** 获取视频、消防栓、应急报警、烟感设备总数量及异常数量。
     * @return
     */
	@ApiOperation(value = "获取视频、消防栓、应急报警、烟感设备总数量及异常数量")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("daping_number_0002")
    public ApiResult daping_number_0002() {
        Chart chart = chartService.getChartByChartName("daping_number_0002");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }
    //大屏数字四方格
	@ApiOperation(value = "大屏数字四方格")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("daping_number_0001")
    public ApiResult daping_number_0001() {
        Chart chart = chartService.getChartByChartName("daping_number_0001");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }
}
