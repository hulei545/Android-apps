package com.lei.higherorlower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
   int randnum;
   public void guess(View view ) {
       EditText guesstext= (EditText) findViewById(R.id.guessEditText);
       int guess = Integer.parseInt(guesstext.getText().toString());
       if(guess > randnum) makeToast("Higher!");
       else if(guess<randnum) makeToast("Lower!");
       else {
           makeToast("Correct!Restart again!");
           Random rand = new Random();
           randnum = rand.nextInt(20) + 1;
       }
   }
   public void makeToast(String s) {
       Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Random rand = new Random();
        randnum = rand.nextInt(20) + 1;
    }
}
