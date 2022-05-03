package com.jp.bettingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OTP_Activity extends AppCompatActivity {

    Button btnVerify;
    TextView txtNumber;
    String mobilenumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        mobilenumber = getIntent().getStringExtra("mobile_number");

        btnVerify= findViewById(R.id.btnVerify);
        txtNumber= findViewById(R.id.txtNumber);

        txtNumber.setText(mobilenumber);

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OTP_Activity.this,LoginProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}