package com.devonfw.application.mtsj.general.dataaccess.api;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QApplicationPersistenceEntity is a Querydsl query type for ApplicationPersistenceEntity
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QApplicationPersistenceEntity extends BeanPath<ApplicationPersistenceEntity> {

    private static final long serialVersionUID = 197185028L;

    public static final QApplicationPersistenceEntity applicationPersistenceEntity = new QApplicationPersistenceEntity("applicationPersistenceEntity");

    public final StringPath id = createString("id");

    public final NumberPath<Integer> modificationCounter = createNumber("modificationCounter", Integer.class);

    public QApplicationPersistenceEntity(String variable) {
        super(ApplicationPersistenceEntity.class, forVariable(variable));
    }

    public QApplicationPersistenceEntity(Path<? extends ApplicationPersistenceEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QApplicationPersistenceEntity(PathMetadata metadata) {
        super(ApplicationPersistenceEntity.class, metadata);
    }

}

