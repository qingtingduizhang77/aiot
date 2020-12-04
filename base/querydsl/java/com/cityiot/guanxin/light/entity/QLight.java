package com.cityiot.guanxin.light.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QLight is a Querydsl query type for Light
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLight extends EntityPathBase<Light> {

    private static final long serialVersionUID = 930962407L;

    public static final QLight light = new QLight("light");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final StringPath deviceCode = createString("deviceCode");

    public final NumberPath<Long> deviceId = createNumber("deviceId", Long.class);

    public final NumberPath<Long> groupId = createNumber("groupId", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QLight(String variable) {
        super(Light.class, forVariable(variable));
    }

    public QLight(Path<? extends Light> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLight(PathMetadata metadata) {
        super(Light.class, metadata);
    }

}

