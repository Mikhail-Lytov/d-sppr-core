package com.lytov.diplom.core.controller;

import com.lytov.diplom.core.configuration.rabbit.DCoreRMQConfig;
import com.lytov.diplom.core.dspprbd.domain.ComponentProcessAnalyze;
import com.lytov.diplom.core.service.v1.api.ComponentProcessAnalyzeService;
import com.lytov.diplom.core.service.v1.dto.marker.AnalyzeRequest;
import com.lytov.diplom.core.service.v1.dto.marker.AnalyzeRow;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Tag(name = "test")
@RequestMapping("/test")
@RestController
@RequiredArgsConstructor
public class TestController {

    private final ComponentProcessAnalyzeService componentProcessAnalyzeService;
    private final RabbitTemplate rabbitTemplate;

    @PostMapping
    public void sentMarker(@RequestBody MessageRequest messageRequest) {
        List<ComponentProcessAnalyze> componentProcessAnalyzes = componentProcessAnalyzeService.findAllByAnalysisId(messageRequest.getAnalysisId());

        AnalyzeRequest request = new AnalyzeRequest(
                messageRequest.getFileId(),
                messageRequest.getProcessId(),
                converter(componentProcessAnalyzes)
        );
        rabbitTemplate.convertAndSend(
                DCoreRMQConfig.FROM_SPPR_MARKING_EXCHANGE,
                "",
                request
        );
    }

    private List<AnalyzeRow> converter(List<ComponentProcessAnalyze> rows) {
        return rows.stream().map(
                r -> {
                    return new AnalyzeRow(
                            r.getBpmnType().toString(),
                            r.getRefId(),
                            r.getRiskId().toString()
                    );
                }
        ).toList();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MessageRequest {
        private UUID processId;
        private UUID fileId;
        private UUID analysisId;
    }
}
