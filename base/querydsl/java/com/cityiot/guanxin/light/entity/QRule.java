package com.cityiot.guanxin.light.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QRule is a Querydsl query type for Rule
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QRule extends EntityPathBase<Rule> {

    private static final long serialVersionUID = -662515189L;

    public static final QRule rule = new QRule("rule");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final NumberPath<Integer> brightness = createNumber("brightness", Integer.class);

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final TimePath<java.util.Date> endTime = createTime("endTime", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final NumberPath<Integer> openStatus = createNumber("openStatus", Integer.class);

    public final TimePath<java.util.Date> startTime = createTime("startTime", java.util.Date.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QRule(String variable) {
        super(Rule.class, forVariable(variable));
    }

    public QRule(Path<? extends Rule> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRule(PathMetadata metadata) {
        super(Rule.class, metadata);
    }

}

