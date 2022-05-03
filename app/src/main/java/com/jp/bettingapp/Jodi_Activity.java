package com.jp.bettingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class Jodi_Activity extends AppCompatActivity {

    Button submitbtn;
    RelativeLayout backlyt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jodi);

        submitbtn = findViewById(R.id.submitbtn);

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Jodi_Activity.this,Fast_Cross_Activity.class);
                startActivity(intent);
            }
        });

        backlyt = findViewById(R.id.backlyt);

        backlyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Jodi_Activity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}