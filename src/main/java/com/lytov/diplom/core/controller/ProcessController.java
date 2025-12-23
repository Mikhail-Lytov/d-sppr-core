package com.lytov.diplom.core.controller;

import com.lytov.diplom.core.dspprbd.domain.Process;
import com.lytov.diplom.core.service.v1.api.ProcessService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Процесс")
@RequestMapping("/process")
@RestController
@RequiredArgsConstructor
public class ProcessController {

    private final ProcessService processService;

    @GetMapping("/first")
    public Process first() {
        return processService.findFirst();
    }

}
