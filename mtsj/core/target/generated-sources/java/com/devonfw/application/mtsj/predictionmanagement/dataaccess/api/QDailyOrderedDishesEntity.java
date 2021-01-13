package com.devonfw.application.mtsj.predictionmanagement.dataaccess.api;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDailyOrderedDishesEntity is a Querydsl query type for DailyOrderedDishesEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDailyOrderedDishesEntity extends EntityPathBase<DailyOrderedDishesEntity> {

    private static final long serialVersionUID = 859768883L;

    public static final QDailyOrderedDishesEntity dailyOrderedDishesEntity = new QDailyOrderedDishesEntity("dailyOrderedDishesEntity");

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final DateTimePath<java.time.Instant> bookingDate = createDateTime("bookingDate", java.time.Instant.class);

    public final StringPath dishId = createString("dishId");

    public final StringPath id = createString("id");

    public final NumberPath<Integer> modificationCounter = createNumber("modificationCounter", Integer.class);

    public QDailyOrderedDishesEntity(String variable) {
        super(DailyOrderedDishesEntity.class, forVariable(variable));
    }

    public QDailyOrderedDishesEntity(Path<? extends DailyOrderedDishesEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDailyOrderedDishesEntity(PathMetadata metadata) {
        super(DailyOrderedDishesEntity.class, metadata);
    }

}

