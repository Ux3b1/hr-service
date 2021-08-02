package com.testcase.hrservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountRq {
    Long id;
    Integer type;
    String role;
    String fio;
    String post;
    String userName;
}
