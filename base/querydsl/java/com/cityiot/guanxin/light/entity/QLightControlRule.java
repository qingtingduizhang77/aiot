package com.cityiot.guanxin.light.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QLightControlRule is a Querydsl query type for LightControlRule
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLightControlRule extends EntityPathBase<LightControlRule> {

    private static final long serialVersionUID = 1017388946L;

    public static final QLightControlRule lightControlRule = new QLightControlRule("lightControlRule");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final StringPath code = createString("code");

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

    public final StringPath name = createString("name");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QLightControlRule(String variable) {
        super(LightControlRule.class, forVariable(variable));
    }

    public QLightControlRule(Path<? extends LightControlRule> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLightControlRule(PathMetadata metadata) {
        super(LightControlRule.class, metadata);
    }

}

