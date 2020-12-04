package com.cityiot.guanxin.workOrder.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEWorkOrderImages is a Querydsl query type for EWorkOrderImages
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEWorkOrderImages extends EntityPathBase<EWorkOrderImages> {

    private static final long serialVersionUID = -581204058L;

    public static final QEWorkOrderImages eWorkOrderImages = new QEWorkOrderImages("eWorkOrderImages");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath imageUrl = createString("imageUrl");

    public final NumberPath<Integer> imgType = createNumber("imgType", Integer.class);

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public final NumberPath<Long> workOrderId = createNumber("workOrderId", Long.class);

    public final NumberPath<Integer> workOrderType = createNumber("workOrderType", Integer.class);

    public QEWorkOrderImages(String variable) {
        super(EWorkOrderImages.class, forVariable(variable));
    }

    public QEWorkOrderImages(Path<? extends EWorkOrderImages> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEWorkOrderImages(PathMetadata metadata) {
        super(EWorkOrderImages.class, metadata);
    }

}

