package com.jp.bettingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class Add_Point_Activity extends AppCompatActivity {

    RelativeLayout backlyt;
    Button pointsbtn;
    EditText etPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_point);

        backlyt = findViewById(R.id.backlyt);
        pointsbtn = findViewById(R.id.pointsbtn);
        etPoint = findViewById(R.id.etPoint);

        backlyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        pointsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etPoint.getText().toString().equals(""))
                {
                    etPoint.setError("empty");
                    etPoint.requestFocus();
                }
            }
        });
    }
}