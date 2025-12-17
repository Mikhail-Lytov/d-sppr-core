package com.lytov.diplom.core.service.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCreateGraph {
    private UUID fileId;
    private UUID processId;
}
