package com.jp.bettingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class Result_Chart_Activity extends AppCompatActivity {

    RelativeLayout backlyt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_chart);

        backlyt = findViewById(R.id.backlyt);

        backlyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Result_Chart_Activity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}