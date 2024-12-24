package com.trello.trello.domain.workspacemember.controller;

import com.trello.trello.domain.workspacemember.entity.WorkspaceMember;
import com.trello.trello.domain.workspacemember.service.WorkspaceMemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workspace-members")
public class WorkspaceMemberController {

    private final WorkspaceMemberService workspaceMemberService;

    public WorkspaceMemberController(WorkspaceMemberService workspaceMemberService) {
        this.workspaceMemberService = workspaceMemberService;
    }

    // 특정 워크스페이스의 모든 멤버 조회
    @GetMapping("/workspace/{workspaceId}")
    public ResponseEntity<List<WorkspaceMember>> getMembersByWorkspace(@PathVariable Long workspaceId) {
        List<WorkspaceMember> members = workspaceMemberService.getMembersByWorkspace(workspaceId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(members);
    }

    // 특정 유저의 모든 워크스페이스 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WorkspaceMember>> getWorkspacesByUser(@PathVariable Long userId) {
        List<WorkspaceMember> workspaces = workspaceMemberService.getWorkspacesByUser(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(workspaces);
    }

    // 워크스페이스에 사용자 추가
    @PostMapping
    public ResponseEntity<WorkspaceMember> addMemberToWorkspace(@RequestBody WorkspaceMember workspaceMember) {
        WorkspaceMember addedMember = workspaceMemberService.addMemberToWorkspace(workspaceMember);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(addedMember);
    }

    // 워크스페이스에서 사용자 제거
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeMemberFromWorkspace(@PathVariable Long id) {
        boolean isRemoved = workspaceMemberService.removeMemberFromWorkspace(id);
        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
