package com.jp.bettingapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.jp.bettingapp.R;
import com.jp.bettingapp.adapter.ResultAdapter;
import com.jp.bettingapp.helper.ApiConfig;
import com.jp.bettingapp.helper.Constant;
import com.jp.bettingapp.model.Result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResultChartActivity extends AppCompatActivity {
    Button btnSubmit;
    Spinner spinMonth,spinYear;
    ArrayList<Result> results = new ArrayList<>();
    ResultAdapter resultAdapter;
    Activity activity;

    RecyclerView recyclerView;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_chart);
        activity = ResultChartActivity.this;
        recyclerView = findViewById(R.id.recyclerView);
        back = findViewById(R.id.back);

        spinMonth = findViewById(R.id.spinMonth);
        spinYear = findViewById(R.id.spinYear);

        btnSubmit = findViewById(R.id.btnSubmit);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        resultAdapter = new ResultAdapter(activity, results);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewResults();
            }
        });
    }
    private void viewResults()
    {
        results.clear();
        Map<String, String> params = new HashMap<>();
        params.put(Constant.MONTH, spinMonth.getSelectedItem().toString().trim());
        params.put(Constant.YEAR, spinYear.getSelectedItem().toString().trim());
        ApiConfig.RequestToVolley((result, response) -> {
            Log.d("RESULT_RESPONSE",response);
            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        JSONObject object = new JSONObject(response);

                        JSONArray jsonArray = object.getJSONArray(Constant.DATA);
                        Gson g = new Gson();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            if (jsonObject1 != null) {
                                Result group = g.fromJson(jsonObject1.toString(), Result.class);
                                results.add(group);
                            } else {
                                break;
                            }
                        }


                        recyclerView.setAdapter(resultAdapter);
                    }
                    else {
                        Log.d("RESULT_RESPONSE",""+jsonObject.getString(Constant.MESSAGE));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("RESULT_RESPONSE",""+e.getMessage());
                }
            }
        }, activity, Constant.RESULT_LISTS_URL, params, true);
    }
}