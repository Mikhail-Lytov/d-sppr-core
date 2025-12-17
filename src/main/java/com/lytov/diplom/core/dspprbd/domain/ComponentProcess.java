package com.lytov.diplom.core.dspprbd.domain;

import com.lytov.diplom.core.external.parser.dto.OperationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "component_process")
public class ComponentProcess {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "component_id", length = Integer.MAX_VALUE)
    private String componentId;

    @Column(name = "component_name", length = Integer.MAX_VALUE)
    private String componentName;

    @Column(name = "component_task_type", length = Integer.MAX_VALUE)
    private String componentTaskType;

    @Column(name = "component_role", length = Integer.MAX_VALUE)
    private String componentRole;

    @Column(name = "component_department", length = Integer.MAX_VALUE)
    private String componentDepartment;

    @Column(name = "component_operation_type", length = Integer.MAX_VALUE)
    @Enumerated(EnumType.STRING)
    private OperationType componentOperationType;

    @Column(name = "weight_factor")
    private Integer weightFactor;

    @ManyToOne()
    @JoinColumn(name = "process_id")
    private Process processId;
}