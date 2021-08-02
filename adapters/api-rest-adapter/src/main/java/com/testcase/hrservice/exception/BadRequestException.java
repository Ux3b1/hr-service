package com.testcase.hrservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Bad request. invalid 'type' value")
public class BadRequestException extends BaseException {
    public BadRequestException() {
    }
}
