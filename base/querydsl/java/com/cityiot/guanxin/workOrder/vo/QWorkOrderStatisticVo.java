package com.cityiot.guanxin.workOrder.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QWorkOrderStatisticVo is a Querydsl query type for WorkOrderStatisticVo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QWorkOrderStatisticVo extends EntityPathBase<WorkOrderStatisticVo> {

    private static final long serialVersionUID = 438836684L;

    public static final QWorkOrderStatisticVo workOrderStatisticVo = new QWorkOrderStatisticVo("workOrderStatisticVo");

    public final NumberPath<Integer> finishedNum = createNumber("finishedNum", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> unfinishedNum = createNumber("unfinishedNum", Integer.class);

    public final NumberPath<Integer> workOrderType = createNumber("workOrderType", Integer.class);

    public QWorkOrderStatisticVo(String variable) {
        super(WorkOrderStatisticVo.class, forVariable(variable));
    }

    public QWorkOrderStatisticVo(Path<? extends WorkOrderStatisticVo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWorkOrderStatisticVo(PathMetadata metadata) {
        super(WorkOrderStatisticVo.class, metadata);
    }

}

