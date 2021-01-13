package com.devonfw.application.mtsj.imagemanagement.dataaccess.api;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QImageEntity is a Querydsl query type for ImageEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QImageEntity extends EntityPathBase<ImageEntity> {

    private static final long serialVersionUID = -592189418L;

    public static final QImageEntity imageEntity = new QImageEntity("imageEntity");

    public final com.devonfw.application.mtsj.general.dataaccess.api.QApplicationPersistenceEntity _super = new com.devonfw.application.mtsj.general.dataaccess.api.QApplicationPersistenceEntity(this);

    public final SimplePath<java.sql.Blob> content = createSimple("content", java.sql.Blob.class);

    public final EnumPath<com.devonfw.application.mtsj.imagemanagement.common.api.datatype.ContentType> contentType = createEnum("contentType", com.devonfw.application.mtsj.imagemanagement.common.api.datatype.ContentType.class);

    //inherited
    public final StringPath id = _super.id;

    public final StringPath mimeType = createString("mimeType");

    //inherited
    public final NumberPath<Integer> modificationCounter = _super.modificationCounter;

    public final StringPath name = createString("name");

    public QImageEntity(String variable) {
        super(ImageEntity.class, forVariable(variable));
    }

    public QImageEntity(Path<? extends ImageEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QImageEntity(PathMetadata metadata) {
        super(ImageEntity.class, metadata);
    }

}

