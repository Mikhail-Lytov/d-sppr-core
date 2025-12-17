package com.lytov.diplom.core.service.v1.impl;

import com.lytov.diplom.core.dspprbd.domain.ComponentProcess;
import com.lytov.diplom.core.exception.NotFoundException;
import com.lytov.diplom.core.repository.ComponentProcessRepository;
import com.lytov.diplom.core.service.v1.api.ComponentProcessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ComponentProcessImpl implements ComponentProcessService {

    private final ComponentProcessRepository repository;

    @Override
    public void addFactor(UUID id, Integer factor) {
        ComponentProcess componentProcess = repository.findById(id).orElseThrow(() -> new NotFoundException("component process not found by id: " + id));

        componentProcess.setWeightFactor(factor);
        repository.save(componentProcess);
    }

}
