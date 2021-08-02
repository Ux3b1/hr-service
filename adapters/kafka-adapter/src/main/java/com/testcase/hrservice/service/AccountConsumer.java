package com.testcase.hrservice.service;

import com.google.gson.Gson;
import com.testcase.hrservice.dto.KafkaAccountRq;
import com.testcase.hrservice.model.dictionary.OperationType;
import com.testcase.hrservice.model.dictionary.SystemType;
import com.testcase.hrservice.model.logging.InputLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${spring.kafka.consumer.account.topic}")
    private final String topic = "account_request";

    @KafkaListener(topics = topic, groupId = "account_group_id")
    public void consume(KafkaAccountRq kafkaAccountRq) {
        loggingService.logIt(InputLog.builder()
                .system(SystemType.KAFKA)
                .message(serializer.toJson(kafkaAccountRq))
                .build());

        if (OperationType.CREATE.equals(kafkaAccountRq.getType())) {
            service.createAccount(kafkaAccountRq);
        } else if (OperationType.BLOCK.equals(kafkaAccountRq.getType())) {
            service.blockAccount(kafkaAccountRq.getId());
        } else if (OperationType.UPDATE.equals(kafkaAccountRq.getType())) {
            service.updateAccount(kafkaAccountRq);
        }
    }
}
