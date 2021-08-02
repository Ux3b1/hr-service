package com.testcase.hrservice.converter;

import com.testcase.hrservice.dto.AccountRq;
import com.testcase.hrservice.model.domain.Account;
import com.testcase.hrservice.model.domain.Post;
import com.testcase.hrservice.model.domain.Role;
import org.springframework.stereotype.Service;

@Service
public class AccountRq2AccountConverter {

    public Account convert(AccountRq rq) {
        return Account.builder()
                .fio(rq.getFio())
                .isActive(Boolean.TRUE)
                .post(Post.builder()
                        .name(rq.getPost())
                        .build())
                .role(Role.builder()
                        .name(rq.getRole())
                        .build())
                .userName(rq.getUserName())
                .build();
    }
}
