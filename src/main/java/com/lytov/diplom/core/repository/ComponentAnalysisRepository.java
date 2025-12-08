package com.lytov.diplom.core.repository;

import com.lytov.diplom.core.dspprbd.domain.ComponentAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ComponentAnalysisRepository extends JpaRepository<ComponentAnalysis, UUID> {
}