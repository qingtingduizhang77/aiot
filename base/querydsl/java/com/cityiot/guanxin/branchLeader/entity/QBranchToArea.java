package com.cityiot.guanxin.branchLeader.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBranchToArea is a Querydsl query type for BranchToArea
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBranchToArea extends EntityPathBase<BranchToArea> {

    private static final long serialVersionUID = 1714580726L;

    public static final QBranchToArea branchToArea = new QBranchToArea("branchToArea");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final NumberPath<Long> areaId = createNumber("areaId", Long.class);

    public final NumberPath<Long> branchId = createNumber("branchId", Long.class);

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

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QBranchToArea(String variable) {
        super(BranchToArea.class, forVariable(variable));
    }

    public QBranchToArea(Path<? extends BranchToArea> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBranchToArea(PathMetadata metadata) {
        super(BranchToArea.class, metadata);
    }

}

