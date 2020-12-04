package com.cityiot.guanxin.monitor.control;


import com.cityiot.guanxin.monitor.entity.dto.ChartDto;
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

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author hjy
 * 市政统计数据
 */
@RestController
@Api(tags = "市政统计数据接口")
@RequestMapping("/api/city")
public class CityControl extends MonitorControl{

	@ApiOperation(value = "通过图表的名字（kongqizhi_line_0001）获取图表")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @GetMapping("kongqizhi_line_0001")
    public ApiResult getkongqizhiLine0001StatisticsData() {
        Chart chart = chartService.getChartByChartName("kongqizhi_line_0001");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

	@ApiOperation(value = "通过图表的名字（shuizhi_line_0001）获取图表")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @GetMapping("shuizhi_line_0001")
    public ApiResult getshuizhiLine0001StatisticsData() {
        Chart chart = chartService.getChartByChartName("shuizhi_line_0001");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        //对取出的数据保留2位小数
        Collection<HistogramData<String,Double>> chartData = chart.getChartData();
        List<HistogramData<String, Double>> collect = chartData.stream().map(a -> {
            Double y1 = a.getY1();
            a.setY1(Double.parseDouble(String.format("%.2f", y1)));
            return a;
        }).collect(Collectors.toList());
        return ApiResult.success(chart.getChartData());
    }

	@ApiOperation(value = "通过图表的名字（zaoyin_line_0001）获取图表")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @GetMapping("zaoyin_line_0001")
    public ApiResult getzaoyinLine0001StatisticsData() {
        Chart chart = chartService.getChartByChartName("zaoyin_line_0001");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

	@ApiOperation(value = "通过图表的名字（ludeng）获取图表")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @GetMapping("ludeng")
    public ApiResult getLudengStatisticsData() {
        Chart chart = chartService.getChartByChartName("ludeng");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

	@ApiOperation(value = "通过图表的名字（penshui）获取图表")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @GetMapping("penshui")
    public ApiResult getPenshuiStatisticsData() {
        Chart chart = chartService.getChartByChartName("penshui");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

	@ApiOperation(value = "通过图表的名字（jinggai）获取图表")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @GetMapping("jinggai")
    public ApiResult getJinggaiStatisticsData() {
        Chart chart = chartService.getChartByChartName("jinggai");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

	@ApiOperation(value = "通过图表的名字（lajitong）获取图表")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @GetMapping("lajitong")
    public ApiResult getLajitongStatisticsData() {
        Chart chart = chartService.getChartByChartName("lajitong");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

    /** 水浸点统计数据
     * @return
     */
	@ApiOperation(value = "水浸点统计数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @GetMapping("shuijindian")
    public ApiResult getShuijindianStatisticsData() {
        Chart chart = chartService.getChartByChartName("shuijindian");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

    /** 水浸点轮播饼图统计数据
     * @return
     */
	@ApiOperation(value = "水浸点轮播饼图统计数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @GetMapping("shuijindian_pie_lunbo")
    public ApiResult getShuijindianLunPieBoStatisticsData() {
        List<ChartDto> chartList = new ArrayList<>();
        for(int i=1;i<=5;i++) {
            ChartDto chartDto = new ChartDto();
            chartDto.setChartIndex(i);
            Chart chart = chartService.getChartByChartName("shuijindian_pie"+i);
            chartList.add(chartDto);
            if(null==chart) {
                chartDto.setChartData(Collections.EMPTY_LIST);
                continue;
            }
            chartDto.setChartData(chart.getChartData());
        }
        return ApiResult.success(chartList);
    }

    /** 水浸点轮播统计数据
     * @return
     */
	@ApiOperation(value = "水浸点轮播统计数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @GetMapping("shuijindian_lunbo")
    public ApiResult getShuijindianLunBoStatisticsData() {
        List<ChartDto> chartList = new ArrayList<>();
        for(int i=1;i<=5;i++) {
            ChartDto chartDto = new ChartDto();
            chartDto.setChartIndex(i);
            Chart chart = chartService.getChartByChartName("shuijindian"+i);
            chartList.add(chartDto);
            if(null==chart) {
                chartDto.setChartData(Collections.EMPTY_LIST);
                continue;
            }
            chartDto.setChartData(chart.getChartData());
        }
        return ApiResult.success(chartList);
    }

    /** 水浸点饼图数据
     * @return
     */
	@ApiOperation(value = "水浸点饼图数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("shuijindianpie")
    public ApiResult getShuijindianPieStatisicsData() {
        Chart chart = chartService.getChartByChartName("shuijindianpie");
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
