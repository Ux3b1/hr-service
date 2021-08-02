package com.testcase.hrservice.model.domain;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ACCOUNT")
@ToString(exclude = "password")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "IS_ACTIVE", nullable = false)
    private Boolean isActive;
    @Column(name = "FIO", nullable = false)
    private String fio;
    @Column(name = "USER_NAME", nullable = false)
    private String userName;
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @OneToOne(targetEntity = Post.class)
    private Post post;

    @OneToOne(targetEntity = Role.class)
    private Role role;

    public void block() {
        this.isActive = Boolean.TRUE;
    }
}
