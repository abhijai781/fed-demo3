package com.example.rest.service;

import com.example.rest.model.Card;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CardService {
    private static final Logger logger = LoggerFactory.getLogger(CardService.class);
    private static final String CARD_SERVICE_URL = "http://localhost:8001/cardservice/api/cards";
    
    private final RestService restService;
    
    public CardService(RestService restService) {
        this.restService = restService;
    }
    
    
    public List<Card> forUser(Long userId) {
        if (userId == null) {
            return Collections.emptyList();
        }
        List<Card> all = findAllCards();
        return all.stream()
                .filter(a -> Objects.equals(userId, a.getUserId()))
                .collect(Collectors.toList());
    }
    
    public Card findCardById(String cardId) {
        if (cardId == null) {
            return null;
        }
        List<Card> all = findAllCards();
        return all.stream()
                .filter(a -> Objects.equals(cardId, a.getCardId()))
                .findFirst()
                .orElse(null);
    }
    
    public List<Card> findAllCards() {
        try {
            Card[] addresses = restService.get(CARD_SERVICE_URL, Card[].class);
            if (addresses == null || addresses.length == 0) {
                return Collections.emptyList();
            }
            return Arrays.asList(addresses);
        } catch (Exception e) {
            logger.error("Error fetching all card: {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
