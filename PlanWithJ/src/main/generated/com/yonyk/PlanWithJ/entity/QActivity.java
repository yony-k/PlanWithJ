package com.yonyk.PlanWithJ.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QActivity is a Querydsl query type for Activity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QActivity extends EntityPathBase<Activity> {

    private static final long serialVersionUID = 119316278L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QActivity activity = new QActivity("activity");

    public final NumberPath<Integer> anum = createNumber("anum", Integer.class);

    public final NumberPath<Long> cost = createNumber("cost", Long.class);

    public final DateTimePath<java.time.LocalDateTime> end_time = createDateTime("end_time", java.time.LocalDateTime.class);

    public final StringPath required_time = createString("required_time");

    public final DateTimePath<java.time.LocalDateTime> start_time = createDateTime("start_time", java.time.LocalDateTime.class);

    public final QSummary summary;

    public final StringPath title = createString("title");

    public final StringPath todo = createString("todo");

    public final StringPath type = createString("type");

    public QActivity(String variable) {
        this(Activity.class, forVariable(variable), INITS);
    }

    public QActivity(Path<? extends Activity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QActivity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QActivity(PathMetadata metadata, PathInits inits) {
        this(Activity.class, metadata, inits);
    }

    public QActivity(Class<? extends Activity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.summary = inits.isInitialized("summary") ? new QSummary(forProperty("summary"), inits.get("summary")) : null;
    }

}

