package com.lytov.diplom.core.service.v1.dto.analyzer;

import com.lytov.diplom.core.service.v1.dto.BpmnGraph;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalyzerRequest {
    private UUID processId;
    private BpmnGraph graph;
}
