package com.lytov.diplom.core.dspprbd.domain;

import com.lytov.diplom.core.service.v1.dto.BpmnGraph;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "process")
public class Process {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "date_create", nullable = false)
    private LocalDateTime dateCreate;

    @Column(name = "file_id")
    private UUID fileId;

    @Column(name = "first_parsing")
    private Boolean firstParsing;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "graph", columnDefinition = "jsonb")
    private BpmnGraph graph;
    public Process(LocalDateTime dateCreate,  Boolean firstParsing,  UUID fileId) {
        this.dateCreate = dateCreate;
        this.firstParsing = firstParsing;
        this.fileId = fileId;
    }
}
