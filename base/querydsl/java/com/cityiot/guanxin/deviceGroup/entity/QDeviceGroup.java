package com.cityiot.guanxin.deviceGroup.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDeviceGroup is a Querydsl query type for DeviceGroup
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDeviceGroup extends EntityPathBase<DeviceGroup> {

    private static final long serialVersionUID = 1459397959L;

    public static final QDeviceGroup deviceGroup = new QDeviceGroup("deviceGroup");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final NumberPath<Long> areaCode = createNumber("areaCode", Long.class);

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

    public final NumberPath<Integer> level = createNumber("level", Integer.class);

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final StringPath name = createString("name");

    public final NumberPath<Long> orderNo = createNumber("orderNo", Long.class);

    public final NumberPath<Long> parentId = createNumber("parentId", Long.class);

    public final StringPath parentIdArr = createString("parentIdArr");

    public final StringPath remark = createString("remark");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QDeviceGroup(String variable) {
        super(DeviceGroup.class, forVariable(variable));
    }

    public QDeviceGroup(Path<? extends DeviceGroup> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeviceGroup(PathMetadata metadata) {
        super(DeviceGroup.class, metadata);
    }

}

