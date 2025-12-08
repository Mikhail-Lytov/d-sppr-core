package com.lytov.diplom.core.service.v1.api;

import com.lytov.diplom.core.controller.dto.PrimaryTaskCreateTaskRequest;

import java.util.UUID;

public interface PrimaryMatrixService {

    UUID createTask(PrimaryTaskCreateTaskRequest request);
}
