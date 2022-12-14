package com.example.solipaire.fragment;

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

import com.example.solipaire.R;

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
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        if (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270) {
            v = inflater.inflate(R.layout.fragment_startscreen_landscape, container, false);
        } else {
            v = inflater.inflate(R.layout.fragment_startscreen, container, false);
        }
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
        if (viewId == R.id.LogInButton) {
            Log.i(null,"StartScreenFragment onClick() LogInButton clicked");
            FragmentManager fm = getParentFragmentManager();
            Fragment fragment = new LoginFragment();
            fm.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack("login_fragment")
                    .commit();
        } else if (viewId == R.id.SignUpButton) {
            Log.i(null,"StartScreenFragment onClick() SignUpButton clicked");
            FragmentManager fm = getParentFragmentManager();
            Fragment fragment = new SignUpFragment();
            fm.beginTransaction()
                    .replace(R.id.fragment_container,fragment)
                    .addToBackStack("signup_fragment")
                    .commit();
        }
        Log.i(null,"StartScreenFragment onClick() finished");
    }
}