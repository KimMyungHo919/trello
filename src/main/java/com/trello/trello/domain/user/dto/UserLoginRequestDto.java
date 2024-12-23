package com.trello.trello.domain.user.dto;

import lombok.Getter;

@Getter
public class UserLoginRequestDto {

    private final String username;
    private final String password;

    public UserLoginRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
