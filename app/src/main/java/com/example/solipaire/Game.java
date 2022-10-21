package com.example.solipaire;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(foreignKeys = {@ForeignKey(
        entity = Account.class, parentColumns = "uid", childColumns = "account_id", onDelete = ForeignKey.CASCADE
), @ForeignKey(
        entity = Player.class, parentColumns = "pid", childColumns = "player1_id", onDelete = ForeignKey.CASCADE
), @ForeignKey(
        entity = Player.class, parentColumns = "pid", childColumns = "player2_id", onDelete = ForeignKey.CASCADE
)}, tableName = "game")
public class Game {

    @PrimaryKey(autoGenerate = true)
    public int gid;

    @ColumnInfo(name = "account_id")
    public int acctid;

    @ColumnInfo(name = "player1_id")
    public String p1id;

    @ColumnInfo(name = "player2_id")
    public String p2id;

    @ColumnInfo
    public Board board;

    public Game () {
        acctid = -1;
        p1id = null;
        p2id = null;
        board = null;
    }
    public Game(int account_id, @NonNull String playerOne, @NonNull String playerTwo) {
        acctid = account_id;
        p1id = playerOne;
        p2id = playerTwo;
        board = new Board();
    }

    public int getId() {
        return gid;
    }

    public int getAccountId() {
        return acctid;
    }

    public Board getBoard() {
        return board;
    }

    public List<String> getPlayerIds() {
        List<String> pids = new ArrayList<>();
        pids.add(p1id);
        pids.add(p2id);
        return pids;
    }
}


