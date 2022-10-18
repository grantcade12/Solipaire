package com.example.solipaire;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class LoginFragment extends Fragment implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(null,"LoginFragment onCreate() started");
        super.onCreate(savedInstanceState);
        Activity activity = requireActivity();
        Log.i(null,"LoginFragment onCreate() complete");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Log.i(null,"LoginFragment onCreateView() started");
        View v;
        Activity activity = requireActivity();
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        if (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270) {
            v = inflater.inflate(R.layout.fragment_login/*_landscape*/, container, false);
        } else {
            v = inflater.inflate(R.layout.fragment_login, container, false);
        }

        final Button loginButton = v.findViewById(R.id.loginButton);
        if (loginButton != null) {
            loginButton.setOnClickListener(this);
        }
        Log.i(null,"LoginFragment onCreateView() complete");
        return v;
    }

    @Override
    public void onResume() {
        Log.i(null,"LoginFragment onResume() started");
        super.onResume();
        Log.i(null,"LoginFragment onResume() complete");
    }

    @Override
    public void onStart() {
        Log.i(null,"LoginFragment onStart() started");
        super.onStart();
        Log.i(null,"LoginFragment onStart() complete");
    }

    @Override
    public void onStop() {
        Log.i(null,"LoginFragment onStop() started");
        super.onStop();
        Log.i(null,"LoginFragment onStop() complete");
    }

    @Override
    public void onPause() {
        Log.i(null,"LoginFragment onPause() started");
        super.onPause();
        Log.i(null,"LoginFragment onPause() complete");
    }

    @Override
    public void onDestroy() {
        Log.i(null,"LoginFragment onDestroy() started");
        super.onDestroy();
        Log.i(null,"LoginFragment onDestroy() complete");
    }

    @Override
    public void onDestroyView() {
        Log.i(null,"LoginFragment onDestroyView() started");
        super.onDestroyView();
        Log.i(null, "LoginFragment onDestroyView() complete");
    }

    @Override
    public void onClick(View view) {
        Log.i(null,"LoginFragment onClick() started");
        final Activity activity = requireActivity();
        final int viewId = view.getId();
        if (viewId == R.id.LogInButton){
            //GO TO LOGIN SCREEN, DO NOTHING FOR NOW
            Log.i(null,"LoginFragment onClick() LogInButton clicked");
        }
        Log.i(null,"LoginFragment onClick() finished");
    }
}