package com.cityiot.guanxin.deviceMoxing.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDeviceMoxingProperty is a Querydsl query type for DeviceMoxingProperty
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDeviceMoxingProperty extends EntityPathBase<DeviceMoxingProperty> {

    private static final long serialVersionUID = 1251482426L;

    public static final QDeviceMoxingProperty deviceMoxingProperty = new QDeviceMoxingProperty("deviceMoxingProperty");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final StringPath dataType = createString("dataType");

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final StringPath name = createString("name");

    public final StringPath tag = createString("tag");

    public final StringPath unit = createString("unit");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QDeviceMoxingProperty(String variable) {
        super(DeviceMoxingProperty.class, forVariable(variable));
    }

    public QDeviceMoxingProperty(Path<? extends DeviceMoxingProperty> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeviceMoxingProperty(PathMetadata metadata) {
        super(DeviceMoxingProperty.class, metadata);
    }

}

