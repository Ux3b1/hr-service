package com.testcase.hrservice.converter;

import com.testcase.hrservice.dto.KafkaAccountRq;
import com.testcase.hrservice.model.domain.Account;
import com.testcase.hrservice.model.domain.Post;
import com.testcase.hrservice.model.domain.Role;
import org.springframework.stereotype.Service;

@Service
public class KafkaAccountRq2AccountConverter {

    public Account convert(KafkaAccountRq rq) {
        return Account.builder()
                .id(rq.getId())
                .fio(rq.getFio())
                .isActive(Boolean.TRUE)
                .post(Post.builder()
                        .name(rq.getPost())
                        .build())
                .role(Role.builder()
                        .name(rq.getPost())
                        .build())
                .userName(rq.getUserName())
                .build();
    }
}
