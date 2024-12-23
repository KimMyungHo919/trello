package com.trello.trello.domain.user.dto;

import lombok.Getter;

@Getter
public class UserLoginDto {

    private final String userEmail;
    private final String password;

    public UserLoginDto(String userEmail, String password) {
        this.userEmail = userEmail;
        this.password = password;
    }
}
