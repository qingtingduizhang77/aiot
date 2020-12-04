package com.cityiot.guanxin.menu.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMenuToRole is a Querydsl query type for MenuToRole
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMenuToRole extends EntityPathBase<MenuToRole> {

    private static final long serialVersionUID = -452549392L;

    public static final QMenuToRole menuToRole = new QMenuToRole("menuToRole");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    public final NumberPath<Long> menuId = createNumber("menuId", Long.class);

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final NumberPath<Long> roleId = createNumber("roleId", Long.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QMenuToRole(String variable) {
        super(MenuToRole.class, forVariable(variable));
    }

    public QMenuToRole(Path<? extends MenuToRole> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMenuToRole(PathMetadata metadata) {
        super(MenuToRole.class, metadata);
    }

}

