package com.trello.trello.domain.comment.controller;

import com.trello.trello.domain.comment.entity.Comment;
import com.trello.trello.domain.comment.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 특정 카드에 대한 모든 댓글 조회
    @GetMapping("/card/{cardId}")
    public ResponseEntity<List<Comment>> getCommentsByCard(@PathVariable Long cardId) {
        List<Comment> comments = commentService.getCommentsByCard(cardId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(comments);
    }

    // 특정 유저가 작성한 모든 댓글 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Comment>> getCommentsByUser(@PathVariable Long userId) {
        List<Comment> comments = commentService.getCommentsByUser(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(comments);
    }

    // 댓글 작성
    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        Comment addedComment = commentService.addComment(comment);
        return new ResponseEntity<>(addedComment, HttpStatus.CREATED);
    }

    // 댓글 수정
    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Comment comment) {
        Comment updatedComment = commentService.updateComment(id, comment);
        if (updatedComment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    // 댓글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        boolean isDeleted = commentService.deleteComment(id);
        if (!isDeleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
