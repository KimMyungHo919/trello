package com.trello.trello.domain.user.dto;

import lombok.Getter;

@Getter
public class UserSignupResponseDto {

    private final Long id;
    private final String userEmail;
    private final String role;
    private final String status;

    public UserSignupResponseDto(Long id, String userEmail, String role) {
        this.id = id;
        this.userEmail = userEmail;
        this.role = role;
        this.status = "ACTIVE";
    }
}
