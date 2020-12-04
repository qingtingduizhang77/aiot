package com.cityiot.guanxin.workOrder.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEInspectionWorkOrder is a Querydsl query type for EInspectionWorkOrder
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEInspectionWorkOrder extends EntityPathBase<EInspectionWorkOrder> {

    private static final long serialVersionUID = 915431226L;

    public static final QEInspectionWorkOrder eInspectionWorkOrder = new QEInspectionWorkOrder("eInspectionWorkOrder");

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

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final StringPath originator = createString("originator");

    public final StringPath originatorPhone = createString("originatorPhone");

    public final NumberPath<Long> recordId = createNumber("recordId", Long.class);

    public final StringPath remark = createString("remark");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QEInspectionWorkOrder(String variable) {
        super(EInspectionWorkOrder.class, forVariable(variable));
    }

    public QEInspectionWorkOrder(Path<? extends EInspectionWorkOrder> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEInspectionWorkOrder(PathMetadata metadata) {
        super(EInspectionWorkOrder.class, metadata);
    }

}

