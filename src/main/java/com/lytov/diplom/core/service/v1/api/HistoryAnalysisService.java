package com.lytov.diplom.core.service.v1.api;

import com.lytov.diplom.core.dspprbd.domain.HistoryAnalysis;
import com.lytov.diplom.core.generic.dto.SearchRequestDTO;
import org.springframework.data.domain.Page;

public interface HistoryAnalysisService {

    Page<HistoryAnalysis> search(SearchRequestDTO search);
}
