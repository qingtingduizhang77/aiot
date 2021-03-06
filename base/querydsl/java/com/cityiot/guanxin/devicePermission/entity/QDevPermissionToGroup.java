package com.cityiot.guanxin.devicePermission.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDevPermissionToGroup is a Querydsl query type for DevPermissionToGroup
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDevPermissionToGroup extends EntityPathBase<DevPermissionToGroup> {

    private static final long serialVersionUID = 959729126L;

    public static final QDevPermissionToGroup devPermissionToGroup = new QDevPermissionToGroup("devPermissionToGroup");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final NumberPath<Long> deviceGroupId = createNumber("deviceGroupId", Long.class);

    public final NumberPath<Long> devicePermissionId = createNumber("devicePermissionId", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QDevPermissionToGroup(String variable) {
        super(DevPermissionToGroup.class, forVariable(variable));
    }

    public QDevPermissionToGroup(Path<? extends DevPermissionToGroup> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDevPermissionToGroup(PathMetadata metadata) {
        super(DevPermissionToGroup.class, metadata);
    }

}

