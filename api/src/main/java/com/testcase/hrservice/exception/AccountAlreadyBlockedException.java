package com.testcase.hrservice.exception;

public class AccountAlreadyBlockedException extends BaseException {
    public AccountAlreadyBlockedException() {
    }

    public AccountAlreadyBlockedException(String message) {
        super(message);
    }

    public AccountAlreadyBlockedException(String message, Throwable cause) {
        super(message, cause);
    }
}
