package com.cityiot.guanxin.workOrder.operatorOrCompanyManage.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QResponsibleArea is a Querydsl query type for ResponsibleArea
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QResponsibleArea extends EntityPathBase<ResponsibleArea> {

    private static final long serialVersionUID = 166821152L;

    public static final QResponsibleArea responsibleArea = new QResponsibleArea("responsibleArea");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final StringPath area = createString("area");

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

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QResponsibleArea(String variable) {
        super(ResponsibleArea.class, forVariable(variable));
    }

    public QResponsibleArea(Path<? extends ResponsibleArea> path) {
        super(path.getType(), path.getMetadata());
    }

    public QResponsibleArea(PathMetadata metadata) {
        super(ResponsibleArea.class, metadata);
    }

}

