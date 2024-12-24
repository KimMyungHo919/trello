package com.trello.trello.domain.comment.service;
import com.trello.trello.domain.comment.entity.Comment;
import com.trello.trello.domain.comment.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    // 특정 카드에 대한 모든 댓글 조회
    public List<Comment> getCommentsByCard(Long cardId) {
        return commentRepository.findByCardId(cardId);
    }

    // 특정 유저가 작성한 모든 댓글 조회
    public List<Comment> getCommentsByUser(Long userId) {
        return commentRepository.findByUserId(userId);
    }

    // 댓글 작성
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    // 댓글 수정
    public Comment updateComment(Long id, Comment comment) {
        Optional<Comment> existingComment = commentRepository.findById(id);
        if (existingComment.isPresent()) {
            Comment updatedComment = existingComment.get();
            updatedComment.setText(comment.getText());
            return commentRepository.save(updatedComment);
        }
        return null;
    }

    // 댓글 삭제
    public boolean deleteComment(Long id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}