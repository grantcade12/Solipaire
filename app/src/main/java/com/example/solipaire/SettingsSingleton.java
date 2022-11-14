package com.example.solipaire;

public class SettingsSingleton {
    private static SettingsSingleton instance = null;

    public CardColor cardColor;
    public TableColor tableColor;
    public String displayName;
    public int userId;
    public static boolean music;
    public String musicUriString;

    private SettingsSingleton(){
        cardColor = CardColor.RED;
        tableColor = TableColor.GREEN;
        displayName = "";
        userId = 1;
        music = true;
        musicUriString = "ERROR";

    }

    public static SettingsSingleton SettingsSingleton() {
        if (instance == null) {
            instance = new SettingsSingleton();
        }
        return instance;
    }


}
