package com.testcase.hrservice.util;

import org.apache.commons.lang.RandomStringUtils;

public class PasswordGenerator {

    public static String generate() {
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;

        return RandomStringUtils.random(length, useLetters, useNumbers);
    }
}
