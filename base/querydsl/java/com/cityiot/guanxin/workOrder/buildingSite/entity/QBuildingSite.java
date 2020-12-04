package com.cityiot.guanxin.workOrder.buildingSite.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBuildingSite is a Querydsl query type for BuildingSite
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBuildingSite extends EntityPathBase<BuildingSite> {

    private static final long serialVersionUID = -1842156024L;

    public static final QBuildingSite buildingSite = new QBuildingSite("buildingSite");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final StringPath areaCode = createString("areaCode");

    public final StringPath buildingSiteAddress = createString("buildingSiteAddress");

    public final StringPath buildingSiteName = createString("buildingSiteName");

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    public final NumberPath<Double> lat = createNumber("lat", Double.class);

    public final NumberPath<Double> lng = createNumber("lng", Double.class);

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final NumberPath<Integer> remark = createNumber("remark", Integer.class);

    public final NumberPath<Integer> totalConstructionLift = createNumber("totalConstructionLift", Integer.class);

    public final NumberPath<Integer> totalConstructionTower = createNumber("totalConstructionTower", Integer.class);

    public final NumberPath<Integer> totalCrane = createNumber("totalCrane", Integer.class);

    public final NumberPath<Integer> totalNoiseDevice = createNumber("totalNoiseDevice", Integer.class);

    public final NumberPath<Integer> totalPm25Device = createNumber("totalPm25Device", Integer.class);

    public final NumberPath<Integer> totalWorkerNumber = createNumber("totalWorkerNumber", Integer.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QBuildingSite(String variable) {
        super(BuildingSite.class, forVariable(variable));
    }

    public QBuildingSite(Path<? extends BuildingSite> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBuildingSite(PathMetadata metadata) {
        super(BuildingSite.class, metadata);
    }

}

