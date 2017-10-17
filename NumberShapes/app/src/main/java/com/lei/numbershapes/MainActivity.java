package com.lei.numbershapes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    class number {
        int num;
        public boolean isSquare() {
           double d = Math.sqrt(this.num);
           if(d==Math.floor(d)) return true;
           else return false;
        }
        public boolean isTriangular() {
             int len = 1;
             int tmp = 0;
             while(tmp<this.num) {
                 tmp +=len++;
             }
             if(tmp==num) return true;
             else return false;
        }
    }
    public void click(View view) {
        EditText  input = (EditText)  findViewById(R.id.inputEditText);
        String  s = "";
        if(input.getText().toString().isEmpty()) {
            s = "Please enter a number!";
        }
        else {
            number test = new number();
            test.num = Integer.parseInt(input.getText().toString());
            if (test.isSquare() && test.isTriangular())
                s = test.num + " is both square and triangular!";
            else if (test.isSquare()) s = test.num + " is square!";
            else if (test.isTriangular()) s = test.num + " is triangular!";
            else s = test.num + " is neither triangular nor square!";
        }
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
