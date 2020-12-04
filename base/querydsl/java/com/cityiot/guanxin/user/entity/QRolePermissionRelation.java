package com.cityiot.guanxin.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QRolePermissionRelation is a Querydsl query type for RolePermissionRelation
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRolePermissionRelation extends EntityPathBase<RolePermissionRelation> {

    private static final long serialVersionUID = 65366669L;

    public static final QRolePermissionRelation rolePermissionRelation = new QRolePermissionRelation("rolePermissionRelation");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

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

    public final NumberPath<Long> permissionId = createNumber("permissionId", Long.class);

    public final NumberPath<Long> roleId = createNumber("roleId", Long.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QRolePermissionRelation(String variable) {
        super(RolePermissionRelation.class, forVariable(variable));
    }

    public QRolePermissionRelation(Path<? extends RolePermissionRelation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRolePermissionRelation(PathMetadata metadata) {
        super(RolePermissionRelation.class, metadata);
    }

}

