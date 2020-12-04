package com.cityiot.guanxin.lightPlans.entity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QLightPlansGroup extends EntityPathBase<LightPlansGroup> {
    private static final long serialVersionUID = -2732266281171408574L;

    public static final QLightPlansGroup lightPlansGroup = new QLightPlansGroup("lightPlansGroup");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final StringPath name = createString("name");
    //public final StringPath lightGroupIds = createString("lightGroupIds");
    //public final StringPath lightPlansIds = createString("lightPlansIds");
    public final NumberPath<Integer> status = createNumber("status", Integer.class);
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

    public QLightPlansGroup(String variable) {
        super(LightPlansGroup.class, forVariable(variable));
    }

    public QLightPlansGroup(Path<? extends LightPlansGroup> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLightPlansGroup(PathMetadata metadata) {
        super(LightPlansGroup.class, metadata);
    }

}
