package com.cityiot.guanxin.monitorDevice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEDustbinDevice is a Querydsl query type for EDustbinDevice
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEDustbinDevice extends EntityPathBase<EDustbinDevice> {

    private static final long serialVersionUID = -55802001L;

    public static final QEDustbinDevice eDustbinDevice = new QEDustbinDevice("eDustbinDevice");

    public final com.cityiot.guanxin.common.entity.QOnlyIdEntity _super = new com.cityiot.guanxin.common.entity.QOnlyIdEntity(this);

    public final NumberPath<Long> deviceInfoId = createNumber("deviceInfoId", Long.class);

    public final NumberPath<Integer> DustbinStatus = createNumber("DustbinStatus", Integer.class);

    public final NumberPath<Integer> dustbinStatus = createNumber("dustbinStatus", Integer.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> useStatus = createNumber("useStatus", Integer.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QEDustbinDevice(String variable) {
        super(EDustbinDevice.class, forVariable(variable));
    }

    public QEDustbinDevice(Path<? extends EDustbinDevice> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEDustbinDevice(PathMetadata metadata) {
        super(EDustbinDevice.class, metadata);
    }

}

