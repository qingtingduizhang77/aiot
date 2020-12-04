package com.cityiot.guanxin.monitor.statistics.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QStatistics is a Querydsl query type for Statistics
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QStatistics extends EntityPathBase<Statistics> {

    private static final long serialVersionUID = -418072442L;

    public static final QStatistics statistics = new QStatistics("statistics");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final NumberPath<Long> areaId = createNumber("areaId", Long.class);

    public final StringPath areaName = createString("areaName");

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

    public final NumberPath<Long> streetId = createNumber("streetId", Long.class);

    public final StringPath streetName = createString("streetName");

    public final NumberPath<Integer> targetCount = createNumber("targetCount", Integer.class);

    public final DateTimePath<java.util.Date> timeScopeEnd = createDateTime("timeScopeEnd", java.util.Date.class);

    public final DateTimePath<java.util.Date> timeScopeStart = createDateTime("timeScopeStart", java.util.Date.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QStatistics(String variable) {
        super(Statistics.class, forVariable(variable));
    }

    public QStatistics(Path<? extends Statistics> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStatistics(PathMetadata metadata) {
        super(Statistics.class, metadata);
    }

}

