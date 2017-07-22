package com.example.jemmycalak.thisismymarket.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.jemmycalak.thisismymarket.MainActivity;
import com.example.jemmycalak.thisismymarket.R;

import java.util.Timer;

public class splash extends AppCompatActivity {

    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        pb=(ProgressBar)findViewById(R.id.pb);

        // Start lengthy operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                doWork();
                startApp();
                finish();
            }
        }).start();
    }

    public void doWork(){
        for (int progress=0; progress<100; progress+=10) {
            try {
                Thread.sleep(1000);
                pb.setProgress(progress);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void startApp(){
        Intent intent = new Intent(splash.this, MainActivity.class);
        startActivity(intent);
    }
}
