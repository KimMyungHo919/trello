package com.trello.trello.domain.card.repository;

import com.trello.trello.domain.card.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

    // 특정 리스트에 속한 모든 카드 조회
    List<Card> findByListId(Long listId);
}
