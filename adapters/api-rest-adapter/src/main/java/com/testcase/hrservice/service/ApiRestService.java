package com.testcase.hrservice.service;

import com.testcase.hrservice.converter.AccountRq2AccountConverter;
import com.testcase.hrservice.dto.AccountRq;
import com.testcase.hrservice.dto.AccountRs;
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

    public AccountRs createAccount(AccountRq accountRq) {
        try {
            Account account = service.createAccount(converter.convert(accountRq));
            return buildResponse(account);
        } catch (Exception exception) {
            throw new CreateAccountException();
        }
    }

    public AccountRs blockAccount(Long id) {
        try {
            service.blockAccount(id);

            return AccountRs.builder()
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

    public AccountRs updateAccount(AccountRq accountRq) {
        try {
            Account account = service.updateAccount(converter.convert(accountRq));
            return buildResponse(account);
        } catch (Exception exception) {
            throw new UpdateAccountException();
        }
    }

    private AccountRs buildResponse(Account account) {
        return AccountRs.builder()
                .userId(account.getId())
                .userName(account.getUserName())
                .pass(account.getPassword())
                .build();
    }
}
