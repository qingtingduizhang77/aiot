package com.cityiot.guanxin.lampPostDevice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QLampPostDeviceStat is a Querydsl query type for LampPostDeviceStat
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLampPostDeviceStat extends EntityPathBase<LampPostDeviceStat> {

    private static final long serialVersionUID = -1384651887L;

    public static final QLampPostDeviceStat lampPostDeviceStat = new QLampPostDeviceStat("lampPostDeviceStat");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final NumberPath<Integer> alarmCount = createNumber("alarmCount", Integer.class);

    public final StringPath area = createString("area");

    public final StringPath coordinates = createString("coordinates");

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final StringPath deviceCode = createString("deviceCode");

    public final StringPath deviceName = createString("deviceName");

    public final NumberPath<Long> deviceTypeId = createNumber("deviceTypeId", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final DateTimePath<java.util.Date> lastComTime = createDateTime("lastComTime", java.util.Date.class);

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    public final StringPath latitude = createString("latitude");

    public final NumberPath<Integer> lightUpCount = createNumber("lightUpCount", Integer.class);

    public final StringPath longitude = createString("longitude");

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QLampPostDeviceStat(String variable) {
        super(LampPostDeviceStat.class, forVariable(variable));
    }

    public QLampPostDeviceStat(Path<? extends LampPostDeviceStat> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLampPostDeviceStat(PathMetadata metadata) {
        super(LampPostDeviceStat.class, metadata);
    }

}

