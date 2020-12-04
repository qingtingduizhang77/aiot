package com.cityiot.guanxin.devicePermission.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDevicePermission is a Querydsl query type for DevicePermission
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDevicePermission extends EntityPathBase<DevicePermission> {

    private static final long serialVersionUID = -1755759285L;

    public static final QDevicePermission devicePermission = new QDevicePermission("devicePermission");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final StringPath areaCode = createString("areaCode");

    public final StringPath areaId = createString("areaId");

    public final StringPath areaName = createString("areaName");

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final StringPath deviceGroupId = createString("deviceGroupId");

    public final StringPath deviceGroupName = createString("deviceGroupName");

    public final StringPath deviceModelId = createString("deviceModelId");

    public final StringPath deviceModelName = createString("deviceModelName");

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final StringPath operRoleId = createString("operRoleId");

    public final StringPath operRoleName = createString("operRoleName");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public final StringPath viewRoleId = createString("viewRoleId");

    public final StringPath viewRoleName = createString("viewRoleName");

    public QDevicePermission(String variable) {
        super(DevicePermission.class, forVariable(variable));
    }

    public QDevicePermission(Path<? extends DevicePermission> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDevicePermission(PathMetadata metadata) {
        super(DevicePermission.class, metadata);
    }

}

