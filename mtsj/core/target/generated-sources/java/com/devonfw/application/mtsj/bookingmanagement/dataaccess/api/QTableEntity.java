package com.devonfw.application.mtsj.bookingmanagement.dataaccess.api;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTableEntity is a Querydsl query type for TableEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTableEntity extends EntityPathBase<TableEntity> {

    private static final long serialVersionUID = 1135542539L;

    public static final QTableEntity tableEntity = new QTableEntity("tableEntity");

    public final com.devonfw.application.mtsj.general.dataaccess.api.QApplicationPersistenceEntity _super = new com.devonfw.application.mtsj.general.dataaccess.api.QApplicationPersistenceEntity(this);

    //inherited
    public final StringPath id = _super.id;

    //inherited
    public final NumberPath<Integer> modificationCounter = _super.modificationCounter;

    public final NumberPath<Integer> seatsNumber = createNumber("seatsNumber", Integer.class);

    public QTableEntity(String variable) {
        super(TableEntity.class, forVariable(variable));
    }

    public QTableEntity(Path<? extends TableEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTableEntity(PathMetadata metadata) {
        super(TableEntity.class, metadata);
    }

}

