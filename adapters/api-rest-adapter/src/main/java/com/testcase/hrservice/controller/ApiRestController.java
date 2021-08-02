package com.testcase.hrservice.controller;

import com.testcase.hrservice.repository.AccountRepository;
import com.testcase.hrservice.repository.PostRepository;
import com.testcase.hrservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiRestController {

    private final AccountRepository accountRepository;
    private final PostRepository postRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public ApiRestController(AccountRepository accountRepository,
                             PostRepository postRepository,
                             RoleRepository roleRepository) {
        this.accountRepository = accountRepository;
        this.postRepository = postRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
