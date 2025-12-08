package com.lytov.diplom.core.controller;

import com.lytov.diplom.core.controller.dto.PrimaryTaskCreateTaskRequest;
import com.lytov.diplom.core.service.v1.api.PrimaryMatrixService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PrimaryMatrixImpl implements PrimaryMatrix {

    private final PrimaryMatrixService primaryMatrixService;

    @Override
    public ResponseEntity<UUID> create(PrimaryTaskCreateTaskRequest request) {
        return ResponseEntity.ok(primaryMatrixService.createTask(request));
    }
}
