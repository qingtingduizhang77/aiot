package com.cityiot.guanxin.workOrder.faultManagement.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QThresholdSetting is a Querydsl query type for ThresholdSetting
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QThresholdSetting extends EntityPathBase<ThresholdSetting> {

    private static final long serialVersionUID = 1196281266L;

    public static final QThresholdSetting thresholdSetting = new QThresholdSetting("thresholdSetting");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final NumberPath<Long> deviceModelId = createNumber("deviceModelId", Long.class);

    public final StringPath faultCode = createString("faultCode");

    public final NumberPath<Integer> faultNum = createNumber("faultNum", Integer.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QThresholdSetting(String variable) {
        super(ThresholdSetting.class, forVariable(variable));
    }

    public QThresholdSetting(Path<? extends ThresholdSetting> path) {
        super(path.getType(), path.getMetadata());
    }

    public QThresholdSetting(PathMetadata metadata) {
        super(ThresholdSetting.class, metadata);
    }

}

