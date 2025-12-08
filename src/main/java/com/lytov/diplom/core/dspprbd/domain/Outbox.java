package com.lytov.diplom.core.dspprbd.domain;

import com.lytov.diplom.core.dspprbd.enums.AggregateType;
import com.lytov.diplom.core.dspprbd.enums.OutboxStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "outbox")
public class Outbox {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "aggregate_type", length = Integer.MAX_VALUE)
    @Enumerated(EnumType.STRING)
    private AggregateType aggregateType;

    @Column(name = "aggregate_id", length = Integer.MAX_VALUE)
    private String aggregateId;

    @Column(name = "payload")
    @JdbcTypeCode(SqlTypes.JSON)
    private String payload;

    @Column(name = "status", length = Integer.MAX_VALUE)
    @Enumerated(EnumType.STRING)
    private OutboxStatus status;

    @Column(name = "attempt_count")
    private Integer attemptCount;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "last_attempt_at")
    private Timestamp lastAttemptAt;

    @Column(name = "sent_at")
    private Timestamp sentAt;

    @Column(name = "last_error", length = Integer.MAX_VALUE)
    private String lastError;

}