package com.example.solipaire;

import android.graphics.Bitmap;

public class Card {
    private Suit suit;
    private int value;
    private Bitmap cardMap = null;
    private static Bitmap cardBackMap = CardHelper.getCardBackMap();
    private boolean flipped;

    public Card(Suit suit, int value) {
        flipped = false;
        this.suit = suit;
        this.value = value;
        cardMap = CardHelper.getBitMap(suit, value);

    }

    public void flipCard() {
        flipped = true;
    }

    public Bitmap getCardMap() {
        if (flipped) {
            return cardMap;
        }
        else {
            return cardBackMap;
        }
    }

    public Suit getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        String suit = CardHelper.getStringSuit(this.suit);
        String value = CardHelper.getStringValue(this.value);
        //toString for ace of Spades would be "as"
        return value.substring(0, 1) + suit.substring(0, 1);
    }

}
