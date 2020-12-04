package com.cityiot.guanxin.deviceInformation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDeviceBrand is a Querydsl query type for DeviceBrand
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDeviceBrand extends EntityPathBase<DeviceBrand> {

    private static final long serialVersionUID = -1453281534L;

    public static final QDeviceBrand deviceBrand = new QDeviceBrand("deviceBrand");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final StringPath deviceBrandName = createString("deviceBrandName");

    public final StringPath deviceManufacturer = createString("deviceManufacturer");

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QDeviceBrand(String variable) {
        super(DeviceBrand.class, forVariable(variable));
    }

    public QDeviceBrand(Path<? extends DeviceBrand> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeviceBrand(PathMetadata metadata) {
        super(DeviceBrand.class, metadata);
    }

}

