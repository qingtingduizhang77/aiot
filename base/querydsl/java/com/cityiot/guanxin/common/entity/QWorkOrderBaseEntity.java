package com.cityiot.guanxin.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QWorkOrderBaseEntity is a Querydsl query type for WorkOrderBaseEntity
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QWorkOrderBaseEntity extends EntityPathBase<WorkOrderBaseEntity> {

    private static final long serialVersionUID = 1240908933L;

    public static final QWorkOrderBaseEntity workOrderBaseEntity = new QWorkOrderBaseEntity("workOrderBaseEntity");

    public final QOnlyIdEntity _super = new QOnlyIdEntity(this);

    public final DateTimePath<java.util.Date> created = createDateTime("created", java.util.Date.class);

    public final NumberPath<Long> creator = createNumber("creator", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final DateTimePath<java.util.Date> lastmodi = createDateTime("lastmodi", java.util.Date.class);

    public final NumberPath<Long> modifier = createNumber("modifier", Long.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QWorkOrderBaseEntity(String variable) {
        super(WorkOrderBaseEntity.class, forVariable(variable));
    }

    public QWorkOrderBaseEntity(Path<? extends WorkOrderBaseEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWorkOrderBaseEntity(PathMetadata metadata) {
        super(WorkOrderBaseEntity.class, metadata);
    }

}

