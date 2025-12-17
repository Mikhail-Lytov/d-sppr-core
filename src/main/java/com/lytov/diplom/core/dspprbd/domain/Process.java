package com.lytov.diplom.core.dspprbd.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    public Process(LocalDateTime dateCreate,  Boolean firstParsing,  UUID fileId) {
        this.dateCreate = dateCreate;
        this.firstParsing = firstParsing;
        this.fileId = fileId;
    }
}
