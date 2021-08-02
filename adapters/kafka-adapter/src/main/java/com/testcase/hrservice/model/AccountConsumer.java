package com.testcase.hrservice.model;

import com.google.gson.Gson;
import com.testcase.hrservice.dto.AccountRq;
import com.testcase.hrservice.model.dictionary.SystemType;
import com.testcase.hrservice.model.logging.InfoLog;
import com.testcase.hrservice.model.logging.InputLog;
import com.testcase.hrservice.service.KafkaService;
import com.testcase.hrservice.service.LoggingService;
import org.apache.kafka.common.protocol.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AccountConsumer {

    @Autowired
    private KafkaService service;
    @Autowired
    private LoggingService loggingService;
    @Autowired
    private Gson serializer;

    @KafkaListener(topics = "account_request", groupId = "account_group_id")
    public void consume(String message){
        if (message == null || message.isEmpty()) {
            return;
        }

        loggingService.logIt(InputLog.builder()
                .system(SystemType.KAFKA)
                .message(message)
                .build());

        AccountRq accountRq = serializer.fromJson(message, AccountRq.class);

        if (accountRq.getType() == 1) {
            service.createAccount(accountRq);
        } else if (accountRq.getType() == 2) {
            service.blockAccount(accountRq.getId());
        } else if (accountRq.getType() == 3) {
            service.updateAccount(accountRq);
        }
    }
}
