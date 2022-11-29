        package com.example.solipaire.fragment;

        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.drawable.Drawable;
        import android.net.Uri;
        import android.os.Bundle;
        import android.preference.PreferenceManager;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.Surface;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.ImageButton;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.fragment.app.Fragment;

        import com.example.solipaire.R;


        import com.facebook.CallbackManager;
        import com.facebook.FacebookActivity;
        import com.facebook.share.model.ShareLinkContent;
        import com.facebook.share.model.SharePhoto;
        import com.facebook.share.model.SharePhotoContent;
        import com.facebook.share.widget.ShareButton;
        import com.facebook.share.widget.ShareDialog;

        import java.util.Locale;

public class StatsFragment extends Fragment implements View.OnClickListener {

    private TextView p1GamesWon;
    private TextView p1GamesPlayed;
    private TextView p1Ratio;
    private TextView p1TotalPoints;

    private TextView p2GamesWon;
    private TextView p2GamesPlayed;
    private TextView p2Ratio;
    private TextView p2TotalPoints;

    int gamesPlayed;
    int p1Wins;
    int p2Wins;
    int p1Total;
    int p2Total;
    int p1Losses;
    int p2Losses;
    String ratio1;
    String ratio2;
    ShareLinkContent content;

    Bitmap image;

    CallbackManager callbackManager;
    ShareDialog shareDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(null,"StatsFragment onCreate() started");
        super.onCreate(savedInstanceState);
        Activity activity = requireActivity();
        image = BitmapFactory.decodeResource(getResources(),R.drawable.solipairelogo);
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        Log.i(null,"StatsFragment onCreate() complete");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Log.i(null,"StatsFragment onCreateView() started");
        View v;
        Activity activity = requireActivity();
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        if (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270) {
            v = inflater.inflate(R.layout.fragment_stats/*_landscape*/, container, false);
        } else {
            v = inflater.inflate(R.layout.fragment_stats, container, false);
        }
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(image)
                .build();
        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();
        ShareButton shareButton = v.findViewById(R.id.statsShareBtn);
        /*if (shareButton != null) {
            shareButton.setOnClickListener(this);
        }*/
        shareButton.setShareContent(content);
        final Button exitStatsButton = v.findViewById(R.id.exitStatsButton);
        if (exitStatsButton != null) {
            exitStatsButton.setOnClickListener(this);
        }

        p1GamesWon = v.findViewById(R.id.p1GamesWon);
        p1GamesPlayed = v.findViewById(R.id.p1GamesPlayed);
        p1Ratio = v.findViewById(R.id.p1Ratio);
        p1TotalPoints = v.findViewById(R.id.p1TotalPoints);

        p2GamesWon = v.findViewById(R.id.p2GamesWon);
        p2GamesPlayed = v.findViewById(R.id.p2GamesPlayed);
        p2Ratio = v.findViewById(R.id.p2Ratio);
        p2TotalPoints = v.findViewById(R.id.p2TotalPoints);
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
        gamesPlayed = settings.getInt("gPlayed",0);
        p1Wins = settings.getInt("gWonP1", 0);
        p2Wins = settings.getInt("gWonP2", 0);
        p1Total = settings.getInt("totalP1", 0);
        p2Total = settings.getInt("totalP2", 0);
        p1Losses = gamesPlayed - p1Wins;
        p2Losses = gamesPlayed - p2Wins;
        p1GamesPlayed.setText(((Integer)gamesPlayed).toString());
        p2GamesPlayed.setText(((Integer)gamesPlayed).toString());
        p1GamesWon.setText(((Integer)p1Wins).toString());
        p2GamesWon.setText(((Integer)p2Wins).toString());
        ratio1 = p1Wins + " : " + p1Losses;
        p1Ratio.setText(ratio1);
        ratio2 = p2Wins + " : " + p2Losses;
        p2Ratio.setText(ratio2);
        p1TotalPoints.setText(((Integer)p1Total).toString());
        p2TotalPoints.setText(((Integer)p2Total).toString());

        Log.i(null,"StatsFragment onCreateView() complete");
        return v;
    }

    @Override
    public void onResume() {
        Log.i(null,"StatsFragment onResume() started");
        super.onResume();
        Activity activity = requireActivity();
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
        gamesPlayed = settings.getInt("gPlayed",0);
        p1Wins = settings.getInt("gWonP1", 0);
        p2Wins = settings.getInt("gWonP2", 0);
        p1Total = settings.getInt("totalP1", 0);
        p2Total = settings.getInt("totalP2", 0);
        p1Losses = gamesPlayed - p1Wins;
        p2Losses = gamesPlayed - p2Wins;
        p1GamesPlayed.setText(((Integer)gamesPlayed).toString());
        p2GamesPlayed.setText(((Integer)gamesPlayed).toString());
        p1GamesWon.setText(((Integer)p1Wins).toString());
        p2GamesWon.setText(((Integer)p2Wins).toString());
        ratio1 = p1Wins + " : " + p1Losses;
        p1Ratio.setText(ratio1);
        ratio2 = p2Wins + " : " + p2Losses;
        p2Ratio.setText(ratio2);
        p1TotalPoints.setText(((Integer)p1Total).toString());
        p2TotalPoints.setText(((Integer)p2Total).toString());
        Log.i(null,"StatsFragment onResume() complete");
    }

    @Override
    public void onStart() {
        Log.i(null,"StatsFragment onStart() started");
        super.onStart();
        Log.i(null,"StatsFragment onStart() complete");
    }

    @Override
    public void onStop() {
        Log.i(null,"StatsFragment onStop() started");
        super.onStop();
        Log.i(null,"StatsFragment onStop() complete");
    }

    @Override
    public void onPause() {
        Log.i(null,"StatsFragment onPause() started");
        super.onPause();
        Log.i(null,"StatsFragment onPause() complete");
    }

    @Override
    public void onDestroy() {
        Log.i(null,"StatsFragment onDestroy() started");
        super.onDestroy();
        Log.i(null,"StatsFragment onDestroy() complete");
    }

    @Override
    public void onDestroyView() {
        Log.i(null,"StatsFragment onDestroyView() started");
        super.onDestroyView();
        Log.i(null, "StatsFragment onDestroyView() complete");
    }

    @Override
    public void onClick(View view) {
        Log.i(null,"StatsFragment onClick() started");
        final Activity activity = requireActivity();
        final Context appContext = activity.getApplicationContext();
        final int viewId = view.getId();
        if (viewId == R.id.exitStatsButton) {
            Log.i(null,"StatsFragment onClick() ExitStatsButton clicked");
            //startActivity(new Intent(activity, MenuActivity.class));
            activity.finish();
        } else if (viewId == R.id.statsShareBtn) {
            Log.i(null, "StatsFragment onClick() statsShareBtn clicked");
            ShareDialog.show(activity, content);

        }
        Log.i(null,"StatsFragment onClick() finished");
    }
}