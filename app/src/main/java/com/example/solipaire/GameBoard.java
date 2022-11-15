package com.example.solipaire;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
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
    private boolean transitioning, moveMade, gameOver;
    private Card pickedCard, placedCard;
    private Player activePlayer, waitingPlayer;
    private Paint paint = new Paint();
    private Paint background = new Paint();
    private GameActivity gameActivity;
    private int passCounter;
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

        if (!gameOver) {
            if (transitioning) {
                background.setColor(Color.BLACK);
                canvas.drawRect(0, 0, 1000, 2000, background);
                background.setColor(Color.WHITE);
                canvas.drawText("Player " + activePlayer.getId() + " Turn", XOFFSET, STACKY, background);
            } else {

                SettingsSingleton s = SettingsSingleton.SettingsSingleton();
                switch (s.tableColor) {
                    case BLUE:
                        background.setColor(Color.rgb(0, 24, 204));
                        break;
                    case RED:
                        background.setColor(Color.rgb(97, 21, 21));
                        break;
                    case PURPLE:
                        background.setColor(Color.rgb(61, 3, 252));
                        break;
                    case WHITE:
                        background.setColor(Color.WHITE);
                        break;
                    default:
                        background.setColor(Color.rgb(53, 97, 21));
                        break;
                }


                canvas.drawRect(0, 0, 1000, 2000, background);

                background.setColor(Color.BLACK);
                canvas.drawRect(250, ACTIVEY + Card.CARDHEIGHT * 1.25f, 750, 2000, background);
                background.setColor(Color.WHITE);
                background.setTextSize(75);
                canvas.drawText("Pass", 420, 1780, background);

                float x;
                float y;

                List<List<Card>> cardColumns = board.getCardColumns();
                for (int i = 0; i < Board.NUMCOLUMNS; i++) {
                    y = STACKY;
                    List<Card> cardColumn = cardColumns.get(i);
                    for (int j = 0; j < cardColumn.size(); j++) {
                    /*
                    if(cardColumn.get(j).getXLoc() > 0f) {
                        x = cardColumn.get(j).getXLoc();
                        y = cardColumn.get(j).getYLoc();
                        canvas.drawBitmap(cardColumn.get(j).getCardMap(), x, y, paint);
                    } else {

                     */
                        canvas.drawBitmap(cardColumn.get(j).getCardMap(), XOFFSET + (DELTAX * i), y, paint);
                        cardColumn.get(j).setXLoc(XOFFSET + (DELTAX * i));
                        cardColumn.get(j).setYLoc(y);
                        //}
                        y = y + (Card.CARDHEIGHT / 3);
                    }
                }

                for (int i = 0; i < activePlayer.getHand().size(); i++) {
                    Card card = activePlayer.getHand().get(i);
                    canvas.drawBitmap(card.getCardMap(), XOFFSET + (DELTAX * i), ACTIVEY, paint);
                    card.setXLoc(XOFFSET + (DELTAX * i));
                    card.setYLoc(ACTIVEY);
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
                            canvas.drawBitmap(Card.heartMap, HX, DONEY, paint);
                        } else if (i == 1) {
                            //Change to Diamond pile
                            canvas.drawBitmap(Card.diamondMap, DX, DONEY, paint);
                        } else if (i == 2) {
                            //Change to Spade pile
                            canvas.drawBitmap(Card.spadeMap, SX, DONEY, paint);
                        } else {
                            //Change to Club pile
                            canvas.drawBitmap(Card.clubMap, CX, DONEY, paint);
                        }
                    } else {
                        Card card = donePile.peek();
                        if (i == 0) {
                            canvas.drawBitmap(card.getCardMap(), HX, DONEY, paint);
                        } else if (i == 1) {
                            canvas.drawBitmap(card.getCardMap(), DX, DONEY, paint);
                        } else if (i == 2) {
                            canvas.drawBitmap(card.getCardMap(), SX, DONEY, paint);
                        } else {
                            canvas.drawBitmap(card.getCardMap(), CX, DONEY, paint);
                        }
                    }
                }
            }
        }
        else {
            int p1Score = player1.getScore();
            int p2Score = player2.getScore();
            gameActivity.getActiveFragment().finishGame(p1Score, p2Score, player1.getId(), player2.getId());
        }
    }

    public boolean performClick() {
        return super.performClick();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (!transitioning) {
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    float x = event.getX();
                    float y = event.getY();
                    //Toast.makeText(gameActivity.getApplicationContext(), "Touched " + x + " " + y, Toast.LENGTH_SHORT).show();
                    pickedCard = CardFinder.findCard(activePlayer, board, x, y);
                    if (pickedCard != null) {
                        //Toast.makeText(gameActivity.getApplicationContext(), "Picked " + pickedCard, Toast.LENGTH_SHORT).show();
                    }
                    //Add check for pass button
                    return true;
                case MotionEvent.ACTION_UP:
                    float x2 = event.getX();
                    float y2 = event.getY();
                    if (pickedCard != null) {
                        if (pickedCard.isFlipped()) {
                            placedCard = CardFinder.findBoardCard(board, x2, y2);
                            if (placedCard != null) {
                                if (board.getCardColumns().toString().contains(placedCard.toString())) {
                                    int column = (int) ((placedCard.getXLoc() - XOFFSET) / DELTAX);
                                    List<Card> cardColumn = board.getCardColumns().get(column);
                                    if (activePlayer.getHand().contains(pickedCard)) {
                                        moveMade = ActionHandler.placeHandCardOnColumn(pickedCard, cardColumn);
                                        if (moveMade) {
                                            activePlayer.removeCard(pickedCard);
                                        } else {
                                            Toast.makeText(gameActivity.getApplicationContext(), "Invalid Move", Toast.LENGTH_SHORT).show();
                                        }
                                    } else if (board.getCardColumns().toString().contains(pickedCard.toString())) {
                                        int cardIdx = (int) ((pickedCard.getXLoc() - XOFFSET) / DELTAX);
                                        List<Card> fromCardColumn = board.getCardColumns().get(cardIdx);
                                        cardIdx = fromCardColumn.indexOf(pickedCard);
                                        moveMade = ActionHandler.placeColumnCardOnColumn(cardIdx, fromCardColumn, cardColumn);
                                        if (!moveMade) {
                                            Toast.makeText(gameActivity.getApplicationContext(), "Invalid Move", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            } else {
                                if (y2 >= STACKY) {
                                    int cardIdx = (int) ((x2 - XOFFSET) / DELTAX);
                                    if (cardIdx >= 0) {
                                        if (activePlayer.getHand().contains(pickedCard)) {
                                            moveMade = ActionHandler.placeHandCardOnEmptyColumn(activePlayer.getHand(), pickedCard, board.getCardColumns(), cardIdx);
                                        } else {
                                            int fromCardIdx = (int) ((pickedCard.getXLoc() - XOFFSET) / DELTAX);
                                            moveMade = ActionHandler.placeColumnCardOnEmptyColumn(board.getCardColumns().get(fromCardIdx), pickedCard, board.getCardColumns(), cardIdx);
                                        }
                                    }
                                } else if (y2 < STACKY && y2 >= DONEY) {
                                    if (x2 >= HX && x2 < DX) {
                                        if (activePlayer.getHand().contains(pickedCard)) {
                                            moveMade = ActionHandler.placeHandCardOnDonePile(activePlayer.getHand(), pickedCard, board.getDonePiles().get(0), Suit.Heart);
                                        } else {
                                            int fromCardIdx = (int) ((pickedCard.getXLoc() - XOFFSET) / DELTAX);
                                            moveMade = ActionHandler.placeColCardOnDonePile(board.getCardColumns().get(fromCardIdx), pickedCard, board.getDonePiles().get(0), Suit.Heart);
                                        }
                                    } else if (x2 < SX) {
                                        if (activePlayer.getHand().contains(pickedCard)) {
                                            moveMade = ActionHandler.placeHandCardOnDonePile(activePlayer.getHand(), pickedCard, board.getDonePiles().get(1), Suit.Diamond);
                                        } else {
                                            int fromCardIdx = (int) ((pickedCard.getXLoc() - XOFFSET) / DELTAX);
                                            moveMade = ActionHandler.placeColCardOnDonePile(board.getCardColumns().get(fromCardIdx), pickedCard, board.getDonePiles().get(1), Suit.Diamond);
                                        }
                                    } else if (x2 < CX) {
                                        if (activePlayer.getHand().contains(pickedCard)) {
                                            moveMade = ActionHandler.placeHandCardOnDonePile(activePlayer.getHand(), pickedCard, board.getDonePiles().get(2), Suit.Spade);
                                        } else {
                                            int fromCardIdx = (int) ((pickedCard.getXLoc() - XOFFSET) / DELTAX);
                                            moveMade = ActionHandler.placeColCardOnDonePile(board.getCardColumns().get(fromCardIdx), pickedCard, board.getDonePiles().get(2), Suit.Spade);
                                        }
                                    } else if (x2 < CX + XOFFSET) {
                                        if (activePlayer.getHand().contains(pickedCard)) {
                                            moveMade = ActionHandler.placeHandCardOnDonePile(activePlayer.getHand(), pickedCard, board.getDonePiles().get(3), Suit.Club);
                                        } else {
                                            int fromCardIdx = (int) ((pickedCard.getXLoc() - XOFFSET) / DELTAX);
                                            moveMade = ActionHandler.placeColCardOnDonePile(board.getCardColumns().get(fromCardIdx), pickedCard, board.getDonePiles().get(3), Suit.Club);
                                        }
                                    }
                                }
                            }
                            //Toast.makeText(gameActivity.getApplicationContext(), "Released " + x2 + " " + y2, Toast.LENGTH_SHORT).show();
                        }
                        //Card picked was from the draw deck so add it to the active players hand
                        else {
                            pickedCard.flipCard();
                            activePlayer.addCard(pickedCard);
                            moveMade = true;
                        }
                        if (moveMade) {
                            passCounter = 0;
                        }
                    }
                    else {
                        if (x2 <= 750 && x2 >= 250 && y2 <= 2000 && y2 >= ACTIVEY + Card.CARDHEIGHT * 1.25f) {
                            if (!board.getDrawPile().isEmpty()) {
                                Card card = board.getDrawPile().pop();
                                card.flipCard();
                                activePlayer.addCard(card);
                                passCounter = 0;
                            } else {
                                passCounter++;
                            }
                            moveMade = true;
                            if(passCounter == 2) {
                                gameOver = true;
                            }
                        }
                    }
                    //Reset card variables to track new presses
                    pickedCard = null;
                    placedCard = null;
                    if (moveMade) {
                        transitioning = true;
                        moveMade = false;
                        if (activePlayer.getHand().isEmpty()) {
                            gameOver = true;
                        }
                        Player temp = activePlayer;
                        activePlayer = waitingPlayer;
                        waitingPlayer = temp;
                        invalidate();
                    }
                    break;
            }
        }
        else {
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    return true;
                case MotionEvent.ACTION_UP:
                    transitioning = false;
                    invalidate();
                    break;
            }

        }
        return super.dispatchTouchEvent(event);
    }
}
