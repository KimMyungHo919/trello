package com.trello.trello.domain.user.dto;

import lombok.Getter;

@Getter
public class UserLoginDto {

    private final String username;
    private final String password;

    public UserLoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
