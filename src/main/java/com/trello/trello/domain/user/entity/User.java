package com.trello.trello.domain.user.entity;

import com.trello.trello.domain.comment.entity.Comment;
import com.trello.trello.domain.workspacemember.entity.WorkspaceMember;
import com.trello.trello.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String role;

    private String status;

    @OneToMany(mappedBy = "user")
    private List<WorkspaceMember> workspaces = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    public User(String username, String password, String role, String status) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public void deleteUser() {
        this.status = "INACTIVE";
    }
}
