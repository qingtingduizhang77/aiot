package com.cityiot.guanxin.workOrder.inspection.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QInspectionRecord is a Querydsl query type for InspectionRecord
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QInspectionRecord extends EntityPathBase<InspectionRecord> {

    private static final long serialVersionUID = 1167557099L;

    public static final QInspectionRecord inspectionRecord = new QInspectionRecord("inspectionRecord");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final NumberPath<Long> deviceId = createNumber("deviceId", Long.class);

    public final NumberPath<Long> faultBillNo = createNumber("faultBillNo", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> isCreatedFault = createNumber("isCreatedFault", Integer.class);

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    public final DateTimePath<java.util.Date> lastPatrolTime = createDateTime("lastPatrolTime", java.util.Date.class);

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final NumberPath<Long> patroller = createNumber("patroller", Long.class);

    public final NumberPath<Long> patrollerNo = createNumber("patrollerNo", Long.class);

    public final StringPath patrollerPhone = createString("patrollerPhone");

    public final NumberPath<Integer> patrolStatus = createNumber("patrolStatus", Integer.class);

    public final DateTimePath<java.util.Date> patrolTime = createDateTime("patrolTime", java.util.Date.class);

    public final StringPath remark = createString("remark");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QInspectionRecord(String variable) {
        super(InspectionRecord.class, forVariable(variable));
    }

    public QInspectionRecord(Path<? extends InspectionRecord> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInspectionRecord(PathMetadata metadata) {
        super(InspectionRecord.class, metadata);
    }

}

