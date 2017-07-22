package com.example.jemmycalak.thisismymarket.view;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.jemmycalak.thisismymarket.MainActivity;
import com.example.jemmycalak.thisismymarket.R;

public class finish extends AppCompatActivity {

    Button fnsh;
    Toolbar tolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish);

        tolbar=(Toolbar)findViewById(R.id.toolfinish);
        setSupportActionBar(tolbar);

        fnsh=(Button)findViewById(R.id.finisBT);
        fnsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //after logout redirect user to login activity
                Intent i= new Intent(finish.this, MainActivity.class);

                //closing all activity
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                //add new flag to start activity

                startActivity(i);
                finish();

            }
        });
    }

    //ketika di tekan tombol back
    public void onBackPressed() {

        //after logout redirect user to login activity
        Intent i= new Intent(finish.this, MainActivity.class);

        //closing all activity
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        //add new flag to start activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(i);
        finish();

    }
}
