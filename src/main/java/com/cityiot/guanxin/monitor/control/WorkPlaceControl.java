package com.cityiot.guanxin.monitor.control;

import com.cityiot.guanxin.old.charts.entity.Chart;

import com.cityiot.guanxin.old.charts.entity.HistogramData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swallow.framework.web.ApiResult;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

/**
 * @author hjy
 * 智慧工地统计数据
 */
@RestController
@Api(tags = "智慧工地统计数据接口")
@RequestMapping("/api/workpalce")
public class WorkPlaceControl extends MonitorControl{

    /** 首页智慧工地统计数据
     * @return
     */
	@ApiOperation(value = "首页智慧工地统计数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @GetMapping("workpalce")
    public ApiResult getWorkpalceStatisticsData() {
        Chart chart = chartService.getChartByChartName("workpalce");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

    /** 工地视频统计数据
     * @return
     */
	@ApiOperation(value = "工地视频统计数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @GetMapping("workvideo/{workPlaceId}")
    public ApiResult getWorkVideoStaticsData(Long workPlaceId) {
        Chart chart = chartService.getChartByChartName("workvideo");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

    /** 实名制作业人数统计数据
     * @return
     */
	@ApiOperation(value = "实名制作业人数统计数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @GetMapping("workerCount/{workPlaceId}")
    public ApiResult getWorkerCountStaticsData(Long workPlaceId) {
        Chart chart = chartService.getChartByChartName("workerCount");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

    /** 工地环境统计数据
     * @return
     */
	@ApiOperation(value = "工地环境统计数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @GetMapping("environment/{workPlaceId}")
    public ApiResult getEnvironmentStatisticsData(Long workPlaceId) {
        Chart chart = chartService.getChartByChartName("workEnvironment");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

    /** 获取工地人员情况分析
     * @return
     */
	@ApiOperation(value = "获取工地人员情况分析")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("gongdi_pie_001")
    public ApiResult gongdi_pie_001() {
        Chart chart = chartService.getChartByChartName("gongdi_pie_001");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

    /** 获取工地设备情况分析
     * @return
     */
	@ApiOperation(value = "获取工地设备情况分析")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("gongdi_bar_001")
    public ApiResult gongdi_bar_001() {
        Chart chart = chartService.getChartByChartName("gongdi_bar_001");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

    //获取最近30天的空气质量PM2.5的值
	@ApiOperation(value = "获取最近30天的空气质量PM2.5的值")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("gongdi_kongqizhi_line_0001")
    public ApiResult gongdi_kongqizhi_line_0001() {
        Chart chart = chartService.getChartByChartName("gongdi_kongqizhi_line_0001");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

     //获取最近30天的水质PH值
	@ApiOperation(value = "获取最近30天的水质PH值")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("gongdi_shuizhi_line_0001")
    public ApiResult gongdi_shuizhi_line_0001() {
        Chart chart = chartService.getChartByChartName("gongdi_shuizhi_line_0001");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

    //获取最近30天的噪音分贝的值
	@ApiOperation(value = "获取最近30天的噪音分贝的值")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("gongdi_zaoyin_line_0001")
    public ApiResult gongdi_zaoyin_line_0001() {
        Chart chart = chartService.getChartByChartName("gongdi_zaoyin_line_0001");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }
    //三合一
	@ApiOperation(value = "三合一")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("three_in_one")
    public ApiResult three_in_one() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("PM2.5",gongdi_kongqizhi_line_0001().getData());
        map.put("shuizhi",gongdi_shuizhi_line_0001().getData());
        map.put("zaoyin",gongdi_zaoyin_line_0001().getData());
        return ApiResult.success(map);
    }
    //工地设备统计
    @ApiOperation(value = "工地设备统计")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("gongdi_device")
    public ApiResult gongdi_device() {
        HashMap<String, Object> map = new HashMap<>();
        Chart chart = chartService.getChartByChartName("gongdi_device");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        HistogramData next = (HistogramData)chart.getChartData().iterator().next();
        return ApiResult.success(next.getValue());
    }
    //超速、逆行、越界告警统计  chaosu_nixing_yuejie
    @ApiOperation(value = "超速、逆行、越界告警统计")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("chaosu_nixing_yuejie")
    public ApiResult chaosu_nixing_yuejie() {
        Chart chart = chartService.getChartByChartName("chaosu_nixing_yuejie");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }
    //水压、开阀、电压异常统计  shuiya_kaifa_dianya
    @ApiOperation(value = "水压、开阀、电压异常统计")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("shuiya_kaifa_dianya")
    public ApiResult shuiya_kaifa_dianya() {
        Chart chart = chartService.getChartByChartName("shuiya_kaifa_dianya");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }
    //烟感和报警统计   yangan_baojing
    @ApiOperation(value = "烟感和报警统计")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("yangan_baojing")
    public ApiResult yangan_baojing() {
        Chart chart = chartService.getChartByChartName("yangan_baojing");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }
    //管网统计   oper_statistics
    @ApiOperation(value = "管网统计")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("oper_statistics")
    public ApiResult oper_statistics() {
        Chart chart = chartService.getChartByChartName("oper_statistics");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }
}
