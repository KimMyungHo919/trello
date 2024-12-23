package com.trello.trello.domain.user.dto;

import com.trello.trello.domain.user.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private final Long id;
    private final String userEmail;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.userEmail = user.getUserEmail();
    }


}
