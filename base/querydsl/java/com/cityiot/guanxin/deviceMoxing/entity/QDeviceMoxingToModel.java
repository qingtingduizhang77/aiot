package com.cityiot.guanxin.deviceMoxing.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDeviceMoxingToModel is a Querydsl query type for DeviceMoxingToModel
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDeviceMoxingToModel extends EntityPathBase<DeviceMoxingToModel> {

    private static final long serialVersionUID = 1394856809L;

    public static final QDeviceMoxingToModel deviceMoxingToModel = new QDeviceMoxingToModel("deviceMoxingToModel");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final StringPath deviceModel = createString("deviceModel");

    public final NumberPath<Long> deviceModelId = createNumber("deviceModelId", Long.class);

    public final NumberPath<Long> deviceMoxingId = createNumber("deviceMoxingId", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QDeviceMoxingToModel(String variable) {
        super(DeviceMoxingToModel.class, forVariable(variable));
    }

    public QDeviceMoxingToModel(Path<? extends DeviceMoxingToModel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeviceMoxingToModel(PathMetadata metadata) {
        super(DeviceMoxingToModel.class, metadata);
    }

}

