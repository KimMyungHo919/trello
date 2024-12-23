package com.trello.trello.domain.user.service;

import com.trello.trello.domain.user.dto.UserDeleteRequestDto;
import com.trello.trello.domain.user.dto.UserSignupRequestDto;
import com.trello.trello.domain.user.dto.UserResponseDto;
import com.trello.trello.domain.user.entity.User;
import com.trello.trello.domain.user.repository.UserRepository;
import com.trello.trello.global.exception.CustomException;
import com.trello.trello.global.exception.ExceptionType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // 회원가입
    public User signup(UserSignupRequestDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new CustomException(ExceptionType.EXIST_USER);
        }

        if (!Objects.equals(registerDto.getRole(), "ROLE_USER") && !Objects.equals(registerDto.getRole(), "ROLE_ADMIN")) {
            throw new CustomException(ExceptionType.USER_ROLE_NOT_MATCH);
        }

        User user = new User(
                registerDto.getUsername(),
                passwordEncoder.encode(registerDto.getPassword()),
                registerDto.getRole(),
                "ACTIVE"
                );

        userRepository.save(user);
        return user;
    }

    // 아이디로 유저찾기
    public UserResponseDto findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomException(ExceptionType.USER_NOT_MATCH));

        return new UserResponseDto(user);
    }

    // 탈퇴
    @Transactional
    public void deleteUser(Long id, UserDeleteRequestDto dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomException(ExceptionType.USER_NOT_MATCH));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new CustomException(ExceptionType.PASSWORD_NOT_CORRECT);
        }

        if (!Objects.equals(user.getId(), id)) {
            throw new CustomException(ExceptionType.USER_NOT_MATCH);
        }

        user.deleteUser();
    }
}
