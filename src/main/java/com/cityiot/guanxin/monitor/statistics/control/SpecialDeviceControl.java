package com.cityiot.guanxin.monitor.statistics.control;

import com.cityiot.guanxin.log.annotation.ViLog;
import com.cityiot.guanxin.monitor.statistics.entity.LajitongStatistics;
import com.cityiot.guanxin.monitor.statistics.service.SpecialDeviceService;

import io.swagger.annotations.Api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import swallow.framework.web.ApiResult;
import swallow.framework.web.PageListData;

@RestController
@Api(tags = "垃圾桶统计接口")
@RequestMapping("/api/statistics/speical")
public class SpecialDeviceControl extends StatisticsControl{

    private static final Logger log = LoggerFactory.getLogger(SpecialDeviceControl.class);
    @Autowired
    private SpecialDeviceService specialDeviceService;

    /**
     * 新增一个新的模块对象
     * @param item
     * @return
     */
    @ViLog(operEvent = "新增一个新的模块对象", operType =1)//日志记录
    @SuppressWarnings("unchecked")
    @PutMapping()
    public ApiResult<LajitongStatistics> saveNewLajitongStatistics(@RequestBody LajitongStatistics item) {
            return ApiResult.success(null);
    }


    /**
     * 修改模块对象
     * @param item
     * @return
     */
    @ViLog(operEvent = "修改模块对象", operType =2)//日志记录
    @PostMapping
    @SuppressWarnings("unchecked")
    public ApiResult<LajitongStatistics> saveLajitongStatistics(@RequestBody LajitongStatistics item){
        return ApiResult.success(null);
    }

    /**
     * 通过查询bean进行分页查询数据
     * @param query
     * @return
     */
    @PostMapping("listpage")
    @SuppressWarnings("unchecked")
    public ApiResult<PageListData<LajitongStatistics>> query(@RequestBody SpecialDeviceControl.QueryBean query){
        return ApiResult.success(specialDeviceService.queryAllItemsPageByQueryBean(query));
    }

    @RequestMapping("{id}")
    @SuppressWarnings("unchecked")
    public ApiResult<LajitongStatistics> getItemById(@PathVariable long id){
        return ApiResult.success(null);
    }

}
