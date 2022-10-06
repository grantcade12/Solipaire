package com.example.solipaire;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class StartScreenFragment extends Fragment implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = requireActivity();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
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
        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        final Activity activity = requireActivity();
        final int viewId = view.getId();
        if (viewId == R.id.LogInButton){
            //GO TO LOGIN SCREEN, DO NOTHING FOR NOW
        } else if (viewId == R.id.SignUpButton){
            //GO TO CREATE ACCOUNT SCREEN, DO NOTHING FOR NOW
        }
    }
}