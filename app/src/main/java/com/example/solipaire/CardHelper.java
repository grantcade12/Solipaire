package com.example.solipaire;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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
                return BitmapFactory.decodeResource(res, R.mipmap.zcardback);
            case PURPLE:
                return BitmapFactory.decodeResource(res, R.mipmap.zcardbackpurple);
            case GREEN:
                return BitmapFactory.decodeResource(res, R.mipmap.zcardbackgreen);
            case BLACK:
                return BitmapFactory.decodeResource(res, R.mipmap.zcardback2);
            default:
                return BitmapFactory.decodeResource(res, R.mipmap.zcardbackred);
        }
    }

    public static Bitmap getDoneMap(Suit suit) {
        switch (suit) {
            case Heart:
                return BitmapFactory.decodeResource(res, R.mipmap.hpile);
            case Diamond:
                return BitmapFactory.decodeResource(res, R.mipmap.dpile);
            case Spade:
                return BitmapFactory.decodeResource(res, R.mipmap.spile);
            default:
                return BitmapFactory.decodeResource(res, R.mipmap.cpile);
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
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.clu_a);
                break;
            case 2:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.clu_2);
                break;
            case 3:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.clu_3);
                break;
            case 4:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.clu_4);
                break;
            case 5:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.clu_5);
                break;
            case 6:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.clu_6);
                break;
            case 7:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.clu_7);
                break;
            case 8:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.clu_8);
                break;
            case 9:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.clu_9);
                break;
            case 10:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.clu_10);
                break;
            case 11:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.clu_j);
                break;
            case 12:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.clu_q);
                break;
            case 13:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.clu_k);
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
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.dia_a);
                break;
            case 2:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.dia_2);
                break;
            case 3:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.dia_3);
                break;
            case 4:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.dia_4);
                break;
            case 5:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.dia_5);
                break;
            case 6:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.dia_6);
                break;
            case 7:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.dia_7);
                break;
            case 8:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.dia_8);
                break;
            case 9:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.dia_9);
                break;
            case 10:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.dia_10);
                break;
            case 11:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.dia_j);
                break;
            case 12:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.dia_q);
                break;
            case 13:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.dia_k);
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
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.hea_a);
                break;
            case 2:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.hea_2);
                break;
            case 3:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.hea_3);
                break;
            case 4:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.hea_4);
                break;
            case 5:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.hea_5);
                break;
            case 6:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.hea_6);
                break;
            case 7:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.hea_7);
                break;
            case 8:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.hea_8);
                break;
            case 9:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.hea_9);
                break;
            case 10:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.hea_10);
                break;
            case 11:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.hea_j);
                break;
            case 12:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.hea_q);
                break;
            case 13:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.hea_k);
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
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.spa_a);
                break;
            case 2:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.spa_2);
                break;
            case 3:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.spa_3);
                break;
            case 4:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.spa_4);
                break;
            case 5:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.spa_5);
                break;
            case 6:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.spa_6);
                break;
            case 7:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.spa_7);
                break;
            case 8:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.spa_8);
                break;
            case 9:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.spa_9);
                break;
            case 10:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.spa_10);
                break;
            case 11:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.spa_j);
                break;
            case 12:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.spa_q);
                break;
            case 13:
                cardMap = BitmapFactory.decodeResource(res, R.mipmap.spa_k);
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
            valMatch = handCard.getValue() + 1 == boardCard.getValue();
        }
        else {
            valMatch = handCard.getValue() - 1 == boardCard.getValue();
        }
        return valMatch;
    }
}
