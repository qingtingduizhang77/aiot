package com.cityiot.guanxin.organization.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QOrgMember is a Querydsl query type for OrgMember
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOrgMember extends EntityPathBase<OrgMember> {

    private static final long serialVersionUID = 897489962L;

    public static final QOrgMember orgMember = new QOrgMember("orgMember");

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

    public final NumberPath<Long> orgId = createNumber("orgId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QOrgMember(String variable) {
        super(OrgMember.class, forVariable(variable));
    }

    public QOrgMember(Path<? extends OrgMember> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrgMember(PathMetadata metadata) {
        super(OrgMember.class, metadata);
    }

}

