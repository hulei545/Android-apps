package com.lei.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    ArrayList<Integer> answers = new ArrayList<>();
    EditText sumText;
    EditText timerText;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button playAgainButton;
    int correctLocation;
    int score = 0;
    EditText correctText;
    EditText pointText;
    int numOfQ = 0;
    RelativeLayout bodyLayout;

    public void playAgain(View view) {
        score = 0;
        numOfQ = 0;
        timerText.setText("30s");
        pointText.setText("0/0");
        correctText.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQ();
        new CountDownTimer(30100,1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timerText.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                timerText.setText("0s");
                correctText.setText("Your score: "+ score+"/"+numOfQ);
            }
        }.start();
    }

    public void start (View view) {
        startButton.setVisibility(View.INVISIBLE);
        bodyLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
    }

    public void chooseAnswer(View view) {
       if(view.getTag().toString().equals(Integer.toString(correctLocation))){
            Log.i("Correct","Answer");
           score++;
           correctText.setText("Correct");
        }
        else {
           Log.i("Wrong","Answer");
           correctText.setText("Wrong");
       }
       numOfQ++;
        pointText.setText(score+"/"+numOfQ);
        generateQ();

    }

    public void generateQ() {
        Random rand = new Random();
        int a = rand.nextInt(1000);
        int b = rand.nextInt(1000);
        sumText.setText(Integer.toString(a) + "*" + Integer.toString(b));
        correctLocation = rand.nextInt(4);
        answers.clear();
        for(int i=0;i<4;i++) {
            if(i==correctLocation) answers.add(a*b);
            else {
                int incorrect = rand.nextInt(1000000);
                while(incorrect==a*b) incorrect = rand.nextInt(999*999);
                answers.add(incorrect);
            }
        }
        button1.setText(String.valueOf(answers.get(0)));
        button2.setText(String.valueOf(answers.get(1)));
        button3.setText(String.valueOf(answers.get(2)));
        button4.setText(String.valueOf(answers.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = (Button) findViewById(R.id.startButton);
        sumText = (EditText) findViewById(R.id.sumText);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        correctText = (EditText) findViewById(R.id.correctText);
        pointText = (EditText) findViewById(R.id.pointText);
        timerText = (EditText) findViewById(R.id.timerText);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        bodyLayout =(RelativeLayout) findViewById(R.id.bodyLayout);

    }
}
