package com.cityiot.guanxin.alarmRule.entity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 *
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAlarmRule extends EntityPathBase<AlarmRule> {

    private static final long serialVersionUID = -4820593142935849176L;

    public static final QAlarmRule alarmRule = new QAlarmRule("alarmRule");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final StringPath name = createString("name");
    public final NumberPath<Long> deviceParameterId = createNumber("deviceParameterId", Long.class);
    public final StringPath math = createString("math");
    public final StringPath preview = createString("preview");
    public final NumberPath<Long> alarmType = createNumber("alarmType", Long.class);
    public final NumberPath<Long> alarmLevel = createNumber("alarmLevel", Long.class);
    public final StringPath remark = createString("remark");
    public final StringPath code = createString("code");
    public final StringPath roleIds = createString("roleIds");
    public final NumberPath<Integer> status = createNumber("status", Integer.class);
    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QAlarmRule(String variable) {
        super(AlarmRule.class, forVariable(variable));
    }

    public QAlarmRule(Path<? extends AlarmRule> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAlarmRule(PathMetadata metadata) {
        super(AlarmRule.class, metadata);
    }

}

