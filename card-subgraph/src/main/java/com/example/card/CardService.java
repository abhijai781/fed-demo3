package com.example.card;

import com.example.card.model.Card;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CardService {
    private final List<Card> cards = new ArrayList<>();

    public CardService() {
        cards.add(new Card("1001", "1229", 1l));
        cards.add(new Card("1002", "1129", 1l));
        cards.add(new Card("1003", "1227", 2l));
        cards.add(new Card("1004", "1229", 3l));
    }

    public List<Card> getCards(Long userId) {
        return cards.stream()
                .filter(c -> Objects.equals(c.getUserId(), userId))
                .collect(Collectors.toList());
    }

}
