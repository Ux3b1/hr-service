package com.testcase.hrservice.service;

import com.testcase.hrservice.model.domain.Account;

public interface AccountService {
    Account createAccount(Account account);

    Account updateAccount(Account account);

    void blockAccount(Long id);
}
