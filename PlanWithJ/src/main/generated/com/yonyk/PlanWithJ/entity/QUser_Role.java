package com.yonyk.PlanWithJ.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser_Role is a Querydsl query type for User_Role
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser_Role extends EntityPathBase<User_Role> {

    private static final long serialVersionUID = -462274237L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser_Role user_Role = new QUser_Role("user_Role");

    public final QRole role;

    public final NumberPath<Integer> urnum = createNumber("urnum", Integer.class);

    public final QUser user;

    public QUser_Role(String variable) {
        this(User_Role.class, forVariable(variable), INITS);
    }

    public QUser_Role(Path<? extends User_Role> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser_Role(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser_Role(PathMetadata metadata, PathInits inits) {
        this(User_Role.class, metadata, inits);
    }

    public QUser_Role(Class<? extends User_Role> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.role = inits.isInitialized("role") ? new QRole(forProperty("role")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

