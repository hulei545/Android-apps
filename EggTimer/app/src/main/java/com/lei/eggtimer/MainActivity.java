package com.lei.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    SeekBar timerSeekBar;
    EditText timerEditText;
    Button timerButton;
    Boolean countIsActive = false;
    CountDownTimer countDownTimer;

    public void updateTimer(int secondLeft) {
        int minutes = secondLeft/60;
        int seconds = secondLeft - minutes*60;
        String secondString = Integer.toString(seconds);
        secondString = secondString.length()==1?"0"+secondString:secondString;
        timerEditText.setText(Integer.toString(minutes)+ ":" + secondString);
    }

    public void resetTimer(){
        timerEditText.setText("0:30");
        timerSeekBar.setEnabled(true);
        timerSeekBar.setProgress(30);
        countDownTimer.cancel();
        timerButton.setText("Go!");
        countIsActive = false;
    }

    public void controlTimer(View view) {
        if(countIsActive==false) {
            countIsActive = true;
            timerSeekBar.setEnabled(false);
            timerButton.setText("Stop");
            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    resetTimer();
                    MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mPlayer.start();
                }
            }.start();
        }
        else {
            resetTimer();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerSeekBar = (SeekBar) findViewById(R.id.timerSeekBar);
        timerEditText = (EditText) findViewById(R.id.timerEditText);
        timerButton = (Button) findViewById(R.id.timerButton);
        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                     updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
