package com.devonfw.application.mtsj.dishmanagement.dataaccess.api;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QIngredientEntity is a Querydsl query type for IngredientEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QIngredientEntity extends EntityPathBase<IngredientEntity> {

    private static final long serialVersionUID = -1246355053L;

    public static final QIngredientEntity ingredientEntity = new QIngredientEntity("ingredientEntity");

    public final com.devonfw.application.mtsj.general.dataaccess.api.QApplicationPersistenceEntity _super = new com.devonfw.application.mtsj.general.dataaccess.api.QApplicationPersistenceEntity(this);

    public final StringPath description = createString("description");

    //inherited
    public final StringPath id = _super.id;

    //inherited
    public final NumberPath<Integer> modificationCounter = _super.modificationCounter;

    public final StringPath name = createString("name");

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public QIngredientEntity(String variable) {
        super(IngredientEntity.class, forVariable(variable));
    }

    public QIngredientEntity(Path<? extends IngredientEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIngredientEntity(PathMetadata metadata) {
        super(IngredientEntity.class, metadata);
    }

}

