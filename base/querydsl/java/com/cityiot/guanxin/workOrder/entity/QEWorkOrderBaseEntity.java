package com.cityiot.guanxin.workOrder.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEWorkOrderBaseEntity is a Querydsl query type for EWorkOrderBaseEntity
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QEWorkOrderBaseEntity extends EntityPathBase<EWorkOrderBaseEntity> {

    private static final long serialVersionUID = 998863938L;

    public static final QEWorkOrderBaseEntity eWorkOrderBaseEntity = new QEWorkOrderBaseEntity("eWorkOrderBaseEntity");

    public final com.cityiot.guanxin.common.entity.QOnlyIdEntity _super = new com.cityiot.guanxin.common.entity.QOnlyIdEntity(this);

    public final DateTimePath<java.util.Date> created = createDateTime("created", java.util.Date.class);

    public final NumberPath<Long> creator = createNumber("creator", Long.class);

    public final StringPath creatorName = createString("creatorName");

    public final StringPath creatorPhone = createString("creatorPhone");

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QEWorkOrderBaseEntity(String variable) {
        super(EWorkOrderBaseEntity.class, forVariable(variable));
    }

    public QEWorkOrderBaseEntity(Path<? extends EWorkOrderBaseEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEWorkOrderBaseEntity(PathMetadata metadata) {
        super(EWorkOrderBaseEntity.class, metadata);
    }

}

