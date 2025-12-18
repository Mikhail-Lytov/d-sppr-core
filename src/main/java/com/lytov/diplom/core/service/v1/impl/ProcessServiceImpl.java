package com.lytov.diplom.core.service.v1.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lytov.diplom.core.configuration.rabbit.DAnalyzerRMQConfig;
import com.lytov.diplom.core.configuration.rabbit.DCoreRMQConfig;
import com.lytov.diplom.core.dspprbd.domain.Process;
import com.lytov.diplom.core.exception.NotFoundException;
import com.lytov.diplom.core.repository.ProcessRepository;
import com.lytov.diplom.core.service.v1.api.ProcessService;
import com.lytov.diplom.core.service.v1.dto.analyzer.AnalyzerRequest;
import com.lytov.diplom.core.service.v1.dto.BpmnGraph;
import com.lytov.diplom.core.service.v1.dto.RequestCreateGraph;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessServiceImpl implements ProcessService {

    private final ProcessRepository processRepository;
    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;

    @Override
    @Scheduled(fixedRate = 60000)
    public void createTaskConstructionMatrix() {
        Set<Process> processes = processRepository.findAllProcessActive();

        processes.forEach(process -> {
            RequestCreateGraph graph = new RequestCreateGraph(
                    process.getFileId(),
                    process.getId()
            );

            try {
                rabbitTemplate.convertAndSend(
                        DCoreRMQConfig.FROM_SPPR_CREATE_GRAPH_EXCHANGE,
                        "",
                        graph
                );
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        });
    }

    @Override
    public void addGraph(UUID processId, BpmnGraph bpmnGraph) {
        processRepository.findById(processId).ifPresent(process -> {
            process.setGraph(bpmnGraph);
            process.setFirstParsing(false);
            processRepository.save(process);
        });

        AnalyzerRequest request = new AnalyzerRequest(
                processId,
                bpmnGraph
        );

        rabbitTemplate.convertAndSend(
                DAnalyzerRMQConfig.FROM_ANALYZER_PROCESS_EXCHANGE,
                "",
                request
        );
    }

    @Override
    public Process findProcessById(UUID processId) {
        return processRepository.findById(processId)
                .orElseThrow(() -> new NotFoundException("Process with id " + processId + " not found"));
    }

    @Scheduled(fixedRate = 60000)
    public void createTask() {
        // Set<Process> processes = processRepository.findAllProcessActive();
    }
}
