package com.lytov.diplom.core.service.v1.impl;

import com.lytov.diplom.core.dspprbd.domain.ProcessAnalyze;
import com.lytov.diplom.core.exception.NotFoundException;
import com.lytov.diplom.core.repository.ProcessAnalyzeRepository;
import com.lytov.diplom.core.service.v1.api.ProcessAnalyzeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessAnalyzeServiceImpl implements ProcessAnalyzeService {

    private final ProcessAnalyzeRepository repository;

    @Override
    public ProcessAnalyze create(ProcessAnalyze processAnalyze) {
        return repository.save(processAnalyze);
    }

    @Override
    public ProcessAnalyze create(UUID processId) {
        return repository.save(new ProcessAnalyze(processId));
    }

    @Override
    public ProcessAnalyze findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("No processAnalyze found with id %s", id)
                ));
    }
}
