package com.lytov.diplom.core.repository;

import com.lytov.diplom.core.dspprbd.domain.ComponentProcessAnalyze;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ComponentProcessAnalyzeRepository extends JpaRepository<ComponentProcessAnalyze, UUID> {
}