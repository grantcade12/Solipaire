package com.example.solipaire;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Card {
    private Suit suit;
    private int value;
    private float xLoc = -1f, yLoc = -1f;
    private Bitmap cardMap = null;
    public static Bitmap cardBackMap = CardHelper.getCardBackMap();
    public static Bitmap heartMap = CardHelper.getDoneMap(Suit.Heart);
    public static Bitmap diamondMap = CardHelper.getDoneMap(Suit.Diamond);
    public static Bitmap spadeMap = CardHelper.getDoneMap(Suit.Spade);
    public static Bitmap clubMap = CardHelper.getDoneMap(Suit.Club);
    public static final int CARDWIDTH = cardBackMap.getWidth(), CARDHEIGHT = cardBackMap.getHeight();
    private boolean flipped;
    private static CardColor cardBack = SettingsSingleton.SettingsSingleton().cardColor;

    public Card(Suit suit, int value) {
        flipped = false;
        this.suit = suit;
        this.value = value;
        cardMap = CardHelper.getBitMap(suit, value);
    }

    public void flipCard() {
        flipped = !flipped;
    }

    public boolean isFlipped() { return flipped; }

    public Bitmap getCardMap() {
        if (flipped) {
            return cardMap;
        }
        else {
            if (SettingsSingleton.SettingsSingleton().cardColor != cardBack) {
                cardBackMap = Bitmap.createScaledBitmap(CardHelper.getCardBackMap(), CARDWIDTH, CARDHEIGHT, false);
                cardBack = SettingsSingleton.SettingsSingleton().cardColor;
            }
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
