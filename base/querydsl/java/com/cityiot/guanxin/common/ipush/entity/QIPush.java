package com.cityiot.guanxin.common.ipush.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QIPush is a Querydsl query type for IPush
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QIPush extends EntityPathBase<IPush> {

    private static final long serialVersionUID = -888423486L;

    public static final QIPush iPush = new QIPush("iPush");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final NumberPath<Long> AccountId = createNumber("AccountId", Long.class);

    public final NumberPath<Long> accountId = createNumber("accountId", Long.class);

    public final StringPath clientId = createString("clientId");

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    public final StringPath message = createString("message");

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final DateTimePath<java.util.Date> pushTime = createDateTime("pushTime", java.util.Date.class);

    public final NumberPath<Long> status = createNumber("status", Long.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QIPush(String variable) {
        super(IPush.class, forVariable(variable));
    }

    public QIPush(Path<? extends IPush> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIPush(PathMetadata metadata) {
        super(IPush.class, metadata);
    }

}

