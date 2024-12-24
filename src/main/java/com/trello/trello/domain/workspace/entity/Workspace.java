package com.trello.trello.domain.workspace.entity;

import com.trello.trello.domain.board.entity.Board;
import com.trello.trello.domain.workspacemember.entity.WorkspaceMember;
import com.trello.trello.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Workspace extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "workspace")
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "workspace")
    private List<WorkspaceMember> members = new ArrayList<>();
}
