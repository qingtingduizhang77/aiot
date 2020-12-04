package com.cityiot.guanxin.devicePermission.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDevPermissionToViewRole is a Querydsl query type for DevPermissionToViewRole
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDevPermissionToViewRole extends EntityPathBase<DevPermissionToViewRole> {

    private static final long serialVersionUID = 382627828L;

    public static final QDevPermissionToViewRole devPermissionToViewRole = new QDevPermissionToViewRole("devPermissionToViewRole");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final NumberPath<Long> devicePermissionId = createNumber("devicePermissionId", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public final NumberPath<Long> viewRoleId = createNumber("viewRoleId", Long.class);

    public QDevPermissionToViewRole(String variable) {
        super(DevPermissionToViewRole.class, forVariable(variable));
    }

    public QDevPermissionToViewRole(Path<? extends DevPermissionToViewRole> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDevPermissionToViewRole(PathMetadata metadata) {
        super(DevPermissionToViewRole.class, metadata);
    }

}

