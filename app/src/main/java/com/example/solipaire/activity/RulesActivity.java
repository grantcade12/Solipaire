package com.example.solipaire.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.solipaire.R;
import com.example.solipaire.fragment.MenuFragment;
import com.example.solipaire.fragment.RulesFragment;

public class RulesActivity extends AppCompatActivity {


    protected Fragment createFragment(){
        return new MenuFragment();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(null,"RulesActivity onCreate() started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = new RulesFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
        Log.i(null,"RulesActivity onCreate() complete");
    }

    @Override
    public void onResume() {
        Log.i(null,"RulesActivity onResume() started");
        super.onResume();
        Log.i(null,"RulesActivity onResume() complete");
    }

    @Override
    public void onStart() {
        Log.i(null,"RulesActivity onStart() started");
        super.onStart();
        Log.i(null,"RulesActivity onStart() complete");
    }

    @Override
    public void onStop() {
        Log.i(null,"RulesActivity onStop() started");
        super.onStop();
        Log.i(null,"RulesActivity onStop() complete");
    }

    @Override
    public void onPause() {
        Log.i(null,"RulesActivity onPause() started");
        super.onPause();
        Log.i(null,"RulesActivity onPause() complete");
    }

    @Override
    public void onDestroy() {
        Log.i(null,"RulesActivity onDestroy() started");
        super.onDestroy();
        Log.i(null,"RulesActivity onDestroy() complete");
    }
}
