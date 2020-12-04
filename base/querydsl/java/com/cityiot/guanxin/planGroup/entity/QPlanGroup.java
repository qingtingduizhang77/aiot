package com.cityiot.guanxin.planGroup.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPlanGroup is a Querydsl query type for PlanGroup
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPlanGroup extends EntityPathBase<PlanGroup> {

    private static final long serialVersionUID = -1837436569L;

    public static final QPlanGroup planGroup = new QPlanGroup("planGroup");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    public final NumberPath<Long> level = createNumber("level", Long.class);

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final StringPath name = createString("name");

    public final NumberPath<Long> orderNo = createNumber("orderNo", Long.class);

    public final NumberPath<Long> parentId = createNumber("parentId", Long.class);

    public final StringPath parentIdArr = createString("parentIdArr");

    public final StringPath remark = createString("remark");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QPlanGroup(String variable) {
        super(PlanGroup.class, forVariable(variable));
    }

    public QPlanGroup(Path<? extends PlanGroup> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPlanGroup(PathMetadata metadata) {
        super(PlanGroup.class, metadata);
    }

}

