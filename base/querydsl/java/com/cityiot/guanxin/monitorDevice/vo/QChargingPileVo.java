package com.cityiot.guanxin.monitorDevice.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QChargingPileVo is a Querydsl query type for ChargingPileVo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QChargingPileVo extends EntityPathBase<ChargingPileVo> {

    private static final long serialVersionUID = -1315589425L;

    public static final QChargingPileVo chargingPileVo = new QChargingPileVo("chargingPileVo");

    public final StringPath area = createString("area");

    public final NumberPath<Integer> ChargingStatus = createNumber("ChargingStatus", Integer.class);

    public final StringPath deviceCode = createString("deviceCode");

    public final NumberPath<Long> deviceId = createNumber("deviceId", Long.class);

    public final StringPath deviceName = createString("deviceName");

    public final StringPath distance = createString("distance");

    public final StringPath lat = createString("lat");

    public final StringPath lng = createString("lng");

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public final NumberPath<Integer> useStatus = createNumber("useStatus", Integer.class);

    public QChargingPileVo(String variable) {
        super(ChargingPileVo.class, forVariable(variable));
    }

    public QChargingPileVo(Path<? extends ChargingPileVo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChargingPileVo(PathMetadata metadata) {
        super(ChargingPileVo.class, metadata);
    }

}

