package com.lytov.diplom.core.service.v1.api;

import com.lytov.diplom.core.service.v1.dto.analyzer.Finding;

import java.util.List;
import java.util.UUID;

public interface AnalyzerService {

    void firstAnalyzer(UUID processId, List<Finding> findings);
}
