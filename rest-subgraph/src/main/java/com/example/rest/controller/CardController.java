package com.example.rest.controller;

import com.example.rest.model.Card;
import com.example.rest.model.User;
import com.example.rest.service.CardService;
import org.springframework.graphql.data.federation.EntityMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CardController {
    
    private final CardService cardService;
    
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }
    
    @QueryMapping
    public List<Card> allCards() {
        return cardService.findAllCards();
    }
    
    @EntityMapping
    public User user(@Argument Long userId) { return new User(userId);}
    
    @EntityMapping
    public Card card(@Argument String cardId) {return cardService.findCardById(cardId);}
    
    @SchemaMapping(typeName = "User", field = "cards")
    public List<Card> cards(User user) {
        return cardService.forUser(user.getUserId());
    }
}
