package com.cityiot.guanxin.branchLeader.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBranchLeader is a Querydsl query type for BranchLeader
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBranchLeader extends EntityPathBase<BranchLeader> {

    private static final long serialVersionUID = 1477252183L;

    public static final QBranchLeader branchLeader = new QBranchLeader("branchLeader");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final StringPath areaName = createString("areaName");

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final StringPath deviceGroupName = createString("deviceGroupName");

    public final StringPath deviceModelName = createString("deviceModelName");

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final NumberPath<Long> operatorOrCompanyManageId = createNumber("operatorOrCompanyManageId", Long.class);

    public final StringPath remark = createString("remark");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QBranchLeader(String variable) {
        super(BranchLeader.class, forVariable(variable));
    }

    public QBranchLeader(Path<? extends BranchLeader> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBranchLeader(PathMetadata metadata) {
        super(BranchLeader.class, metadata);
    }

}

