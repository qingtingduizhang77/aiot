package com.cityiot.guanxin.log.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QLogOperation is a Querydsl query type for LogOperation
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLogOperation extends EntityPathBase<LogOperation> {

    private static final long serialVersionUID = -253133632L;

    public static final QLogOperation logOperation = new QLogOperation("logOperation");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final StringPath clientIp = createString("clientIp");

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath identity = createString("identity");

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final StringPath operEvent = createString("operEvent");

    public final DateTimePath<java.util.Date> operTime = createDateTime("operTime", java.util.Date.class);

    public final NumberPath<Long> operType = createNumber("operType", Long.class);

    public final StringPath operUrl = createString("operUrl");

    public final StringPath reqType = createString("reqType");

    public final StringPath username = createString("username");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QLogOperation(String variable) {
        super(LogOperation.class, forVariable(variable));
    }

    public QLogOperation(Path<? extends LogOperation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLogOperation(PathMetadata metadata) {
        super(LogOperation.class, metadata);
    }

}

