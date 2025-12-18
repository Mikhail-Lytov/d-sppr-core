package com.lytov.diplom.core.service.v1.impl;

import com.lytov.diplom.core.dspprbd.domain.ComponentProcessAnalyze;
import com.lytov.diplom.core.repository.ComponentProcessAnalyzeRepository;
import com.lytov.diplom.core.service.v1.api.ComponentProcessAnalyzeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class ComponentProcessAnalyzeServiceImpl implements ComponentProcessAnalyzeService {

    private final ComponentProcessAnalyzeRepository componentProcessAnalyzeRepository;

    @Override
    public void createAll(Collection<ComponentProcessAnalyze> processes) {
        componentProcessAnalyzeRepository.saveAll(processes);
    }
}
