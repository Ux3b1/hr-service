package com.testcase.hrservice.dto;

import com.testcase.hrservice.model.dictionary.OperationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestAccountRq {
    Long id;
    OperationType type;
    String role;
    String fio;
    String post;
    String userName;
}
