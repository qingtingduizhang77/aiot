package com.cityiot.guanxin.message.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEMessageCenter is a Querydsl query type for EMessageCenter
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEMessageCenter extends EntityPathBase<EMessageCenter> {

    private static final long serialVersionUID = -367746985L;

    public static final QEMessageCenter eMessageCenter = new QEMessageCenter("eMessageCenter");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    public final StringPath messageContent = createString("messageContent");

    public final NumberPath<Integer> messageStatus = createNumber("messageStatus", Integer.class);

    public final DateTimePath<java.util.Date> messageTime = createDateTime("messageTime", java.util.Date.class);

    public final NumberPath<Integer> messageType = createNumber("messageType", Integer.class);

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final NumberPath<Long> targetId = createNumber("targetId", Long.class);

    public final StringPath url = createString("url");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QEMessageCenter(String variable) {
        super(EMessageCenter.class, forVariable(variable));
    }

    public QEMessageCenter(Path<? extends EMessageCenter> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEMessageCenter(PathMetadata metadata) {
        super(EMessageCenter.class, metadata);
    }

}

