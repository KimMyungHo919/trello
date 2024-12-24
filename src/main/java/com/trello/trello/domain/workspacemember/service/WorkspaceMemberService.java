package com.trello.trello.domain.workspacemember.service;

import com.trello.trello.domain.workspacemember.entity.WorkspaceMember;
import com.trello.trello.domain.workspacemember.repository.WorkspaceMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkspaceMemberService {

    private final WorkspaceMemberRepository workspaceMemberRepository;

    // 특정 워크스페이스의 모든 멤버 조회
    public List<WorkspaceMember> getMembersByWorkspace(Long workspaceId) {
        return workspaceMemberRepository.findByWorkspaceId(workspaceId);
    }

    // 특정 유저의 모든 워크스페이스 조회
    public List<WorkspaceMember> getWorkspacesByUser(Long userId) {
        return workspaceMemberRepository.findByUserId(userId);
    }

    // 워크스페이스에 사용자 추가
    public WorkspaceMember addMemberToWorkspace(WorkspaceMember workspaceMember) {
        return workspaceMemberRepository.save(workspaceMember);
    }

    // 워크스페이스에서 사용자 제거
    public boolean removeMemberFromWorkspace(Long id) {
        if (workspaceMemberRepository.existsById(id)) {
            workspaceMemberRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
