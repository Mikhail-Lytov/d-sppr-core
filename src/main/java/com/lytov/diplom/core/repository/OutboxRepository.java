package com.lytov.diplom.core.repository;

import com.lytov.diplom.core.dspprbd.domain.Outbox;
import com.lytov.diplom.core.dspprbd.enums.AggregateType;
import com.lytov.diplom.core.dspprbd.enums.OutboxStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OutboxRepository extends JpaRepository<Outbox, UUID> {

    @Query(
            value = """
                    SELECT o from Outbox o
                    where o.status = ?1 and o.aggregateType = ?2
                    """
    )
    List<Outbox> findAllByPrimaryMatrix(OutboxStatus status, AggregateType aggregateType);
}