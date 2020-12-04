package com.cityiot.guanxin.monitor.control;

import com.cityiot.guanxin.common.repository.CommonRepository;
import com.cityiot.guanxin.deviceInformation.entity.QDeviceType;
import com.cityiot.guanxin.deviceInformation.entity.QDeviceinformation;
import com.querydsl.core.types.Projections;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swallow.framework.web.ApiResult;

import java.util.List;

@RestController
@Api(tags = "主面统计各个设备下的类型")
@RequestMapping("/api/monitor-home")
public class HomeControl {
    @Autowired
    private CommonRepository commonRepository;
    /**
     * 统计各个设备下的类型
     */
    @ApiOperation(value = "统计各个设备下的类型")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @GetMapping("allDeviceCount")
    public ApiResult countDeviceSumOfType() {
        QDeviceinformation qDeviceinformation = QDeviceinformation.deviceinformation;
        QDeviceType qDeviceType = QDeviceType.deviceType;
        List<DeviceCountVo> deviceCountVoList = commonRepository
                .getFactory()
                .from(qDeviceType)
                .leftJoin(qDeviceinformation).on(qDeviceinformation.deviceTypeId.eq(qDeviceType.id))
                .groupBy(qDeviceType.id)
                .select(
                        Projections.bean(DeviceCountVo.class,
                        qDeviceType.id.as("deviceTypeId"),
                qDeviceType.deviceTypeName,
                qDeviceType.id.count().as("deviceCount")))
        .fetch();
        return ApiResult.success(deviceCountVoList);
    }
}
