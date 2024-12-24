package com.trello.trello.domain.lists.entity;

import com.trello.trello.domain.board.entity.Board;
import com.trello.trello.domain.card.entity.Card;
import com.trello.trello.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Lists extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToMany(mappedBy = "list")
    private List<Card> cards = new ArrayList<>();
}
