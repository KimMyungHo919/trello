package com.trello.trello.domain.board.service;

import com.trello.trello.domain.board.entity.Board;
import com.trello.trello.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 모든 게시판 조회
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    // 특정 게시판 조회
    public Board getBoardById(Long id) {
        Optional<Board> board = boardRepository.findById(id);
        return board.orElse(null);
    }

    // 게시판 생성
    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    // 게시판 수정
    public Board updateBoard(Long id, Board board) {
        if (boardRepository.existsById(id)) {
            board.setId(id);
            return boardRepository.save(board);
        }
        return null;
    }

    // 게시판 삭제
    public boolean deleteBoard(Long id) {
        if (boardRepository.existsById(id)) {
            boardRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
