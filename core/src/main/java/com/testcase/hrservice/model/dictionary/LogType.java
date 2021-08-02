package com.testcase.hrservice.model.dictionary;

public enum LogType {
    OUTPUT("output"),
    INPUT("input"),
    ERROR("error"),
    INFO("info");

    private String name;

    LogType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
