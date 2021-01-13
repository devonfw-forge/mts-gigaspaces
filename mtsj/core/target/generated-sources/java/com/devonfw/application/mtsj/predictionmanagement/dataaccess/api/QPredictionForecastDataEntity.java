package com.devonfw.application.mtsj.predictionmanagement.dataaccess.api;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPredictionForecastDataEntity is a Querydsl query type for PredictionForecastDataEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPredictionForecastDataEntity extends EntityPathBase<PredictionForecastDataEntity> {

    private static final long serialVersionUID = -1072722517L;

    public static final QPredictionForecastDataEntity predictionForecastDataEntity = new QPredictionForecastDataEntity("predictionForecastDataEntity");

    public final com.devonfw.application.mtsj.general.dataaccess.api.QApplicationPersistenceEntity _super = new com.devonfw.application.mtsj.general.dataaccess.api.QApplicationPersistenceEntity(this);

    public final NumberPath<Integer> holiday = createNumber("holiday", Integer.class);

    //inherited
    public final StringPath id = _super.id;

    //inherited
    public final NumberPath<Integer> modificationCounter = _super.modificationCounter;

    public final NumberPath<Double> temperature = createNumber("temperature", Double.class);

    public final NumberPath<Integer> timestamp = createNumber("timestamp", Integer.class);

    public QPredictionForecastDataEntity(String variable) {
        super(PredictionForecastDataEntity.class, forVariable(variable));
    }

    public QPredictionForecastDataEntity(Path<? extends PredictionForecastDataEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPredictionForecastDataEntity(PathMetadata metadata) {
        super(PredictionForecastDataEntity.class, metadata);
    }

}

