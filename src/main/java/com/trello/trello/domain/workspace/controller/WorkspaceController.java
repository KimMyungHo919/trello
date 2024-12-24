package com.trello.trello.domain.workspace.controller;

import com.trello.trello.domain.workspace.entity.Workspace;
import com.trello.trello.domain.workspace.service.WorkspaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workspaces")
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    public WorkspaceController(WorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    // 모든 워크스페이스 조회
    @GetMapping
    public ResponseEntity<List<Workspace>> getAllWorkspaces() {
        List<Workspace> workspaces = workspaceService.getAllWorkspaces();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(workspaces);
    }

    // 특정 워크스페이스 조회
    @GetMapping("/{id}")
    public ResponseEntity<Workspace> getWorkspaceById(@PathVariable Long id) {
        Workspace workspace = workspaceService.getWorkspaceById(id);
        if (workspace == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(workspace);
    }

    // 워크스페이스 생성
    @PostMapping
    public ResponseEntity<Workspace> createWorkspace(@RequestBody Workspace workspace) {
        Workspace createdWorkspace = workspaceService.createWorkspace(workspace);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdWorkspace);
    }

    // 워크스페이스 수정
    @PutMapping("/{id}")
    public ResponseEntity<Workspace> updateWorkspace(@PathVariable Long id, @RequestBody Workspace workspace) {
        Workspace updatedWorkspace = workspaceService.updateWorkspace(id, workspace);
        if (updatedWorkspace == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedWorkspace);
    }

    // 워크스페이스 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkspace(@PathVariable Long id) {
        boolean isDeleted = workspaceService.deleteWorkspace(id);
        if (!isDeleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
