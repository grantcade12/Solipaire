package com.example.solipaire.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
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

import com.example.solipaire.CardCreator;
import com.example.solipaire.GameBoard;
import com.example.solipaire.GameView;
import com.example.solipaire.R;
import com.example.solipaire.data.Game;
import com.example.solipaire.data.Player;
import com.example.solipaire.viewmodel.AccountViewModel;
import com.example.solipaire.viewmodel.GameViewModel;
import com.example.solipaire.viewmodel.PlayerViewModel;

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


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(null,"GameFragment onCreate() started");
        super.onCreate(savedInstanceState);
        Context context = requireContext();
        Activity activity = requireActivity();
        playerViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(PlayerViewModel.class);
        gameViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(GameViewModel.class);
        prefs = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
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

        createNewGame(v);

        Log.i(null,"GameFragment onCreateView() complete");
        return v;
    }

    private void createNewGame(View v) {
        gameBoard = v.findViewById(R.id.GameBoard);
        p1id = "a";
        p2id = "b";
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
}