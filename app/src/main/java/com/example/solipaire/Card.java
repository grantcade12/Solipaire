package com.example.solipaire;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Card {
    private Suit suit;
    private int value;
    public static final int CARDWIDTH = 100, CARDHEIGHT = 160;
    private float xLoc = -1f, yLoc = -1f;
    private Bitmap cardMap = null;
    public static Bitmap cardBackMap = Bitmap.createScaledBitmap(CardHelper.getCardBackMap(), CARDWIDTH, CARDHEIGHT, false);
    public static Bitmap heartMap = Bitmap.createScaledBitmap(CardHelper.getDoneMap(Suit.Heart), CARDWIDTH, CARDHEIGHT, false);
    public static Bitmap diamondMap = Bitmap.createScaledBitmap(CardHelper.getDoneMap(Suit.Diamond), CARDWIDTH, CARDHEIGHT, false);
    public static Bitmap spadeMap = Bitmap.createScaledBitmap(CardHelper.getDoneMap(Suit.Spade), CARDWIDTH, CARDHEIGHT, false);
    public static Bitmap clubMap = Bitmap.createScaledBitmap(CardHelper.getDoneMap(Suit.Club), CARDWIDTH, CARDHEIGHT, false);
    private boolean flipped;

    public Card(Suit suit, int value) {
        flipped = false;
        this.suit = suit;
        this.value = value;
        cardMap = Bitmap.createScaledBitmap(CardHelper.getBitMap(suit, value), CARDWIDTH, CARDHEIGHT, false);

    }

    public void flipCard() {
        flipped = true;
    }

    public boolean isFlipped() { return flipped; }

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

    public float getXLoc() { return xLoc; }

    public float getYLoc() { return yLoc; }

    public void setXLoc(float x) { xLoc = x; }

    public void setYLoc(float y) { yLoc = y; }

    public String toString() {
        String suit = CardHelper.getStringSuit(this.suit);
        String value = CardHelper.getStringValue(this.value);
        //toString for ace of Spades would be "as"
        return value.substring(0, 1) + suit.substring(0, 1);
    }

}
