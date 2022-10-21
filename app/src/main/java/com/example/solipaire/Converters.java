package com.example.solipaire;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.List;

public class Converters {
    @TypeConverter
    public Board StringToBoard(String string) {
        return new Board(string);
    }

    @TypeConverter
    public String BoardToString(Board board) {
        return board.toString();
    }

    @TypeConverter
    public List<Card> StringToHand(String string) {
        return new ArrayList<>();
    }

    @TypeConverter
    public String HandToString(List<Card> hand) {
        return "";
    }
}
