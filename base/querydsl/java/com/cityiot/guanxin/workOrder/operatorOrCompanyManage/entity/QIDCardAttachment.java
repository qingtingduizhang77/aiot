package com.cityiot.guanxin.workOrder.operatorOrCompanyManage.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QIDCardAttachment is a Querydsl query type for IDCardAttachment
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QIDCardAttachment extends EntityPathBase<IDCardAttachment> {

    private static final long serialVersionUID = 1392137553L;

    public static final QIDCardAttachment iDCardAttachment = new QIDCardAttachment("iDCardAttachment");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> imgType = createNumber("imgType", Integer.class);

    public final StringPath imgUrl = createString("imgUrl");

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final NumberPath<Long> operatorId = createNumber("operatorId", Long.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QIDCardAttachment(String variable) {
        super(IDCardAttachment.class, forVariable(variable));
    }

    public QIDCardAttachment(Path<? extends IDCardAttachment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIDCardAttachment(PathMetadata metadata) {
        super(IDCardAttachment.class, metadata);
    }

}

