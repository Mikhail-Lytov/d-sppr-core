package com.lytov.diplom.core.service.v1.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lytov.diplom.core.controller.dto.PrimaryTaskCreateTaskRequest;
import com.lytov.diplom.core.dspprbd.domain.ComponentProcess;
import com.lytov.diplom.core.dspprbd.domain.Outbox;
import com.lytov.diplom.core.dspprbd.domain.Process;
import com.lytov.diplom.core.dspprbd.enums.AggregateType;
import com.lytov.diplom.core.dspprbd.enums.OutboxStatus;
import com.lytov.diplom.core.external.parser.ParserConnector;
import com.lytov.diplom.core.external.parser.dto.Component;
import com.lytov.diplom.core.repository.ComponentProcessRepository;
import com.lytov.diplom.core.repository.OutboxRepository;
import com.lytov.diplom.core.repository.ProcessRepository;
import com.lytov.diplom.core.service.v1.api.PrimaryMatrixService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrimaryMatrixServiceImpl implements PrimaryMatrixService {

    private final OutboxRepository outboxRepository;

    private final ObjectMapper objectMapper;

    private final ParserConnector parserConnector;

    private final ComponentProcessRepository componentProcessRepository;

    private final ProcessRepository processRepository;
    @Override
    @Transactional
    public UUID createTask(PrimaryTaskCreateTaskRequest request) {
        Outbox outbox = new Outbox();
        outbox.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        outbox.setStatus(OutboxStatus.NEW);
        try {
            outbox.setPayload(objectMapper.writeValueAsString(request));
        } catch (JsonProcessingException e) {
            log.error("Error serializing Primary TaskCreateTaskRequest", e);
            throw new RuntimeException(e);
        }
        outbox.setAggregateType(AggregateType.FIRST_PARSING);
        Outbox savedOutbox = outboxRepository.save(outbox);
        return savedOutbox.getId();
    }

    @Scheduled(cron = "0 * * * * *")
    public void uploadTask() throws JsonProcessingException {
        List<Outbox> tasks = outboxRepository.findAllByPrimaryMatrix(OutboxStatus.NEW, AggregateType.FIRST_PARSING);

        for (Outbox outbox : tasks) {
            PrimaryTaskCreateTaskRequest request = objectMapper.readValue(outbox.getPayload(), PrimaryTaskCreateTaskRequest.class);
            List<Component> components = parserConnector.parserFile(request.getFileId()).getBody();

            //TODO добавить автоматическикое определение типов

            Process process = processRepository.save(new Process(LocalDateTime.now(), true, request.getFileId()));

            List<ComponentProcess> componentToAnalysis = components.stream()
                    .map(
                            cta -> {
                                ComponentProcess c = new ComponentProcess();
                                c.setComponentId(cta.getId());
                                c.setComponentName(cta.getName());
                                c.setComponentRole(cta.getRole());
                                c.setComponentOperationType(cta.getOperationType());
                                c.setComponentTaskType(cta.getBpmnTaskType());
                                c.setProcessId(process);
                                return c;
                            }
                    )
                    .toList();

                componentProcessRepository.saveAll(componentToAnalysis);
                outbox.setStatus(OutboxStatus.CLOSED);
                outboxRepository.save(outbox);
        }
    }
}
