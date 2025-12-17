package com.lytov.diplom.core.service.v1.api;

import com.lytov.diplom.core.service.v1.dto.BpmnGraph;

import java.util.UUID;

public interface ProcessService {
    void createTaskConstructionMatrix();

    void addGraph(UUID processId, BpmnGraph bpmnGraph);
}
