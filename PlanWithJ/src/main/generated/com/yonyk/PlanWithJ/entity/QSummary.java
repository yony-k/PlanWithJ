package com.yonyk.PlanWithJ.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSummary is a Querydsl query type for Summary
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSummary extends EntityPathBase<Summary> {

    private static final long serialVersionUID = -2077468001L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSummary summary = new QSummary("summary");

    public final ListPath<Activity, QActivity> activityList = this.<Activity, QActivity>createList("activityList", Activity.class, QActivity.class, PathInits.DIRECT2);

    public final NumberPath<Long> budget = createNumber("budget", Long.class);

    public final StringPath companion = createString("companion");

    public final NumberPath<Integer> draft = createNumber("draft", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> end_date = createDateTime("end_date", java.time.LocalDateTime.class);

    public final StringPath place = createString("place");

    public final NumberPath<Integer> reviewd = createNumber("reviewd", Integer.class);

    public final NumberPath<Integer> snum = createNumber("snum", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> start_date = createDateTime("start_date", java.time.LocalDateTime.class);

    public final StringPath title = createString("title");

    public final StringPath transportation = createString("transportation");

    public final QUser user;

    public QSummary(String variable) {
        this(Summary.class, forVariable(variable), INITS);
    }

    public QSummary(Path<? extends Summary> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSummary(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSummary(PathMetadata metadata, PathInits inits) {
        this(Summary.class, metadata, inits);
    }

    public QSummary(Class<? extends Summary> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

