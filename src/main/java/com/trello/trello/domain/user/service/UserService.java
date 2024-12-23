package com.trello.trello.domain.user.service;

import com.trello.trello.domain.user.dto.UserSignupRequestDto;
import com.trello.trello.domain.user.dto.UserResponseDto;
import com.trello.trello.domain.user.dto.UserLoginDto;
import com.trello.trello.domain.user.entity.User;
import com.trello.trello.domain.user.repository.UserRepository;
import com.trello.trello.global.config.PasswordEncoder;
import com.trello.trello.global.exception.CustomException;
import com.trello.trello.global.exception.ExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    public User signup(UserSignupRequestDto registerDto) {
        if (userRepository.existsByUserEmail(registerDto.getUserEmail())) {
            throw new CustomException(ExceptionType.USER_NOT_MATCH);
        }

        if (!Objects.equals(registerDto.getRole(), "ROLE_USER") && !Objects.equals(registerDto.getRole(), "ROLE_ADMIN")) {
            throw new CustomException(ExceptionType.USER_ROLE_NOT_MATCH);
        }

        User user = new User(
                registerDto.getUserEmail(),
                passwordEncoder.encode(registerDto.getPassword()),
                registerDto.getRole()
                );

        userRepository.save(user);
        return user;
    }

    // 로그인
    public User login(UserLoginDto signupDto) {
        User user = userRepository.findByUserEmail(signupDto.getUserEmail()).orElseThrow(() -> new CustomException(ExceptionType.NOT_LOGIN));

        if (!passwordEncoder.matches(signupDto.getPassword(), user.getPassword())) {
            throw new CustomException(ExceptionType.PASSWORD_NOT_CORRECT);
        }

        return user;
    }

    // 아이디로 유저찾기
    public UserResponseDto findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomException(ExceptionType.USER_NOT_MATCH));

        UserResponseDto findUser = new UserResponseDto(user);

        return findUser;
    }

}
