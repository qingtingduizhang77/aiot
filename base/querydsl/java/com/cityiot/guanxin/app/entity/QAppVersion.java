package com.cityiot.guanxin.app.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAppVersion is a Querydsl query type for AppVersion
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAppVersion extends EntityPathBase<AppVersion> {

    private static final long serialVersionUID = -1596217711L;

    public static final QAppVersion appVersion1 = new QAppVersion("appVersion1");
    
    public static final QAppVersion qAppVersion = new QAppVersion("appVersion");


    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final StringPath appFileUrl = createString("appFileUrl");

    public final NumberPath<Integer> appInnerVersoin = createNumber("appInnerVersoin", Integer.class);

    public final NumberPath<Integer> appPlatformType = createNumber("appPlatformType", Integer.class);

    public final NumberPath<Integer> appType = createNumber("appType", Integer.class);

    public final StringPath appVersion = createString("appVersion");

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final NumberPath<Double> fileSize = createNumber("fileSize", Double.class);

    public final NumberPath<Integer> forceUpdate = createNumber("forceUpdate", Integer.class);

    public final StringPath hotUpdateUrl = createString("hotUpdateUrl");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> isHotUpdate = createNumber("isHotUpdate", Integer.class);

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final NumberPath<Integer> publishStatus = createNumber("publishStatus", Integer.class);

    public final StringPath updateDescription = createString("updateDescription");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QAppVersion(String variable) {
        super(AppVersion.class, forVariable(variable));
    }

    public QAppVersion(Path<? extends AppVersion> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAppVersion(PathMetadata metadata) {
        super(AppVersion.class, metadata);
    }

}

