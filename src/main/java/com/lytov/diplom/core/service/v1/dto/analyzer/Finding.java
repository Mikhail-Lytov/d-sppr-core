package com.lytov.diplom.core.service.v1.dto.analyzer;


import com.lytov.diplom.core.dspprbd.domain.BpmnType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Finding {

    private String ruleId;
    private String code;
    private BpmnType bpmnType; // NODE/EDGE
    private String refId;    // id элемента BPMN или ребра
    private String severity;
    private String confidence;
    private String evidence;
    private String idTask;
    private UUID riskId;
}
