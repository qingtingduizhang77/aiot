package com.cityiot.guanxin.emergency.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEmergency is a Querydsl query type for Emergency
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEmergency extends EntityPathBase<Emergency> {

    private static final long serialVersionUID = -1196286265L;

    public static final QEmergency emergency = new QEmergency("emergency");

    public final com.cityiot.guanxin.common.entity.QOnlyIdEntity _super = new com.cityiot.guanxin.common.entity.QOnlyIdEntity(this);

    public final NumberPath<Long> deviceId = createNumber("deviceId", Long.class);

    public final NumberPath<Long> deviceTypeId = createNumber("deviceTypeId", Long.class);

    public final StringPath emergencyDesc = createString("emergencyDesc");

    public final NumberPath<Integer> emergencyType = createNumber("emergencyType", Integer.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> isReaded = createNumber("isReaded", Integer.class);

    public final DateTimePath<java.util.Date> publishTime = createDateTime("publishTime", java.util.Date.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QEmergency(String variable) {
        super(Emergency.class, forVariable(variable));
    }

    public QEmergency(Path<? extends Emergency> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmergency(PathMetadata metadata) {
        super(Emergency.class, metadata);
    }

}

