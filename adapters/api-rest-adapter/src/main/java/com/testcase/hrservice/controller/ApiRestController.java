package com.testcase.hrservice.controller;

import com.google.gson.Gson;
import com.testcase.hrservice.dto.RestAccountRq;
import com.testcase.hrservice.dto.RestAccountRs;
import com.testcase.hrservice.exception.BadRequestException;
import com.testcase.hrservice.model.dictionary.OperationType;
import com.testcase.hrservice.model.dictionary.SystemType;
import com.testcase.hrservice.model.logging.InputLog;
import com.testcase.hrservice.service.ApiRestService;
import com.testcase.hrservice.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiRestController {

    @Autowired
    private ApiRestService restService;
    @Autowired
    private LoggingService loggingService;
    @Autowired
    private Gson serializer;

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
    
    @PostMapping(value = "/postAccount", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestAccountRs postAccount(@RequestBody RestAccountRq restAccountRq) {
        loggingService.logIt(InputLog.builder()
                .system(SystemType.REST)
                .message(serializer.toJson(restAccountRq))
                .build());

        if (restAccountRq == null) {
            throw new BadRequestException();
        } else if (OperationType.CREATE.equals(restAccountRq.getType())) {
            return restService.createAccount(restAccountRq);
        } else if (OperationType.BLOCK.equals(restAccountRq.getType())) {
            return restService.blockAccount(restAccountRq.getId());
        } else if (OperationType.UPDATE.equals(restAccountRq.getType())) {
            return restService.updateAccount(restAccountRq);
        } else {
            throw new BadRequestException();
        }
    }
}
