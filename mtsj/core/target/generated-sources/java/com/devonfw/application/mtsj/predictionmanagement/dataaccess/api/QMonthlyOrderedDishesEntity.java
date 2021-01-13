package com.devonfw.application.mtsj.predictionmanagement.dataaccess.api;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMonthlyOrderedDishesEntity is a Querydsl query type for MonthlyOrderedDishesEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMonthlyOrderedDishesEntity extends EntityPathBase<MonthlyOrderedDishesEntity> {

    private static final long serialVersionUID = -1416115009L;

    public static final QMonthlyOrderedDishesEntity monthlyOrderedDishesEntity = new QMonthlyOrderedDishesEntity("monthlyOrderedDishesEntity");

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final DateTimePath<java.time.Instant> bookingDate = createDateTime("bookingDate", java.time.Instant.class);

    public final StringPath dishId = createString("dishId");

    public final StringPath id = createString("id");

    public final NumberPath<Double> temperature = createNumber("temperature", Double.class);

    public final NumberPath<Integer> versionId = createNumber("versionId", Integer.class);

    public final StringPath yearmonth = createString("yearmonth");

    public QMonthlyOrderedDishesEntity(String variable) {
        super(MonthlyOrderedDishesEntity.class, forVariable(variable));
    }

    public QMonthlyOrderedDishesEntity(Path<? extends MonthlyOrderedDishesEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMonthlyOrderedDishesEntity(PathMetadata metadata) {
        super(MonthlyOrderedDishesEntity.class, metadata);
    }

}

