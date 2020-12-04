package com.cityiot.guanxin.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAccountinfo is a Querydsl query type for Accountinfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAccountinfo extends EntityPathBase<Accountinfo> {

    private static final long serialVersionUID = 1622603087L;

    public static final QAccountinfo accountinfo = new QAccountinfo("accountinfo");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final StringPath account = createString("account");

    public final StringPath apptoken = createString("apptoken");

    public final StringPath clientId = createString("clientId");

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final NumberPath<Integer> did = createNumber("did", Integer.class);

    public final NumberPath<Integer> disable = createNumber("disable", Integer.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final StringPath password = createString("password");

    public final StringPath pctoken = createString("pctoken");

    public final StringPath phone = createString("phone");

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QAccountinfo(String variable) {
        super(Accountinfo.class, forVariable(variable));
    }

    public QAccountinfo(Path<? extends Accountinfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAccountinfo(PathMetadata metadata) {
        super(Accountinfo.class, metadata);
    }

}

