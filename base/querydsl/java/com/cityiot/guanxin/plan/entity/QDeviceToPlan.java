package com.cityiot.guanxin.plan.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDeviceToPlan is a Querydsl query type for DeviceToPlan
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDeviceToPlan extends EntityPathBase<DeviceToPlan> {

    private static final long serialVersionUID = -20985820L;

    public static final QDeviceToPlan deviceToPlan = new QDeviceToPlan("deviceToPlan");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final NumberPath<Long> devHeight = createNumber("devHeight", Long.class);

    public final NumberPath<Long> deviceId = createNumber("deviceId", Long.class);

    public final NumberPath<Long> devWidth = createNumber("devWidth", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    public final NumberPath<Long> layerOrder = createNumber("layerOrder", Long.class);

    public final StringPath layerType = createString("layerType");

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final NumberPath<Long> planAngle = createNumber("planAngle", Long.class);

    public final NumberPath<Long> planId = createNumber("planId", Long.class);

    public final NumberPath<Long> planLock = createNumber("planLock", Long.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public final NumberPath<Long> x = createNumber("x", Long.class);

    public final NumberPath<Long> y = createNumber("y", Long.class);

    public QDeviceToPlan(String variable) {
        super(DeviceToPlan.class, forVariable(variable));
    }

    public QDeviceToPlan(Path<? extends DeviceToPlan> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeviceToPlan(PathMetadata metadata) {
        super(DeviceToPlan.class, metadata);
    }

}

