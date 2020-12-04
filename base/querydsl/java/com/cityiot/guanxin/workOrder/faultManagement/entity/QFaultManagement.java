package com.cityiot.guanxin.workOrder.faultManagement.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QFaultManagement is a Querydsl query type for FaultManagement
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFaultManagement extends EntityPathBase<FaultManagement> {

    private static final long serialVersionUID = 2035745816L;

    public static final QFaultManagement faultManagement = new QFaultManagement("faultManagement");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final NumberPath<Long> deviceId = createNumber("deviceId", Long.class);

    public final NumberPath<Long> faultBillNo = createNumber("faultBillNo", Long.class);

    public final StringPath faultCode = createString("faultCode");

    public final NumberPath<Integer> faultCount = createNumber("faultCount", Integer.class);

    public final StringPath faultInfo = createString("faultInfo");

    public final DateTimePath<java.util.Date> faultTime = createDateTime("faultTime", java.util.Date.class);

    public final NumberPath<Long> handlerId = createNumber("handlerId", Long.class);

    public final StringPath handlerName = createString("handlerName");

    public final NumberPath<Integer> handleStatus = createNumber("handleStatus", Integer.class);

    public final DateTimePath<java.util.Date> handleTime = createDateTime("handleTime", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final NumberPath<Long> registrarId = createNumber("registrarId", Long.class);

    public final StringPath registrarPhone = createString("registrarPhone");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QFaultManagement(String variable) {
        super(FaultManagement.class, forVariable(variable));
    }

    public QFaultManagement(Path<? extends FaultManagement> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFaultManagement(PathMetadata metadata) {
        super(FaultManagement.class, metadata);
    }

}

