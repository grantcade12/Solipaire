package com.example.solipaire;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Board {
    private String board;
    private List<Stack<Card>> cardColumns, donePiles;
    private Stack<Card> drawPile;
    public Board() {
        board = "";
    }

    public Board(String string) {
        board = string;
        cardColumns = new ArrayList<>();
        donePiles = new ArrayList<>();
        drawPile = new Stack<>();
    }

    public List<Card> createBoard(List<Card> deck) {
        for (int i = 1; i < 8; i++) {
            cardColumns.add(new Stack<Card>());
        }

        for (int i = 1; i < 5; i++) {
            donePiles.add(new Stack<Card>());
        }
        Random rand = new Random();
        int cardIdx;
        Card card;
        for (int i = 0; i < cardColumns.size(); i++) {
            for (int j = i; j < cardColumns.size(); j++) {
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

    @Override
    public String toString() {
        String cards = "Board columns: ";
        for (Stack<Card> stack : cardColumns) {
            for (Card card : stack) {
                cards = cards + card.toString();
            }
        }

        cards = cards + "Done piles: ";
        for (Stack<Card> stack : donePiles) {
            for (Card card : stack) {
                cards = cards + card.toString();
            }
        }

        cards = cards + " Draw pile: ";
        for (Card card : drawPile) {
            cards = cards + card.toString();
        }
        return cards;
    }
}
