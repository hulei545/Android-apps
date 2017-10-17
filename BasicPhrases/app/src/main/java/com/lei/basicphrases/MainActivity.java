package com.lei.basicphrases;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public void ButtonTapped(View view ) {
        int id = view.getId();
        String ourID = view.getResources().getResourceEntryName(id);
        Log.i("Button Tapped",ourID);
        int resourceID = getResources().getIdentifier(ourID,"raw","com.lei.basicphrases");
        MediaPlayer mplayer = MediaPlayer.create(this,resourceID);
        mplayer.start();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
