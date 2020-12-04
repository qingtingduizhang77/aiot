package com.cityiot.guanxin.branchLeader.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBranchToDeviceModel is a Querydsl query type for BranchToDeviceModel
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBranchToDeviceModel extends EntityPathBase<BranchToDeviceModel> {

    private static final long serialVersionUID = 54050282L;

    public static final QBranchToDeviceModel branchToDeviceModel = new QBranchToDeviceModel("branchToDeviceModel");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final NumberPath<Long> branchId = createNumber("branchId", Long.class);

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final NumberPath<Long> deviceModelId = createNumber("deviceModelId", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QBranchToDeviceModel(String variable) {
        super(BranchToDeviceModel.class, forVariable(variable));
    }

    public QBranchToDeviceModel(Path<? extends BranchToDeviceModel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBranchToDeviceModel(PathMetadata metadata) {
        super(BranchToDeviceModel.class, metadata);
    }

}

