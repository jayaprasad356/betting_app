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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jp.bettingapp.helper.ApiConfig;
import com.jp.bettingapp.helper.Constant;
import com.jp.bettingapp.helper.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddPointsActivity extends AppCompatActivity {

    ImageButton back;
    Button pointsbtn;
    EditText etPoint;
    Activity activity;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpoints);
        activity = AddPointsActivity.this;
        session = new Session(activity);

        Log.d("SESSION_VALUE",session.getData(Constant.ID));


        back = findViewById(R.id.back);
        pointsbtn = findViewById(R.id.pointsbtn);
        etPoint = findViewById(R.id.etPoint);

        back.setOnClickListener(new View.OnClickListener() {
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
                else if (etPoint.getText().toString().equals("0"))
                {
                    etPoint.setError("Enter Valid Points");
                    etPoint.requestFocus();
                }
                else {
                    addPoints();
                }
            }
        });
    }

    private void addPoints()
    {
        Map<String, String> params = new HashMap<>();
        params.put(Constant.USER_ID,session.getData(Constant.ID));
        params.put(Constant.POINTS,etPoint.getText().toString().trim());
        ApiConfig.RequestToVolley((result, response) -> {
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
        }, activity, Constant.ADD_POINTS_URL, params,true);
    }
}