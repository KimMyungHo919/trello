package com.trello.trello.domain.workspacemember.repository;

import com.trello.trello.domain.workspacemember.entity.WorkspaceMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkspaceMemberRepository extends JpaRepository<WorkspaceMember, Long> {

    // 특정 워크스페이스의 모든 멤버 조회
    List<WorkspaceMember> findByWorkspaceId(Long workspaceId);

    // 특정 유저의 모든 워크스페이스 조회
    List<WorkspaceMember> findByUserId(Long userId);
}
