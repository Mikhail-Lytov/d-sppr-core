package com.lytov.diplom.core.external.parser;

import com.lytov.diplom.core.external.parser.dto.Component;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.UUID;

@FeignClient(value = "sppr-parser", url = "${external.sppr.parser}")
public interface ParserConnector {

    @PostMapping("/api/v0/parsing-process/{id}")
    ResponseEntity<List<Component>> parserFile(@PathVariable UUID id);
}
