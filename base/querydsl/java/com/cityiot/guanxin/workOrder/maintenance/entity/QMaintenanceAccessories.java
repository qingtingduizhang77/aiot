package com.cityiot.guanxin.workOrder.maintenance.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMaintenanceAccessories is a Querydsl query type for MaintenanceAccessories
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMaintenanceAccessories extends EntityPathBase<MaintenanceAccessories> {

    private static final long serialVersionUID = 1661174456L;

    public static final QMaintenanceAccessories maintenanceAccessories = new QMaintenanceAccessories("maintenanceAccessories");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final NumberPath<Integer> fileType = createNumber("fileType", Integer.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final NumberPath<Long> recordID = createNumber("recordID", Long.class);

    public final StringPath saveRoute = createString("saveRoute");

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QMaintenanceAccessories(String variable) {
        super(MaintenanceAccessories.class, forVariable(variable));
    }

    public QMaintenanceAccessories(Path<? extends MaintenanceAccessories> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMaintenanceAccessories(PathMetadata metadata) {
        super(MaintenanceAccessories.class, metadata);
    }

}

