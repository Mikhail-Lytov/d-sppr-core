package com.lytov.diplom.core.controller;

import com.lytov.diplom.core.dspprbd.domain.HistoryAnalysis;
import com.lytov.diplom.core.generic.dto.SearchRequestDTO;
import com.lytov.diplom.core.service.v1.api.HistoryAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HistoryAnalysisControllerImpl  implements HistoryAnalysisController {

    private final HistoryAnalysisService service;
    @Override
    public Page<HistoryAnalysis> search(SearchRequestDTO request) {
        return service.search(request);
    }
}
