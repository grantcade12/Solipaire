package com.example.solipaire;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.solipaire.data.Player;

public class GameBoard extends View {

    private Board board;
    private Player player1;
    private Player player2;
    private float columnWidth;
    private float columnHeight;
    private Paint paint = new Paint();

    public GameBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setDither(true);
    }

    public void setComponents(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float canvasWidth = getWidth();
        float canvasHeight = getHeight();

        if (canvasWidth < canvasHeight) {
            canvasHeight = Math.min(canvasHeight, canvasWidth);
        }
        else {
            canvasWidth = Math.min(canvasHeight, canvasWidth);
        }

        float x = canvasWidth / Board.NUMCOLUMNS;

        for (int i = 0; i < Board.NUMCOLUMNS; i++) {
            int j = 0;
            float y = canvasHeight / 2;
            Card[][] cardColumns = board.getCardColumns();
            while(cardColumns[i][j] != null) {
                canvas.drawBitmap(cardColumns[i][j].getCardMap(), x, y, paint);
                y = y + (cardColumns[i][j].getCardMap().getHeight()/3);
                j++;
            }
            x = x + 100;
        }

        x = canvasWidth / Board.NUMCOLUMNS;
        for (int i = 0; i < player1.getHand().size(); i++) {
            Card card = player1.getHand().get(i);
            float y = canvasHeight + (2 * card.getCardMap().getHeight());
            canvas.drawBitmap(card.getCardMap(), x, y, paint);
            x = x + 100;
        }

        x = canvasWidth / Board.NUMCOLUMNS;
        for (int i = 0; i < player2.getHand().size(); i++) {
            float y = 0;
            canvas.drawBitmap(Card.cardBackMap, x, y, paint);
            x = x + 100;
        }
    }
}
