package com.lytov.diplom.core.repository;

import com.lytov.diplom.core.dspprbd.domain.HistoryAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HistoryAnalysisRepository extends JpaRepository<HistoryAnalysis, UUID>, JpaSpecificationExecutor<HistoryAnalysis> {
}