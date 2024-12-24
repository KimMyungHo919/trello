package com.trello.trello.domain.card.entity;

import com.trello.trello.domain.comment.entity.Comment;
import com.trello.trello.domain.user.entity.User;
import com.trello.trello.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Card extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "list_id")
    private List list;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User assignedTo;

    @OneToMany(mappedBy = "card")
    private List<Comment> comments = new ArrayList<>();
}
