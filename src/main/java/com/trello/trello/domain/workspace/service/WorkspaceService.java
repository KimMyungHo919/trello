package com.trello.trello.domain.workspace.service;

import com.trello.trello.domain.workspace.entity.Workspace;
import com.trello.trello.domain.workspace.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;

    // 모든 워크스페이스 조회
    public List<Workspace> getAllWorkspaces() {
        return workspaceRepository.findAll();
    }

    // 특정 워크스페이스 조회
    public Workspace getWorkspaceById(Long id) {
        Optional<Workspace> workspace = workspaceRepository.findById(id);
        return workspace.orElse(null);
    }

    // 워크스페이스 생성
    public Workspace createWorkspace(Workspace workspace) {
        return workspaceRepository.save(workspace);
    }

    // 워크스페이스 수정
    public Workspace updateWorkspace(Long id, Workspace workspace) {
        if (workspaceRepository.existsById(id)) {
            workspace.setId(id);
            return workspaceRepository.save(workspace);
        }
        return null;
    }

    // 워크스페이스 삭제
    public boolean deleteWorkspace(Long id) {
        if (workspaceRepository.existsById(id)) {
            workspaceRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
