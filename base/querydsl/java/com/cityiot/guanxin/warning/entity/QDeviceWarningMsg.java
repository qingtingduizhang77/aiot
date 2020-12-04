package com.cityiot.guanxin.warning.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDeviceWarningMsg is a Querydsl query type for DeviceWarningMsg
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDeviceWarningMsg extends EntityPathBase<DeviceWarningMsg> {

    private static final long serialVersionUID = -364429488L;

    public static final QDeviceWarningMsg deviceWarningMsg = new QDeviceWarningMsg("deviceWarningMsg");

    public final com.cityiot.guanxin.common.entity.QBaseSimpleEntity _super = new com.cityiot.guanxin.common.entity.QBaseSimpleEntity(this);

    public final StringPath area = createString("area");

    public final StringPath coordinates = createString("coordinates");

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    public final StringPath deviceCode = createString("deviceCode");

    public final NumberPath<Long> deviceId = createNumber("deviceId", Long.class);

    public final NumberPath<Integer> deviceModelId = createNumber("deviceModelId", Integer.class);

    public final StringPath deviceName = createString("deviceName");

    public final NumberPath<Integer> deviceTypeId = createNumber("deviceTypeId", Integer.class);

    public final StringPath handleText = createString("handleText");

    public final DateTimePath<java.util.Date> handleTime = createDateTime("handleTime", java.util.Date.class);

    public final NumberPath<Integer> handleUserId = createNumber("handleUserId", Integer.class);

    public final StringPath handleUserName = createString("handleUserName");

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    public final StringPath noticeRoleId = createString("noticeRoleId");

    public final StringPath noticeUserId = createString("noticeUserId");

    public final StringPath noticeUserName = createString("noticeUserName");

    public final NumberPath<Integer> ruleId = createNumber("ruleId", Integer.class);

    public final StringPath ruleName = createString("ruleName");

    public final NumberPath<Integer> state = createNumber("state", Integer.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public final StringPath warningEvel = createString("warningEvel");

    public final DateTimePath<java.util.Date> warningTime = createDateTime("warningTime", java.util.Date.class);

    public final StringPath warningType = createString("warningType");

    public QDeviceWarningMsg(String variable) {
        super(DeviceWarningMsg.class, forVariable(variable));
    }

    public QDeviceWarningMsg(Path<? extends DeviceWarningMsg> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeviceWarningMsg(PathMetadata metadata) {
        super(DeviceWarningMsg.class, metadata);
    }

}

