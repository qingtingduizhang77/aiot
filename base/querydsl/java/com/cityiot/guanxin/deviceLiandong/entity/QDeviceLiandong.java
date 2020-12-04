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
public class QDeviceLiandong extends EntityPathBase<DeviceLiandong> {
    private static final long serialVersionUID = 2757492948137871554L;

    public static final QDeviceLiandong deviceLiandong = new QDeviceLiandong("deviceLiandong");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final NumberPath<Integer> sourceDeviceMoxingId = createNumber("sourceDeviceMoxingId", Integer.class);
    public final NumberPath<Integer> targetDeviceMoxingId = createNumber("targetDeviceMoxingId", Integer.class);
    public final NumberPath<Integer> status = createNumber("status", Integer.class);
    public final StringPath name = createString("name");
    public final StringPath code = createString("code");
    public final StringPath math = createString("math");
    public final StringPath remark = createString("remark");
    public final StringPath sourceDeviceMoxingName = createString("sourceDeviceMoxingName");
    public final StringPath targetDeviceMoxingName = createString("targetDeviceMoxingName");
    public final StringPath sourceDeviceModelIds = createString("sourceDeviceModelIds");
    public final StringPath tagetDeviceModelIds = createString("tagetDeviceModelIds");


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

    public QDeviceLiandong(String variable) {
        super(DeviceLiandong.class, forVariable(variable));
    }

    public QDeviceLiandong(Path<? extends DeviceLiandong> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeviceLiandong(PathMetadata metadata) {
        super(DeviceLiandong.class, metadata);
    }

}

