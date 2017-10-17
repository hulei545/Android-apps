package com.lei.guessthecelebrity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    ArrayList<String>  celebURLs = new ArrayList<>();
    ArrayList<String>  celebName = new ArrayList<>();
    int chosenCeleb = 0;
    ImageView imageView;
    int correctLocation;
    String[] answers = new String[4];
    Button button0;
    Button button1;
    Button button2;
    Button button3;

    // button click
    public void celChosen (View view) {
        if(view.getTag().toString().equals(Integer.toString(correctLocation))) {
            Toast.makeText(getApplicationContext(),"Correct!",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(),"Wrong!" + celebName.get(chosenCeleb),Toast.LENGTH_LONG).show();
        }
        generateQuestion();




    }

    // downloading the image based on the url
    public class ImageDownloader extends AsyncTask<String,Void,Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);
                return myBitmap;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    // download the web content based on the source page
    public class DownloadTask extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... urls) {
            String res = "";
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while(data!=-1) {
                    char cur = (char) data;
                    res +=cur;
                    data = reader.read();
                }
                return res;
            } catch (MalformedURLException e) {

                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    // generate new question
    public void generateQuestion() {
        Random rand = new Random();
        chosenCeleb = rand.nextInt(celebURLs.size());
        ImageDownloader imageTask = new ImageDownloader();
        Bitmap celebImage = null;
        try {
            celebImage = imageTask.execute(celebURLs.get(chosenCeleb)).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        imageView.setImageBitmap(celebImage);
        // assign  names for buttons
        correctLocation = rand.nextInt(4);
        int incorrectAnswer;
        for(int i=0;i<4;i++) {
            if(i==correctLocation) answers[i] = celebName.get(chosenCeleb);
            else {
                incorrectAnswer = rand.nextInt(celebName.size());
                while(incorrectAnswer==chosenCeleb) {
                    incorrectAnswer = rand.nextInt(celebName.size());
                }
                answers[i] = celebName.get(incorrectAnswer);
            }
        }
        button0.setText(answers[0]);
        button1.setText(answers[1]);
        button2.setText(answers[2]);
        button3.setText(answers[3]);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        DownloadTask task = new DownloadTask();
        String res = null;
        try {
            res = task.execute("http://www.posh24.se/kandisar").get();
            String[] splitRes = res.split("<div class=\"sidebarContainer\">");
            Pattern p = Pattern.compile("img src=\"(.*?)\"");
            Matcher m = p.matcher(splitRes[0]);
            while(m.find()) {
               celebURLs.add(m.group(1));
            }
            p = Pattern.compile("alt=\"(.*?)\"");
            m = p.matcher(splitRes[0]);
            while(m.find()) {
               celebName.add(m.group(1));
            }
            generateQuestion();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
