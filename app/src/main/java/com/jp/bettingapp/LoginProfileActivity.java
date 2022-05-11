package com.jp.bettingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
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

public class LoginProfileActivity extends AppCompatActivity {
    Button btnContinue;
    EditText txtName;
    Activity activity;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_profile);

        activity = LoginProfileActivity.this;

        btnContinue = findViewById(R.id.btnContinue);
        txtName = findViewById(R.id.txtName);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtName.getText().toString().equals("")){
                    txtName.setError("empty");
                    txtName.requestFocus();
                }
                else
                    {
                    Intent intent = new Intent(activity,MainActivity.class);
                    startActivity(intent);
                    }

            }
        });

    }
    private void login() {
        Map<String, String> params = new HashMap<>();
        //request
        params.put(Constant.NAME,txtName.getText().toString().trim());
        params.put(Constant.ID,txtName.getText().toString().trim());

        ApiConfig.RequestToVolley((result, response) -> {
            if (result) {

                try {
                    JSONObject jsonObject = new JSONObject(response);


                    if (jsonObject.getBoolean(Constant.SUCCESS)) {


                        Intent intent = new Intent(activity,MainActivity.class);
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
        }, LoginProfileActivity.this, Constant.LOGIN_URL, params,true);



    }
}