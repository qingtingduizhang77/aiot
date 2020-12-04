package com.cityiot.guanxin.moduleAuth.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QModuleAuthMumber is a Querydsl query type for ModuleAuthMumber
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QModuleAuthMumber extends EntityPathBase<ModuleAuthMumber> {

    private static final long serialVersionUID = -1129644557L;

    public static final QModuleAuthMumber moduleAuthMumber = new QModuleAuthMumber("moduleAuthMumber");

    public final com.cityiot.guanxin.common.entity.QOnlyIdEntity _super = new com.cityiot.guanxin.common.entity.QOnlyIdEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final StringPath memberName = createString("memberName");

    public final NumberPath<Integer> memberType = createNumber("memberType", Integer.class);

    public final NumberPath<Long> moAuthId = createNumber("moAuthId", Long.class);

    public final NumberPath<Long> orgId = createNumber("orgId", Long.class);

    public final NumberPath<Integer> state = createNumber("state", Integer.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QModuleAuthMumber(String variable) {
        super(ModuleAuthMumber.class, forVariable(variable));
    }

    public QModuleAuthMumber(Path<? extends ModuleAuthMumber> path) {
        super(path.getType(), path.getMetadata());
    }

    public QModuleAuthMumber(PathMetadata metadata) {
        super(ModuleAuthMumber.class, metadata);
    }

}

