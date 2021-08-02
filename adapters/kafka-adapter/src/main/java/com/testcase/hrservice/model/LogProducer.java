package com.testcase.hrservice.model;

import com.testcase.hrservice.model.logging.AbstractLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class LogProducer {

    private final String KEY = "logger";

    @Autowired
    private KafkaTemplate<String, AbstractLog> kafkaTemplate;

    public void produce(AbstractLog value) {
        kafkaTemplate.send(KEY, value);
    }
}
