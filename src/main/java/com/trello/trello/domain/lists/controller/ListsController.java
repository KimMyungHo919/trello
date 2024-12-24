package com.trello.trello.domain.lists.controller;

import com.trello.trello.domain.lists.entity.Lists;
import com.trello.trello.domain.lists.service.ListsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lists")
public class ListsController {

    private final ListsService listsService;

    public ListsController(ListsService listsService) {
        this.listsService = listsService;
    }

    // 특정 보드에 속한 모든 리스트 조회
    @GetMapping("/board/{boardId}")
    public ResponseEntity<List<Lists>> getListsByBoard(@PathVariable Long boardId) {
        List<Lists> lists = listsService.getListsByBoard(boardId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(lists);
    }

    // 리스트 작성
    @PostMapping
    public ResponseEntity<Lists> addList(@RequestBody Lists list) {
        Lists addedList = listsService.addList(list);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(addedList);
    }

    // 리스트 수정
    @PutMapping("/{id}")
    public ResponseEntity<Lists> updateList(@PathVariable Long id, @RequestBody Lists list) {
        Lists updatedList = listsService.updateList(id, list);
        if (updatedList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedList);
    }

    // 리스트 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteList(@PathVariable Long id) {
        boolean isDeleted = listsService.deleteList(id);
        if (!isDeleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
