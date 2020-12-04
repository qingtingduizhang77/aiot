package com.cityiot.guanxin.monitor.control;

import com.cityiot.guanxin.monitor.entity.dto.ChartDto;
import com.cityiot.guanxin.old.charts.entity.Chart;
import com.cityiot.guanxin.serviceClient.ServiceClientUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swallow.framework.web.ApiResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author hjy
 * 交通统计数据
 */
@RestController
@Api(tags = "交通统计数据接口")
@RequestMapping("/api/traffic")
public class TrafficControl extends MonitorControl {
    @Autowired
    private ServiceClientUtil serviceClientUtil;

    /** 路内停车场饼图数据
     * @return
     */
	@ApiOperation(value = "智慧停车数据接口")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "param", value = "方法名", required = true, dataType = "String",paramType="query"),
    })
    @RequestMapping("cardata")
    public ApiResult cardata(@RequestParam(value = "param", required = true) String param) {
        try {

            String clientParam="{\"function\":\"carData\",\"type\":\""+param+"\"}";

            String re=serviceClientUtil.csExtend(clientParam);

            return ApiResult.success(re);
        } catch (Exception e) {

            return ApiResult.fail("智慧停车数据出错:"+e.getMessage());
        }
    }


    @ApiOperation(value = "路内停车场饼图数据")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("luneitingchechang_pie")
    public ApiResult getLuneitingchechangPieStatisicsData() {
        Chart chart = chartService.getChartByChartName("luneitingchechang_pie");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

    /** 路外停车场饼图数据
     * @return
     */
	@ApiOperation(value = "路外停车场饼图数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("luwaitingchechang_pie")
    public ApiResult getLuwaitingchechangPieStatisicsData() {
        Chart chart = chartService.getChartByChartName("luwaitingchechang_pie");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

    /** 诱导屏饼图数据
     * @return
     */
	@ApiOperation(value = "诱导屏饼图数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("youdaoping_pie")
    public ApiResult getYoudaopingPieStatisicsData() {
        Chart chart = chartService.getChartByChartName("youdaoping_pie");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

    /** 摄像头饼图数据
     * @return
     */
	@ApiOperation(value = "摄像头饼图数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("shexiangtou_pie")
    public ApiResult getShexiangtouPieStatisicsData() {
        Chart chart = chartService.getChartByChartName("shexiangtou_pie");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

    /** 充电桩饼图数据
     * @return
     */
	@ApiOperation(value = "充电桩饼图数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("chongdianzhuang_pie")
    public ApiResult getChongdianzhuangPieStatisicsData() {
        Chart chart = chartService.getChartByChartName("chongdianzhuang_pie");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

    /** 充电桩的正常及故障
     * @return
     */
	@ApiOperation(value = "充电桩的正常及故障")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("chongdian_pie_001")
    public ApiResult getchongdianPie001StatisicsData() {
        Chart chart = chartService.getChartByChartName("chongdian_pie_001");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

    /** 路外停车场数据
     * @return
     */
	@ApiOperation(value = "路外停车场数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("luwaitingchechang")
    public ApiResult getLuwaitingchechangStatisicsData() {
        Chart chart = chartService.getChartByChartName("luwaitingchechang");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

    /** 路内停车场数据
     * @return
     */
	@ApiOperation(value = "路内停车场数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("luneitingchechang")
    public ApiResult getLuneitingchechangStatisicsData() {
        Chart chart = chartService.getChartByChartName("luneitingchechang");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

    /** 充电桩数据
     * @return
     */
	@ApiOperation(value = "充电桩数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("chongdianzhuang")
    public ApiResult getChongdianzhuangStatisicsData() {
        Chart chart = chartService.getChartByChartName("chongdianzhuang");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

    /** 机械立体停车库数据
     * @return
     */
	@ApiOperation(value = " 机械立体停车库数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("jixielititingcheku")
    public ApiResult getJixielititingchekuStatisicsData() {
        Chart chart = chartService.getChartByChartName("jixielititingcheku");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }


    /** 路外，路内，机械停车场内部所有数据
     * @return
     */
	@ApiOperation(value = "路外，路内，机械停车场内部所有数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("luwaitingchechang_inner")
    public ApiResult getLuwaitingchechangInnerStatisicsData() {
        List<ChartDto> chartList = new ArrayList<>();
        for(int i=1;i<=8;i++) {
            ChartDto chartDto = new ChartDto();
            chartDto.setChartIndex(i);
            Chart chart = chartService.getChartByChartName("luwaitingchechang_inner"+i);
            chartList.add(chartDto);
            if(null==chart) {
                chartDto.setChartData(Collections.EMPTY_LIST);
                continue;
            }
            chartDto.setChartData(chart.getChartData());
        }
        return ApiResult.success(chartList);
    }

    /** 充电桩数据
     * @return
     */
	@ApiOperation(value = "充电桩数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("chongdianzhuang_inner")
    public ApiResult getChongdianzhuangInnerStatisicsData() {
        List<ChartDto> chartList = new ArrayList<>();
        // 充电时长图表
        ChartDto chartDto1 = new ChartDto();
        chartDto1.setChartIndex(1);
        Chart chart = chartService.getChartByChartName("chongdianshichang");
        if(null==chart) chartDto1.setChartData(chart.getChartData());
        chartDto1.setChartData(chart.getChartData());
        chartList.add(chartDto1);
        // 使用率图表
        ChartDto chartDto2 = new ChartDto();
        chartDto2.setChartIndex(2);
        chart = chartService.getChartByChartName("shitonglv");
        if(null==chart) chartDto2.setChartData(chart.getChartData());
        chartDto2.setChartData(chart.getChartData());
        chartList.add(chartDto2);
        // 故障率图表
        ChartDto chartDto3 = new ChartDto();
        chartDto3.setChartIndex(3);
        chart = chartService.getChartByChartName("guzhanglv");
        if(null==chart) chartDto3.setChartData(chart.getChartData());
        chartDto3.setChartData(chart.getChartData());
        chartList.add(chartDto3);

        return ApiResult.success(chartList);
    }

    /** 车位使用率，显示当天全市车位使用情况。
     * @return
     */
	@ApiOperation(value = "车位使用率，显示当天全市车位使用情况")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("chewei_pie_001")
    public ApiResult getChewei_pie_001() {
        Chart chart = chartService.getChartByChartName("chewei_pie_001");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

    /** 车位占用率。
     * @return
     */
	@ApiOperation(value = "车位占用率")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("chewei_pie_002")
    public ApiResult chewei_pie_002() {
        Chart chart = chartService.getChartByChartName("chewei_pie_002");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

    /** 车位周转率。
     * @return
     */
	@ApiOperation(value = "车位周转率")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("chewei_pie_003")
    public ApiResult chewei_pie_003() {
        Chart chart = chartService.getChartByChartName("chewei_pie_003");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

    /** 显示全市最近30天每日路灯的能耗数值。。
     * @return
     */
	@ApiOperation(value = "显示全市最近30天每日路灯的能耗数值")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("ludeng_line_001")
    public ApiResult ludeng_line_001() {
        Chart chart = chartService.getChartByChartName("ludeng_line_001");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

    /** 显示全市最近30天每日路灯的平均亮灯时长。
     * @return
     */
	@ApiOperation(value = "显示全市最近30天每日路灯的平均亮灯时长")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("ludeng_line_002")
    public ApiResult ludeng_line_002() {
        Chart chart = chartService.getChartByChartName("ludeng_line_002");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

    /** 显示全市最近30天每日井盖的正常率。
     * @return
     */
	@ApiOperation(value = "显示全市最近30天每日井盖的正常率")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("jinggai_line_001")
    public ApiResult jinggai_line_001() {
        Chart chart = chartService.getChartByChartName("jinggai_line_001");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

    /** 显示全市最近30天每日充电桩使用率和故障率。
     * @return
     */
	@ApiOperation(value = "显示全市最近30天每日充电桩使用率和故障率")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("chongdian_line_001")
    public ApiResult chongdian_line_001() {
        Chart chart = chartService.getChartByChartName("chongdian_line_001");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

    /** 显示全市最近30天每日充电桩的充电时长。
     * @return
     */
	@ApiOperation(value = "显示全市最近30天每日充电桩的充电时长")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("chongdian_line_002")
    public ApiResult chongdian_line_002() {
        Chart chart = chartService.getChartByChartName("chongdian_line_002");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }

    /** 显示全市最近30天每日充电桩的充电量。
     * @return
     */
	@ApiOperation(value = "显示全市最近30天每日充电桩的充电量")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping("chongdian_line_003")
    public ApiResult chongdian_line_003() {
        Chart chart = chartService.getChartByChartName("chongdian_line_003");
        if(null==chart) return ApiResult.success(Collections.EMPTY_LIST);
        return ApiResult.success(chart.getChartData());
    }




}
