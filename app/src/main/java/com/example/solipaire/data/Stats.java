package com.example.solipaire.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.solipaire.Card;

import java.util.List;

@Entity(foreignKeys = {@ForeignKey(
        entity = Account.class, parentColumns = "uid", childColumns = "account_id", onDelete = ForeignKey.CASCADE
)})
public class Stats {

    @PrimaryKey
    @NonNull
    public String pid;

    @ColumnInfo
    public int account_id;

    @ColumnInfo
    public int score;

    @ColumnInfo
    public List<Card> hand;

    public String getId() {
        return pid;
    }

    public int getScore() {
        return score;
    }

    public List<Card> getHand() {
        return hand;
    }
}
