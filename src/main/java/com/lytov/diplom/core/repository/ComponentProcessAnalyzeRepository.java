package com.lytov.diplom.core.repository;

import com.lytov.diplom.core.dspprbd.domain.ComponentProcessAnalyze;
import com.lytov.diplom.core.dspprbd.enums.AnalyzeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ComponentProcessAnalyzeRepository extends JpaRepository<ComponentProcessAnalyze, UUID> {

    @Query(value = """
            SELECT cpa from ComponentProcessAnalyze cpa
                        where cpa.processAnalyze.id = ?1
                                    and cpa.analyzeType = ?2
            """)
    List<ComponentProcessAnalyze> findAllByProcessAnalyze(UUID analysisId, AnalyzeType analyzeType);
}