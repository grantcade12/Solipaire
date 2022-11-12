package com.example.solipaire.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.solipaire.Card;

import java.util.ArrayList;
import java.util.List;

@Entity(foreignKeys = {@ForeignKey(
        entity = Account.class, parentColumns = "uid", childColumns = "account_id", onDelete = ForeignKey.CASCADE
)})
public class Player {

    @PrimaryKey
    @NonNull
    public String pid;

    @ColumnInfo
    public int account_id;

    @ColumnInfo
    public int score;

    @ColumnInfo
    public List<Card> hand;

    public Player(@NonNull String pid, int account_id) {
        this.pid = pid;
        this.account_id = account_id;
        score = 0;
        hand = new ArrayList<>();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public Card removeCard() {
        return hand.remove(0);
    }

    public String getId() {
        return pid;
    }

    public int getScore() {
        return score;
    }

    public List<Card> getHand() {
        return hand;
    }

    @Override
    public String toString() {
        String cards = "Player " + pid + " cards:";
        for (Card card : hand) {
            cards = cards + card.toString();
        }
        return cards;
    }
}
