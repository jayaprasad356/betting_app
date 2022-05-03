package com.jp.bettingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    Button btnContinue;
    EditText etMobile;
    Activity activity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnContinue= findViewById(R.id.btnContinue);
        etMobile= findViewById(R.id.etMobile);

        activity = LoginActivity.this;

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (etMobile.getText().toString().equals(""))
                {
                    etMobile.setError("empty");
                    etMobile.requestFocus();
                }
                else if (etMobile.getText().length() !=10){
                    etMobile.setError("invalid");
                    etMobile.requestFocus();
                }
                else
                {
                    Intent intent = new Intent(activity,OTP_Activity.class);
                    intent.putExtra("mobile_number",etMobile.getText().toString());
                    startActivity(intent);

                }

            }
        });



    }
}