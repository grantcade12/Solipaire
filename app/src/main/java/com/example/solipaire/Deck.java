package com.example.solipaire;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private static Suit suit;
    public static final List<Card> CARDDECK = new ArrayList<Card>() {
        {
            for (int i = 0; i < 4; i++) {

                if (i == 0) {
                    suit = Suit.Heart;
                } else if (i == 1) {
                    suit = Suit.Diamond;
                } else if (i == 2) {
                    suit = Suit.Spade;
                } else {
                    suit = Suit.Club;
                }

                for (int j = 1; j < 14; j++) {
                    add(new Card(suit, j));
                }
            }
        }
    };

    public static List<Card> getCarddeck() {
        if (CARDDECK.size() > 0) {
            for (int i = 0; i < CARDDECK.size(); i++) {
                Card card = CARDDECK.get(i);
                if (card.isFlipped()) {
                    card.flipCard();
                }
            }
        }
        return CARDDECK;
    }
}
