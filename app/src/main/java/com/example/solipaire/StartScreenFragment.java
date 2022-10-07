package com.example.solipaire;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class StartScreenFragment extends Fragment implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(null,"StartScreenFragment onCreate() started");
        super.onCreate(savedInstanceState);
        Activity activity = requireActivity();
        Log.i(null,"StartScreenFragment onCreate() complete");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Log.i(null,"StartScreenFragment onCreateView() started");
        View v;
        Activity activity = requireActivity();
        v = inflater.inflate(R.layout.fragment_startscreen, container, false);

        final Button createAccountButton = v.findViewById(R.id.SignUpButton);
        if (createAccountButton != null) {
            createAccountButton.setOnClickListener(this);
        }

        final Button loginButton = v.findViewById(R.id.LogInButton);
        if (loginButton != null) {
            loginButton.setOnClickListener(this);
        }
        Log.i(null,"StartScreenFragment onCreateView() complete");
        return v;
    }

    @Override
    public void onResume() {
        Log.i(null,"StartScreenFragment onResume() started");
        super.onResume();
        Log.i(null,"StartScreenFragment onResume() complete");
    }

    @Override
    public void onStart() {
        Log.i(null,"StartScreenFragment onStart() started");
        super.onStart();
        Log.i(null,"StartScreenFragment onStart() complete");
    }

    @Override
    public void onStop() {
        Log.i(null,"StartScreenFragment onStop() started");
        super.onStop();
        Log.i(null,"StartScreenFragment onStop() complete");
    }

    @Override
    public void onPause() {
        Log.i(null,"StartScreenFragment onPause() started");
        super.onPause();
        Log.i(null,"StartScreenFragment onPause() complete");
    }

    @Override
    public void onDestroy() {
        Log.i(null,"StartScreenFragment onDestroy() started");
        super.onDestroy();
        Log.i(null,"StartScreenFragment onDestroy() complete");
    }

    @Override
    public void onDestroyView() {
        Log.i(null,"StartScreenFragment onDestroyView() started");
        super.onDestroyView();
        Log.i(null, "StartScreenFragment onDestroyView() complete");
    }

    @Override
    public void onClick(View view) {
        Log.i(null,"StartScreenFragment onClick() started");
        final Activity activity = requireActivity();
        final int viewId = view.getId();
        if (viewId == R.id.LogInButton){
            //GO TO LOGIN SCREEN, DO NOTHING FOR NOW
            Log.i(null,"StartScreenFragment onClick() LogInButton clicked");
        } else if (viewId == R.id.SignUpButton){
            //GO TO CREATE ACCOUNT SCREEN, DO NOTHING FOR NOW
            Log.i(null,"StartScreenFragment onClick() SignUpButton clicked");
        }
        Log.i(null,"StartScreenFragment onClick() started");
    }
}