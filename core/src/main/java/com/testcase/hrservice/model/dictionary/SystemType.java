package com.testcase.hrservice.model.dictionary;

public enum SystemType {
    HIBERNATE("AccountStorage"),
    KAFKA("Kafka-Broker"),
    REST("WebService");

    private String name;

    SystemType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
