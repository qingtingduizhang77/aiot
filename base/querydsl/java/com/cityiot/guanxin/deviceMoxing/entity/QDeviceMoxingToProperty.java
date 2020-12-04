package com.cityiot.guanxin.deviceMoxing.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDeviceMoxingToProperty is a Querydsl query type for DeviceMoxingToProperty
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDeviceMoxingToProperty extends EntityPathBase<DeviceMoxingToProperty> {

    private static final long serialVersionUID = -3400299L;

    public static final QDeviceMoxingToProperty deviceMoxingToProperty = new QDeviceMoxingToProperty("deviceMoxingToProperty");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final NumberPath<Long> deviceMoxingId = createNumber("deviceMoxingId", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final NumberPath<Long> propertyId = createNumber("propertyId", Long.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QDeviceMoxingToProperty(String variable) {
        super(DeviceMoxingToProperty.class, forVariable(variable));
    }

    public QDeviceMoxingToProperty(Path<? extends DeviceMoxingToProperty> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeviceMoxingToProperty(PathMetadata metadata) {
        super(DeviceMoxingToProperty.class, metadata);
    }

}

