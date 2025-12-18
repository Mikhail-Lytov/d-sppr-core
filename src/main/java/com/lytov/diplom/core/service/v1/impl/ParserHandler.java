package com.lytov.diplom.core.service.v1.impl;

import com.lytov.diplom.core.configuration.rabbit.DCoreRMQConfig;
import com.lytov.diplom.core.service.v1.api.ProcessService;
import com.lytov.diplom.core.service.v1.dto.ResultBpmnParserGraphDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParserHandler {

    private final ProcessService processService;

    @RabbitListener(queues = DCoreRMQConfig.FROM_CORE_SECOND_PARS_RESULT_QUEUE)
    public void handleResultSecondPars(@Payload ResultBpmnParserGraphDto graph) {
        processService.addGraph(graph.getProcessId(), graph.getGraph());
    }
}
