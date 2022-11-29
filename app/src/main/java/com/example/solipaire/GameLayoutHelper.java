package com.example.solipaire;

import java.util.List;

public class GameLayoutHelper {

    public static float findDrawX(float screenWidth, float cardWidth, int numCards) {
        float drawX = (screenWidth - (cardWidth * numCards)) / 2;
        if (drawX < 0) {
            drawX = (screenWidth / numCards) * -1;
        }
        return drawX;
    }

    public static int getMaxColumn(List<List<Card>> cardColumns) {
        int maxColumn = 0;
        for (List<Card> cardColumn : cardColumns) {
            if (cardColumn.size() > maxColumn) {
                maxColumn = cardColumn.size();
            }
        }
        return maxColumn;
    }
}
