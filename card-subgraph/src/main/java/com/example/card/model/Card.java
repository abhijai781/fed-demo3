package com.example.card.model;

public class Card {

    private String cardId;
    private String cardExpiry;
    private Long userId;

    public Card() {
    }

    public Card(String cardId, String cardExpiry, Long userId) {
        this.cardId = cardId;
        this.cardExpiry = cardExpiry;
        this.userId = userId;
    }


    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardExpiry() {
        return cardExpiry;
    }

    public void setCardExpiry(String cardExpiry) {
        this.cardExpiry = cardExpiry;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
