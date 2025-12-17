package com.lytov.diplom.core.repository;

import com.lytov.diplom.core.dspprbd.domain.Process;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface ProcessRepository extends JpaRepository<Process, UUID> {

    @Query(value =
            """
                    select p.*
                    from process p
                    where p.first_parsing = false
                      and not exists (select 1
                                      from component_process cp
                                      where cp.process_id = p.id
                                        and cp.weight_factor is not null);
                    """, nativeQuery = true)
    Set<Process> findAllProcessActive();
}