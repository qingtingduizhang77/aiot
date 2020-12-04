package com.cityiot.guanxin.lampPostDevice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QLampPostDevice is a Querydsl query type for LampPostDevice
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLampPostDevice extends EntityPathBase<LampPostDevice> {

    private static final long serialVersionUID = -1601994851L;

    public static final QLampPostDevice lampPostDevice = new QLampPostDevice("lampPostDevice");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final NumberPath<Integer> brightness = createNumber("brightness", Integer.class);

    public final StringPath co = createString("co");

    public final StringPath co2 = createString("co2");

    public final NumberPath<Integer> conNum = createNumber("conNum", Integer.class);

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final NumberPath<Long> deviceId = createNumber("deviceId", Long.class);

    public final NumberPath<Long> deviceTypeId = createNumber("deviceTypeId", Long.class);

    public final NumberPath<Integer> dimmingStatus = createNumber("dimmingStatus", Integer.class);

    public final StringPath electricCurrent = createString("electricCurrent");

    public final StringPath humidity = createString("humidity");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> isLease = createNumber("isLease", Integer.class);

    public final NumberPath<Integer> isOwn = createNumber("isOwn", Integer.class);

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    public final StringPath lessor = createString("lessor");

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final StringPath msgParsingJson = createString("msgParsingJson");

    public final StringPath no = createString("no");

    public final StringPath noise = createString("noise");

    public final StringPath pm10 = createString("pm10");

    public final StringPath pm2point5 = createString("pm2point5");

    public final StringPath power = createString("power");

    public final NumberPath<Integer> switchStatus = createNumber("switchStatus", Integer.class);

    public final StringPath temperature = createString("temperature");

    public final StringPath transmissionRate = createString("transmissionRate");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public final StringPath voltage = createString("voltage");

    public QLampPostDevice(String variable) {
        super(LampPostDevice.class, forVariable(variable));
    }

    public QLampPostDevice(Path<? extends LampPostDevice> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLampPostDevice(PathMetadata metadata) {
        super(LampPostDevice.class, metadata);
    }

}

