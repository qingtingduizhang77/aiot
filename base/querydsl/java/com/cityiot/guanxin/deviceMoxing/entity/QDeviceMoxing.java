package com.cityiot.guanxin.deviceMoxing.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDeviceMoxing is a Querydsl query type for DeviceMoxing
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDeviceMoxing extends EntityPathBase<DeviceMoxing> {

    private static final long serialVersionUID = 1733029445L;

    public static final QDeviceMoxing deviceMoxing = new QDeviceMoxing("deviceMoxing");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final StringPath deviceModelIds = createString("deviceModelIds");

    public final StringPath deviceModels = createString("deviceModels");

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final StringPath name = createString("name");

    public final StringPath remark = createString("remark");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QDeviceMoxing(String variable) {
        super(DeviceMoxing.class, forVariable(variable));
    }

    public QDeviceMoxing(Path<? extends DeviceMoxing> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeviceMoxing(PathMetadata metadata) {
        super(DeviceMoxing.class, metadata);
    }

}

