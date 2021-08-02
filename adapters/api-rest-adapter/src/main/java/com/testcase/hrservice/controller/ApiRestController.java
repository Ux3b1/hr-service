package com.testcase.hrservice.controller;

import com.google.gson.Gson;
import com.testcase.hrservice.dto.AccountRq;
import com.testcase.hrservice.dto.AccountRs;
import com.testcase.hrservice.exception.BadRequestException;
import com.testcase.hrservice.model.dictionary.SystemType;
import com.testcase.hrservice.model.logging.InputLog;
import com.testcase.hrservice.service.ApiRestService;
import com.testcase.hrservice.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public AccountRs postAccount(@RequestBody AccountRq accountRq) {
        loggingService.logIt(InputLog.builder()
                .system(SystemType.REST)
                .message(serializer.toJson(accountRq))
                .build());

        if (accountRq == null) {
            throw new BadRequestException();
        } else if (accountRq.getType() == 1) {
            return restService.createAccount(accountRq);
        } else if (accountRq.getType() == 2) {
            return restService.blockAccount(accountRq.getId());
        } else if (accountRq.getType() == 3) {
            return restService.updateAccount(accountRq);
        } else {
            throw new BadRequestException();
        }
    }
}
