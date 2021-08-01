package com.testcase.hrservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiRestController {

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
