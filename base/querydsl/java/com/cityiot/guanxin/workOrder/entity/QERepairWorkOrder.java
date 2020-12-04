package com.cityiot.guanxin.workOrder.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QERepairWorkOrder is a Querydsl query type for ERepairWorkOrder
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QERepairWorkOrder extends EntityPathBase<ERepairWorkOrder> {

    private static final long serialVersionUID = -26998335L;

    public static final QERepairWorkOrder eRepairWorkOrder = new QERepairWorkOrder("eRepairWorkOrder");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final StringPath code = createString("code");

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final StringPath desContent = createString("desContent");

    public final NumberPath<Long> deviceInfoId = createNumber("deviceInfoId", Long.class);

    public final NumberPath<Long> handlerId = createNumber("handlerId", Long.class);

    public final NumberPath<Integer> handleStatus = createNumber("handleStatus", Integer.class);

    public final DateTimePath<java.util.Date> handleTime = createDateTime("handleTime", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final NumberPath<Double> money = createNumber("money", Double.class);

    public final StringPath originator = createString("originator");

    public final StringPath originatorPhone = createString("originatorPhone");

    public final NumberPath<Long> recordId = createNumber("recordId", Long.class);

    public final NumberPath<Integer> recordType = createNumber("recordType", Integer.class);

    public final StringPath remark = createString("remark");

    public final StringPath repairItems = createString("repairItems");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QERepairWorkOrder(String variable) {
        super(ERepairWorkOrder.class, forVariable(variable));
    }

    public QERepairWorkOrder(Path<? extends ERepairWorkOrder> path) {
        super(path.getType(), path.getMetadata());
    }

    public QERepairWorkOrder(PathMetadata metadata) {
        super(ERepairWorkOrder.class, metadata);
    }

}

