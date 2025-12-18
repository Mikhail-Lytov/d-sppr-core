package com.lytov.diplom.core.service.v1.api;

import com.lytov.diplom.core.dspprbd.domain.ProcessAnalyze;

import java.util.UUID;

public interface ProcessAnalyzeService {

    ProcessAnalyze create(ProcessAnalyze processAnalyze);

    ProcessAnalyze create(UUID processId);

    ProcessAnalyze findById(UUID processId);
}
