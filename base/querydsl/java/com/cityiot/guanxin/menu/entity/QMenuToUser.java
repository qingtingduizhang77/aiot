package com.cityiot.guanxin.menu.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMenuToUser is a Querydsl query type for MenuToUser
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMenuToUser extends EntityPathBase<MenuToUser> {

    private static final long serialVersionUID = -452456379L;

    public static final QMenuToUser menuToUser = new QMenuToUser("menuToUser");

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

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QMenuToUser(String variable) {
        super(MenuToUser.class, forVariable(variable));
    }

    public QMenuToUser(Path<? extends MenuToUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMenuToUser(PathMetadata metadata) {
        super(MenuToUser.class, metadata);
    }

}

