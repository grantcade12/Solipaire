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
    private List<List<Card>> cardColumns;
    private List<Stack<Card>> donePiles;
    private Stack<Card> drawPile;

    public Board(String string) {
        board = string;
        cardColumns = new ArrayList<>();
        donePiles = new ArrayList<>();
        for (int i = 0; i < NUMCOLUMNS; i++) {
            cardColumns.add(new ArrayList<>());
        }
        for (int i = 0; i < NUMPILES; i++) {
            donePiles.add(new Stack<>());
        }
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
                cardColumns.get(j).add(card);
            }
        }
        return deck;
    }

    public void setDrawPile(Stack<Card> drawPile) {
        this.drawPile = drawPile;
    }

    public Stack<Card> getDrawPile() { return drawPile; }

    public List<List<Card>> getCardColumns() {
        return cardColumns;
    }

    public List<Stack<Card>> getDonePiles() {
        return donePiles;
    }

    @Override
    public String toString() {
        String cards = "Board columns: ";
        for (List<Card> cardColumn : cardColumns) {
            for (Card card : cardColumn) {
                if (card != null) {
                    cards = cards + card.toString();
                }
            }
        }

        cards = cards + "Done piles: ";
        for (Stack<Card> donePile : donePiles) {
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
