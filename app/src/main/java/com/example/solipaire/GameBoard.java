package com.example.solipaire;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
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
    private boolean transitioning, moveMade, gameOver, moving;
    private Card pickedCard, placedCard;
    private Player activePlayer, waitingPlayer;
    private Paint paint = new Paint();
    private Paint background = new Paint();
    private GameActivity gameActivity;
    private int passCounter, maxColumn = 7;
    private float screenHeight, screenWidth;
    private boolean portrait = true;
    public static float drawX, deltaX, stackStartX, stackEndX, hX, dX, cX, sX, dPileX, activeStartX, activeEndX, passStartX, passEndX,
            drawY, deltaY, stackStartY, stackEndY, doneY, activeY, passStartY, passEndY, hY, dY, sY, cY;

    public GameBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setDither(true);
        gameActivity = (GameActivity) context;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        gameActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels;
        screenWidth = displayMetrics.widthPixels;
        if (screenWidth > screenHeight) {
            portrait = false;
        }
    }

    public void setComponents(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        activePlayer = this.player1;
        waitingPlayer = this.player2;
    }

    public void updateScreenSize(boolean portrait) {
        if (portrait != this.portrait) {
            this.portrait = portrait;
            float temp = screenHeight;
            screenHeight = screenWidth;
            screenWidth = temp;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!gameOver) {
            //Draw Transition Screen
            if (transitioning) {
                background.setColor(Color.BLACK);
                canvas.drawRect(0, 0, screenWidth, screenHeight, background);
                background.setColor(Color.WHITE);
                canvas.drawText("Player " + activePlayer.getId() + " Turn", 0.3f * screenWidth, 0.4f * screenHeight, background);
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


                //Draw background
                canvas.drawRect(0, 0, screenWidth, screenHeight, background);

                background.setColor(Color.BLACK);
                if (portrait) {
                    drawPortrait(canvas);
                } else {
                    drawLandScape(canvas);
                }

                if (pickedCard != null && pickedCard.isFlipped()) {
                    canvas.drawBitmap(pickedCard.getCardMap(), pickedCard.getXLoc(), pickedCard.getYLoc(), background);
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
                    pickedCard = CardFinder.findCard(activePlayer, board, x, y);
                    return true;
                case MotionEvent.ACTION_MOVE:
                    float x2 = event.getX();
                    float y2 = event.getY();
                    if (pickedCard != null) {
                        pickedCard.setXLoc(x2);
                        pickedCard.setYLoc(y2);
                        invalidate();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    x2 = event.getX();
                    y2 = event.getY();
                    if (pickedCard != null) {
                        //Did not pick a draw card
                        if (pickedCard.isFlipped()) {
                            placedCard = CardFinder.findBoardCard(board, x2, y2);
                            //Placed card was a card on the board
                            if (placedCard != null) {
                                if (board.getCardColumns().toString().contains(placedCard.toString())) {
                                    int column = (int) ((placedCard.getXLoc() - stackStartX) / Card.CARDWIDTH);
                                    List<Card> cardColumn = board.getCardColumns().get(column);
                                    if (activePlayer.getHand().contains(pickedCard)) {
                                        moveMade = ActionHandler.placeHandCardOnColumn(pickedCard, cardColumn);
                                        if (moveMade) {
                                            activePlayer.removeCard(pickedCard);
                                        } else {
                                            Toast.makeText(gameActivity.getApplicationContext(), "Invalid Move", Toast.LENGTH_SHORT).show();
                                        }
                                    } else if (board.getCardColumns().toString().contains(pickedCard.toString())) {
                                        List<Card> fromCardColumn =  board.getCardColumns().get(CardFinder.findCardInColumn(board.getCardColumns(), pickedCard));
                                        int cardIdx =fromCardColumn.indexOf(pickedCard);
                                        moveMade = ActionHandler.placeColumnCardOnColumn(cardIdx, fromCardColumn, cardColumn);
                                        if (!moveMade) {
                                            Toast.makeText(gameActivity.getApplicationContext(), "Invalid Move", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            } else {
                                //Placing King card on an empty stack
                                if (y2 >= stackStartY && x2 >= stackStartX && x2 <= stackEndX) {
                                    int cardIdx = (int) ((x2 - stackStartX) / Card.CARDWIDTH);
                                    if (cardIdx >= 0) {
                                        if (activePlayer.getHand().contains(pickedCard)) {
                                            moveMade = ActionHandler.placeHandCardOnEmptyColumn(activePlayer.getHand(), pickedCard, board.getCardColumns(), cardIdx);
                                        } else {
                                            int fromCardIdx = CardFinder.findCardInColumn(board.getCardColumns(), pickedCard);
                                            moveMade = ActionHandler.placeColumnCardOnEmptyColumn(board.getCardColumns().get(fromCardIdx), pickedCard, board.getCardColumns(), cardIdx);
                                        }
                                    }
                                //Placing card on a done pile
                                } else {
                                    //placing card on heart pile
                                    if (x2 >= hX && x2 < hX + Card.CARDWIDTH && y2 <= hY + Card.CARDHEIGHT) {
                                        //From player hand
                                        if (activePlayer.getHand().contains(pickedCard)) {
                                            moveMade = ActionHandler.placeHandCardOnDonePile(activePlayer.getHand(), pickedCard, board.getDonePiles().get(0), Suit.Heart);
                                        //From board
                                        } else {
                                            int fromCardIdx = CardFinder.findCardInColumn(board.getCardColumns(), pickedCard);
                                            moveMade = ActionHandler.placeColCardOnDonePile(board.getCardColumns().get(fromCardIdx), pickedCard, board.getDonePiles().get(0), Suit.Heart);
                                        }
                                    //Placing on diamonds pile
                                    } else if (x2 >= dX && x2 < dX + Card.CARDWIDTH && y2 <= dY + Card.CARDHEIGHT) {
                                        //From player hand
                                        if (activePlayer.getHand().contains(pickedCard)) {
                                            moveMade = ActionHandler.placeHandCardOnDonePile(activePlayer.getHand(), pickedCard, board.getDonePiles().get(1), Suit.Diamond);
                                        //From board
                                        } else {
                                            int fromCardIdx = CardFinder.findCardInColumn(board.getCardColumns(), pickedCard);
                                            moveMade = ActionHandler.placeColCardOnDonePile(board.getCardColumns().get(fromCardIdx), pickedCard, board.getDonePiles().get(1), Suit.Diamond);
                                        }
                                    //Placing on spades pile
                                    } else if (x2 >= sX && x2 < sX + Card.CARDWIDTH && y2 <= sY + Card.CARDHEIGHT) {
                                        //From player hand
                                        if (activePlayer.getHand().contains(pickedCard)) {
                                            moveMade = ActionHandler.placeHandCardOnDonePile(activePlayer.getHand(), pickedCard, board.getDonePiles().get(2), Suit.Spade);
                                        //From board
                                        } else {
                                            int fromCardIdx = CardFinder.findCardInColumn(board.getCardColumns(), pickedCard);
                                            moveMade = ActionHandler.placeColCardOnDonePile(board.getCardColumns().get(fromCardIdx), pickedCard, board.getDonePiles().get(2), Suit.Spade);
                                        }
                                    //Placing on clubs pile
                                    } else if (x2 >= cX && x2 < cX + Card.CARDWIDTH && y2 <= cY + Card.CARDHEIGHT) {
                                        //From player hand
                                        if (activePlayer.getHand().contains(pickedCard)) {
                                            moveMade = ActionHandler.placeHandCardOnDonePile(activePlayer.getHand(), pickedCard, board.getDonePiles().get(3), Suit.Club);
                                        //From board
                                        } else {
                                            int fromCardIdx = CardFinder.findCardInColumn(board.getCardColumns(), pickedCard);
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
                        //Check to reset pass counter
                        if (moveMade) {
                            passCounter = 0;
                        }
                    }

                    //Check if pass has been clicked
                    else {
                        if (x2 <= passEndX && x2 >= passStartX && y2 <= passEndY && y2 >= passStartY) {
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

    private void drawPortrait(Canvas canvas) {
        //Draw Waiting Hand
        deltaX = Card.CARDWIDTH;
        drawX = GameLayoutHelper.findDrawX(screenWidth, Card.CARDWIDTH, waitingPlayer.getHand().size());
        if (drawX < 0) {
            deltaX = -1 * drawX;
            drawX = 0;
        }
        for (int i = 0; i < waitingPlayer.getHand().size(); i++) {
            canvas.drawBitmap(Card.cardBackMap, drawX + (i * deltaX), screenHeight - screenHeight, paint);
        }

        //Draw Done Piles
        drawY = Card.CARDHEIGHT * 1.5f;
        doneY = drawY;
        drawX = 0.1f * screenWidth - (Card.CARDWIDTH / 2);
        deltaX = (0.8f * screenWidth) / 5;
        List<Stack<Card>> donePiles = board.getDonePiles();
        for (int i = 0; i < board.NUMPILES; i++) {
            Stack<Card> donePile = donePiles.get(i);
            if (donePile.isEmpty()) {
                if (i == 0) {
                    //Change to Heart pile
                    canvas.drawBitmap(Card.heartMap, drawX, drawY, paint);
                    hX = drawX;
                    hY = drawY;
                } else if (i == 1) {
                    //Change to Diamond pile
                    canvas.drawBitmap(Card.diamondMap, drawX + (deltaX * i), drawY, paint);
                    dX = drawX + (deltaX * i);
                    dY = drawY;
                } else if (i == 2) {
                    //Change to Spade pile
                    canvas.drawBitmap(Card.spadeMap, drawX + (deltaX * i), drawY, paint);
                    sX = drawX + (deltaX * i);
                    sY = drawY;
                } else {
                    //Change to Club pile
                    canvas.drawBitmap(Card.clubMap, drawX + (deltaX * i), drawY, paint);
                    cX = drawX + (deltaX * i);
                    cY = drawY;
                }
            } else {
                Card card = donePile.peek();
                if (i == 0) {
                    canvas.drawBitmap(card.getCardMap(), drawX + (deltaX * i), drawY, paint);
                } else if (i == 1) {
                    canvas.drawBitmap(card.getCardMap(), drawX + (deltaX * i), drawY, paint);
                } else if (i == 2) {
                    canvas.drawBitmap(card.getCardMap(), drawX + (deltaX * i), drawY, paint);
                } else {
                    canvas.drawBitmap(card.getCardMap(), drawX + (deltaX * i), drawY, paint);
                }
            }
        }

        //Draw the drawpile if it exists
        if (!board.getDrawPile().isEmpty()) {
            Card drawableCard = board.getDrawPile().peek();
            canvas.drawBitmap(drawableCard.getCardMap(), drawX + (deltaX * 4), drawY, paint);
            dPileX = drawX + (deltaX * 4);
            drawableCard.setXLoc(drawX + (deltaX * 4));
            drawableCard.setYLoc(drawY);
        }

        //Draw Board
        drawX = GameLayoutHelper.findDrawX(screenWidth, Card.CARDWIDTH, 7);
        stackStartX = drawX;
        deltaX = Card.CARDWIDTH;
        drawY = drawY + (Card.CARDHEIGHT * 1.2f);
        stackStartY = drawY;
        maxColumn = GameLayoutHelper.getMaxColumn(board.getCardColumns());
        deltaY = ((0.7f * screenHeight) - drawY) / maxColumn;
        List<List<Card>> cardColumns = board.getCardColumns();
        for (int i = 0; i < Board.NUMCOLUMNS; i++) {
            List<Card> cardColumn = cardColumns.get(i);
            for (int j = 0; j < cardColumn.size(); j++) {
                if (cardColumn.get(j) != pickedCard) {
                    canvas.drawBitmap(cardColumn.get(j).getCardMap(), drawX + (deltaX * i), drawY + (deltaY * j), paint);
                    cardColumn.get(j).setXLoc(drawX + (deltaX * i));
                    cardColumn.get(j).setYLoc(drawY + (deltaY * j));
                    if (drawY + (deltaY * j) + Card.CARDHEIGHT > stackEndY) {
                        stackEndY = drawY + (deltaY * j) + Card.CARDHEIGHT;
                    }
                }
            }
            stackEndX = drawX + (deltaX * i) + Card.CARDWIDTH;
        }

        //Draw Active Player Hand
        deltaX = Card.CARDWIDTH;
        drawX = GameLayoutHelper.findDrawX(screenWidth, Card.CARDWIDTH, activePlayer.getHand().size());
        if (drawX < 0) {
            deltaX = -1 * drawX;
            drawX = 0;
        }
        activeStartX = drawX;
        drawY = 0.75f * screenHeight;
        activeY = drawY;
        for (int i = 0; i < activePlayer.getHand().size(); i++) {
            Card card = activePlayer.getHand().get(i);
            if (card != pickedCard) {
                canvas.drawBitmap(card.getCardMap(), drawX + (deltaX * i), drawY, paint);
                card.setXLoc(drawX + (deltaX * i));
                card.setYLoc(drawY);
                activeEndX = drawX + (deltaX * i);
            }
        }

        //PassButton
        passStartX = 0.25f * screenWidth;
        passStartY = drawY + (Card.CARDHEIGHT * 1.5f);
        passEndX = 0.75f * screenWidth;
        passEndY = screenHeight;
        canvas.drawRect(passStartX, passStartY, passEndX, passEndY, background);
        background.setColor(Color.WHITE);
        background.setTextSize(75);
        canvas.drawText("Pass", screenWidth / 3,0.95f * screenHeight, background);
    }

    private void drawLandScape(Canvas canvas) {

        //Draw Waiting Hand
        deltaX = Card.CARDWIDTH;
        drawX = GameLayoutHelper.findDrawX(screenWidth, Card.CARDWIDTH, waitingPlayer.getHand().size());
        if (drawX < 0) {
            deltaX = -1 * drawX;
            drawX = 0;
        }
        drawY = -1 * Card.CARDHEIGHT / 2;
        for (int i = 0; i < waitingPlayer.getHand().size(); i++) {
            canvas.drawBitmap(Card.cardBackMap, drawX + (i * deltaX), drawY, paint);
        }
        stackStartY = Card.CARDHEIGHT * 1.2f + drawY;

        //Draw Active Player Hand
        deltaX = Card.CARDWIDTH;
        drawX = GameLayoutHelper.findDrawX(screenWidth, Card.CARDWIDTH, activePlayer.getHand().size());
        if (drawX < 0) {
            deltaX = -1 * drawX;
            drawX = 0;
        }
        activeStartX = drawX;
        drawY = screenHeight - (1.5f * Card.CARDHEIGHT);
        activeY = drawY;
        for (int i = 0; i < activePlayer.getHand().size(); i++) {
            Card card = activePlayer.getHand().get(i);
            if (card != pickedCard) {
                canvas.drawBitmap(card.getCardMap(), drawX + (deltaX * i), drawY, paint);
                card.setXLoc(drawX + (deltaX * i));
                card.setYLoc(drawY);
                activeEndX = drawX + (deltaX * i);
            }
        }

        //Draw Board
        drawX = GameLayoutHelper.findDrawX(screenWidth, Card.CARDWIDTH, 7);
        stackStartX = drawX;
        deltaX = Card.CARDWIDTH;
        drawY = stackStartY;
        maxColumn = GameLayoutHelper.getMaxColumn(board.getCardColumns());
        deltaY = (activeY - drawY - Card.CARDHEIGHT) / maxColumn;
        List<List<Card>> cardColumns = board.getCardColumns();
        for (int i = 0; i < Board.NUMCOLUMNS; i++) {
            List<Card> cardColumn = cardColumns.get(i);
            for (int j = 0; j < cardColumn.size(); j++) {
                if (cardColumn.get(j) != pickedCard) {
                    canvas.drawBitmap(cardColumn.get(j).getCardMap(), drawX + (deltaX * i), drawY + (deltaY * j), paint);
                    cardColumn.get(j).setXLoc(drawX + (deltaX * i));
                    cardColumn.get(j).setYLoc(drawY + (deltaY * j));
                    stackEndY = drawY + (deltaY * j) + Card.CARDHEIGHT;
                }
            }
            stackEndX = drawX + (deltaX * i) + Card.CARDWIDTH;
        }

        //Draw Done Piles
        drawY = Card.CARDHEIGHT * 1.5f;
        doneY = drawY;
        drawX = 0.1f * screenWidth - (Card.CARDWIDTH / 2);
        deltaX = (stackStartX - drawX - (2 * Card.CARDWIDTH)) / 2;
        deltaY = drawY;
        List<Stack<Card>> donePiles = board.getDonePiles();
        for (int i = 0; i < board.NUMPILES; i++) {
            Stack<Card> donePile = donePiles.get(i);
            if (donePile.isEmpty()) {
                if (i == 0) {
                    //Change to Heart pile
                    canvas.drawBitmap(Card.heartMap, drawX, drawY, paint);
                    hX = drawX;
                    hY = drawY;
                } else if (i == 1) {
                    //Change to Diamond pile
                    canvas.drawBitmap(Card.diamondMap, drawX + deltaX, drawY, paint);
                    dX = drawX + deltaX;
                    dY = drawY;
                } else if (i == 2) {
                    //Change to Spade pile
                    canvas.drawBitmap(Card.spadeMap, drawX, drawY + deltaY, paint);
                    sX = drawX;
                    sY = drawY + deltaY;
                } else {
                    //Change to Club pile
                    canvas.drawBitmap(Card.clubMap, drawX + deltaX, drawY + deltaY, paint);
                    cX = drawX + deltaX;
                    cY = drawY + deltaY;
                }
            } else {
                Card card = donePile.peek();
                if (i == 0) {
                    canvas.drawBitmap(card.getCardMap(), drawX, drawY, paint);
                } else if (i == 1) {
                    canvas.drawBitmap(card.getCardMap(), drawX + deltaX, drawY, paint);
                } else if (i == 2) {
                    canvas.drawBitmap(card.getCardMap(), drawX, drawY + deltaY, paint);
                } else {
                    canvas.drawBitmap(card.getCardMap(), drawX + deltaX, drawY + deltaY, paint);
                }
            }
        }

        //Draw the drawpile if it exists
        drawX = (screenWidth - stackEndX - Card.CARDWIDTH) / 2 + stackEndX;
        if (!board.getDrawPile().isEmpty()) {
            Card drawableCard = board.getDrawPile().peek();
            canvas.drawBitmap(drawableCard.getCardMap(), drawX, drawY, paint);
            dPileX = drawX;
            drawableCard.setXLoc(drawX);
            drawableCard.setYLoc(drawY);
        }

        //PassButton
        passStartX = drawX;
        passStartY = drawY + deltaY;
        passEndX = screenWidth;
        passEndY = passStartY + Card.CARDHEIGHT;
        canvas.drawRect(passStartX, passStartY, passEndX, passEndY, background);
        background.setColor(Color.WHITE);
        background.setTextSize(75);
        canvas.drawText("Pass", (passEndX - passStartX) / 4 + passStartX,(passEndY - passStartY) / 2 + passStartY, background);
    }
}
