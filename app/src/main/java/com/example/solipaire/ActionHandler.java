package com.example.solipaire;

import android.widget.Toast;

import java.util.List;
import java.util.Stack;

public class ActionHandler {

    public static boolean placeHandCardOnColumn(Card handCard, List<Card> cardColumn) {
        boolean valid = false;
        Card bottom = cardColumn.get(cardColumn.size() - 1);
        if (CardHelper.compareCardsToStack(bottom, handCard)) {
            cardColumn.add(handCard);
            handCard.setXLoc(bottom.getXLoc());
            handCard.setYLoc(bottom.getYLoc() + (Card.CARDHEIGHT / 3));
            valid = true;
        }
        return valid;
    }

    public static boolean placeColumnCardOnColumn(int cardIdx, List<Card> fromColumn,List<Card> toColumn) {
        boolean valid = false;
        Card bottom = toColumn.get(toColumn.size() - 1);
        Card moveCard = fromColumn.get(cardIdx);
        if (CardHelper.compareCardsToStack(bottom, moveCard)) {
            while (cardIdx < fromColumn.size()) {
                toColumn.add(fromColumn.remove(cardIdx));
            }
            if (!fromColumn.isEmpty() && !fromColumn.get(fromColumn.size() - 1).isFlipped()) {
                fromColumn.get(fromColumn.size() - 1).flipCard();
            }
            valid = true;
        }
        return valid;
    }

    public static boolean placeHandCardOnEmptyColumn(List<Card> hand, Card card, List<List<Card>> columns, int columnIdx) {
        boolean valid = false;
        if (card.getValue() == 13) {
            if (columns.get(columnIdx).isEmpty()) {
                columns.get(columnIdx).add(card);
                hand.remove(card);
                valid = true;
            } else if (columns.get(columnIdx - 1).isEmpty()) {
                columns.get(columnIdx - 1).add(card);
                hand.remove(card);
                valid = true;
            } else if (columns.get(columnIdx + 1).isEmpty()) {
                columns.get(columnIdx + 1).add(card);
                hand.remove(card);
                valid = true;
            }
        }
        return valid;
    }

    public static boolean placeColumnCardOnEmptyColumn(List<Card> fromColumn, Card card, List<List<Card>> columns, int columnIdx) {
        boolean valid = false;
        int cardIdx = fromColumn.indexOf(card);
        if (card.getValue() == 13) {
            if (columns.get(columnIdx).isEmpty()) {
                while (cardIdx < fromColumn.size()) {
                    columns.get(columnIdx).add(fromColumn.remove(cardIdx));
                }
                if (!fromColumn.get(fromColumn.size() - 1).isFlipped()) {
                    fromColumn.get(fromColumn.size() - 1).flipCard();
                }
                valid = true;
            } else if (columns.get(columnIdx - 1).isEmpty()) {
                while (cardIdx < fromColumn.size()) {
                    columns.get(columnIdx - 1).add(fromColumn.remove(cardIdx));
                }
                if (!fromColumn.get(fromColumn.size() - 1).isFlipped()) {
                    fromColumn.get(fromColumn.size() - 1).flipCard();
                }
                valid = true;
            } else if (columns.get(columnIdx + 1).isEmpty()) {
                while (cardIdx < fromColumn.size()) {
                    columns.get(columnIdx + 1).add(fromColumn.remove(cardIdx));
                }
                if (!fromColumn.get(fromColumn.size() - 1).isFlipped()) {
                    fromColumn.get(fromColumn.size() - 1).flipCard();
                }
                valid = true;
            }
        }
        return valid;
    }

    public static boolean placeHandCardOnDonePile(List<Card> hand, Card card, Stack<Card> donePile, Suit suit) {
        boolean valid = false;
        if (donePile.isEmpty()) {
            if(card.getValue() == 1 && card.getSuit() == suit) {
                donePile.push(card);
                hand.remove(card);
                valid = true;
            }
        } else {
            if (CardHelper.compareCardsToPile(donePile.peek(), card)) {
                donePile.push(card);
                hand.remove(card);
                valid = true;
            }
        }
        return valid;
    }

    public static boolean placeColCardOnDonePile(List<Card> hand, Card card, Stack<Card> donePile, Suit suit) {
        boolean valid = false;
        if (donePile.isEmpty()) {
            if(card.getValue() == 1 && card.getSuit() == suit) {
                donePile.push(card);
                hand.remove(card);
                if (!hand.get(hand.size() - 1).isFlipped()) {
                    hand.get(hand.size() - 1).flipCard();
                }
                valid = true;
            }
        } else {
            if (CardHelper.compareCardsToPile(donePile.peek(), card)) {
                donePile.push(card);
                hand.remove(card);
                if (!hand.get(hand.size() - 1).isFlipped()) {
                    hand.get(hand.size() - 1).flipCard();
                }
                valid = true;
            }
        }
        return valid;
    }


}
