package com.testcase.hrservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Not found account with this id")
public class AccountNotFoundByIdException extends BaseException {

    public AccountNotFoundByIdException() {
    }
}
