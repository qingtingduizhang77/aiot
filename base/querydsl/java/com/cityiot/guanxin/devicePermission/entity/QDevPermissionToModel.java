package com.cityiot.guanxin.devicePermission.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDevPermissionToModel is a Querydsl query type for DevPermissionToModel
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDevPermissionToModel extends EntityPathBase<DevPermissionToModel> {

    private static final long serialVersionUID = 965169808L;

    public static final QDevPermissionToModel devPermissionToModel = new QDevPermissionToModel("devPermissionToModel");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final NumberPath<Long> deviceModelId = createNumber("deviceModelId", Long.class);

    public final NumberPath<Long> devicePermissionId = createNumber("devicePermissionId", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QDevPermissionToModel(String variable) {
        super(DevPermissionToModel.class, forVariable(variable));
    }

    public QDevPermissionToModel(Path<? extends DevPermissionToModel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDevPermissionToModel(PathMetadata metadata) {
        super(DevPermissionToModel.class, metadata);
    }

}

