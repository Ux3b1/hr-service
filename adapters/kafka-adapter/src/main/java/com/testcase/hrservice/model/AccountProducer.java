package com.testcase.hrservice.model;

import com.testcase.hrservice.dto.AccountRs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AccountProducer {

    private final String KEY = "account_response";

    @Autowired
    private KafkaTemplate<String, AccountRs> kafkaTemplate;

    public void produce(AccountRs value) {
        kafkaTemplate.send(KEY, value);
    }
}
