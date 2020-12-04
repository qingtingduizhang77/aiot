package com.cityiot.guanxin.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSystemVariable is a Querydsl query type for SystemVariable
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSystemVariable extends EntityPathBase<SystemVariable> {

    private static final long serialVersionUID = -1785644558L;

    public static final QSystemVariable systemVariable = new QSystemVariable("systemVariable");

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

    public final NumberPath<Integer> variable = createNumber("variable", Integer.class);

    public final NumberPath<Long> vartype = createNumber("vartype", Long.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QSystemVariable(String variable) {
        super(SystemVariable.class, forVariable(variable));
    }

    public QSystemVariable(Path<? extends SystemVariable> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSystemVariable(PathMetadata metadata) {
        super(SystemVariable.class, metadata);
    }

}

