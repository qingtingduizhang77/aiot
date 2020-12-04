package com.cityiot.guanxin.organization.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QOrgAreaRelation is a Querydsl query type for OrgAreaRelation
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOrgAreaRelation extends EntityPathBase<OrgAreaRelation> {

    private static final long serialVersionUID = -301336775L;

    public static final QOrgAreaRelation orgAreaRelation = new QOrgAreaRelation("orgAreaRelation");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final NumberPath<Long> areaId = createNumber("areaId", Long.class);

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

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QOrgAreaRelation(String variable) {
        super(OrgAreaRelation.class, forVariable(variable));
    }

    public QOrgAreaRelation(Path<? extends OrgAreaRelation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrgAreaRelation(PathMetadata metadata) {
        super(OrgAreaRelation.class, metadata);
    }

}

