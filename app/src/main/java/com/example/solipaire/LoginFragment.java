package com.example.solipaire;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

public class LoginFragment extends Fragment implements View.OnClickListener {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private AccountViewModel accountViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(null,"LoginFragment onCreate() started");
        super.onCreate(savedInstanceState);
        Activity activity = requireActivity();
        accountViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(AccountViewModel.class);
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
        usernameEditText = v.findViewById(R.id.mUserName);
        passwordEditText = v.findViewById(R.id.mPassword);

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
        usernameEditText = null;
        passwordEditText = null;
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
        final Activity activity = requireActivity();
        accountViewModel.getAllAccounts().removeObservers((LifecycleOwner) activity);
        Log.i(null,"LoginFragment onDestroy() complete");

    }

    @Override
    public void onDestroyView() {
        Log.i(null,"LoginFragment onDestroyView() started");
        super.onDestroyView();
        Log.i(null, "LoginFragment onDestroyView() complete");
    }

    private void logIn() {
        final String username = usernameEditText.getText().toString();
        final String password = passwordEditText.getText().toString();
        Activity activity = requireActivity();
        Account account = new Account(username, password);
        //TODO: Finish logIn method
    }

    @Override
    public void onClick(View view) {
        Log.i(null,"LoginFragment onClick() started");
        final Activity activity = requireActivity();
        final int viewId = view.getId();
        if (viewId == R.id.LogInButton){
            logIn();
            Log.i(null,"LoginFragment onClick() LogInButton clicked");
        }
        Log.i(null,"LoginFragment onClick() finished");
    }
}