package com.jp.bettingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.Calendar;

public class Result_Chart_Activity extends AppCompatActivity {

    RelativeLayout backlyt;
    TextView monthtxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_chart);

        backlyt = findViewById(R.id.backlyt);

        backlyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { onBackPressed();}
        });

        monthtxt = findViewById(R.id.monthtxt);


    }
}