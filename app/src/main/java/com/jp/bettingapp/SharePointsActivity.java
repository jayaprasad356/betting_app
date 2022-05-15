package com.jp.bettingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.jp.bettingapp.helper.ApiConfig;
import com.jp.bettingapp.helper.Constant;
import com.jp.bettingapp.helper.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SharePointsActivity extends AppCompatActivity {

    ImageButton back;
    Button btnShare;
    EditText etPoints,etMobile;
    Activity activity;
    Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharepoints);

        activity = SharePointsActivity.this;
        session = new Session(activity);

        back = findViewById(R.id.back);
        btnShare = findViewById(R.id.btnShare);
        etPoints = findViewById(R.id.etPoints);
        etMobile = findViewById(R.id.etMobile);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { onBackPressed(); }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharePoints();

            }
        });


    }

    private void sharePoints()
    {
        Map<String, String> params = new HashMap<>();
        params.put(Constant.USER_ID,session.getData(Constant.ID));
        params.put(Constant.MOBILE,etMobile.getText().toString().trim());
        params.put(Constant.POINTS,etPoints.getText().toString().trim());
        ApiConfig.RequestToVolley((result, response) -> {
            Log.d("SHARE_RES",response);
            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        JSONArray jsonArray = jsonObject.getJSONArray(Constant.DATA);
                        session.setData(Constant.MOBILE,jsonArray.getJSONObject(0).getString(Constant.MOBILE));
                        session.setData(Constant.NAME,jsonArray.getJSONObject(0).getString(Constant.NAME));
                        session.setData(Constant.EARN,jsonArray.getJSONObject(0).getString(Constant.EARN));
                        session.setData(Constant.POINTS,jsonArray.getJSONObject(0).getString(Constant.POINTS));

                        Toast.makeText(activity, jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(activity, MainActivity.class);
                        activity.startActivity(intent);
                        activity.finish();
                    }
                    else {
                        Toast.makeText(activity, jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
            else {
                Toast.makeText(activity, String.valueOf(response) +String.valueOf(result), Toast.LENGTH_SHORT).show();

            }
            //pass url
        }, activity, Constant.SHAREPOINTS_URL, params,true);
    }
}