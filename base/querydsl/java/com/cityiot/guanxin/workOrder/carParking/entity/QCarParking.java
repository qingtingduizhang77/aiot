package com.cityiot.guanxin.workOrder.carParking.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCarParking is a Querydsl query type for CarParking
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCarParking extends EntityPathBase<CarParking> {

    private static final long serialVersionUID = 1105004698L;

    public static final QCarParking carParking = new QCarParking("carParking");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final StringPath areaCode = createString("areaCode");

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

    public final StringPath parkingAddress = createString("parkingAddress");

    public final StringPath parkingName = createString("parkingName");

    public final NumberPath<Integer> parkingType = createNumber("parkingType", Integer.class);

    public final StringPath remark = createString("remark");

    public final NumberPath<Integer> totalNumber = createNumber("totalNumber", Integer.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QCarParking(String variable) {
        super(CarParking.class, forVariable(variable));
    }

    public QCarParking(Path<? extends CarParking> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCarParking(PathMetadata metadata) {
        super(CarParking.class, metadata);
    }

}

