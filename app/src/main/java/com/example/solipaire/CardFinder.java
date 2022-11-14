package com.example.solipaire;

import com.example.solipaire.data.Player;

import java.util.List;
import java.util.Stack;

public class CardFinder {

    public static Card findCard(Player activePlayer, Board board, float x, float y) {
        Card card = null;
        boolean cardFound = false;
        List<Card> hand = activePlayer.getHand();
        for (Card playerCard : hand) {
            if(compareTouch(playerCard, x, y) && !cardFound) {
                card = playerCard;
                cardFound = true;
            }
        }

        if (!cardFound) {
            card = findBoardCard(board, x, y);
        }

        return card;
    }

    public static Card findBoardCard(Board board, float x, float y) {
        Card card = null;
        boolean cardFound = false;
        List<List<Card>> cardColumns = board.getCardColumns();
        for (int i = 0; i < Board.NUMCOLUMNS; i++) {
            List<Card> cardColumn = cardColumns.get(i);
            for (int j = 0; j < cardColumn.size(); j++) {
                Card columnCard = cardColumn.get(j);
                if (columnCard != null && columnCard.isFlipped()) {
                    if (compareTouch(columnCard, x, y) && !cardFound) {
                        card = columnCard;
                        cardFound = true;
                    }
                }
            }
        }

        if (!cardFound) {
            card = findDoneCard(board.getDonePiles(), x, y);
            if (card == null) {
                card = findDrawCard(board.getDrawPile(), x, y);
            }
        }
        return card;
    }

    public static Card findDoneCard(List<Stack<Card>> donePiles, float x, float y) {
        Card card = null;
        boolean cardFound = false;
        for (int i = 0; i < Board.NUMPILES; i++) {
            if (!cardFound) {
                Stack<Card> cardPile = donePiles.get(i);
                if (cardPile.isEmpty()) {
                /*
                    switch (i) {
                        case 0:
                            card = new Card(Suit.Heart, 0);

                        case 1:
                        case 2:
                        case 3:
                    }

                 */
                } else {
                    if (compareTouch(cardPile.peek(), x, y)) {
                        card = cardPile.peek();
                        cardFound = true;
                    }
                }
            }
        }
        return card;
    }

    public static Card findDrawCard(Stack<Card> drawPile, float x, float y) {
        Card card = null;
        if (!drawPile.isEmpty()) {
            card = drawPile.peek();
            if (compareTouch(card, x, y)) {
                drawPile.pop();
                if (!drawPile.isEmpty()) {
                    drawPile.peek().setXLoc(card.getXLoc());
                    drawPile.peek().setYLoc(card.getYLoc());
                }
            } else {
                card = null;
            }
        }
        return card;
    }

    private static boolean compareTouch(Card card, float x, float y) {
        boolean isMatch = false;
        if (card.getXLoc() < x  && x < card.getXLoc() + Card.CARDWIDTH && card.getYLoc() < y && y < card.getYLoc() + Card.CARDHEIGHT) {
            isMatch = true;
        }
        return isMatch;
    }
}
