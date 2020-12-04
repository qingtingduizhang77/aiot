package com.cityiot.guanxin.deviceLiandong.entity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QDeviceMoxing is a Querydsl query type for DeviceMoxing
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDeviceLiandongDetails extends EntityPathBase<DeviceLiandongDetails> {

    private static final long serialVersionUID = 7822291667452089865L;

    public static final QDeviceLiandongDetails deviceLiandongDetails = new QDeviceLiandongDetails("deviceLiandongDetails");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final NumberPath<Integer> deviceLiandongId = createNumber("deviceLiandongId", Integer.class);
    public final NumberPath<Integer> sourceDeviceId = createNumber("sourceDeviceId", Integer.class);
    public final NumberPath<Integer> targetDeviceId = createNumber("targetDeviceId", Integer.class);
    public final StringPath math = createString("math");
    public final StringPath code = createString("code");
    public final StringPath sourceDeviceName = createString("sourceDeviceName");
    public final StringPath targetDeviceName = createString("targetDeviceName");

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QDeviceLiandongDetails(String variable) {
        super(DeviceLiandongDetails.class, forVariable(variable));
    }

    public QDeviceLiandongDetails(Path<? extends DeviceLiandongDetails> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeviceLiandongDetails(PathMetadata metadata) {
        super(DeviceLiandongDetails.class, metadata);
    }

}

