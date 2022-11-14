package com.example.solipaire;

public enum CardColor {
    RED, BLUE, GREEN, PURPLE, BLACK;
    public static CardColor toCardColor(String cardColorString) {
        return valueOf(cardColorString);
    }
}
