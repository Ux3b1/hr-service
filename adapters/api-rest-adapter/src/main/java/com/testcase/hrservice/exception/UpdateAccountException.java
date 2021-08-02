package com.testcase.hrservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Update account is failed")
public class UpdateAccountException extends BaseException {
    public UpdateAccountException() {
    }
}
