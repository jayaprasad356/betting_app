package com.jp.bettingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class Add_Account_Details_Activity extends AppCompatActivity {

    RelativeLayout backlyt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account_details);

        backlyt = findViewById(R.id.backlyt);

        backlyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { onBackPressed(); }
        });
    }
}