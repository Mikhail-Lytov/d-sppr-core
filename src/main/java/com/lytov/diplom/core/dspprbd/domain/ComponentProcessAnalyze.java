package com.lytov.diplom.core.dspprbd.domain;

import com.lytov.diplom.core.dspprbd.enums.AnalyzeType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "component_process_analyze")
public class ComponentProcessAnalyze {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "process_id")
    private Process process;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "process_analyze_id")
    private ProcessAnalyze processAnalyze;

    @Column(name = "bpmn_type")
    @Enumerated(EnumType.STRING)
    private BpmnType bpmnType;

    @Column(name = "ref_id")
    private String refId;

    @Column(name = "risk_id")
    private UUID riskId;

    @Column(name = "task_id")
    private String taskId;

    @Column(name = "date_create")
    private LocalDateTime dateCreate;

    @Column(name = "analyze_type")
    @Enumerated(EnumType.STRING)
    private AnalyzeType analyzeType;

    public ComponentProcessAnalyze(
            Process process,
            ProcessAnalyze processAnalyze,
            BpmnType bpmnType,
            String refId,
            UUID riskId,
            String taskId,
            AnalyzeType analyzeType
    ) {
        this.process = process;
        this.processAnalyze = processAnalyze;
        this.bpmnType = bpmnType;
        this.refId = refId;
        this.riskId = riskId;
        this.taskId = taskId;
        this.dateCreate = LocalDateTime.now();
        this.analyzeType = analyzeType;
    }

}