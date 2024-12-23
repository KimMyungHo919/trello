package com.trello.trello.domain.user.controller;

import com.trello.trello.domain.user.dto.UserSignupRequestDto;
import com.trello.trello.domain.user.dto.UserResponseDto;
import com.trello.trello.domain.user.dto.UserLoginDto;
import com.trello.trello.domain.user.dto.UserSignupResponseDto;
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

    // 로그인
    @PostMapping("/login")
    public String login(
            @RequestBody UserLoginDto loginDto,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            throw new CustomException(ExceptionType.ALREADY_LOGIN);
        }

        User user = userService.login(loginDto);

        session = request.getSession(true);
        session.setAttribute("user", user);

        return "로그인 성공!";
    }

    // 로그아웃
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.invalidate();

        return "로그아웃 성공";
    }

    // 아이디로 유저찾기
    @GetMapping("/{id}")
    public UserResponseDto findUserById (@PathVariable Long id) {
        return userService.findUserById(id);
    }
}
