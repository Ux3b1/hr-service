package com.testcase.hrservice.service;

import com.testcase.hrservice.dto.KafkaAccountRs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AccountProducer {

    @Value("${spring.kafka.producer.account.topic}")
    private String key;

    @Autowired
    private KafkaTemplate<String, KafkaAccountRs> kafkaTemplate;

    public void produce(KafkaAccountRs value) {
        kafkaTemplate.send(key, value);
    }
}
