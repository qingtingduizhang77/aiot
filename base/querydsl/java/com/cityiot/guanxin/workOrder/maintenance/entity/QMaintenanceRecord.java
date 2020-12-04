package com.cityiot.guanxin.workOrder.maintenance.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMaintenanceRecord is a Querydsl query type for MaintenanceRecord
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMaintenanceRecord extends EntityPathBase<MaintenanceRecord> {

    private static final long serialVersionUID = 2046752713L;

    public static final QMaintenanceRecord maintenanceRecord = new QMaintenanceRecord("maintenanceRecord");

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

    public final DateTimePath<java.util.Date> lastMaintainTime = createDateTime("lastMaintainTime", java.util.Date.class);

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    public final NumberPath<Integer> maintainCycle = createNumber("maintainCycle", Integer.class);

    public final NumberPath<Long> maintainNo = createNumber("maintainNo", Long.class);

    public final NumberPath<Integer> maintainStatus = createNumber("maintainStatus", Integer.class);

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final NumberPath<Long> operatorId = createNumber("operatorId", Long.class);

    public final StringPath operatorPhone = createString("operatorPhone");

    public final DateTimePath<java.util.Date> operatorTime = createDateTime("operatorTime", java.util.Date.class);

    public final StringPath remark = createString("remark");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QMaintenanceRecord(String variable) {
        super(MaintenanceRecord.class, forVariable(variable));
    }

    public QMaintenanceRecord(Path<? extends MaintenanceRecord> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMaintenanceRecord(PathMetadata metadata) {
        super(MaintenanceRecord.class, metadata);
    }

}

