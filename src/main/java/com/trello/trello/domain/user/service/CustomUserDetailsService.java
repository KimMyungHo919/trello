package com.trello.trello.domain.user.service;

import com.trello.trello.domain.user.dto.CustomUserDetails;
import com.trello.trello.domain.user.entity.User;
import com.trello.trello.domain.user.repository.UserRepository;
import com.trello.trello.global.exception.CustomException;
import com.trello.trello.global.exception.ExceptionType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


// 사용자를 DB에서 조회하고, UserDetails 객체를 반환
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    // 주어진 username을 통해 DB에서 사용자 정보를 조회하고, 이를 CustomUserDetails 객체로 반환
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //DB에서 조회
        User user = userRepository.findByUsername(username).orElseThrow(() -> new CustomException(ExceptionType.EXIST_USER));

        if (user != null) {
            //UserDetails에 담아서 return하면 AutneticationManager가 검증 함
            return new CustomUserDetails(user);
        }

        return null;
    }
}

