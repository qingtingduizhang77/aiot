package com.cityiot.guanxin.planGroup.entity;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import java.util.Date;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QPlanGroupToPlan extends EntityPathBase<PlanGroupToPlan> {
    private static final long serialVersionUID = -2056031694L;

    public static final QPlanGroupToPlan PlanGroupToPlan = new QPlanGroupToPlan("planGroupToPlan");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final NumberPath<Long> planId = createNumber("planId", Long.class);

    public final NumberPath<Long> planGroupId = createNumber("planGroupId", Long.class);

    public final NumberPath<Long> ordering = createNumber("ordering", Long.class);

    //inherited
    public final DateTimePath<Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;


    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;


    //inherited
    public final NumberPath<Long> version = _super.version;

    public QPlanGroupToPlan(String variable) {
        super(PlanGroupToPlan.class, forVariable(variable));
    }

    public QPlanGroupToPlan(Path<? extends PlanGroupToPlan> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPlanGroupToPlan(PathMetadata metadata) {
        super(PlanGroupToPlan.class, metadata);
    }
}
