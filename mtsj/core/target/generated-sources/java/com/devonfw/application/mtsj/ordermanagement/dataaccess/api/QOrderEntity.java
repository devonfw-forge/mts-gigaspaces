package com.devonfw.application.mtsj.ordermanagement.dataaccess.api;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderEntity is a Querydsl query type for OrderEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOrderEntity extends EntityPathBase<OrderEntity> {

    private static final long serialVersionUID = 205861302L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderEntity orderEntity = new QOrderEntity("orderEntity");

    public final com.devonfw.application.mtsj.general.dataaccess.api.QApplicationPersistenceEntity _super = new com.devonfw.application.mtsj.general.dataaccess.api.QApplicationPersistenceEntity(this);

    public final com.devonfw.application.mtsj.bookingmanagement.dataaccess.api.QBookingEntity booking;

    public final StringPath bookingId = createString("bookingId");

    public final com.devonfw.application.mtsj.bookingmanagement.dataaccess.api.QBookingEntity host;

    public final StringPath hostId = createString("hostId");

    //inherited
    public final StringPath id = _super.id;

    public final com.devonfw.application.mtsj.bookingmanagement.dataaccess.api.QInvitedGuestEntity invitedGuest;

    public final StringPath invitedGuestId = createString("invitedGuestId");

    //inherited
    public final NumberPath<Integer> modificationCounter = _super.modificationCounter;

    public final ListPath<OrderLineEntity, QOrderLineEntity> orderLines = this.<OrderLineEntity, QOrderLineEntity>createList("orderLines", OrderLineEntity.class, QOrderLineEntity.class, PathInits.DIRECT2);

    public QOrderEntity(String variable) {
        this(OrderEntity.class, forVariable(variable), INITS);
    }

    public QOrderEntity(Path<? extends OrderEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderEntity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrderEntity(PathMetadata metadata, PathInits inits) {
        this(OrderEntity.class, metadata, inits);
    }

    public QOrderEntity(Class<? extends OrderEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.booking = inits.isInitialized("booking") ? new com.devonfw.application.mtsj.bookingmanagement.dataaccess.api.QBookingEntity(forProperty("booking"), inits.get("booking")) : null;
        this.host = inits.isInitialized("host") ? new com.devonfw.application.mtsj.bookingmanagement.dataaccess.api.QBookingEntity(forProperty("host"), inits.get("host")) : null;
        this.invitedGuest = inits.isInitialized("invitedGuest") ? new com.devonfw.application.mtsj.bookingmanagement.dataaccess.api.QInvitedGuestEntity(forProperty("invitedGuest"), inits.get("invitedGuest")) : null;
    }

}

