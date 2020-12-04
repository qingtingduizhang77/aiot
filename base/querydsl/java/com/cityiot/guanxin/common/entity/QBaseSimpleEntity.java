package com.cityiot.guanxin.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseSimpleEntity is a Querydsl query type for BaseSimpleEntity
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QBaseSimpleEntity extends EntityPathBase<BaseSimpleEntity> {

    private static final long serialVersionUID = 394143314L;

    public static final QBaseSimpleEntity baseSimpleEntity = new QBaseSimpleEntity("baseSimpleEntity");

    public final QOnlyIdEntity _super = new QOnlyIdEntity(this);

    public final DateTimePath<java.util.Date> created = createDateTime("created", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final DateTimePath<java.util.Date> lastmodi = createDateTime("lastmodi", java.util.Date.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QBaseSimpleEntity(String variable) {
        super(BaseSimpleEntity.class, forVariable(variable));
    }

    public QBaseSimpleEntity(Path<? extends BaseSimpleEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseSimpleEntity(PathMetadata metadata) {
        super(BaseSimpleEntity.class, metadata);
    }

}

