package com.lei.currencyconvert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class MainActivity extends AppCompatActivity {
   public void convert(View view ) {
       EditText dolloar = (EditText) findViewById(R.id.editText);
       Double dollardouble = Double.parseDouble(dolloar.getText().toString());
       Double pound = dollardouble*0.74;
       Toast.makeText(this,"£"+ String.format("%.2f",pound), Toast.LENGTH_SHORT).show();
       Log.i("pound",pound.toString());
   }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
