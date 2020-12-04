package com.cityiot.guanxin.monitorDevice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEChargingPileDevice is a Querydsl query type for EChargingPileDevice
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEChargingPileDevice extends EntityPathBase<EChargingPileDevice> {

    private static final long serialVersionUID = 1599026773L;

    public static final QEChargingPileDevice eChargingPileDevice = new QEChargingPileDevice("eChargingPileDevice");

    public final com.cityiot.guanxin.common.entity.QOnlyIdEntity _super = new com.cityiot.guanxin.common.entity.QOnlyIdEntity(this);

    public final NumberPath<Integer> ChargingStatus = createNumber("ChargingStatus", Integer.class);

    public final NumberPath<Integer> chargingStatus = createNumber("chargingStatus", Integer.class);

    public final NumberPath<Long> deviceInfoId = createNumber("deviceInfoId", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> useStatus = createNumber("useStatus", Integer.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QEChargingPileDevice(String variable) {
        super(EChargingPileDevice.class, forVariable(variable));
    }

    public QEChargingPileDevice(Path<? extends EChargingPileDevice> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEChargingPileDevice(PathMetadata metadata) {
        super(EChargingPileDevice.class, metadata);
    }

}

