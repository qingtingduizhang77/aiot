package com.cityiot.guanxin.monitorDevice.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDeviceVo is a Querydsl query type for DeviceVo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDeviceVo extends EntityPathBase<DeviceVo> {

    private static final long serialVersionUID = 798126946L;

    public static final QDeviceVo deviceVo = new QDeviceVo("deviceVo");

    public final NumberPath<Integer> alarmStatus = createNumber("alarmStatus", Integer.class);

    public final StringPath appcode = createString("appcode");

    public final StringPath area = createString("area");

    public final StringPath cid = createString("cid");

    public final StringPath deviceCode = createString("deviceCode");

    public final NumberPath<Long> deviceId = createNumber("deviceId", Long.class);

    public final StringPath deviceName = createString("deviceName");

    public final NumberPath<Integer> deviceType = createNumber("deviceType", Integer.class);

    public final StringPath deviceTypeName = createString("deviceTypeName");

    public final StringPath distance = createString("distance");

    public final StringPath lat = createString("lat");

    public final StringPath lng = createString("lng");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public QDeviceVo(String variable) {
        super(DeviceVo.class, forVariable(variable));
    }

    public QDeviceVo(Path<? extends DeviceVo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeviceVo(PathMetadata metadata) {
        super(DeviceVo.class, metadata);
    }

}

