package com.lytov.diplom.core.service.v1.api;

import com.lytov.diplom.core.dspprbd.domain.ComponentProcessAnalyze;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface ComponentProcessAnalyzeService {

    void createAll(Collection<ComponentProcessAnalyze> processes);

    List<ComponentProcessAnalyze> findAllByAnalysisId(UUID analysisId);
}
