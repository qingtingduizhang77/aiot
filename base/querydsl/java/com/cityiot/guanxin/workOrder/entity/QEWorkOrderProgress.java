package com.cityiot.guanxin.workOrder.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEWorkOrderProgress is a Querydsl query type for EWorkOrderProgress
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEWorkOrderProgress extends EntityPathBase<EWorkOrderProgress> {

    private static final long serialVersionUID = -332844069L;

    public static final QEWorkOrderProgress eWorkOrderProgress = new QEWorkOrderProgress("eWorkOrderProgress");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final NumberPath<Long> operatorId = createNumber("operatorId", Long.class);

    public final StringPath progressContent = createString("progressContent");

    public final NumberPath<Integer> progressType = createNumber("progressType", Integer.class);

    public final StringPath remark = createString("remark");

    public final DateTimePath<java.util.Date> time = createDateTime("time", java.util.Date.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public final NumberPath<Long> workOrderId = createNumber("workOrderId", Long.class);

    public final NumberPath<Integer> workOrderType = createNumber("workOrderType", Integer.class);

    public QEWorkOrderProgress(String variable) {
        super(EWorkOrderProgress.class, forVariable(variable));
    }

    public QEWorkOrderProgress(Path<? extends EWorkOrderProgress> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEWorkOrderProgress(PathMetadata metadata) {
        super(EWorkOrderProgress.class, metadata);
    }

}

