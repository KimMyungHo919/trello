package com.trello.trello.domain.user.dto;

import lombok.Getter;

@Getter
public class UserLoginRequestDto {

    private final String userEmail;
    private final String password;

    public UserLoginRequestDto(String userEmail, String password) {
        this.userEmail = userEmail;
        this.password = password;
    }
}
