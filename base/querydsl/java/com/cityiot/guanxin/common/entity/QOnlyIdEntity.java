package com.cityiot.guanxin.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QOnlyIdEntity is a Querydsl query type for OnlyIdEntity
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QOnlyIdEntity extends EntityPathBase<OnlyIdEntity> {

    private static final long serialVersionUID = -639847594L;

    public static final QOnlyIdEntity onlyIdEntity = new QOnlyIdEntity("onlyIdEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> version = createNumber("version", Long.class);

    public QOnlyIdEntity(String variable) {
        super(OnlyIdEntity.class, forVariable(variable));
    }

    public QOnlyIdEntity(Path<? extends OnlyIdEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOnlyIdEntity(PathMetadata metadata) {
        super(OnlyIdEntity.class, metadata);
    }

}

