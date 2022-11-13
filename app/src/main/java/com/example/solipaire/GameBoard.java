package com.example.solipaire;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.solipaire.activity.GameActivity;
import com.example.solipaire.data.Player;

import java.util.List;
import java.util.Stack;

public class GameBoard extends View {

    private Board board;
    private Player player1;
    private Player player2;
    private Player activePlayer, waitingPlayer;
    private Paint paint = new Paint();
    private GameActivity gameActivity;
    public static final float HX = 200f, DX = 350f, SX = 500f, CX = 650f, XOFFSET = 150f, DRAWX = 850f, DELTAX = 100f;
    public static final float DONEY = 250f, STACKY = 550f, ACTIVEY = 1500f, WAITY = 0f;

    public GameBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setDither(true);
        gameActivity = (GameActivity) context;
    }

    public void setComponents(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        activePlayer = this.player1;
        waitingPlayer = this.player2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float x;
        float y;

        for (int i = 0; i < Board.NUMCOLUMNS; i++) {
            int j = 0;
            y = STACKY;
            Card[][] cardColumns = board.getCardColumns();
            while(cardColumns[i][j] != null) {
                if(cardColumns[i][j].getXLoc() > 0f) {
                    x = cardColumns[i][j].getXLoc();
                    y = cardColumns[i][j].getYLoc();
                    canvas.drawBitmap(cardColumns[i][j].getCardMap(), x, y, paint);
                } else {
                    canvas.drawBitmap(cardColumns[i][j].getCardMap(), XOFFSET + (DELTAX * i), y, paint);
                    cardColumns[i][j].setXLoc(XOFFSET + (DELTAX * i));
                    cardColumns[i][j].setYLoc(y);
                }
                y = y + (Card.CARDHEIGHT/3);
                j++;
            }
        }

        for (int i = 0; i < activePlayer.getHand().size(); i++) {
            Card card = activePlayer.getHand().get(i);

            if(card.getXLoc() > 0f) {
                x = card.getXLoc();
                y = card.getYLoc();
                canvas.drawBitmap(card.getCardMap(), x, y, paint);
            } else {
                canvas.drawBitmap(card.getCardMap(), XOFFSET + (DELTAX * i), ACTIVEY, paint);
                card.setXLoc(XOFFSET + (DELTAX * i));
                card.setYLoc(ACTIVEY);
            }
        }

        for (int i = 0; i < waitingPlayer.getHand().size(); i++) {
            canvas.drawBitmap(Card.cardBackMap, XOFFSET + (i * DELTAX), WAITY, paint);
        }

        if (!board.getDrawPile().isEmpty()) {
            Card drawableCard = board.getDrawPile().peek();
            canvas.drawBitmap(drawableCard.getCardMap(), DRAWX, DONEY, paint);
            drawableCard.setXLoc(DRAWX);
            drawableCard.setYLoc(DONEY);
        }

        List<Stack<Card>> donePiles = board.getDonePiles();
        for (int i = 0; i < board.NUMPILES; i++) {
            Stack<Card> donePile = donePiles.get(i);
            if (donePile.isEmpty()) {
                if (i == 0) {
                    //Change to Heart pile
                    canvas.drawBitmap(Card.cardBackMap, HX, DONEY, paint);
                } else if (i == 1) {
                    //Change to Diamond pile
                    canvas.drawBitmap(Card.cardBackMap, DX, DONEY, paint);
                } else if (i == 2) {
                    //Change to Spade pile
                    canvas.drawBitmap(Card.cardBackMap, SX, DONEY, paint);
                } else {
                    //Change to Club pile
                    canvas.drawBitmap(Card.cardBackMap, CX, DONEY, paint);
                }
            } else {
                Card card = donePile.peek();
                canvas.drawBitmap(card.getCardMap(), card.getXLoc(), card.getYLoc(), paint);
            }
        }
    }

    public boolean performClick() {
        return super.performClick();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch(event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();
                Toast.makeText(gameActivity.getApplicationContext(), "Touched " + x + " " + y, Toast.LENGTH_SHORT).show();
                Card card = CardFinder.findCard(activePlayer, board, x, y);
                if (card != null) {
                    Toast.makeText(gameActivity.getApplicationContext(), "Picked " + card, Toast.LENGTH_SHORT).show();
                }
                return true;
            case MotionEvent.ACTION_UP:
                float x2 = event.getX();
                float y2 = event.getY();
                Toast.makeText(gameActivity.getApplicationContext(), "Released " + x2 + " " + y2, Toast.LENGTH_SHORT).show();
                CardFinder.findCard(activePlayer, board, x2, y2);
                break;
        }
        return super.dispatchTouchEvent(event);
    }
}
