package com.testcase.hrservice.service;

import com.testcase.hrservice.model.logging.AbstractLog;
import com.testcase.hrservice.model.LogProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoggingServiceImpl implements LoggingService {

    @Autowired
    private LogProducer logProducer;

    @Override
    public void logIt(AbstractLog log) {
        logProducer.produce(log);
    }
}
