package com.jp.bettingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jp.bettingapp.helper.ApiConfig;
import com.jp.bettingapp.helper.Constant;
import com.jp.bettingapp.helper.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    Button btnContinue;
    EditText etMobile;
    Activity activity;
    Session session;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnContinue= findViewById(R.id.btnContinue);
        etMobile= findViewById(R.id.etMobile);


        activity = LoginActivity.this;
        session = new Session(activity);


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
                    finish();
                    //login();


                }

            }

        });



    }
    private void login() {
        Map<String, String> params = new HashMap<>();
        params.put(Constant.MOBILE,etMobile.getText().toString().trim());

        ApiConfig.RequestToVolley((result, response) -> {
            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {

                        Intent intent = new Intent(activity,OTP_Activity.class);
                        intent.putExtra("mobile_number",etMobile.getText().toString());
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(this, "Failed"+jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e){
                    e.printStackTrace();
                }



            }
            else {
                Toast.makeText(this, String.valueOf(response) +String.valueOf(result), Toast.LENGTH_SHORT).show();

            }
            //pass url
        }, LoginActivity.this, Constant.LOGIN_URL, params,true);



    }
}