package com.example.solipaire.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.solipaire.R;
import com.example.solipaire.fragment.MenuFragment;

public class FacebookActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(null,"MenuActivity onCreate() started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = new MenuFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
        Log.i(null,"MenuActivity onCreate() complete");
    }

    @Override
    public void onResume() {
        Log.i(null,"MenuActivity onResume() started");
        super.onResume();
        Log.i(null,"MenuActivity onResume() complete");
    }

    @Override
    public void onStart() {
        Log.i(null,"MenuActivity onStart() started");
        super.onStart();
        Log.i(null,"MenuActivity onStart() complete");
    }

    @Override
    public void onStop() {
        Log.i(null,"MenuActivity onStop() started");
        super.onStop();
        Log.i(null,"MenuActivity onStop() complete");
    }

    @Override
    public void onPause() {
        Log.i(null,"MenuActivity onPause() started");
        super.onPause();
        Log.i(null,"MenuActivity onPause() complete");
    }

    @Override
    public void onDestroy() {
        Log.i(null,"MenuActivity onDestroy() started");
        super.onDestroy();
        Log.i(null,"MenuActivity onDestroy() complete");
    }
}

