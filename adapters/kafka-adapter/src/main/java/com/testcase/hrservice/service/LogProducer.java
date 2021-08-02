package com.testcase.hrservice.service;

import com.testcase.hrservice.model.logging.AbstractLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class LogProducer {

    @Autowired
    private KafkaTemplate<String, AbstractLog> kafkaTemplate;
    @Value("${spring.kafka.producer.logger.topic}")
    private String key;

    public void produce(AbstractLog value) {
        kafkaTemplate.send(key, value);
    }
}
