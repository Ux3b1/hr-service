package com.testcase.hrservice.service;

import com.testcase.hrservice.converter.AccountRq2AccountConverter;
import com.testcase.hrservice.dto.AccountRq;
import com.testcase.hrservice.dto.AccountRs;
import com.testcase.hrservice.exception.AccountAlreadyBlockedException;
import com.testcase.hrservice.exception.AccountNotFoundException;
import com.testcase.hrservice.model.AccountProducer;
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
    private AccountRq2AccountConverter converter;
    @Autowired
    private LoggingService loggingService;
    @Autowired
    private AccountProducer producer;

    public void createAccount(AccountRq accountRq) {
        try {
            Account account = service.createAccount(converter.convert(accountRq));
            producer.produce(buildResponse(account));
        } catch (Exception exception) {
            logError(exception.getMessage());
        }
    }

    public void blockAccount(Long id) {
        try {
            service.blockAccount(id);

            producer.produce(AccountRs.builder()
                    .userId(id)
                    .userName("blocked")
                    .pass("blocked")
                    .build());
        } catch (AccountNotFoundException | AccountAlreadyBlockedException exception) {
            logError(exception.getMessage());
        }
    }

    public void updateAccount(AccountRq accountRq) {
        try {
            Account account = service.updateAccount(converter.convert(accountRq));
            producer.produce(buildResponse(account));
        } catch (Exception exception) {
            logError(exception.getMessage());
        }
    }

    private AccountRs buildResponse(Account account) {
        return AccountRs.builder()
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
