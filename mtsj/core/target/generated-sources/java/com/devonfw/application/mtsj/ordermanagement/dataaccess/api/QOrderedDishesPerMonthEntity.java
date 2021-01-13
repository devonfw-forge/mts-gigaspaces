package com.devonfw.application.mtsj.ordermanagement.dataaccess.api;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderedDishesPerMonthEntity is a Querydsl query type for OrderedDishesPerMonthEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOrderedDishesPerMonthEntity extends EntityPathBase<OrderedDishesPerMonthEntity> {

    private static final long serialVersionUID = 1894327808L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderedDishesPerMonthEntity orderedDishesPerMonthEntity = new QOrderedDishesPerMonthEntity("orderedDishesPerMonthEntity");

    public final com.devonfw.application.mtsj.general.dataaccess.api.QApplicationPersistenceEntity _super = new com.devonfw.application.mtsj.general.dataaccess.api.QApplicationPersistenceEntity(this);

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final DateTimePath<java.sql.Timestamp> bookingdate = createDateTime("bookingdate", java.sql.Timestamp.class);

    public final com.devonfw.application.mtsj.dishmanagement.dataaccess.api.QDishEntity dish;

    public final StringPath dishId = createString("dishId");

    //inherited
    public final StringPath id = _super.id;

    //inherited
    public final NumberPath<Integer> modificationCounter = _super.modificationCounter;

    public final NumberPath<Double> temperature = createNumber("temperature", Double.class);

    public QOrderedDishesPerMonthEntity(String variable) {
        this(OrderedDishesPerMonthEntity.class, forVariable(variable), INITS);
    }

    public QOrderedDishesPerMonthEntity(Path<? extends OrderedDishesPerMonthEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderedDishesPerMonthEntity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderedDishesPerMonthEntity(PathMetadata metadata, PathInits inits) {
        this(OrderedDishesPerMonthEntity.class, metadata, inits);
    }

    public QOrderedDishesPerMonthEntity(Class<? extends OrderedDishesPerMonthEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.dish = inits.isInitialized("dish") ? new com.devonfw.application.mtsj.dishmanagement.dataaccess.api.QDishEntity(forProperty("dish"), inits.get("dish")) : null;
    }

}

