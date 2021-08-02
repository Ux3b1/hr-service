package com.testcase.hrservice.service;

import com.testcase.hrservice.converter.AccountRq2AccountConverter;
import com.testcase.hrservice.dto.RestAccountRq;
import com.testcase.hrservice.dto.RestAccountRs;
import com.testcase.hrservice.exception.*;
import com.testcase.hrservice.model.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiRestService {

    @Autowired
    private AccountService service;
    @Autowired
    private AccountRq2AccountConverter converter;

    public RestAccountRs createAccount(RestAccountRq restAccountRq) {
        try {
            Account account = service.createAccount(converter.convert(restAccountRq));
            return buildResponse(account);
        } catch (Exception exception) {
            throw new CreateAccountException();
        }
    }

    public RestAccountRs blockAccount(Long id) {
        try {
            service.blockAccount(id);

            return RestAccountRs.builder()
                    .userId(id)
                    .userName("blocked")
                    .pass("blocked")
                    .build();
        } catch (AccountNotFoundException exception) {
            throw new AccountNotFoundByIdException();
        } catch (AccountAlreadyBlockedException exception) {
            throw new AccountIsAlreadyBlockedException();
        }
    }

    public RestAccountRs updateAccount(RestAccountRq restAccountRq) {
        try {
            Account account = service.updateAccount(converter.convert(restAccountRq));
            return buildResponse(account);
        } catch (Exception exception) {
            throw new UpdateAccountException();
        }
    }

    private RestAccountRs buildResponse(Account account) {
        return RestAccountRs.builder()
                .userId(account.getId())
                .userName(account.getUserName())
                .pass(account.getPassword())
                .build();
    }
}
