package com.lytov.diplom.core.service.v1.impl;

import com.lytov.diplom.core.dspprbd.domain.ComponentProcessAnalyze;
import com.lytov.diplom.core.dspprbd.domain.Process;
import com.lytov.diplom.core.dspprbd.domain.ProcessAnalyze;
import com.lytov.diplom.core.dspprbd.enums.AnalyzeType;
import com.lytov.diplom.core.service.v1.api.AnalyzerService;
import com.lytov.diplom.core.service.v1.api.ComponentProcessAnalyzeService;
import com.lytov.diplom.core.service.v1.api.ProcessAnalyzeService;
import com.lytov.diplom.core.service.v1.api.ProcessService;
import com.lytov.diplom.core.service.v1.dto.analyzer.Finding;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnalyzerServiceImpl implements AnalyzerService {

    private final ProcessService processService;
    private final ProcessAnalyzeService processAnalyzeService;
    private final ComponentProcessAnalyzeService componentProcessAnalyzeService;

    @Override
    public void firstAnalyzer(UUID processId, List<Finding> findings) {
        Process process = processService.findProcessById(processId);

        ProcessAnalyze processAnalyze = processAnalyzeService.create(processId);

        Set<ComponentProcessAnalyze> threats = new HashSet<ComponentProcessAnalyze>();

        threats = findings
                .stream()
                .map(f -> {
                            return new ComponentProcessAnalyze(
                                    process,
                                    processAnalyze,
                                    f.getBpmnType(),
                                    f.getRefId(),
                                    f.getRiskId(),
                                    f.getIdTask(),
                                    AnalyzeType.PROCESS_ANALYZE
                            );
                        }
                )
                .collect(Collectors.toSet());

        componentProcessAnalyzeService.createAll(threats);
    }
}
