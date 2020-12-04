package com.cityiot.guanxin.workOrder.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEWorkOrder is a Querydsl query type for EWorkOrder
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEWorkOrder extends EntityPathBase<EWorkOrder> {

    private static final long serialVersionUID = 598702990L;

    public static final QEWorkOrder eWorkOrder = new QEWorkOrder("eWorkOrder");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final StringPath area = createString("area");

    public final StringPath code = createString("code");

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final StringPath desContent = createString("desContent");

    public final StringPath deviceCode = createString("deviceCode");

    public final NumberPath<Long> deviceInfoId = createNumber("deviceInfoId", Long.class);

    public final StringPath deviceModel = createString("deviceModel");

    public final StringPath deviceName = createString("deviceName");

    public final StringPath deviceType = createString("deviceType");

    public final StringPath distance = createString("distance");

    public final NumberPath<Long> handlerId = createNumber("handlerId", Long.class);

    public final StringPath handlerName = createString("handlerName");

    public final NumberPath<Integer> handleStatus = createNumber("handleStatus", Integer.class);

    public final DateTimePath<java.util.Date> handleTime = createDateTime("handleTime", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    public final StringPath latitude = createString("latitude");

    public final StringPath longitude = createString("longitude");

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final NumberPath<Long> recordId = createNumber("recordId", Long.class);

    public final StringPath remark = createString("remark");

    public final StringPath title = createString("title");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public final NumberPath<Integer> workOrderType = createNumber("workOrderType", Integer.class);

    public QEWorkOrder(String variable) {
        super(EWorkOrder.class, forVariable(variable));
    }

    public QEWorkOrder(Path<? extends EWorkOrder> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEWorkOrder(PathMetadata metadata) {
        super(EWorkOrder.class, metadata);
    }

}

