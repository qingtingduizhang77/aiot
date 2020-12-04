package com.cityiot.guanxin.monitorDevice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEVideoDevice is a Querydsl query type for EVideoDevice
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEVideoDevice extends EntityPathBase<EVideoDevice> {

    private static final long serialVersionUID = -1303469227L;

    public static final QEVideoDevice eVideoDevice = new QEVideoDevice("eVideoDevice");

    public final com.cityiot.guanxin.common.entity.QOnlyIdEntity _super = new com.cityiot.guanxin.common.entity.QOnlyIdEntity(this);

    public final NumberPath<Long> deviceInfoId = createNumber("deviceInfoId", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath streamUrl = createString("streamUrl");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public final NumberPath<Integer> VideoStatus = createNumber("VideoStatus", Integer.class);

    public final NumberPath<Integer> videoStatus = createNumber("videoStatus", Integer.class);

    public QEVideoDevice(String variable) {
        super(EVideoDevice.class, forVariable(variable));
    }

    public QEVideoDevice(Path<? extends EVideoDevice> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEVideoDevice(PathMetadata metadata) {
        super(EVideoDevice.class, metadata);
    }

}

