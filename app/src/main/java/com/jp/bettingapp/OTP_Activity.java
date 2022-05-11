package com.jp.bettingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jp.bettingapp.helper.ApiConfig;
import com.jp.bettingapp.helper.Constant;
import com.jp.bettingapp.helper.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OTP_Activity extends AppCompatActivity {

    Button btnVerify;
    TextView txtNumber;
    String mobilenumber;
    Session session;
    Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        mobilenumber = getIntent().getStringExtra("mobile_number");

        btnVerify= findViewById(R.id.btnVerify);
        txtNumber= findViewById(R.id.txtNumber);

        activity = OTP_Activity.this;
        session = new Session(activity);


        txtNumber.setText(mobilenumber);

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (session.getBoolean("registered")){
                    Intent intent = new Intent(OTP_Activity.this,MainActivity.class);
                    startActivity(intent);

                }
                else {
                    Intent intent = new Intent(OTP_Activity.this,LoginProfileActivity.class);
                    startActivity(intent);
                }



            }
        });
    }


}