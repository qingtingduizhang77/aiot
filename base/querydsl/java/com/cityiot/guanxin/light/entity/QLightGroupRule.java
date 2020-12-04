package com.cityiot.guanxin.light.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QLightGroupRule is a Querydsl query type for LightGroupRule
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLightGroupRule extends EntityPathBase<LightGroupRule> {

    private static final long serialVersionUID = -1918180876L;

    public static final QLightGroupRule lightGroupRule = new QLightGroupRule("lightGroupRule");

    public final QRule _super = new QRule(this);

    //inherited
    public final NumberPath<Integer> brightness = _super.brightness;

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    //inherited
    public final TimePath<java.util.Date> endTime = _super.endTime;

    public final NumberPath<Long> groupId = createNumber("groupId", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    //inherited
    public final NumberPath<Integer> openStatus = _super.openStatus;

    //inherited
    public final TimePath<java.util.Date> startTime = _super.startTime;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QLightGroupRule(String variable) {
        super(LightGroupRule.class, forVariable(variable));
    }

    public QLightGroupRule(Path<? extends LightGroupRule> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLightGroupRule(PathMetadata metadata) {
        super(LightGroupRule.class, metadata);
    }

}

