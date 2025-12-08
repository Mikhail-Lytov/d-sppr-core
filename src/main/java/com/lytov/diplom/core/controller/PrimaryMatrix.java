package com.lytov.diplom.core.controller;

import com.lytov.diplom.core.controller.dto.PrimaryTaskCreateTaskRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Tag(name = "Первичная матрица")
@RequestMapping("/primary-matrix")
public interface PrimaryMatrix {

    @PostMapping("/create")
    @Operation(description = "Создать первичную матрицу")
    ResponseEntity<UUID> create(@RequestBody PrimaryTaskCreateTaskRequest request);
}
