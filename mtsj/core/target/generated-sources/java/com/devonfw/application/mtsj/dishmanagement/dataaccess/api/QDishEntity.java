package com.devonfw.application.mtsj.dishmanagement.dataaccess.api;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDishEntity is a Querydsl query type for DishEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDishEntity extends EntityPathBase<DishEntity> {

    private static final long serialVersionUID = -42660388L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDishEntity dishEntity = new QDishEntity("dishEntity");

    public final com.devonfw.application.mtsj.general.dataaccess.api.QApplicationPersistenceEntity _super = new com.devonfw.application.mtsj.general.dataaccess.api.QApplicationPersistenceEntity(this);

    public final ListPath<CategoryEntity, QCategoryEntity> categories = this.<CategoryEntity, QCategoryEntity>createList("categories", CategoryEntity.class, QCategoryEntity.class, PathInits.DIRECT2);

    public final StringPath description = createString("description");

    public final ListPath<IngredientEntity, QIngredientEntity> extras = this.<IngredientEntity, QIngredientEntity>createList("extras", IngredientEntity.class, QIngredientEntity.class, PathInits.DIRECT2);

    //inherited
    public final StringPath id = _super.id;

    public final com.devonfw.application.mtsj.imagemanagement.dataaccess.api.QImageEntity image;

    public final StringPath imageId = createString("imageId");

    //inherited
    public final NumberPath<Integer> modificationCounter = _super.modificationCounter;

    public final StringPath name = createString("name");

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public QDishEntity(String variable) {
        this(DishEntity.class, forVariable(variable), INITS);
    }

    public QDishEntity(Path<? extends DishEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDishEntity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDishEntity(PathMetadata metadata, PathInits inits) {
        this(DishEntity.class, metadata, inits);
    }

    public QDishEntity(Class<? extends DishEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.image = inits.isInitialized("image") ? new com.devonfw.application.mtsj.imagemanagement.dataaccess.api.QImageEntity(forProperty("image")) : null;
    }

}

