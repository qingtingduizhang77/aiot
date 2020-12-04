package com.cityiot.guanxin.monitorDevice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QELampDevice is a Querydsl query type for ELampDevice
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QELampDevice extends EntityPathBase<ELampDevice> {

    private static final long serialVersionUID = -518084310L;

    public static final QELampDevice eLampDevice = new QELampDevice("eLampDevice");

    public final com.cityiot.guanxin.common.entity.QOnlyIdEntity _super = new com.cityiot.guanxin.common.entity.QOnlyIdEntity(this);

    public final NumberPath<Integer> brightness = createNumber("brightness", Integer.class);

    public final NumberPath<Long> deviceInfoId = createNumber("deviceInfoId", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> switchStatus = createNumber("switchStatus", Integer.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QELampDevice(String variable) {
        super(ELampDevice.class, forVariable(variable));
    }

    public QELampDevice(Path<? extends ELampDevice> path) {
        super(path.getType(), path.getMetadata());
    }

    public QELampDevice(PathMetadata metadata) {
        super(ELampDevice.class, metadata);
    }

}

