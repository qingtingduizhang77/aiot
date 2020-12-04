package com.cityiot.guanxin.monitor.control;

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
 * 城市环境统计数据
 */
@RestController
@Api(tags = "城市环境统计数据接口")
@RequestMapping("/api/environment")
public class EnvironmentControl extends MonitorControl {
    /** 首页环境统计数据
     * @return
     */
	@ApiOperation(value = "首页环境统计数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @GetMapping("environment")
    public ApiResult getEnvironmentStatisticsData() {
        Chart chart = chartService.getChartByChartName("environment");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

    /** 显示全市最近30天每日垃圾桶的平均清理时长。
     * @return
     */
	@ApiOperation(value = "显示全市最近30天每日垃圾桶的平均清理时长")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("lajitong_line_001")
    public ApiResult lajitong_line_001() {
        Chart chart = chartService.getChartByChartName("lajitong_line_001");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

}
