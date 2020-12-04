package com.cityiot.guanxin.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSystemVariableType is a Querydsl query type for SystemVariableType
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSystemVariableType extends EntityPathBase<SystemVariableType> {

    private static final long serialVersionUID = -1782123444L;

    public static final QSystemVariableType systemVariableType = new QSystemVariableType("systemVariableType");

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

    public final StringPath name = createString("name");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QSystemVariableType(String variable) {
        super(SystemVariableType.class, forVariable(variable));
    }

    public QSystemVariableType(Path<? extends SystemVariableType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSystemVariableType(PathMetadata metadata) {
        super(SystemVariableType.class, metadata);
    }

}

