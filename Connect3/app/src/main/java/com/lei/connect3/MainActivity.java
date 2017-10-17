package com.lei.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 0 = cross, 1 = circle;
    boolean gameIsActive = true;
    int activePlayer = 0;
    // state on the board
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    // winning positions
    int[][] Positions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropIn(View view ) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString()); // add a Tag for every ImageView in the XML
        if(gameState[tappedCounter]==2&&gameIsActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.cross);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.circle);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(720).setDuration(800);
            // check if someone wins this game
            for(int[] Position:Positions) {
                if(gameState[Position[0]]!=2&&gameState[Position[0]]==gameState[Position[1]]&&gameState[Position[0]]==gameState[Position[2]]) {
                    // some body has won the game !
                    gameIsActive = false;
                    TextView winner = (TextView) findViewById(R.id.WinnerText);
                    winner.setText("Player " + gameState[Position[0]] + " win the game!");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout); // show out the button and winner text
                    layout.setVisibility(View.VISIBLE);
                    return;
                }
            }
            // check if the game is over which means if there is empty in the board
            boolean gameIsOver = true;
            for(int state:gameState) {
                if(state==2) gameIsOver = false;
            }
            // if all the blocks are filled in the board , then we restart the game and play again
            if(gameIsOver) {
                TextView winner = (TextView) findViewById(R.id.WinnerText);
                winner.setText("It's a draw!");
                LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout); // show out the button and winner text
                layout.setVisibility(View.VISIBLE);
            }
        }
    }
    // restart the game after somebody has won
    public void playAgain (View view ) {
        //  LinearLayout disappear
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        // Reset the state
        gameIsActive = true;
        activePlayer = 0;
        for(int i=0;i<gameState.length;i++) gameState[i] = 2;
        //  ImageView in GridLayout disappear
        GridLayout grid = (GridLayout) findViewById(R.id.gridLayout);
        for(int i=0;i<grid.getChildCount();i++) {
            ((ImageView) grid.getChildAt(i)).setImageResource(0); // delete the image source for every ImageView
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
