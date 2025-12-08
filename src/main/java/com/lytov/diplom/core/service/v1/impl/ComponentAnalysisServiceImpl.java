package com.lytov.diplom.core.service.v1.impl;

import com.lytov.diplom.core.dspprbd.domain.HistoryAnalysis;
import com.lytov.diplom.core.generic.dto.SearchRequestDTO;
import com.lytov.diplom.core.repository.HistoryAnalysisRepository;
import com.lytov.diplom.core.repository.SearchSpecification;
import com.lytov.diplom.core.service.v1.api.HistoryAnalysisService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ComponentAnalysisServiceImpl implements HistoryAnalysisService {

    private final HistoryAnalysisRepository repository;

    @Override
    public Page<HistoryAnalysis> search(SearchRequestDTO search) {
        SearchSpecification<HistoryAnalysis> specification = new SearchSpecification<>(search);
        Pageable pageable = SearchSpecification.getPageable(search.getPage(), search.getSize());

        return repository.findAll(specification, pageable);
    }

}
