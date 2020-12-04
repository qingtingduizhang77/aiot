package com.cityiot.guanxin.lightPlans.entity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;

import javax.annotation.Generated;

import java.util.Date;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QLightPlans extends EntityPathBase<LightPlans> {

    private static final long serialVersionUID = 887256096690454947L;

    public static final QLightPlans lightPlans = new QLightPlans("lightPlans");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final StringPath name = createString("name");
    public final NumberPath<Integer> dateType = createNumber("dateType", Integer.class);
    public final StringPath zhou = createString("zhou");
    public final DatePath<Date> startDate = createDate("startDate", java.util.Date.class);
    public final DatePath<Date> endDate = createDate("endDate", java.util.Date.class);
    public final StringPath plansTime = createString("plansTime");
    public final StringPath remark = createString("remark");

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

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QLightPlans(String variable) {
        super(LightPlans.class, forVariable(variable));
    }

    public QLightPlans(Path<? extends LightPlans> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLightPlans(PathMetadata metadata) {
        super(LightPlans.class, metadata);
    }

}

