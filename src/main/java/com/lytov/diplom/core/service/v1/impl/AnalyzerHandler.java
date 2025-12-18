package com.lytov.diplom.core.service.v1.impl;

import com.lytov.diplom.core.configuration.rabbit.DAnalyzerRMQConfig;
import com.lytov.diplom.core.service.v1.api.AnalyzerService;
import com.lytov.diplom.core.service.v1.dto.analyzer.ResultAnalyzeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnalyzerHandler {

    private final AnalyzerService analyzerService;

    @RabbitListener(queues = DAnalyzerRMQConfig.FROM_ANALYZER_RESULT_QUEUE)
    public void analyzeResult(@Payload ResultAnalyzeMessage message) {
        try {
            analyzerService.firstAnalyzer(message.getProcessId(), message.getFindings());
        } catch (Exception e) {
            log.error("error analyzing result");
            log.error(e.getMessage());
        }
    }
}
