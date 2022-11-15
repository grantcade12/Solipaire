package com.example.solipaire.fragment;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.solipaire.MusicPlayback;
import com.example.solipaire.R;
import com.example.solipaire.SettingsSingleton;
import com.example.solipaire.activity.GameActivity;
import com.example.solipaire.activity.RulesActivity;
import com.example.solipaire.activity.SettingsActivity;
import com.example.solipaire.activity.StatsActivity;

import java.io.File;

public class MenuFragment extends Fragment implements View.OnClickListener {
    private TextView usernameDisplay;
    private Intent musicIntent;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(null,"MenuFragment onCreate() started");
        super.onCreate(savedInstanceState);
        Activity activity = requireActivity();
        Context context = requireContext();
        final String audioResourceName = "android.resource://" + context.getPackageName() +
                File.separator + R.raw.music;
        Uri mAudioFileUri = Uri.parse(audioResourceName);
        SettingsSingleton s = SettingsSingleton.SettingsSingleton();
        s.musicUriString = mAudioFileUri.toString();
        if (s.music){
            musicIntent = new Intent(activity.getApplicationContext(), MusicPlayback.class);
            musicIntent.putExtra("URIString", mAudioFileUri.toString());
            activity.startService(musicIntent);
            Log.e(null, "Music has started" );
        }
        Log.i(null,"MenuFragment onCreate() complete");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Log.i(null,"MenuFragment onCreateView() started");
        View v;
        Activity activity = requireActivity();
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        if (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270) {
            v = inflater.inflate(R.layout.fragment_menu_landscape, container, false);
        } else {
            v = inflater.inflate(R.layout.fragment_menu, container, false);
        }
        final Button createGameButton = v.findViewById(R.id.NewGameButton);
        if (createGameButton != null) {
            createGameButton.setOnClickListener(this);
        }

        final Button statsButton = v.findViewById(R.id.StatsButton);
        if (statsButton != null) {
            statsButton.setOnClickListener(this);
        }
        final Button rulesButton = v.findViewById(R.id.RulesButton);
        if (rulesButton != null) {
            rulesButton.setOnClickListener(this);
        }

        final Button settingsButton = v.findViewById(R.id.SettingsButton);
        if (settingsButton != null) {
            settingsButton.setOnClickListener(this);
        }

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
        usernameDisplay = v.findViewById(R.id.userDisplay);
        usernameDisplay.setText(settings.getString("name", "Welcome!"));
        Log.i(null,"MenuFragment onCreateView() complete");
        return v;
    }

    @Override
    public void onResume() {
        Log.i(null,"MenuFragment onResume() started");
        super.onResume();
        final Activity activity = requireActivity();
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
        usernameDisplay.setText(settings.getString("name", "Welcome!"));
        SettingsSingleton s = SettingsSingleton.SettingsSingleton();
        if (!s.music){
            activity.stopService(musicIntent);
        }
        Log.i(null,"MenuFragment onResume() complete");
    }

    @Override
    public void onStart() {
        Log.i(null,"MenuFragment onStart() started");
        super.onStart();
        Log.i(null,"MenuFragment onStart() complete");
    }

    @Override
    public void onStop() {
        Log.i(null,"MenuFragment onStop() started");
        super.onStop();
        Log.i(null,"MenuFragment onStop() complete");
    }

    @Override
    public void onPause() {
        Log.i(null,"MenuFragment onPause() started");
        super.onPause();
        Log.i(null,"MenuFragment onPause() complete");
    }

    @Override
    public void onDestroy() {
        Log.i(null,"MenuFragment onDestroy() started");
        getActivity().stopService(musicIntent);
        super.onDestroy();
        Log.i(null,"MenuFragment onDestroy() complete");
    }

    @Override
    public void onDestroyView() {
        Log.i(null,"MenuFragment onDestroyView() started");
        super.onDestroyView();
        Log.i(null, "MenuFragment onDestroyView() complete");
    }

    @Override
    public void onClick(View view) {
        Log.i(null,"MenuFragment onClick() started");
        final Activity activity = requireActivity();
        final Context appContext = activity.getApplicationContext();
        final int viewId = view.getId();
        if (viewId == R.id.NewGameButton) {
            Log.i(null,"MenuFragment onClick() NewGameButton clicked");
            startActivity(new Intent(appContext, GameActivity.class));
        } else if (viewId == R.id.StatsButton) {
            Log.i(null,"MenuFragment onClick() StatsButton clicked");
            startActivity(new Intent(appContext, StatsActivity.class));
        } else if (viewId == R.id.RulesButton) {
            Log.i(null,"MenuFragment onClick() HowToPlayButton clicked");
            startActivity(new Intent(appContext, RulesActivity.class));
            //activity.finish();
        } else if (viewId == R.id.SettingsButton) {
            Log.i(null,"MenuFragment onClick() SettingsButton clicked");
            startActivity(new Intent(appContext, SettingsActivity.class));
            //activity.finish();
        }
        Log.i(null,"MenuFragment onClick() finished");
    }
}
