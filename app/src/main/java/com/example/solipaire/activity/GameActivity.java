package com.example.solipaire.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import com.example.solipaire.CardHelper;
import com.example.solipaire.R;
import com.example.solipaire.fragment.GameFragment;

public class GameActivity extends AppCompatActivity {

    protected Fragment createFragment(){
        return new GameFragment();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(null,"GameActivity onCreate() started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = new GameFragment();
            CardHelper.setResources(getResources());
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
        Log.i(null,"GameActivity onCreate() complete");
    }

    @Override
    public void onResume() {
        Log.i(null,"GameActivity onResume() started");
        super.onResume();
        Log.i(null,"GameActivity onResume() complete");
    }

    @Override
    public void onStart() {
        Log.i(null,"GameActivity onStart() started");
        super.onStart();
        Log.i(null,"GameActivity onStart() complete");
    }

    @Override
    public void onStop() {
        Log.i(null,"GameActivity onStop() started");
        super.onStop();
        Log.i(null,"GameActivity onStop() complete");
    }

    @Override
    public void onPause() {
        Log.i(null,"GameActivity onPause() started");
        super.onPause();
        Log.i(null,"GameActivity onPause() complete");
    }

    @Override
    public void onDestroy() {
        Log.i(null,"GameActivity onDestroy() started");
        super.onDestroy();
        Log.i(null,"GameActivity onDestroy() complete");
    }
}