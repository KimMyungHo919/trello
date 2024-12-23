package com.trello.trello.domain.user.entity;

import com.trello.trello.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String password;

    private String role;

    public User(String userEmail, String password, String role) {
        this.userEmail = userEmail;
        this.password = password;
        this.role = role;
    }

    // TODO: 패스워드 인코딩으로 확인하고 바꿔줘야함
    public void changePassword(String oldPassword, String newPassword) {
        this.password = newPassword;
    }

}
