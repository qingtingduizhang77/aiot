package com.cityiot.guanxin.deviceInformation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDeviceType is a Querydsl query type for DeviceType
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDeviceType extends EntityPathBase<DeviceType> {

    private static final long serialVersionUID = -1431809953L;

    public static final QDeviceType deviceType = new QDeviceType("deviceType");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final NumberPath<Integer> belongModule = createNumber("belongModule", Integer.class);

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final StringPath deviceTypeName = createString("deviceTypeName");

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QDeviceType(String variable) {
        super(DeviceType.class, forVariable(variable));
    }

    public QDeviceType(Path<? extends DeviceType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeviceType(PathMetadata metadata) {
        super(DeviceType.class, metadata);
    }

}

