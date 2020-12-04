package com.cityiot.guanxin.lampPostDevice.entity.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QLampPostDeviceVo is a Querydsl query type for LampPostDeviceVo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLampPostDeviceVo extends EntityPathBase<LampPostDeviceVo> {

    private static final long serialVersionUID = -87338483L;

    public static final QLampPostDeviceVo lampPostDeviceVo = new QLampPostDeviceVo("lampPostDeviceVo");

    public final NumberPath<Integer> alarmStatus = createNumber("alarmStatus", Integer.class);

    public final NumberPath<Integer> brightness = createNumber("brightness", Integer.class);

    public final NumberPath<Integer> conNum = createNumber("conNum", Integer.class);

    public final NumberPath<Long> deviceId = createNumber("deviceId", Long.class);

    public final StringPath deviceName = createString("deviceName");

    public final NumberPath<Long> deviceTypeId = createNumber("deviceTypeId", Long.class);

    public final StringPath deviceTypeName = createString("deviceTypeName");

    public final StringPath electricCurrent = createString("electricCurrent");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> isLease = createNumber("isLease", Integer.class);

    public final NumberPath<Integer> isOwn = createNumber("isOwn", Integer.class);

    public final StringPath lessor = createString("lessor");

    public final StringPath noise = createString("noise");

    public final StringPath pm10 = createString("pm10");

    public final StringPath pm2point5 = createString("pm2point5");

    public final StringPath power = createString("power");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final NumberPath<Integer> switchStatus = createNumber("switchStatus", Integer.class);

    public final StringPath transmissionRate = createString("transmissionRate");

    public final StringPath voltage = createString("voltage");

    public QLampPostDeviceVo(String variable) {
        super(LampPostDeviceVo.class, forVariable(variable));
    }

    public QLampPostDeviceVo(Path<? extends LampPostDeviceVo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLampPostDeviceVo(PathMetadata metadata) {
        super(LampPostDeviceVo.class, metadata);
    }

}

