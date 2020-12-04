package com.cityiot.guanxin.branchLeader.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBranchToDeviceGroup is a Querydsl query type for BranchToDeviceGroup
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBranchToDeviceGroup extends EntityPathBase<BranchToDeviceGroup> {

    private static final long serialVersionUID = 48609600L;

    public static final QBranchToDeviceGroup branchToDeviceGroup = new QBranchToDeviceGroup("branchToDeviceGroup");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final NumberPath<Long> branchId = createNumber("branchId", Long.class);

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final NumberPath<Long> deviceGroupId = createNumber("deviceGroupId", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QBranchToDeviceGroup(String variable) {
        super(BranchToDeviceGroup.class, forVariable(variable));
    }

    public QBranchToDeviceGroup(Path<? extends BranchToDeviceGroup> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBranchToDeviceGroup(PathMetadata metadata) {
        super(BranchToDeviceGroup.class, metadata);
    }

}

