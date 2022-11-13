package com.example.solipaire;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Board {
    private String board;
    public static final int NUMCOLUMNS = 7;
    public static final int NUMROWS = 13;
    public static final int NUMPILES = 4;
    private Card[][] cardColumns, donePiles;
    private Stack<Card> drawPile;

    public Board(String string) {
        board = string;
        cardColumns = new Card[NUMCOLUMNS][NUMROWS];
        donePiles = new Card[NUMPILES][NUMROWS];
        drawPile = new Stack<>();
    }

    public List<Card> createBoard(List<Card> deck) {
        Random rand = new Random();
        int cardIdx;
        Card card;
        for (int i = 0; i < NUMCOLUMNS; i++) {
            for (int j = i; j < NUMCOLUMNS; j++) {
                cardIdx = rand.nextInt(deck.size() - 1) + 1;
                card = deck.remove(cardIdx);
                if (j == i) card.flipCard();
                cardColumns[j][i] =  card;
            }
        }
        return deck;
    }

    public void setDrawPile(Stack<Card> drawPile) {
        this.drawPile = drawPile;
    }

    public Card[][] getCardColumns() {
        return cardColumns;
    }

    public Card[][] getDonePiles() {
        return donePiles;
    }

    @Override
    public String toString() {
        String cards = "Board columns: ";
        for (Card[] cardColumn : cardColumns) {
            for (Card card : cardColumn) {
                if (card != null) {
                    cards = cards + card.toString();
                }
            }
        }

        cards = cards + "Done piles: ";
        for (Card[] donePile : donePiles) {
            for (Card card : donePile) {
                if (card != null) {
                    cards = cards + card.toString();
                }
            }
        }

        cards = cards + " Draw pile: ";
        for (Card card : drawPile) {
            if (card != null) {
                cards = cards + card.toString();
            }
        }
        return cards;
    }
}
