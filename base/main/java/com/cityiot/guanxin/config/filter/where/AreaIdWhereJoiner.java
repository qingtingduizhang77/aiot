package com.cityiot.guanxin.config.filter.where;

import com.cityiot.guanxin.user.entity.QUser;
import com.querydsl.core.types.Predicate;

public class AreaIdWhereJoiner implements WhereJoiner {
    @Override
    public Predicate getPredicate(Object o) {
        return QUser.user.id.eq(1l);
    }
}
