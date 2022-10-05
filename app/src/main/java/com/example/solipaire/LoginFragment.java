package com.example.solipaire;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class LoginFragment extends Fragment implements View.OnClickListener {
    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Activity activity = requireActivity();
        //setContentView(R.layout.activity_main);
    }


    @Override
    public void onClick(View view) {

    }
}