package com.cityiot.guanxin.videoGroup.entity;

import com.cityiot.guanxin.common.entity.QOnlyIdEntity;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;

import javax.annotation.Generated;
import java.util.Date;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QVideoGroupToDevice is a Querydsl query type for VideoGroupToDevice
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QVideoGroupToDevice extends EntityPathBase<VideoGroupToDevice> {

    private static final long serialVersionUID = 6339135037876236392L;

    public static final QVideoGroupToDevice videoGroupToDevice = new QVideoGroupToDevice("videoGroupToDevice");

    public final QOnlyIdEntity _super = new QOnlyIdEntity(this);

    public final NumberPath<Long> deviceId = createNumber("deviceId", Long.class);

    public final NumberPath<Long> videoGroupId = createNumber("videoGroupId", Long.class);

    public final NumberPath<Long> ordering = createNumber("ordering", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QVideoGroupToDevice(String variable) {
        super(VideoGroupToDevice.class, forVariable(variable));
    }

    public QVideoGroupToDevice(Path<? extends VideoGroupToDevice> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVideoGroupToDevice(PathMetadata metadata) {
        super(VideoGroupToDevice.class, metadata);
    }
}
