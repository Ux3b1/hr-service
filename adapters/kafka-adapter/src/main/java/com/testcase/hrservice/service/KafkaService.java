package com.testcase.hrservice.service;

import com.testcase.hrservice.converter.KafkaAccountRq2AccountConverter;
import com.testcase.hrservice.dto.KafkaAccountRq;
import com.testcase.hrservice.dto.KafkaAccountRs;
import com.testcase.hrservice.exception.AccountAlreadyBlockedException;
import com.testcase.hrservice.exception.AccountNotFoundException;
import com.testcase.hrservice.model.dictionary.SystemType;
import com.testcase.hrservice.model.domain.Account;
import com.testcase.hrservice.model.logging.ErrorLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
    @Autowired
    private AccountService service;
    @Autowired
    private KafkaAccountRq2AccountConverter converter;
    @Autowired
    private LoggingService loggingService;
    @Autowired
    private AccountProducer producer;

    public void createAccount(KafkaAccountRq kafkaAccountRq) {
        try {
            Account account = service.createAccount(converter.convert(kafkaAccountRq));
            producer.produce(buildResponse(account));
        } catch (Exception exception) {
            logError(exception.getMessage());
        }
    }

    public void blockAccount(Long id) {
        try {
            service.blockAccount(id);

            producer.produce(KafkaAccountRs.builder()
                    .userId(id)
                    .userName("blocked")
                    .pass("blocked")
                    .build());
        } catch (AccountNotFoundException | AccountAlreadyBlockedException exception) {
            logError(exception.getMessage());
        }
    }

    public void updateAccount(KafkaAccountRq kafkaAccountRq) {
        try {
            Account account = service.updateAccount(converter.convert(kafkaAccountRq));
            producer.produce(buildResponse(account));
        } catch (Exception exception) {
            logError(exception.getMessage());
        }
    }

    private KafkaAccountRs buildResponse(Account account) {
        return KafkaAccountRs.builder()
                .userId(account.getId())
                .userName(account.getUserName())
                .pass(account.getPassword())
                .build();
    }

    private void logError(String message) {
        loggingService.logIt(ErrorLog.builder()
                .system(SystemType.KAFKA)
                .message(message)
                .build());
    }
}
