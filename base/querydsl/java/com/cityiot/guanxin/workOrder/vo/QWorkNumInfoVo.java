package com.cityiot.guanxin.workOrder.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QWorkNumInfoVo is a Querydsl query type for WorkNumInfoVo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QWorkNumInfoVo extends EntityPathBase<WorkNumInfoVo> {

    private static final long serialVersionUID = 2111424124L;

    public static final QWorkNumInfoVo workNumInfoVo = new QWorkNumInfoVo("workNumInfoVo");

    public final NumberPath<Integer> chargebackNum = createNumber("chargebackNum", Integer.class);

    public final NumberPath<Integer> doingNum = createNumber("doingNum", Integer.class);

    public final NumberPath<Integer> finishedNum = createNumber("finishedNum", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> todoNum = createNumber("todoNum", Integer.class);

    public QWorkNumInfoVo(String variable) {
        super(WorkNumInfoVo.class, forVariable(variable));
    }

    public QWorkNumInfoVo(Path<? extends WorkNumInfoVo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWorkNumInfoVo(PathMetadata metadata) {
        super(WorkNumInfoVo.class, metadata);
    }

}

