package com.example.card.controller;

import com.example.card.CardService;
import com.example.card.model.Card;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cards")

public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/{userId}")
    public List<Card> getProduct(@PathVariable Long userId) {
        return cardService.getCards(userId);
    }

}
