package com.cityiot.guanxin.monitorDevice.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDustbinDeviceVo is a Querydsl query type for DustbinDeviceVo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDustbinDeviceVo extends EntityPathBase<DustbinDeviceVo> {

    private static final long serialVersionUID = 2021609681L;

    public static final QDustbinDeviceVo dustbinDeviceVo = new QDustbinDeviceVo("dustbinDeviceVo");

    public final StringPath area = createString("area");

    public final StringPath deviceCode = createString("deviceCode");

    public final NumberPath<Long> deviceId = createNumber("deviceId", Long.class);

    public final StringPath deviceName = createString("deviceName");

    public final StringPath distance = createString("distance");

    public final NumberPath<Integer> DustbinStatus = createNumber("DustbinStatus", Integer.class);

    public final StringPath lat = createString("lat");

    public final StringPath lng = createString("lng");

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public final NumberPath<Integer> useStatus = createNumber("useStatus", Integer.class);

    public QDustbinDeviceVo(String variable) {
        super(DustbinDeviceVo.class, forVariable(variable));
    }

    public QDustbinDeviceVo(Path<? extends DustbinDeviceVo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDustbinDeviceVo(PathMetadata metadata) {
        super(DustbinDeviceVo.class, metadata);
    }

}

