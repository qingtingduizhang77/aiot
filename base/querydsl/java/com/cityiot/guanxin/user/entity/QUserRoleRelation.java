package com.cityiot.guanxin.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserRoleRelation is a Querydsl query type for UserRoleRelation
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserRoleRelation extends EntityPathBase<UserRoleRelation> {

    private static final long serialVersionUID = 179241865L;

    public static final QUserRoleRelation userRoleRelation = new QUserRoleRelation("userRoleRelation");

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

    public final NumberPath<Long> roleId = createNumber("roleId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QUserRoleRelation(String variable) {
        super(UserRoleRelation.class, forVariable(variable));
    }

    public QUserRoleRelation(Path<? extends UserRoleRelation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserRoleRelation(PathMetadata metadata) {
        super(UserRoleRelation.class, metadata);
    }

}

