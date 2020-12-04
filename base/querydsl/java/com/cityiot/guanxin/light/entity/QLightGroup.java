package com.cityiot.guanxin.light.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QLightGroup is a Querydsl query type for LightGroup
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLightGroup extends EntityPathBase<LightGroup> {

    private static final long serialVersionUID = -974060840L;

    public static final QLightGroup lightGroup = new QLightGroup("lightGroup");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final StringPath address = createString("address");

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final StringPath groupName = createString("groupName");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> lampCount = createNumber("lampCount", Integer.class);

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final NumberPath<Integer> timeSwitchStatus = createNumber("timeSwitchStatus", Integer.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QLightGroup(String variable) {
        super(LightGroup.class, forVariable(variable));
    }

    public QLightGroup(Path<? extends LightGroup> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLightGroup(PathMetadata metadata) {
        super(LightGroup.class, metadata);
    }

}

