package com.example.solipaire.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.solipaire.R;
import com.example.solipaire.activity.MenuActivity;
import com.example.solipaire.activity.RulesActivity;

public class RulesFragment extends Fragment implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(null,"RulesFragment onCreate() started");
        super.onCreate(savedInstanceState);
        Activity activity = requireActivity();
        Log.i(null,"RulesFragment onCreate() complete");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Log.i(null,"RulesFragment onCreateView() started");
        View v;
        Activity activity = requireActivity();
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        if (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270) {
            v = inflater.inflate(R.layout.fragment_rules/*_landscape*/, container, false);
        } else {
            v = inflater.inflate(R.layout.fragment_rules, container, false);
        }

        final Button exitRulesButton = v.findViewById(R.id.ExitRulesButton);
        if (exitRulesButton != null) {
            exitRulesButton.setOnClickListener(this);
        }
        Log.i(null,"RulesFragment onCreateView() complete");
        return v;
    }

    @Override
    public void onResume() {
        Log.i(null,"RulesFragment onResume() started");
        super.onResume();
        Log.i(null,"RulesFragment onResume() complete");
    }

    @Override
    public void onStart() {
        Log.i(null,"RulesFragment onStart() started");
        super.onStart();
        Log.i(null,"RulesFragment onStart() complete");
    }

    @Override
    public void onStop() {
        Log.i(null,"RulesFragment onStop() started");
        super.onStop();
        Log.i(null,"RulesFragment onStop() complete");
    }

    @Override
    public void onPause() {
        Log.i(null,"RulesFragment onPause() started");
        super.onPause();
        Log.i(null,"RulesFragment onPause() complete");
    }

    @Override
    public void onDestroy() {
        Log.i(null,"RulesFragment onDestroy() started");
        super.onDestroy();
        Log.i(null,"RulesFragment onDestroy() complete");
    }

    @Override
    public void onDestroyView() {
        Log.i(null,"RulesFragment onDestroyView() started");
        super.onDestroyView();
        Log.i(null, "RulesFragment onDestroyView() complete");
    }

    @Override
    public void onClick(View view) {
        Log.i(null,"RulesFragment onClick() started");
        final Activity activity = requireActivity();
        final Context appContext = activity.getApplicationContext();
        final int viewId = view.getId();
        if (viewId == R.id.ExitRulesButton) {
            Log.i(null,"RulesFragment onClick() ExitRulesButton clicked");
            //startActivity(new Intent(activity, MenuActivity.class));
            activity.finish();
        }
        Log.i(null,"RulesFragment onClick() finished");
    }
}
