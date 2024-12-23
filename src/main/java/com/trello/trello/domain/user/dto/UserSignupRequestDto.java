package com.trello.trello.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserSignupRequestDto {

    @NotBlank
    @Email
    private final String username;

    @NotBlank
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "비밀번호는 영문 대소문자, 숫자, 특수문자를 최소 1글자 이상 포함하고 8자 이상이어야 합니다."
    )
    private final String password;

    @NotBlank
    private final String role;

    public UserSignupRequestDto(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
