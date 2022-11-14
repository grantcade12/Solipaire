package com.example.solipaire;

public class SettingsSingleton {
    private static SettingsSingleton instance = null;

    public CardColor cardColor;
    public TableColor tableColor;
    public String displayName;
    public int userId;
    public boolean music;

    private SettingsSingleton(){
        cardColor = CardColor.RED;
        tableColor = TableColor.GREEN;
        displayName = "";
        userId = 1;
        music=true;
    }

    public static SettingsSingleton SettingsSingleton() {
        if (instance == null) {
            instance = new SettingsSingleton();
        }

        return instance;
    }

}
