package com.example.solipaire;

public enum TableColor {
    GREEN, BLUE, RED, PURPLE, WHITE;
    public static TableColor toTableColor(String tableColorString){
        return valueOf(tableColorString);
    }
}
