package com.cityiot.guanxin.videoGroup.entity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QVideoGroup is a Querydsl query type for VideoGroup
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QVideoGroup extends EntityPathBase<VideoGroup> {

    private static final long serialVersionUID = -2447645726025694918L;

    public static final QVideoGroup videoGroup = new QVideoGroup("videoGroup");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    public final NumberPath<Long> level = createNumber("level", Long.class);

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final StringPath name = createString("name");

    public final NumberPath<Long> orderNo = createNumber("orderNo", Long.class);

    public final NumberPath<Long> parentId = createNumber("parentId", Long.class);

    public final StringPath parentIdArr = createString("parentIdArr");

    public final StringPath remark = createString("remark");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QVideoGroup(String variable) {
        super(VideoGroup.class, forVariable(variable));
    }

    public QVideoGroup(Path<? extends VideoGroup> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVideoGroup(PathMetadata metadata) {
        super(VideoGroup.class, metadata);
    }

}

