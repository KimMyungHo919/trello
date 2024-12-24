package com.trello.trello.domain.card.controller;

import com.trello.trello.domain.card.entity.Card;
import com.trello.trello.domain.card.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    // 특정 리스트에 속한 모든 카드 조회
    @GetMapping("/list/{listId}")
    public ResponseEntity<List<Card>> getCardsByList(@PathVariable Long listId) {
        List<Card> cards = cardService.getCardsByList(listId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cards);
    }

    // 카드 생성
    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        Card createdCard = cardService.createCard(card);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdCard);
    }

    // 카드 수정
    @PutMapping("/{id}")
    public ResponseEntity<Card> updateCard(@PathVariable Long id, @RequestBody Card card) {
        Card updatedCard = cardService.updateCard(id, card);
        if (updatedCard == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedCard);
    }

    // 카드 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long id) {
        boolean isDeleted = cardService.deleteCard(id);
        if (!isDeleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
