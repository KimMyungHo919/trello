package com.trello.trello.domain.user.dto;

import lombok.Getter;

@Getter
public class UserSignupResponseDto {

    private final Long id;
    private final String username;
    private final String role;
    private final String status;

    public UserSignupResponseDto(Long id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.status = "ACTIVE";
    }
}
