package com.cityiot.guanxin.deviceInformation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDeviceModel is a Querydsl query type for DeviceModel
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDeviceModel extends EntityPathBase<DeviceModel> {

    private static final long serialVersionUID = -1443209564L;

    public static final QDeviceModel deviceModel1 = new QDeviceModel("deviceModel");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final NumberPath<Integer> checkUpCycle = createNumber("checkUpCycle", Integer.class);

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final NumberPath<Long> deviceBrandId = createNumber("deviceBrandId", Long.class);

    public final StringPath deviceModel = createString("deviceModel");

    public final NumberPath<Long> deviceTypeId = createNumber("deviceTypeId", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    public final NumberPath<Integer> maintenanceCycle = createNumber("maintenanceCycle", Integer.class);

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QDeviceModel(String variable) {
        super(DeviceModel.class, forVariable(variable));
    }

    public QDeviceModel(Path<? extends DeviceModel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeviceModel(PathMetadata metadata) {
        super(DeviceModel.class, metadata);
    }

}

