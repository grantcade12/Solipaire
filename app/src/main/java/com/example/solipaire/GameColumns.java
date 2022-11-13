package com.example.solipaire;

public class GameColumns {
    public static final int LENGTH = 13;
    public static final int WIDTH = 7;

    private final Card[][] cardColumns;

    public GameColumns() {
        cardColumns = new Card[WIDTH][LENGTH];
    }
}
