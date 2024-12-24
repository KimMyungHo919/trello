package com.trello.trello.domain.workspacemember.entity;

import com.trello.trello.domain.user.entity.User;
import com.trello.trello.domain.workspace.entity.Workspace;
import com.trello.trello.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class WorkspaceMember extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "workspace_id")
    private Workspace workspace;

    private String role;

    public void setUser(User user) {
        this.user = user;
        user.addWorkspaceMember(this);
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
        workspace.addWorkspaceMember(this);
    }
}
