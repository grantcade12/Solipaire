package com.example.solipaire.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.solipaire.R;
import com.example.solipaire.data.Game;

public class GameFragment extends Fragment implements View.OnClickListener {

    private Game game;
    private String player1;
    private String player2;
    private SharedPreferences prefs;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(null,"GameFragment onCreate() started");
        super.onCreate(savedInstanceState);
        Context context = requireContext();
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
            String player1 = "a";
            String player2 = "b";
            int id = 0;
            game = new Game(prefs.getInt("Id", id), player1, player2);
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