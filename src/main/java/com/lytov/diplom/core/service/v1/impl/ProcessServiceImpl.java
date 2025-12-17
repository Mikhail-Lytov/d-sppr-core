package com.lytov.diplom.core.service.v1.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lytov.diplom.core.dspprbd.domain.Outbox;
import com.lytov.diplom.core.dspprbd.domain.Process;
import com.lytov.diplom.core.dspprbd.enums.AggregateType;
import com.lytov.diplom.core.dspprbd.enums.OutboxStatus;
import com.lytov.diplom.core.external.parser.dto.UuidComponent;
import com.lytov.diplom.core.repository.OutboxRepository;
import com.lytov.diplom.core.repository.ProcessRepository;
import com.lytov.diplom.core.service.v1.api.ProcessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessServiceImpl implements ProcessService {

    private final ProcessRepository processRepository;
    private final OutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;

    @Override
    @Scheduled(fixedRate = 60000)
    public void createTaskConstructionMatrix() {
        Set<Process> processes = processRepository.findAllProcessActive();

        processes.forEach(process -> {

        });
        /*Set<Outbox> outboxes = processes.stream().map(
                p -> {
                    Outbox outbox = new Outbox();
                    outbox.setAggregateType(AggregateType.SECOND_PARSING);
                    outbox.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                    outbox.setStatus(OutboxStatus.NEW);
                    UuidComponent uuidComponent = new UuidComponent(p.getId());
                    try {
                        outbox.setPayload(objectMapper.writeValueAsString(uuidComponent));
                    } catch (JsonProcessingException e) {
                        log.error("Error serializing Primary TaskCreateTaskRequest", e);
                        throw new RuntimeException(e);
                    }
                    return outbox;
                }
        ).collect(Collectors.toSet());

        outboxRepository.saveAll(outboxes);*/
    }

    @Scheduled(fixedRate = 60000)
    public void createTask() {
       // Set<Process> processes = processRepository.findAllProcessActive();
    }
}
