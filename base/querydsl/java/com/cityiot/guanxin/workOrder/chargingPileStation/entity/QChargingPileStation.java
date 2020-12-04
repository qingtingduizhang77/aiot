package com.cityiot.guanxin.workOrder.chargingPileStation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QChargingPileStation is a Querydsl query type for ChargingPileStation
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QChargingPileStation extends EntityPathBase<ChargingPileStation> {

    private static final long serialVersionUID = 672103576L;

    public static final QChargingPileStation chargingPileStation = new QChargingPileStation("chargingPileStation");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final StringPath areaCode = createString("areaCode");

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    public final NumberPath<Double> lat = createNumber("lat", Double.class);

    public final NumberPath<Double> lng = createNumber("lng", Double.class);

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final StringPath remark = createString("remark");

    public final StringPath stationAddress = createString("stationAddress");

    public final StringPath stationName = createString("stationName");

    public final NumberPath<Integer> totalPile = createNumber("totalPile", Integer.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QChargingPileStation(String variable) {
        super(ChargingPileStation.class, forVariable(variable));
    }

    public QChargingPileStation(Path<? extends ChargingPileStation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChargingPileStation(PathMetadata metadata) {
        super(ChargingPileStation.class, metadata);
    }

}

