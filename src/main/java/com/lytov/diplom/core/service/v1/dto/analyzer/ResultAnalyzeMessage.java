package com.lytov.diplom.core.service.v1.dto.analyzer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultAnalyzeMessage {
    private UUID processId;
    private List<Finding> findings;
}
