package com.cityiot.guanxin.lightPlans.entity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QLightPlansGroupdo extends EntityPathBase<LightPlansGroupdo> {
    private static final long serialVersionUID = 7183565420366567903L;

    public static final QLightPlansGroupdo lightPlansGroupdo = new QLightPlansGroupdo("lightPlansGroupdo");

    public final com.cityiot.guanxin.common.entity.QOnlyIdEntity _super = new com.cityiot.guanxin.common.entity.QOnlyIdEntity(this);

    public final NumberPath<Long> lightPlansGroupId = createNumber("lightPlansGroupId", Long.class);
    public final NumberPath<Long> lightDeviceId = createNumber("lightDeviceId", Long.class);
    public final NumberPath<Long> lightPlansId = createNumber("lightPlansId", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QLightPlansGroupdo(String variable) {
        super(LightPlansGroupdo.class, forVariable(variable));
    }

    public QLightPlansGroupdo(Path<? extends LightPlansGroupdo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLightPlansGroupdo(PathMetadata metadata) {
        super(LightPlansGroupdo.class, metadata);
    }

}
