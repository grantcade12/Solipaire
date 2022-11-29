package com.example.solipaire.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.solipaire.CardCreator;
import com.example.solipaire.GameBoard;
import com.example.solipaire.GameView;
import com.example.solipaire.R;
import com.example.solipaire.activity.GameActivity;
import com.example.solipaire.data.Game;
import com.example.solipaire.data.Player;
import com.example.solipaire.viewmodel.AccountViewModel;
import com.example.solipaire.viewmodel.GameViewModel;
import com.example.solipaire.viewmodel.PlayerViewModel;
import com.facebook.CallbackManager;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;

import java.util.Locale;

public class GameFragment extends Fragment implements View.OnClickListener {

    private Game game;
    private String p1id;
    private String p2id;
    private SharedPreferences prefs;
    private GameBoard gameBoard;
    private GameView gameView;
    private Bundle savedInstanceState;
    private GameViewModel gameViewModel;
    private PlayerViewModel playerViewModel;
    SharePhotoContent content;

    Bitmap image;

    CallbackManager callbackManager;
    ShareDialog shareDialog;

    Sensor sensor;
    SensorManager sensorManager;
    static boolean noAction = true;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(null,"GameFragment onCreate() started");
        super.onCreate(savedInstanceState);
        Context context = requireContext();
        Activity activity = requireActivity();
        image = BitmapFactory.decodeResource(getResources(),R.drawable.solipairelogo);
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        playerViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(PlayerViewModel.class);
        gameViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(GameViewModel.class);
        prefs = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        sensorManager = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_SIGNIFICANT_MOTION);
        Log.i(null,"GameFragment onCreate() complete");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Log.i(null,"GameFragment onCreateView() started");
        View v;
        Activity activity = requireActivity();
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        if (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270) {
            v = inflater.inflate(R.layout.fragment_game/*_landscape*/, container, false);
        } else {
            v = inflater.inflate(R.layout.fragment_game, container, false);
        }
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(image)
                .build();
        content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();
        ShareButton shareButton = v.findViewById(R.id.statsShareBtn);
        shareButton.setShareContent(content);

        createNewGame(v);

        Log.i(null,"GameFragment onCreateView() complete");
        return v;
    }

    private void createNewGame(View v) {
        gameBoard = v.findViewById(R.id.GameBoard);
        p1id = "1";
        p2id = "2";
        int id = 0;
        game = new Game(prefs.getInt("Id", id), p1id, p2id);
        Player player1 = new Player(p1id, prefs.getInt("Id", id));
        Player player2 = new Player(p2id, prefs.getInt("Id", id));
        gameViewModel.insert(game);
        playerViewModel.insert(player1);
        playerViewModel.insert(player2);
        CardCreator.generateCards(player1, player2, game.getBoard());
        gameBoard.setComponents(game.getBoard(), player1, player2);
        gameView = new GameView();
        gameView.setGameViewComponents(gameBoard);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            this.savedInstanceState = savedInstanceState;
        }
    }

    @Override
    public void onResume() {
        Log.i(null,"GameFragment onResume() started");
        super.onResume();
        Log.i(null,"GameFragment onResume() complete");
    }

    @Override
    public void onStart() {
        Log.i(null,"GameFragment onStart() started");
        super.onStart();
        Log.i(null,"GameFragment onStart() complete");
    }

    @Override
    public void onStop() {
        Log.i(null,"GameFragment onStop() started");
        super.onStop();
        Log.i(null,"GameFragment onStop() complete");
    }

    @Override
    public void onPause() {
        Log.i(null,"GameFragment onPause() started");
        super.onPause();
        Log.i(null,"GameFragment onPause() complete");
    }

    @Override
    public void onDestroy() {
        Log.i(null,"GameFragment onDestroy() started");
        super.onDestroy();
        Log.i(null,"GameFragment onDestroy() complete");
    }

    @Override
    public void onDestroyView() {
        Log.i(null,"GameFragment onDestroyView() started");
        super.onDestroyView();
        Log.i(null, "GameFragment onDestroyView() complete");
    }

    @Override
    public void onClick(View view) {
        Log.i(null,"GameFragment onClick() started");
        Log.i(null,"GameFragment onClick() finished");
    }

    public void finishGame(int p1Score, int p2Score, String p1Name, String p2Name) {
        String endMessage = saveResults(p1Score, p2Score, p1Name, p2Name);
        final Activity activity = requireActivity();
        new AlertDialog.Builder(activity)
                .setTitle(endMessage)
                .setMessage("Player " + p1Name + " Score: " + p1Score + "\n" + "Player " + p2Name + " Score: " + p2Score)
                .setNeutralButton("Share", (dialog, which) -> {
                    ShareDialog.show(activity, content);
                })
                .setNegativeButton("Exit", (dialog, which) -> {
                    activity.finish();
                })
                .show();

        TriggerEventListener triggerEventListener = new TriggerEventListener() {
            @Override
            public void onTrigger(TriggerEvent event) {
                Toast.makeText(activity.getApplicationContext(), "Starting new game", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(activity.getApplicationContext(), GameActivity.class));
            }
        };

        sensorManager.requestTriggerSensor(triggerEventListener, sensor);
    }

    private String saveResults(int p1Score, int p2Score, String p1Name, String p2Name) {
        String endMessage = "";
        SharedPreferences.Editor editor = prefs.edit();
        if (p1Score < p2Score) {
            int p1Won = prefs.getInt("gWonP1", 0) + 1;
            editor.putInt("gWonP1", p1Won);
            endMessage = "Player " + p1Name + " Wins";
        } else if (p2Score < p1Score) {
            int p2Won = prefs.getInt("gWonP2", 0) + 1;
            editor.putInt("gWonP2", p2Won);
            endMessage = "Player " + p2Name + " Wins";
        } else {
            endMessage = "Tie game";
        }
        int gamesPlayed = prefs.getInt("gPlayed", 0) + 1;
        p1Score = prefs.getInt("totalP1", 0) + p1Score;
        p2Score = prefs.getInt("totalP2", 0) + p2Score;
        editor.putInt("totalP1", p1Score);
        editor.putInt("totalP2", p2Score);
        editor.putInt("gPlayed", gamesPlayed);
        editor.apply();
        return endMessage;

    }

}