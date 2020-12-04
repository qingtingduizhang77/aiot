package com.cityiot.guanxin.workOrder.operatorOrCompanyManage.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QOperatorOrCompanyManage is a Querydsl query type for OperatorOrCompanyManage
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOperatorOrCompanyManage extends EntityPathBase<OperatorOrCompanyManage> {

    private static final long serialVersionUID = -1560805032L;

    public static final QOperatorOrCompanyManage operatorOrCompanyManage = new QOperatorOrCompanyManage("operatorOrCompanyManage");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final StringPath account = createString("account");

    public final NumberPath<Long> accountid = createNumber("accountid", Long.class);

    public final StringPath companyname = createString("companyname");

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final NumberPath<Integer> disable = createNumber("disable", Integer.class);

    public final NumberPath<Integer> gender = createNumber("gender", Integer.class);

    public final StringPath headPortrait = createString("headPortrait");

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final StringPath name = createString("name");

    public final NumberPath<Integer> operatorType = createNumber("operatorType", Integer.class);

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final StringPath remark = createString("remark");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final StringPath token = createString("token");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QOperatorOrCompanyManage(String variable) {
        super(OperatorOrCompanyManage.class, forVariable(variable));
    }

    public QOperatorOrCompanyManage(Path<? extends OperatorOrCompanyManage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOperatorOrCompanyManage(PathMetadata metadata) {
        super(OperatorOrCompanyManage.class, metadata);
    }

}

