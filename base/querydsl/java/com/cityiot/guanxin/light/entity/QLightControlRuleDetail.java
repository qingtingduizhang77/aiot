package com.cityiot.guanxin.light.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QLightControlRuleDetail is a Querydsl query type for LightControlRuleDetail
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLightControlRuleDetail extends EntityPathBase<LightControlRuleDetail> {

    private static final long serialVersionUID = -1209160061L;

    public static final QLightControlRuleDetail lightControlRuleDetail = new QLightControlRuleDetail("lightControlRuleDetail");

    public final QRule _super = new QRule(this);

    //inherited
    public final NumberPath<Integer> brightness = _super.brightness;

    public final StringPath code = createString("code");

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    //inherited
    public final TimePath<java.util.Date> endTime = _super.endTime;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    public final NumberPath<Long> lightControlRuleId = createNumber("lightControlRuleId", Long.class);

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    //inherited
    public final NumberPath<Integer> openStatus = _super.openStatus;

    //inherited
    public final TimePath<java.util.Date> startTime = _super.startTime;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QLightControlRuleDetail(String variable) {
        super(LightControlRuleDetail.class, forVariable(variable));
    }

    public QLightControlRuleDetail(Path<? extends LightControlRuleDetail> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLightControlRuleDetail(PathMetadata metadata) {
        super(LightControlRuleDetail.class, metadata);
    }

}

