package com.testcase.hrservice.model.logging;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.testcase.hrservice.model.dictionary.LogType;
import com.testcase.hrservice.model.dictionary.SystemType;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class InfoLog extends AbstractLog {
    private final LogType TYPE = LogType.INFO;

    @NotNull
    private SystemType system;
    @NotNull
    private String message;
}
