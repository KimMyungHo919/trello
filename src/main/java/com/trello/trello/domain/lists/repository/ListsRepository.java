package com.trello.trello.domain.lists.repository;

import com.trello.trello.domain.lists.entity.Lists;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListsRepository extends JpaRepository<Lists, Long> {

    // 특정 보드에 속한 모든 리스트 조회
    List<Lists> findByBoardId(Long boardId);
}
