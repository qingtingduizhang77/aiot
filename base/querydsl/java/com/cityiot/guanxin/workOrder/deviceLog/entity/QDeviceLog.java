package com.cityiot.guanxin.workOrder.deviceLog.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDeviceLog is a Querydsl query type for DeviceLog
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDeviceLog extends EntityPathBase<DeviceLog> {

    private static final long serialVersionUID = -2638536L;

    public static final QDeviceLog deviceLog = new QDeviceLog("deviceLog");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final StringPath description = createString("description");

    public final NumberPath<Long> deviceId = createNumber("deviceId", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    public final DateTimePath<java.util.Date> markTime = createDateTime("markTime", java.util.Date.class);

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final NumberPath<Integer> operationType = createNumber("operationType", Integer.class);

    public final NumberPath<Long> operatorId = createNumber("operatorId", Long.class);

    public final StringPath operatorPhone = createString("operatorPhone");

    public final StringPath remark = createString("remark");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QDeviceLog(String variable) {
        super(DeviceLog.class, forVariable(variable));
    }

    public QDeviceLog(Path<? extends DeviceLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeviceLog(PathMetadata metadata) {
        super(DeviceLog.class, metadata);
    }

}

