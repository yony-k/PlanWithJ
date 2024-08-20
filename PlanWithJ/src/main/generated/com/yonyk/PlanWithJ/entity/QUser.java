package com.yonyk.PlanWithJ.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1276756850L;

    public static final QUser user = new QUser("user");

    public final StringPath email = createString("email");

    public final StringPath name = createString("name");

    public final StringPath pwd = createString("pwd");

    public final StringPath user_id = createString("user_id");

    public final ListPath<User_Role, QUser_Role> user_roles = this.<User_Role, QUser_Role>createList("user_roles", User_Role.class, QUser_Role.class, PathInits.DIRECT2);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

