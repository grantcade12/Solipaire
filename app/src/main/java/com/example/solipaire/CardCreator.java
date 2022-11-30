package com.example.solipaire;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.solipaire.data.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class CardCreator {
    public static void distributeCards(List<Card> deck, Player p1, Player p2, Board board) {
        //List<Card> deck = createDeck();
        deck = board.createBoard(deck);
        Stack<Card> drawPile = dealStartingHands(p1, p2, deck);
        Bitmap bitmap = drawPile.peek().getCardMap();
        board.setDrawPile(drawPile);
        Log.i(null, p1.toString());
        Log.i(null, p2.toString());
        Log.i(null, board.toString());

    }

    private static List<Card> createDeck() {
        List<Card> deck = new ArrayList<Card>();
        Suit suit = Suit.Heart;
        for (int i = 1; i <= 4; i++) {
            if (i == 2) suit = Suit.Diamond;
            else if (i == 3) suit = Suit.Spade;
            else if (i == 4) suit = Suit.Club;
            for (int j = 1; j <= 13; j++) {
                deck.add(new Card(suit, j));
            }
        }
        return deck;
    }

    private static Stack<Card> dealStartingHands(Player player1, Player player2, List<Card> deck) {
        Stack<Card> stack = new Stack<Card>();
        Random rand = new Random();
        Card card;
        int cardIdx;
        for (int i = 1; i < 8; i++) {
            cardIdx = rand.nextInt(deck.size() - 1) + 1;
            card = deck.remove(cardIdx);
            card.flipCard();
            player1.getHand().add(card);
            cardIdx = rand.nextInt(deck.size() - 1) + 1;
            card = deck.remove(cardIdx);
            card.flipCard();
            player2.getHand().add(card);
        }

        for (int i = 0; i < deck.size(); i++) {
            stack.push(deck.get(i));
        }

        return stack;
    }
}
