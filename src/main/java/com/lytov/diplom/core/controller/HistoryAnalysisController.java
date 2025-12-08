package com.lytov.diplom.core.controller;

import com.lytov.diplom.core.dspprbd.domain.HistoryAnalysis;
import com.lytov.diplom.core.generic.dto.SearchRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.Page;

@Tag(name = "История анализов")
@RequestMapping("/history-analysis")
public interface HistoryAnalysisController {

    @PostMapping("/search")
    @Operation(description = "Поиск")
    Page<HistoryAnalysis> search(@RequestBody SearchRequestDTO request);

}
