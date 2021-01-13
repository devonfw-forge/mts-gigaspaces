package com.devonfw.application.mtsj.ordermanagement.dataaccess.api;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderLineEntity is a Querydsl query type for OrderLineEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOrderLineEntity extends EntityPathBase<OrderLineEntity> {

    private static final long serialVersionUID = 1400694090L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderLineEntity orderLineEntity = new QOrderLineEntity("orderLineEntity");

    public final com.devonfw.application.mtsj.general.dataaccess.api.QApplicationPersistenceEntity _super = new com.devonfw.application.mtsj.general.dataaccess.api.QApplicationPersistenceEntity(this);

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final StringPath comment = createString("comment");

    public final com.devonfw.application.mtsj.dishmanagement.dataaccess.api.QDishEntity dish;

    public final StringPath dishId = createString("dishId");

    public final ListPath<com.devonfw.application.mtsj.dishmanagement.dataaccess.api.IngredientEntity, com.devonfw.application.mtsj.dishmanagement.dataaccess.api.QIngredientEntity> extras = this.<com.devonfw.application.mtsj.dishmanagement.dataaccess.api.IngredientEntity, com.devonfw.application.mtsj.dishmanagement.dataaccess.api.QIngredientEntity>createList("extras", com.devonfw.application.mtsj.dishmanagement.dataaccess.api.IngredientEntity.class, com.devonfw.application.mtsj.dishmanagement.dataaccess.api.QIngredientEntity.class, PathInits.DIRECT2);

    //inherited
    public final StringPath id = _super.id;

    //inherited
    public final NumberPath<Integer> modificationCounter = _super.modificationCounter;

    public final QOrderEntity order;

    public final StringPath orderId = createString("orderId");

    public QOrderLineEntity(String variable) {
        this(OrderLineEntity.class, forVariable(variable), INITS);
    }

    public QOrderLineEntity(Path<? extends OrderLineEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderLineEntity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderLineEntity(PathMetadata metadata, PathInits inits) {
        this(OrderLineEntity.class, metadata, inits);
    }

    public QOrderLineEntity(Class<? extends OrderLineEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.dish = inits.isInitialized("dish") ? new com.devonfw.application.mtsj.dishmanagement.dataaccess.api.QDishEntity(forProperty("dish"), inits.get("dish")) : null;
        this.order = inits.isInitialized("order") ? new QOrderEntity(forProperty("order"), inits.get("order")) : null;
    }

}

