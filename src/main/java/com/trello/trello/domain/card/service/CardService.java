package com.trello.trello.domain.card.service;

import com.trello.trello.domain.card.repository.CardRepository;
import org.springframework.stereotype.Service;
import com.trello.trello.domain.card.entity.Card;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    // 특정 리스트에 속한 모든 카드 조회
    public List<Card> getCardsByList(Long listId) {
        return cardRepository.findByListId(listId);
    }

    // 카드 생성
    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    // 카드 수정
    public Card updateCard(Long id, Card card) {
        Optional<Card> existingCard = cardRepository.findById(id);
        if (existingCard.isPresent()) {
            Card updatedCard = existingCard.get();
            // 필요한 필드 업데이트
            updatedCard.setTitle(card.getTitle());
            updatedCard.setDescription(card.getDescription());
            updatedCard.setDueDate(card.getDueDate());
            updatedCard.setAssignedTo(card.getAssignedTo());
            return cardRepository.save(updatedCard);
        }
        return null;
    }

    // 카드 삭제
    public boolean deleteCard(Long id) {
        if (cardRepository.existsById(id)) {
            cardRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
