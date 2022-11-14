package com.example.solipaire;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;

public class CardHelper {
    private static Resources res;

    public static Bitmap getBitMap(Suit suit, int value) {
        Bitmap cardMap;
        if (suit == Suit.Club) {
            cardMap = getClubMap(value);
        }
        else if (suit == Suit.Diamond) {
            cardMap = getDiamondMap(value);
        }
        else if (suit == Suit.Heart) {
            cardMap = getHeartMap(value);
        }
        else {
            cardMap = getSpadeMap(value);
        }
        return cardMap;
    }

    public static Bitmap getCardBackMap() {
        SettingsSingleton s = SettingsSingleton.SettingsSingleton();
        switch (s.cardColor) {
            case BLUE:
                return BitmapFactory.decodeResource(res, R.drawable.zcardback);
            case PURPLE:
                return BitmapFactory.decodeResource(res, R.drawable.zcardbackpurple);
            case GREEN:
                return BitmapFactory.decodeResource(res, R.drawable.zcardbackgreen);
            case BLACK:
                return BitmapFactory.decodeResource(res, R.drawable.zcardback2);
            default:
                return BitmapFactory.decodeResource(res, R.drawable.zcardbackred);
        }
    }

    public static void setResources(Resources resources) {
        if (res == null) {
            res = resources;
        }
    }

    public static String getStringSuit(Suit suit){
        String strSuit;
        if (suit == Suit.Club) {
            strSuit = "club";
        }
        else if (suit == Suit.Diamond) {
            strSuit = "diamond";
        }
        else if (suit == Suit.Heart) {
            strSuit = "heart";
        }
        else {
            strSuit = "spade";
        }
        return strSuit;
    }

    public static String getStringValue(int value) {
        String strValue = "";
        switch(value) {
            case 1:
                strValue = "ace";
                break;
            case 2:
                strValue = "2";
                break;
            case 3:
                strValue = "3";
                break;
            case 4:
                strValue = "4";
                break;
            case 5:
                strValue = "5";
                break;
            case 6:
                strValue = "6";
                break;
            case 7:
                strValue = "7";
                break;
            case 8:
                strValue = "8";
                break;
            case 9:
                strValue = "9";
                break;
            case 10:
                strValue = "10";
                break;
            case 11:
                strValue = "jack";
                break;
            case 12:
                strValue = "queen";
                break;
            case 13:
                strValue = "king";
                break;
        }
        return strValue;
    }

    public static boolean compareCardsToStack(Card boardCard, Card handCard) {
        boolean canPlace = false;
        canPlace = compareSuits(boardCard, handCard, false);
        if (canPlace) {
            canPlace = compareValue(boardCard, handCard, true);
        }
        return canPlace;
    }

    public static boolean compareCardsToPile(Card pileCard, Card playCard) {
        boolean canPlace = false;
        canPlace = compareSuits(pileCard, playCard, true);
        if (canPlace) {
            canPlace = compareValue(pileCard, playCard, false);
        }
        return canPlace;
    }

    private static Bitmap getClubMap(int value) {
        Bitmap cardMap = null;
        switch (value) {
            case 1:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.clu_a);
                break;
            case 2:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.clu_2);
                break;
            case 3:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.clu_3);
                break;
            case 4:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.clu_4);
                break;
            case 5:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.clu_5);
                break;
            case 6:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.clu_6);
                break;
            case 7:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.clu_7);
                break;
            case 8:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.clu_8);
                break;
            case 9:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.clu_9);
                break;
            case 10:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.clu_10);
                break;
            case 11:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.clu_j);
                break;
            case 12:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.clu_q);
                break;
            case 13:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.clu_k);
                break;
            default:
                break;
        }
        return cardMap;
    }

    private static Bitmap getDiamondMap(int value) {
        Bitmap cardMap = null;
        switch (value) {
            case 1:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.dia_a);
                break;
            case 2:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.dia_2);
                break;
            case 3:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.dia_3);
                break;
            case 4:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.dia_4);
                break;
            case 5:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.dia_5);
                break;
            case 6:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.dia_6);
                break;
            case 7:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.dia_7);
                break;
            case 8:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.dia_8);
                break;
            case 9:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.dia_9);
                break;
            case 10:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.dia_10);
                break;
            case 11:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.dia_j);
                break;
            case 12:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.dia_q);
                break;
            case 13:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.dia_k);
                break;
            default:
                break;
        }
        return cardMap;
    }

    private static Bitmap getHeartMap(int value) {
        Bitmap cardMap = null;
        switch (value) {
            case 1:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.hea_a);
                break;
            case 2:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.hea_2);
                break;
            case 3:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.hea_3);
                break;
            case 4:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.hea_4);
                break;
            case 5:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.hea_5);
                break;
            case 6:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.hea_6);
                break;
            case 7:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.hea_7);
                break;
            case 8:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.hea_8);
                break;
            case 9:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.hea_9);
                break;
            case 10:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.hea_10);
                break;
            case 11:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.hea_j);
                break;
            case 12:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.hea_q);
                break;
            case 13:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.hea_k);
                break;
            default:
                break;
        }
        return cardMap;
    }

    private static Bitmap getSpadeMap(int value) {
        Bitmap cardMap = null;
        switch (value) {
            case 1:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.spa_a);
                break;
            case 2:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.spa_2);
                break;
            case 3:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.spa_3);
                break;
            case 4:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.spa_4);
                break;
            case 5:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.spa_5);
                break;
            case 6:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.spa_6);
                break;
            case 7:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.spa_7);
                break;
            case 8:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.spa_8);
                break;
            case 9:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.spa_9);
                break;
            case 10:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.spa_10);
                break;
            case 11:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.spa_j);
                break;
            case 12:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.spa_q);
                break;
            case 13:
                cardMap = BitmapFactory.decodeResource(res, R.drawable.spa_k);
                break;
            default:
                break;
        }
        return cardMap;
    }

    private static boolean compareSuits(Card boardCard, Card handCard, boolean sameSuit) {
        boolean suitMatch = false;
        if (sameSuit) {
            suitMatch = boardCard.getSuit() == handCard.getSuit();
        }
        else {
            if ((boardCard.getSuit() == Suit.Club || boardCard.getSuit() == Suit.Spade) && (handCard.getSuit() == Suit.Diamond || handCard.getSuit() == Suit.Heart)) {
                suitMatch = true;
            }
            else if ((boardCard.getSuit() == Suit.Diamond || boardCard.getSuit() == Suit.Heart) && (handCard.getSuit() == Suit.Spade || handCard.getSuit() == Suit.Club)) {
                suitMatch = true;
            }
        }
        return suitMatch;
    }

    private static boolean compareValue(Card boardCard, Card handCard, boolean lowerValue) {
        boolean valMatch;
        if (lowerValue) {
            valMatch = handCard.getValue() < boardCard.getValue();
        }
        else {
            valMatch = handCard.getValue() > boardCard.getValue();
        }
        return valMatch;
    }
}
