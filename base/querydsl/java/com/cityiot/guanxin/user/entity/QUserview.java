package com.cityiot.guanxin.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserview is a Querydsl query type for Userview
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserview extends EntityPathBase<Userview> {

    private static final long serialVersionUID = -2091931524L;

    public static final QUserview userview = new QUserview("userview");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final StringPath account = createString("account");

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final NumberPath<Integer> did = createNumber("did", Integer.class);

    public final NumberPath<Integer> disable = createNumber("disable", Integer.class);

    public final StringPath headPortrait = createString("headPortrait");

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public final StringPath username = createString("username");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QUserview(String variable) {
        super(Userview.class, forVariable(variable));
    }

    public QUserview(Path<? extends Userview> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserview(PathMetadata metadata) {
        super(Userview.class, metadata);
    }

}

