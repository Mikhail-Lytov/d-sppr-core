package com.lytov.diplom.core.service.v1.dto;

import com.lytov.diplom.core.dspprbd.enums.AnalyzeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConnectorHandler {

    private AnalyzeType analyzeType;

}
