package com.trello.trello.domain.user.controller;

import com.trello.trello.domain.user.dto.*;
import com.trello.trello.domain.user.entity.User;
import com.trello.trello.domain.user.service.UserService;
import com.trello.trello.global.exception.CustomException;
import com.trello.trello.global.exception.ExceptionType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<UserSignupResponseDto> signup(
            @RequestBody @Valid UserSignupRequestDto signupDto,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            throw new CustomException(ExceptionType.ALREADY_LOGIN);
        }

        User user = userService.signup(signupDto);
        UserSignupResponseDto signupUser = new UserSignupResponseDto(
                user.getId(),
                user.getUsername(),
                user.getRole()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(signupUser);
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("로그아웃 성공");
    }

    // 아이디로 유저찾기
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.findUserById(id));
    }

    // 탈퇴
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<String> deleteUser(
            @RequestBody UserDeleteRequestDto dto,
            @PathVariable Long id
    ) {

        userService.deleteUser(id, dto);

        return ResponseEntity.status(HttpStatus.OK).body("탈퇴완료!");
    }
}
