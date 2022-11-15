package com.example.solipaire;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class MusicPlayback extends Service {
    public static MediaPlayer player;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.i(null, "MusicPlayback onCreate() started");
        player = MediaPlayer.create(this, R.raw.music);
        player.setLooping(true);
        Log.i(null, "MusicPlayback onCreate() stopped");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String audioFileURIString = extras.getString("URIString");
            Uri audioFileURI = Uri.parse(audioFileURIString);
            try {
                player.reset();
                player.setDataSource(this.getApplicationContext(), audioFileURI);
                player.prepare();
                player.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        player.stop();
    }
}
