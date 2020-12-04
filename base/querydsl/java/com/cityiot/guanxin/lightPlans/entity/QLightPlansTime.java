package com.cityiot.guanxin.lightPlans.entity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;

import javax.annotation.Generated;
import java.util.Date;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QLightPlansTime extends EntityPathBase<LightPlansTime> {

    private static final long serialVersionUID = 3629241903692876586L;


    public static final QLightPlansTime lightPlansTime = new QLightPlansTime("lightPlansTime");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final NumberPath<Long> lightPlansId = createNumber("lightPlansId", Long.class);
    public final TimePath<Date> time = createTime("time", Date.class);
    public final NumberPath<Integer> luminance = createNumber("luminance", Integer.class);
    public final StringPath type = createString("type");
    public final StringPath zhou = createString("zhou");
    public final DatePath<Date> startDate = createDate("startDate", Date.class);
    public final DatePath<Date> endDate = createDate("endDate", Date.class);
    public final StringPath plansTime = createString("plansTime");

    //inherited
    public final DateTimePath<Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QLightPlansTime(String variable) {
        super(LightPlansTime.class, forVariable(variable));
    }

    public QLightPlansTime(Path<? extends LightPlansTime> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLightPlansTime(PathMetadata metadata) {
        super(LightPlansTime.class, metadata);
    }

}

