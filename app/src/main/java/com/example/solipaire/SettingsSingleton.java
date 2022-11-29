package com.example.solipaire;

public class SettingsSingleton {
    private static SettingsSingleton instance = null;

    public CardColor cardColor;
    public TableColor tableColor;
    public String displayName;
    public int userId;
    public boolean music;
    public String musicUriString;

    private SettingsSingleton(){
        cardColor = CardColor.RED;
        tableColor = TableColor.GREEN;

    }

    public static SettingsSingleton SettingsSingleton() {
        if (instance == null) {
            instance = new SettingsSingleton();
        }
        return instance;
    }


}
