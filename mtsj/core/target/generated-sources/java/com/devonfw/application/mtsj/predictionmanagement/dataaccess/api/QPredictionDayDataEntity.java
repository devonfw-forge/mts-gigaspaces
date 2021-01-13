package com.devonfw.application.mtsj.predictionmanagement.dataaccess.api;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPredictionDayDataEntity is a Querydsl query type for PredictionDayDataEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPredictionDayDataEntity extends EntityPathBase<PredictionDayDataEntity> {

    private static final long serialVersionUID = -189043226L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPredictionDayDataEntity predictionDayDataEntity = new QPredictionDayDataEntity("predictionDayDataEntity");

    public final com.devonfw.application.mtsj.general.dataaccess.api.QApplicationPersistenceEntity _super = new com.devonfw.application.mtsj.general.dataaccess.api.QApplicationPersistenceEntity(this);

    public final com.devonfw.application.mtsj.dishmanagement.dataaccess.api.QDishEntity dish;

    public final StringPath dishId = createString("dishId");

    public final NumberPath<Double> forecast = createNumber("forecast", Double.class);

    //inherited
    public final StringPath id = _super.id;

    //inherited
    public final NumberPath<Integer> modificationCounter = _super.modificationCounter;

    public final NumberPath<Integer> timestamp = createNumber("timestamp", Integer.class);

    public QPredictionDayDataEntity(String variable) {
        this(PredictionDayDataEntity.class, forVariable(variable), INITS);
    }

    public QPredictionDayDataEntity(Path<? extends PredictionDayDataEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPredictionDayDataEntity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPredictionDayDataEntity(PathMetadata metadata, PathInits inits) {
        this(PredictionDayDataEntity.class, metadata, inits);
    }

    public QPredictionDayDataEntity(Class<? extends PredictionDayDataEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.dish = inits.isInitialized("dish") ? new com.devonfw.application.mtsj.dishmanagement.dataaccess.api.QDishEntity(forProperty("dish"), inits.get("dish")) : null;
    }

}

