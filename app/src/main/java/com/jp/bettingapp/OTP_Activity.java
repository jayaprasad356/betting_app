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
                login();



            }
        });
    }
    private void login() {
        Map<String, String> params = new HashMap<>();
        params.put(Constant.MOBILE,mobilenumber);

        ApiConfig.RequestToVolley((result, response) -> {
            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {


                        if (jsonObject.getBoolean(Constant.Login)){
                            session.setBoolean("is_logged_in", true);
                            JSONArray jsonArray = jsonObject.getJSONArray(Constant.DATA);
                            session.setData(Constant.ID,jsonArray.getJSONObject(0).getString(Constant.ID));
                            session.setData(Constant.MOBILE,jsonArray.getJSONObject(0).getString(Constant.MOBILE));
                            session.setData(Constant.NAME,jsonArray.getJSONObject(0).getString(Constant.NAME));
                            session.setData(Constant.EARN,jsonArray.getJSONObject(0).getString(Constant.EARN));

                            Intent intent = new Intent(OTP_Activity.this,MainActivity.class);
                            startActivity(intent);
                            finish();

                        }else{
                            Intent intent = new Intent(OTP_Activity.this,LoginProfileActivity.class);
                            intent.putExtra(Constant.MOBILE,mobilenumber);
                            startActivity(intent);
                            finish();

                        }
                    }
                    else {

                    }
                } catch (JSONException e){
                    e.printStackTrace();
                }



            }
            else {
                Toast.makeText(this, String.valueOf(response) +String.valueOf(result), Toast.LENGTH_SHORT).show();

            }
            //pass url
        }, activity, Constant.LOGIN_URL, params,true);



    }


}