package com.trello.trello.domain.comment.repository;

import com.trello.trello.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 특정 카드에 대한 모든 댓글 조회
    List<Comment> findByCardId(Long cardId);

    // 특정 유저가 작성한 모든 댓글 조회
    List<Comment> findByUserId(Long userId);
}
