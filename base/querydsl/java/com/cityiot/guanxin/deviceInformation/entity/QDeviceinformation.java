package com.cityiot.guanxin.deviceInformation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDeviceinformation is a Querydsl query type for Deviceinformation
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDeviceinformation extends EntityPathBase<Deviceinformation> {

    private static final long serialVersionUID = -1128568697L;

    public static final QDeviceinformation deviceinformation = new QDeviceinformation("deviceinformation");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final NumberPath<Integer> alarmStatus = createNumber("alarmStatus", Integer.class);

    public final StringPath appcode = createString("appcode");

    public final StringPath area = createString("area");

    public final NumberPath<Long> areaCode = createNumber("areaCode", Long.class);

    public final NumberPath<Long> areaId = createNumber("areaId", Long.class);

    public final StringPath cid = createString("cid");

    public final StringPath coordinates = createString("coordinates");

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final StringPath deviceCode = createString("deviceCode");

    public final NumberPath<Long> deviceModelId = createNumber("deviceModelId", Long.class);

    public final StringPath deviceName = createString("deviceName");

    public final NumberPath<Long> deviceTypeId = createNumber("deviceTypeId", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> isShowMap = createNumber("isShowMap", Integer.class);

    public final DateTimePath<java.util.Date> lastComTime = createDateTime("lastComTime", java.util.Date.class);

    public final DateTimePath<java.util.Date> lastMaintenanceTime = createDateTime("lastMaintenanceTime", java.util.Date.class);

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    public final StringPath latitude = createString("latitude");

    public final StringPath longitude = createString("longitude");

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final NumberPath<Long> parentId = createNumber("parentId", Long.class);

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QDeviceinformation(String variable) {
        super(Deviceinformation.class, forVariable(variable));
    }

    public QDeviceinformation(Path<? extends Deviceinformation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeviceinformation(PathMetadata metadata) {
        super(Deviceinformation.class, metadata);
    }

}

