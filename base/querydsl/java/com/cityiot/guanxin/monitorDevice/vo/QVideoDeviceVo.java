package com.cityiot.guanxin.monitorDevice.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QVideoDeviceVo is a Querydsl query type for VideoDeviceVo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QVideoDeviceVo extends EntityPathBase<VideoDeviceVo> {

    private static final long serialVersionUID = -175175305L;

    public static final QVideoDeviceVo videoDeviceVo = new QVideoDeviceVo("videoDeviceVo");

    public final StringPath area = createString("area");

    public final StringPath deviceCode = createString("deviceCode");

    public final NumberPath<Long> deviceId = createNumber("deviceId", Long.class);

    public final StringPath deviceName = createString("deviceName");

    public final StringPath distance = createString("distance");

    public final StringPath lat = createString("lat");

    public final StringPath lng = createString("lng");

    public final StringPath streamUrl = createString("streamUrl");

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public final NumberPath<Integer> videoStatus = createNumber("videoStatus", Integer.class);

    public QVideoDeviceVo(String variable) {
        super(VideoDeviceVo.class, forVariable(variable));
    }

    public QVideoDeviceVo(Path<? extends VideoDeviceVo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVideoDeviceVo(PathMetadata metadata) {
        super(VideoDeviceVo.class, metadata);
    }

}

