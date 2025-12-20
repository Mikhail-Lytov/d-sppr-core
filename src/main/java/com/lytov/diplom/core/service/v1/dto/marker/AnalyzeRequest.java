package com.lytov.diplom.core.service.v1.dto.marker;

import com.lytov.diplom.core.dspprbd.domain.ComponentProcessAnalyze;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalyzeRequest {
    private UUID fileId;
    private UUID processId;
    private List<AnalyzeRow> rows;
}
