package com.devonfw.application.mtsj.usermanagement.dataaccess.api;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserEntity is a Querydsl query type for UserEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserEntity extends EntityPathBase<UserEntity> {

    private static final long serialVersionUID = 2025856254L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserEntity userEntity = new QUserEntity("userEntity");

    public final com.devonfw.application.mtsj.general.dataaccess.api.QApplicationPersistenceEntity _super = new com.devonfw.application.mtsj.general.dataaccess.api.QApplicationPersistenceEntity(this);

    public final ListPath<com.devonfw.application.mtsj.bookingmanagement.dataaccess.api.BookingEntity, com.devonfw.application.mtsj.bookingmanagement.dataaccess.api.QBookingEntity> bookings = this.<com.devonfw.application.mtsj.bookingmanagement.dataaccess.api.BookingEntity, com.devonfw.application.mtsj.bookingmanagement.dataaccess.api.QBookingEntity>createList("bookings", com.devonfw.application.mtsj.bookingmanagement.dataaccess.api.BookingEntity.class, com.devonfw.application.mtsj.bookingmanagement.dataaccess.api.QBookingEntity.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final ListPath<com.devonfw.application.mtsj.dishmanagement.dataaccess.api.DishEntity, com.devonfw.application.mtsj.dishmanagement.dataaccess.api.QDishEntity> favourites = this.<com.devonfw.application.mtsj.dishmanagement.dataaccess.api.DishEntity, com.devonfw.application.mtsj.dishmanagement.dataaccess.api.QDishEntity>createList("favourites", com.devonfw.application.mtsj.dishmanagement.dataaccess.api.DishEntity.class, com.devonfw.application.mtsj.dishmanagement.dataaccess.api.QDishEntity.class, PathInits.DIRECT2);

    //inherited
    public final StringPath id = _super.id;

    //inherited
    public final NumberPath<Integer> modificationCounter = _super.modificationCounter;

    public final StringPath password = createString("password");

    public final StringPath secret = createString("secret");

    public final BooleanPath twoFactorStatus = createBoolean("twoFactorStatus");

    public final StringPath username = createString("username");

    public final QUserRoleEntity userRole;

    public final StringPath userRoleId = createString("userRoleId");

    public QUserEntity(String variable) {
        this(UserEntity.class, forVariable(variable), INITS);
    }

    public QUserEntity(Path<? extends UserEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserEntity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserEntity(PathMetadata metadata, PathInits inits) {
        this(UserEntity.class, metadata, inits);
    }

    public QUserEntity(Class<? extends UserEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userRole = inits.isInitialized("userRole") ? new QUserRoleEntity(forProperty("userRole")) : null;
    }

}

