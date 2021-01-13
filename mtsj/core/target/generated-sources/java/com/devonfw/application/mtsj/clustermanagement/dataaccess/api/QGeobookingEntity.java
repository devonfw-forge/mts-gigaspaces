package com.devonfw.application.mtsj.clustermanagement.dataaccess.api;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QGeobookingEntity is a Querydsl query type for GeobookingEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGeobookingEntity extends EntityPathBase<GeobookingEntity> {

    private static final long serialVersionUID = -1702184238L;

    public static final QGeobookingEntity geobookingEntity = new QGeobookingEntity("geobookingEntity");

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final NumberPath<Long> bookingDate = createNumber("bookingDate", Long.class);

    public final StringPath dishId = createString("dishId");

    public final StringPath dishName = createString("dishName");

    public final StringPath id = createString("id");

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final NumberPath<Double> longitude = createNumber("longitude", Double.class);

    public final NumberPath<Integer> modificationCounter = createNumber("modificationCounter", Integer.class);

    public final NumberPath<Integer> prediction = createNumber("prediction", Integer.class);

    public QGeobookingEntity(String variable) {
        super(GeobookingEntity.class, forVariable(variable));
    }

    public QGeobookingEntity(Path<? extends GeobookingEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGeobookingEntity(PathMetadata metadata) {
        super(GeobookingEntity.class, metadata);
    }

}

