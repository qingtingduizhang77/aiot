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
public class QAlarmRuleToRole extends EntityPathBase<AlarmRuleToRole> {
    private static final long serialVersionUID = 4264202681133663103L;

    public static final QAlarmRuleToRole alarmRuleToRole = new QAlarmRuleToRole("alarmRuleToRole");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final NumberPath<Long> alarmRuleId = createNumber("alarmRuleId", Long.class);
    public final NumberPath<Long> roleId = createNumber("roleId", Long.class);

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

    public QAlarmRuleToRole(String variable) {
        super(AlarmRuleToRole.class, forVariable(variable));
    }

    public QAlarmRuleToRole(Path<? extends AlarmRuleToRole> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAlarmRuleToRole(PathMetadata metadata) {
        super(AlarmRuleToRole.class, metadata);
    }

}

