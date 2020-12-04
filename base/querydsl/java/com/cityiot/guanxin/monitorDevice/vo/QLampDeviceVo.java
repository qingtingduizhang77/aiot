package com.cityiot.guanxin.monitorDevice.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QLampDeviceVo is a Querydsl query type for LampDeviceVo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLampDeviceVo extends EntityPathBase<LampDeviceVo> {

    private static final long serialVersionUID = 1945829338L;

    public static final QLampDeviceVo lampDeviceVo = new QLampDeviceVo("lampDeviceVo");

    public final StringPath area = createString("area");

    public final NumberPath<Integer> brightness = createNumber("brightness", Integer.class);

    public final StringPath deviceCode = createString("deviceCode");

    public final NumberPath<Long> deviceId = createNumber("deviceId", Long.class);

    public final StringPath deviceName = createString("deviceName");

    public final StringPath distance = createString("distance");

    public final StringPath lat = createString("lat");

    public final StringPath lng = createString("lng");

    public final NumberPath<Integer> switchStatus = createNumber("switchStatus", Integer.class);

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public QLampDeviceVo(String variable) {
        super(LampDeviceVo.class, forVariable(variable));
    }

    public QLampDeviceVo(Path<? extends LampDeviceVo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLampDeviceVo(PathMetadata metadata) {
        super(LampDeviceVo.class, metadata);
    }

}

