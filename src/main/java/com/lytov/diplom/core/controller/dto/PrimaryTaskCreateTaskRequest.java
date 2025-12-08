package com.lytov.diplom.core.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrimaryTaskCreateTaskRequest {
    private UUID fileId;
}
