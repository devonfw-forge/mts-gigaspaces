package com.devonfw.application.mtsj.bookingmanagement.dataaccess.api;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBookingEntity is a Querydsl query type for BookingEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBookingEntity extends EntityPathBase<BookingEntity> {

    private static final long serialVersionUID = -2018512170L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBookingEntity bookingEntity = new QBookingEntity("bookingEntity");

    public final com.devonfw.application.mtsj.general.dataaccess.api.QApplicationPersistenceEntity _super = new com.devonfw.application.mtsj.general.dataaccess.api.QApplicationPersistenceEntity(this);

    public final NumberPath<Integer> assistants = createNumber("assistants", Integer.class);

    public final DateTimePath<java.time.Instant> bookingDate = createDateTime("bookingDate", java.time.Instant.class);

    public final StringPath bookingToken = createString("bookingToken");

    public final EnumPath<com.devonfw.application.mtsj.bookingmanagement.common.api.datatype.BookingType> bookingType = createEnum("bookingType", com.devonfw.application.mtsj.bookingmanagement.common.api.datatype.BookingType.class);

    public final BooleanPath canceled = createBoolean("canceled");

    public final StringPath comment = createString("comment");

    public final DateTimePath<java.time.Instant> creationDate = createDateTime("creationDate", java.time.Instant.class);

    public final StringPath email = createString("email");

    public final DateTimePath<java.time.Instant> expirationDate = createDateTime("expirationDate", java.time.Instant.class);

    //inherited
    public final StringPath id = _super.id;

    public final ListPath<InvitedGuestEntity, QInvitedGuestEntity> invitedGuests = this.<InvitedGuestEntity, QInvitedGuestEntity>createList("invitedGuests", InvitedGuestEntity.class, QInvitedGuestEntity.class, PathInits.DIRECT2);

    //inherited
    public final NumberPath<Integer> modificationCounter = _super.modificationCounter;

    public final StringPath name = createString("name");

    public final com.devonfw.application.mtsj.ordermanagement.dataaccess.api.QOrderEntity order;

    public final StringPath orderId = createString("orderId");

    public final ListPath<com.devonfw.application.mtsj.ordermanagement.dataaccess.api.OrderEntity, com.devonfw.application.mtsj.ordermanagement.dataaccess.api.QOrderEntity> orders = this.<com.devonfw.application.mtsj.ordermanagement.dataaccess.api.OrderEntity, com.devonfw.application.mtsj.ordermanagement.dataaccess.api.QOrderEntity>createList("orders", com.devonfw.application.mtsj.ordermanagement.dataaccess.api.OrderEntity.class, com.devonfw.application.mtsj.ordermanagement.dataaccess.api.QOrderEntity.class, PathInits.DIRECT2);

    public final QTableEntity table;

    public final StringPath tableId = createString("tableId");

    public final com.devonfw.application.mtsj.usermanagement.dataaccess.api.QUserEntity user;

    public final StringPath userId = createString("userId");

    public QBookingEntity(String variable) {
        this(BookingEntity.class, forVariable(variable), INITS);
    }

    public QBookingEntity(Path<? extends BookingEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBookingEntity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBookingEntity(PathMetadata metadata, PathInits inits) {
        this(BookingEntity.class, metadata, inits);
    }

    public QBookingEntity(Class<? extends BookingEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.order = inits.isInitialized("order") ? new com.devonfw.application.mtsj.ordermanagement.dataaccess.api.QOrderEntity(forProperty("order"), inits.get("order")) : null;
        this.table = inits.isInitialized("table") ? new QTableEntity(forProperty("table")) : null;
        this.user = inits.isInitialized("user") ? new com.devonfw.application.mtsj.usermanagement.dataaccess.api.QUserEntity(forProperty("user"), inits.get("user")) : null;
    }

}

