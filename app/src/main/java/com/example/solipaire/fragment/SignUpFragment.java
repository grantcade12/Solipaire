package com.example.solipaire.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.solipaire.R;
import com.example.solipaire.SettingsSingleton;
import com.example.solipaire.data.Account;
import com.example.solipaire.viewmodel.AccountViewModel;

public class SignUpFragment extends Fragment implements View.OnClickListener {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText passwordReEnterEditText;
    private AccountViewModel accountViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(null,"SignUpFragment onCreate() started");
        super.onCreate(savedInstanceState);
        Activity activity = requireActivity();
        accountViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(AccountViewModel.class);
        Log.i(null,"SignUpFragment onCreate() complete");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Log.i(null,"SignUpFragment onCreateView() started");
        View v;
        Activity activity = requireActivity();
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        if (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270) {
            v = inflater.inflate(R.layout.fragment_signup/*_landscape*/, container, false);
        } else {
            v = inflater.inflate(R.layout.fragment_signup, container, false);
        }
        usernameEditText = v.findViewById(R.id.mNewUserName);
        passwordEditText = v.findViewById(R.id.mNewPassword);
        passwordReEnterEditText = v.findViewById(R.id.mNewPasswordReEnter);

        final Button createAccountButton = v.findViewById(R.id.createAccountButton);
        if (createAccountButton != null) {
            createAccountButton.setOnClickListener(this);
        }
        Log.i(null,"SignUpFragment onCreateView() complete");
        return v;
    }

    @Override
    public void onResume() {
        Log.i(null,"SignUpFragment onResume() started");
        super.onResume();
        Log.i(null,"SignUpFragment onResume() complete");
    }

    @Override
    public void onStart() {
        Log.i(null,"SignUpFragment onStart() started");
        super.onStart();
        Log.i(null,"SignUpFragment onStart() complete");
    }

    @Override
    public void onStop() {
        Log.i(null,"SignUpFragment onStop() started");
        super.onStop();
        Log.i(null,"SignUpFragment onStop() complete");
    }

    @Override
    public void onPause() {
        Log.i(null,"SignUpFragment onPause() started");
        super.onPause();
        Log.i(null,"SignUpFragment onPause() complete");
    }

    @Override
    public void onDestroy() {
        Log.i(null,"SignUpFragment onDestroy() started");
        super.onDestroy();
        final Activity activity = requireActivity();
        accountViewModel.getAllAccounts().removeObservers((LifecycleOwner) activity);
        Log.i(null,"SignUpFragment onDestroy() complete");

    }

    @Override
    public void onDestroyView() {
        Log.i(null,"SignUpFragment onDestroyView() started");
        super.onDestroyView();
        usernameEditText = null;
        passwordEditText = null;
        passwordReEnterEditText = null;
        Log.i(null, "SignUpFragment onDestroyView() complete");
    }

    private void createAccount() {
        final String username = usernameEditText.getText().toString();
        final String password = passwordEditText.getText().toString();
        final String confirm = passwordReEnterEditText.getText().toString();
        Activity activity = requireActivity();
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) && password.equals(confirm)){
            Account account = new Account(username, password);
            SettingsSingleton s = SettingsSingleton.SettingsSingleton();
            s.displayName = username;
            accountViewModel.insert(account);
            Toast.makeText(activity.getApplicationContext(), "New Account added", Toast.LENGTH_SHORT).show();
            Log.i(null, "new Account added");
        } else if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirm)){
            Log.i(null, "missing field");
            Toast.makeText(activity.getApplicationContext(), "Missing field", Toast.LENGTH_SHORT).show();
        } else if (!password.equals(confirm)) {
            Log.i(null, "passwords do not match");
            Toast.makeText(activity.getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
        } else {
            Log.i(null, "unknown error occured");
        }

    }

    @Override
    public void onClick(View view) {
        Log.i(null,"SignUpFragment onClick() started");
        final Activity activity = requireActivity();
        final int viewId = view.getId();
        if (viewId == R.id.createAccountButton){
            createAccount();
            Log.i(null,"SignUpFragment onClick() createAccountButton clicked");
        }
        Log.i(null,"SignUpFragment onClick() finished");
    }
}