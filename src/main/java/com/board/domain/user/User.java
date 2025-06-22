package com.board.domain.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String nickname;

    @NotEmpty
    private String password;
}
