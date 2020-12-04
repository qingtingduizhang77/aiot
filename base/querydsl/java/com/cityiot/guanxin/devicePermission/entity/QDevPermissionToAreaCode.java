package com.cityiot.guanxin.devicePermission.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDevPermissionToAreaCode is a Querydsl query type for DevPermissionToAreaCode
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDevPermissionToAreaCode extends EntityPathBase<DevPermissionToAreaCode> {

    private static final long serialVersionUID = 1815150419L;

    public static final QDevPermissionToAreaCode devPermissionToAreaCode = new QDevPermissionToAreaCode("devPermissionToAreaCode");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final NumberPath<Long> areaCode = createNumber("areaCode", Long.class);

    public final NumberPath<Long> areaId = createNumber("areaId", Long.class);

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final NumberPath<Long> devicePermissionId = createNumber("devicePermissionId", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QDevPermissionToAreaCode(String variable) {
        super(DevPermissionToAreaCode.class, forVariable(variable));
    }

    public QDevPermissionToAreaCode(Path<? extends DevPermissionToAreaCode> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDevPermissionToAreaCode(PathMetadata metadata) {
        super(DevPermissionToAreaCode.class, metadata);
    }

}

