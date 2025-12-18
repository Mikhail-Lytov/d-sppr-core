package com.lytov.diplom.core.dspprbd.domain;

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
@Table(name = "process_analyze")
public class ProcessAnalyze {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "date_crete")
    private LocalDateTime dateCrete;

    @Column(name = "process_id")
    private UUID processId;

    public ProcessAnalyze(UUID processId) {
        this.processId = processId;
        this.dateCrete = LocalDateTime.now();
    }
}