package com.testcase.hrservice.model.dictionary;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.Arrays;

public enum OperationType implements Serializable {
    CREATE(1),
    BLOCK(2),
    UPDATE(3);

    private Integer code;

    OperationType(Integer code) {
        this.code = code;
    }

    @JsonValue
    public Integer getCode() {
        return code;
    }

    @JsonCreator
    public static OperationType fromValue(int typeCode) {

        return Arrays.stream(OperationType.values())
                .filter(el -> el.getCode() == typeCode)
                .findFirst()
                .orElseThrow();
    }
}
