package com.lytov.diplom.core.service.v1.api;

import com.lytov.diplom.core.dspprbd.domain.Process;
import com.lytov.diplom.core.service.v1.dto.BpmnGraph;

import java.util.UUID;

public interface ProcessService {
    void createTaskConstructionMatrix();

    void addGraph(UUID processId, BpmnGraph bpmnGraph);

    Process findProcessById(UUID processId);

    Process findFirst();
}
