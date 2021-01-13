package com.devonfw.application.mtsj.bookingmanagement.dataaccess.api;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInvitedGuestEntity is a Querydsl query type for InvitedGuestEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QInvitedGuestEntity extends EntityPathBase<InvitedGuestEntity> {

    private static final long serialVersionUID = -181233306L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInvitedGuestEntity invitedGuestEntity = new QInvitedGuestEntity("invitedGuestEntity");

    public final com.devonfw.application.mtsj.general.dataaccess.api.QApplicationPersistenceEntity _super = new com.devonfw.application.mtsj.general.dataaccess.api.QApplicationPersistenceEntity(this);

    public final BooleanPath accepted = createBoolean("accepted");

    public final QBookingEntity booking;

    public final StringPath bookingId = createString("bookingId");

    public final StringPath email = createString("email");

    public final StringPath guestToken = createString("guestToken");

    //inherited
    public final StringPath id = _super.id;

    //inherited
    public final NumberPath<Integer> modificationCounter = _super.modificationCounter;

    public final DateTimePath<java.time.Instant> modificationDate = createDateTime("modificationDate", java.time.Instant.class);

    public final com.devonfw.application.mtsj.ordermanagement.dataaccess.api.QOrderEntity order;

    public final StringPath orderId = createString("orderId");

    public QInvitedGuestEntity(String variable) {
        this(InvitedGuestEntity.class, forVariable(variable), INITS);
    }

    public QInvitedGuestEntity(Path<? extends InvitedGuestEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QInvitedGuestEntity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QInvitedGuestEntity(PathMetadata metadata, PathInits inits) {
        this(InvitedGuestEntity.class, metadata, inits);
    }

    public QInvitedGuestEntity(Class<? extends InvitedGuestEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.booking = inits.isInitialized("booking") ? new QBookingEntity(forProperty("booking"), inits.get("booking")) : null;
        this.order = inits.isInitialized("order") ? new com.devonfw.application.mtsj.ordermanagement.dataaccess.api.QOrderEntity(forProperty("order"), inits.get("order")) : null;
    }

}

