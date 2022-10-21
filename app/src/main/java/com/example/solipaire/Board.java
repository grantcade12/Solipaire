package com.example.solipaire;

public class Board {
    private String board;
    //TODO: Add variables for tracking draw pile, solitaire stacks, and finished pile
    public Board() {
        board = "";
    }

    public Board(String string) {
        board = string;
    }

    @Override
    public String toString() {
        return board;
    }
}
