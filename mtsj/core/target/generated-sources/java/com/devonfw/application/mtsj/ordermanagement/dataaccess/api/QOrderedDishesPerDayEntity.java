package com.devonfw.application.mtsj.ordermanagement.dataaccess.api;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderedDishesPerDayEntity is a Querydsl query type for OrderedDishesPerDayEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOrderedDishesPerDayEntity extends EntityPathBase<OrderedDishesPerDayEntity> {

    private static final long serialVersionUID = 856751452L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderedDishesPerDayEntity orderedDishesPerDayEntity = new QOrderedDishesPerDayEntity("orderedDishesPerDayEntity");

    public final com.devonfw.application.mtsj.general.dataaccess.api.QApplicationPersistenceEntity _super = new com.devonfw.application.mtsj.general.dataaccess.api.QApplicationPersistenceEntity(this);

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final DateTimePath<java.sql.Timestamp> bookingdate = createDateTime("bookingdate", java.sql.Timestamp.class);

    public final StringPath designation = createString("designation");

    public final com.devonfw.application.mtsj.dishmanagement.dataaccess.api.QDishEntity dish;

    public final StringPath dishId = createString("dishId");

    //inherited
    public final StringPath id = _super.id;

    //inherited
    public final NumberPath<Integer> modificationCounter = _super.modificationCounter;

    public final NumberPath<Double> temperature = createNumber("temperature", Double.class);

    public QOrderedDishesPerDayEntity(String variable) {
        this(OrderedDishesPerDayEntity.class, forVariable(variable), INITS);
    }

    public QOrderedDishesPerDayEntity(Path<? extends OrderedDishesPerDayEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderedDishesPerDayEntity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderedDishesPerDayEntity(PathMetadata metadata, PathInits inits) {
        this(OrderedDishesPerDayEntity.class, metadata, inits);
    }

    public QOrderedDishesPerDayEntity(Class<? extends OrderedDishesPerDayEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.dish = inits.isInitialized("dish") ? new com.devonfw.application.mtsj.dishmanagement.dataaccess.api.QDishEntity(forProperty("dish"), inits.get("dish")) : null;
    }

}

