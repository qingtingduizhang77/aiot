package com.cityiot.guanxin.moduleAuth.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QModuleAuth is a Querydsl query type for ModuleAuth
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QModuleAuth extends EntityPathBase<ModuleAuth> {

    private static final long serialVersionUID = -1669144087L;

    public static final QModuleAuth moduleAuth = new QModuleAuth("moduleAuth");

    public final com.cityiot.guanxin.common.entity.QOnlyIdEntity _super = new com.cityiot.guanxin.common.entity.QOnlyIdEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath moCode = createString("moCode");

    public final StringPath name = createString("name");

    public final NumberPath<Long> orgId = createNumber("orgId", Long.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QModuleAuth(String variable) {
        super(ModuleAuth.class, forVariable(variable));
    }

    public QModuleAuth(Path<? extends ModuleAuth> path) {
        super(path.getType(), path.getMetadata());
    }

    public QModuleAuth(PathMetadata metadata) {
        super(ModuleAuth.class, metadata);
    }

}

