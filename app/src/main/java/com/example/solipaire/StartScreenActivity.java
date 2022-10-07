package com.example.solipaire;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

public class StartScreenActivity extends AppCompatActivity {


    protected Fragment createFragment(){
        return new StartScreenFragment();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(null,"StartScreenActivity onCreate() started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = new StartScreenFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
        Log.i(null,"StartScreenActivity onCreate() complete");
    }

    @Override
    public void onResume() {
        Log.i(null,"StartScreenActivity onResume() started");
        super.onResume();
        Log.i(null,"StartScreenActivity onResume() complete");
    }

    @Override
    public void onStart() {
        Log.i(null,"StartScreenActivity onStart() started");
        super.onStart();
        Log.i(null,"StartScreenActivity onStart() complete");
    }

    @Override
    public void onStop() {
        Log.i(null,"StartScreenActivity onStop() started");
        super.onStop();
        Log.i(null,"StartScreenActivity onStop() complete");
    }

    @Override
    public void onPause() {
        Log.i(null,"StartScreenActivity onPause() started");
        super.onPause();
        Log.i(null,"StartScreenActivity onPause() complete");
    }

    @Override
    public void onDestroy() {
        Log.i(null,"StartScreenActivity onDestroy() started");
        super.onDestroy();
        Log.i(null,"StartScreenActivity onDestroy() complete");
    }





}