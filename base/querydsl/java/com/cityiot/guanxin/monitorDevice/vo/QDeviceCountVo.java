package com.cityiot.guanxin.monitorDevice.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDeviceCountVo is a Querydsl query type for DeviceCountVo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDeviceCountVo extends EntityPathBase<DeviceCountVo> {

    private static final long serialVersionUID = -1178177249L;

    public static final QDeviceCountVo deviceCountVo = new QDeviceCountVo("deviceCountVo");

    public final NumberPath<Integer> deviceCount = createNumber("deviceCount", Integer.class);

    public final NumberPath<Integer> deviceType = createNumber("deviceType", Integer.class);

    public final StringPath deviceTypeName = createString("deviceTypeName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QDeviceCountVo(String variable) {
        super(DeviceCountVo.class, forVariable(variable));
    }

    public QDeviceCountVo(Path<? extends DeviceCountVo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeviceCountVo(PathMetadata metadata) {
        super(DeviceCountVo.class, metadata);
    }

}

