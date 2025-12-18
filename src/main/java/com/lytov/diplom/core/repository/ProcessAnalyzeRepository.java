package com.lytov.diplom.core.repository;

import com.lytov.diplom.core.dspprbd.domain.ProcessAnalyze;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProcessAnalyzeRepository extends JpaRepository<ProcessAnalyze, UUID> {
}