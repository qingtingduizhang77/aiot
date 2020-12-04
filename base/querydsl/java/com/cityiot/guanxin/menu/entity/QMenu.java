package com.cityiot.guanxin.menu.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMenu is a Querydsl query type for Menu
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMenu extends EntityPathBase<Menu> {

    private static final long serialVersionUID = 1190233727L;

    public static final QMenu menu = new QMenu("menu");

    public final com.cityiot.guanxin.common.entity.QBaseEntity _super = new com.cityiot.guanxin.common.entity.QBaseEntity(this);

    public final StringPath accountId = createString("accountId");

    public final StringPath accountName = createString("accountName");

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    //inherited
    public final NumberPath<Long> creator = _super.creator;

    public final StringPath icon = createString("icon");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath jsonParams = createString("jsonParams");

    public final StringPath jsonQuery = createString("jsonQuery");

    //inherited
    public final DateTimePath<java.util.Date> lastmodi = _super.lastmodi;

    public final NumberPath<Long> level = createNumber("level", Long.class);

    public final NumberPath<Long> menuOrder = createNumber("menuOrder", Long.class);

    public final NumberPath<Integer> menuShow = createNumber("menuShow", Integer.class);

    //inherited
    public final NumberPath<Long> modifier = _super.modifier;

    public final StringPath name = createString("name");

    public final NumberPath<Long> parentId = createNumber("parentId", Long.class);

    public final StringPath parentIdArr = createString("parentIdArr");

    public final StringPath remark = createString("remark");

    public final StringPath roleId = createString("roleId");

    public final StringPath roleName = createString("roleName");

    public final StringPath routeName = createString("routeName");

    public final NumberPath<Integer> systemMenu = createNumber("systemMenu", Integer.class);

    public final StringPath url = createString("url");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QMenu(String variable) {
        super(Menu.class, forVariable(variable));
    }

    public QMenu(Path<? extends Menu> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMenu(PathMetadata metadata) {
        super(Menu.class, metadata);
    }

}

