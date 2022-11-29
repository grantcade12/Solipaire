package com.example.solipaire.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.solipaire.CardColor;
import com.example.solipaire.MusicPlayback;
import com.example.solipaire.R;
import com.example.solipaire.SettingsSingleton;
import com.example.solipaire.TableColor;
import com.example.solipaire.activity.MenuActivity;
import com.example.solipaire.activity.RulesActivity;
import com.example.solipaire.activity.StartScreenActivity;
import com.example.solipaire.data.Account;
import com.example.solipaire.viewmodel.AccountViewModel;

import java.io.File;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SettingsFragment extends Fragment implements View.OnClickListener {

    private EditText newUsernameText;
    private Switch musicSwitch;
    private AccountViewModel accountViewModel;
    private final List<Account> accountList = new CopyOnWriteArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(null,"settingsFragment onCreate() started");
        super.onCreate(savedInstanceState);
        Activity activity = requireActivity();
        accountViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(AccountViewModel.class);
        accountViewModel.getAllAccounts().observe((LifecycleOwner) activity, userAccounts -> {
            accountList.clear();
            accountList.addAll(userAccounts);
        });
        //Log.i(null, ((Integer)accountList.size()).toString());
        Log.i(null,"settingsFragment onCreate() complete");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Log.i(null,"settingsFragment onCreateView() started");
        View v;
        Activity activity = requireActivity();
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        if (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270) {
            v = inflater.inflate(R.layout.fragment_settings/*_landscape*/, container, false);
        } else {
            v = inflater.inflate(R.layout.fragment_settings, container, false);
        }
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
        newUsernameText = v.findViewById(R.id.settings_changeUser);
        newUsernameText.setText(settings.getString("displayName", "user"));

        final Button changeUsernameButton = v.findViewById(R.id.settings_changeUserButton);
        if (changeUsernameButton != null) {
            changeUsernameButton.setOnClickListener(this);
        }

        final Button cardRedButton = v.findViewById(R.id.card_red);
        if (cardRedButton != null) {
            cardRedButton.setOnClickListener(this);
        }
        final Button cardBlueButton = v.findViewById(R.id.card_blue);
        if (cardBlueButton != null) {
            cardBlueButton.setOnClickListener(this);
        }
        final Button cardGreenButton = v.findViewById(R.id.card_green);
        if (cardGreenButton != null) {
            cardGreenButton.setOnClickListener(this);
        }
        final Button cardPurpleButton = v.findViewById(R.id.card_purple);
        if (cardPurpleButton != null) {
            cardPurpleButton.setOnClickListener(this);
        }
        final Button cardBlackButton = v.findViewById(R.id.card_black);
        if (cardBlackButton != null) {
            cardBlackButton.setOnClickListener(this);
        }
        final Button tableGreenButton = v.findViewById(R.id.table_green);
        if (tableGreenButton != null) {
            tableGreenButton.setOnClickListener(this);
        }
        final Button tableBlueButton = v.findViewById(R.id.table_blue);
        if (tableBlueButton != null) {
            tableBlueButton.setOnClickListener(this);
        }
        final Button tableRedButton = v.findViewById(R.id.table_red);
        if (tableRedButton != null) {
            tableRedButton.setOnClickListener(this);
        }
        final Button tablePurpleButton = v.findViewById(R.id.table_purple);
        if (tablePurpleButton != null) {
            tablePurpleButton.setOnClickListener(this);
        }
        final Button tableWhiteButton = v.findViewById(R.id.table_white);
        if (tableWhiteButton != null) {
            tableWhiteButton.setOnClickListener(this);
        }
        final Button resetStatsButton = v.findViewById(R.id.settings_resetStats);
        if (resetStatsButton != null) {
            resetStatsButton.setOnClickListener(this);
        }
        final Button deleteUserButton = v.findViewById(R.id.settings_deleteUser);
        if (deleteUserButton != null) {
            deleteUserButton.setOnClickListener(this);
        }
        final Button resetGameButton = v.findViewById(R.id.settings_resetGame);
        if (resetGameButton != null) {
            resetGameButton.setOnClickListener(this);
        }
        final Button returnButton = v.findViewById(R.id.settings_return);
        if (returnButton != null) {
            returnButton.setOnClickListener(this);
        }
        final Button musicButton = v.findViewById(R.id.settings_musicButton);
        if (musicButton != null) {
            musicButton.setOnClickListener(this);
        }
        musicSwitch = v.findViewById(R.id.settings_music);
        musicSwitch.setChecked(settings.getBoolean("musicBool", false));
        Log.i(null,"settingsFragment onCreateView() complete");
        return v;
    }

    @Override
    public void onResume() {
        Log.i(null,"settingsFragment onResume() started");
        super.onResume();
        Log.i(null,"settingsFragment onResume() complete");
    }

    @Override
    public void onStart() {
        Log.i(null,"settingsFragment onStart() started");
        super.onStart();
        Log.i(null,"settingsFragment onStart() complete");
    }

    @Override
    public void onStop() {
        Log.i(null,"settingsFragment onStop() started");
        super.onStop();
        Log.i(null,"settingsFragment onStop() complete");
    }

    @Override
    public void onPause() {
        Log.i(null,"settingsFragment onPause() started");
        super.onPause();
        Log.i(null,"settingsFragment onPause() complete");
    }

    @Override
    public void onDestroy() {
        Log.i(null,"settingsFragment onDestroy() started");
        super.onDestroy();
        final Activity activity = requireActivity();
        accountViewModel.getAllAccounts().removeObservers((LifecycleOwner) activity);
        Log.i(null,"settingsFragment onDestroy() complete");
    }

    @Override
    public void onDestroyView() {
        Log.i(null,"settingsFragment onDestroyView() started");
        super.onDestroyView();
        Log.i(null, "settingsFragment onDestroyView() complete");
    }

    @Override
    public void onClick(View view) {
        Log.i(null, "settingsFragment onClick() started");
        final Activity activity = requireActivity();
        final Context appContext = activity.getApplicationContext();
        final int viewId = view.getId();
        if (viewId == R.id.settings_changeUserButton) {
            Log.i(null, "settingsFragment onClick() changeUserButton clicked");
            updateUsername();
        } else if (viewId == R.id.card_red) {
            Log.i(null, "settingsFragment onClick() card_red clicked");
            changeCardBack(CardColor.RED);
        } else if (viewId == R.id.card_blue) {
            Log.i(null, "settingsFragment onClick() card_blue clicked");
            changeCardBack(CardColor.BLUE);
        } else if (viewId == R.id.card_green) {
            Log.i(null, "settingsFragment onClick() card_green clicked");
            changeCardBack(CardColor.GREEN);
        } else if (viewId == R.id.card_purple) {
            Log.i(null, "settingsFragment onClick() card_purple clicked");
            changeCardBack(CardColor.PURPLE);
        } else if (viewId == R.id.card_black) {
            Log.i(null, "settingsFragment onClick() card_black clicked");
            changeCardBack(CardColor.BLACK);
        } else if (viewId == R.id.table_green) {
            Log.i(null, "settingsFragment onClick() table_green clicked");
            changeTableColor(TableColor.GREEN);
        } else if (viewId == R.id.table_blue) {
            Log.i(null, "settingsFragment onClick() table_blue clicked");
            changeTableColor(TableColor.BLUE);
        } else if (viewId == R.id.table_red) {
            Log.i(null, "settingsFragment onClick() table_red clicked");
            changeTableColor(TableColor.RED);
        } else if (viewId == R.id.table_purple) {
            Log.i(null, "settingsFragment onClick() table_purple clicked");
            changeTableColor(TableColor.PURPLE);
        } else if (viewId == R.id.table_white) {
            Log.i(null, "settingsFragment onClick() table_white clicked");
            changeTableColor(TableColor.WHITE);
        } else if (viewId == R.id.settings_return) {
            activity.finish();
        } else if (viewId == R.id.settings_deleteUser) {
            deleteUser();
        } else if (viewId == R.id.settings_resetGame) {
            resetGame();
        }  else if (viewId == R.id.settings_resetStats) {
            clearStats();
        } else if (viewId == R.id.settings_musicButton) {
            toggleMusic(musicSwitch.isChecked());
        }
        Log.i(null, "settingsFragment onClick() finished");
    }


    


    private void toggleMusic(boolean music) {
        Activity activity = requireActivity();
        Context context = requireContext();
        final String audioResourceName = "android.resource://" + context.getPackageName() +
                File.separator + R.raw.music;
        Uri mAudioFileUri = Uri.parse(audioResourceName);
        if (music) {
            Intent musicIntent = new Intent(activity.getApplicationContext(), MusicPlayback.class);
            musicIntent.putExtra("URIString", mAudioFileUri.toString());
            activity.startService(musicIntent);
            Log.e(null, "Music has started" );
        } else {
            MusicPlayback.player.stop();

        }
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("musicBool",music);
    }

    private void resetGame() {
        Activity activity = requireActivity();
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
        SharedPreferences.Editor editor = settings.edit();
        editor.remove("name");
        editor.apply();
        for (Account a: accountList) {
            accountViewModel.delete(a);
        }
        startActivity(new Intent(activity, StartScreenActivity.class));
        Log.i(null, "All accounts deleted. Exiting app.");
        activity.finish();
    }

    private void deleteUser() {
        Activity activity = requireActivity();
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
        SharedPreferences.Editor editor = settings.edit();
        editor.remove("name");
        editor.apply();
        int userId = settings.getInt("Id",1);
        Log.i(null,("accountList size: " + (Integer)accountList.size()).toString());
        Log.e(null, accountList.toString());
        int index = 0;
        for (int i = 0; i< accountList.size(); i++){
            Account a = accountList.get(i);
            if (a.uid == userId){
                index = i;
            }
        }
        Account accountToEdit = accountList.get(index);
        Log.e(null, accountToEdit.toString());
        accountViewModel.delete(accountToEdit);
        Toast.makeText(activity.getApplicationContext(), "User deleted", Toast.LENGTH_SHORT).show();
        Log.i(null, "User deleted.");
        startActivity(new Intent(activity, StartScreenActivity.class));
        activity.finish();
    }

    private void updateUsername() {
        final String newUsername = newUsernameText.getText().toString();
        Activity activity = requireActivity();
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
        SharedPreferences.Editor editor = settings.edit();
        editor.remove("name");
        editor.apply();
        editor.putString("name", newUsername);
        editor.apply();
        int userId = settings.getInt("Id",1);
        Log.i(null,("accountList size: " + (Integer)accountList.size()).toString());
        Log.e(null, accountList.toString());
        int index = 0;
        for (int i = 0; i< accountList.size(); i++){
            Account a = accountList.get(i);
            if (a.uid == userId){
                index = i;
            }
        }
        Account accountToEdit = accountList.get(index);
        Account newAccount = new Account(newUsername, accountToEdit.password);
        accountViewModel.delete(accountToEdit);
        newAccount.uid = accountToEdit.uid;
        Log.e(null, accountToEdit.toString());
        accountViewModel.insert(newAccount);
        Toast.makeText(activity.getApplicationContext(), "Username updated to " + newUsername, Toast.LENGTH_SHORT).show();

        Log.i(null, "Username updated");
    }

    private void changeCardBack(CardColor color) {
        SettingsSingleton s = SettingsSingleton.SettingsSingleton();
        s.cardColor = color;
        Activity activity = requireActivity();
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
        SharedPreferences.Editor editor = settings.edit();
        if (settings.contains("Card color")){
            editor.remove("Card color");
        }
        editor.putString("Card color", color.toString());
        editor.apply();

    }

    private void changeTableColor(TableColor color) {
        SettingsSingleton s = SettingsSingleton.SettingsSingleton();
        s.tableColor = color;
        Activity activity = requireActivity();
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
        SharedPreferences.Editor editor = settings.edit();
        if (settings.contains("Table color")){
            editor.remove("Table color");
        }
        editor.putString("Table color", color.toString());
        editor.apply();
    }

    private void clearStats() {
        Activity activity = requireActivity();
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("gPlayed", 0);
        editor.putInt("gWonP1", 0);
        editor.putInt("gWonP2", 0);
        editor.putInt("totalP1", 0);
        editor.putInt("totalP2", 0);
        editor.apply();
    }
}
