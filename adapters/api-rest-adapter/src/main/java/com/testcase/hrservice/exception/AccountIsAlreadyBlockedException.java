package com.testcase.hrservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Account is already blocked")
public class AccountIsAlreadyBlockedException extends BaseException {
    public AccountIsAlreadyBlockedException() {
    }
}
