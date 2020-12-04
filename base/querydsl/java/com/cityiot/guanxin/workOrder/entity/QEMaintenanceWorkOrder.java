package com.cityiot.guanxin.workOrder.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEMaintenanceWorkOrder is a Querydsl query type for EMaintenanceWorkOrder
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEMaintenanceWorkOrder extends EntityPathBase<EMaintenanceWorkOrder> {

    private static final long serialVersionUID = 367697785L;

    public static final QEMaintenanceWorkOrder eMaintenanceWorkOrder = new QEMaintenanceWorkOrder("eMaintenanceWorkOrder");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final StringPath code = createString("code");

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final StringPath desContent = createString("desContent");

    public final StringPath deviceIds = createString("deviceIds");

    public final StringPath deviceName = createString("deviceName");

    public final NumberPath<Long> handlerId = createNumber("handlerId", Long.class);

    public final NumberPath<Integer> handleStatus = createNumber("handleStatus", Integer.class);

    public final DateTimePath<java.util.Date> handleTime = createDateTime("handleTime", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    public final StringPath maintainenceItems = createString("maintainenceItems");

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final StringPath originator = createString("originator");

    public final StringPath originatorPhone = createString("originatorPhone");

    public final StringPath remark = createString("remark");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QEMaintenanceWorkOrder(String variable) {
        super(EMaintenanceWorkOrder.class, forVariable(variable));
    }

    public QEMaintenanceWorkOrder(Path<? extends EMaintenanceWorkOrder> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEMaintenanceWorkOrder(PathMetadata metadata) {
        super(EMaintenanceWorkOrder.class, metadata);
    }

}

