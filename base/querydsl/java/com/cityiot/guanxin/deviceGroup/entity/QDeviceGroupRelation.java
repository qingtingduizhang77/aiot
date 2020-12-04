package com.cityiot.guanxin.deviceGroup.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDeviceGroupRelation is a Querydsl query type for DeviceGroupRelation
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDeviceGroupRelation extends EntityPathBase<DeviceGroupRelation> {

    private static final long serialVersionUID = -1202664349L;

    public static final QDeviceGroupRelation deviceGroupRelation = new QDeviceGroupRelation("deviceGroupRelation");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final NumberPath<Long> deviceGroupId = createNumber("deviceGroupId", Long.class);

    public final NumberPath<Long> deviceId = createNumber("deviceId", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QDeviceGroupRelation(String variable) {
        super(DeviceGroupRelation.class, forVariable(variable));
    }

    public QDeviceGroupRelation(Path<? extends DeviceGroupRelation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeviceGroupRelation(PathMetadata metadata) {
        super(DeviceGroupRelation.class, metadata);
    }

}

