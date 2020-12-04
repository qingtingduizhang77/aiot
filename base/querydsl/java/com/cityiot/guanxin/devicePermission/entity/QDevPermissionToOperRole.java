package com.cityiot.guanxin.devicePermission.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDevPermissionToOperRole is a Querydsl query type for DevPermissionToOperRole
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDevPermissionToOperRole extends EntityPathBase<DevPermissionToOperRole> {

    private static final long serialVersionUID = -1314169059L;

    public static final QDevPermissionToOperRole devPermissionToOperRole = new QDevPermissionToOperRole("devPermissionToOperRole");

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

    public final NumberPath<Long> operRoleId = createNumber("operRoleId", Long.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QDevPermissionToOperRole(String variable) {
        super(DevPermissionToOperRole.class, forVariable(variable));
    }

    public QDevPermissionToOperRole(Path<? extends DevPermissionToOperRole> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDevPermissionToOperRole(PathMetadata metadata) {
        super(DevPermissionToOperRole.class, metadata);
    }

}

