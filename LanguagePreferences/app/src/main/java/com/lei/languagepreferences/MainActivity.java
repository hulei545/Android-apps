package com.lei.languagepreferences;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView textView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       super.onOptionsItemSelected(item);
        if(item.getItemId()==R.id.english) setLanguage("English");
        else setLanguage("Spanish");
        return true;
    }

    public void setLanguage(String language) {
        sharedPreferences.edit().putString("Language",language).apply();
        textView.setText(language);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        sharedPreferences = this.getSharedPreferences("com.lei.languagepreferences", Context.MODE_PRIVATE);
        String language = sharedPreferences.getString("Language","##");
         if(language.equals("##")) {

             new AlertDialog.Builder(this)
                     .setIcon(android.R.drawable.ic_dialog_alert)
                     .setTitle("Choose a language")
                     .setMessage("Which language would you like?")
                     .setPositiveButton("English", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             setLanguage("English");
                         }
                     })
                     .setNegativeButton("Spanish", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             setLanguage("Spanish");
                         }
                     })
                     .show();
         }
         else {
             textView.setText(language);
         }
    }
}
