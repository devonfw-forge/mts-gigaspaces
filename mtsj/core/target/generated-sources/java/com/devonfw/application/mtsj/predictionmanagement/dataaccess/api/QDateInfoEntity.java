package com.devonfw.application.mtsj.predictionmanagement.dataaccess.api;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDateInfoEntity is a Querydsl query type for DateInfoEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDateInfoEntity extends EntityPathBase<DateInfoEntity> {

    private static final long serialVersionUID = 813948339L;

    public static final QDateInfoEntity dateInfoEntity = new QDateInfoEntity("dateInfoEntity");

    public final DateTimePath<java.sql.Timestamp> date = createDateTime("date", java.sql.Timestamp.class);

    public final StringPath designation = createString("designation");

    public final StringPath id = createString("id");

    public final NumberPath<Double> temperature = createNumber("temperature", Double.class);

    public final NumberPath<Integer> versionId = createNumber("versionId", Integer.class);

    public QDateInfoEntity(String variable) {
        super(DateInfoEntity.class, forVariable(variable));
    }

    public QDateInfoEntity(Path<? extends DateInfoEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDateInfoEntity(PathMetadata metadata) {
        super(DateInfoEntity.class, metadata);
    }

}

