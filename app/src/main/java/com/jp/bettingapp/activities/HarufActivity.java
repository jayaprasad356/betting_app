package com.jp.bettingapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.jp.bettingapp.R;
import com.jp.bettingapp.adapter.HarufAdapter;
import com.jp.bettingapp.adapter.JodiFastCrossAdapter;

public class HarufActivity extends AppCompatActivity {

    Activity activity;
    RecyclerView recyclerView;
    HarufAdapter harufAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haruf);

        recyclerView = findViewById(R.id.recyclerView);
        activity = HarufActivity.this;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);

        harufAdapter = new HarufAdapter(activity);
        recyclerView.setAdapter(harufAdapter);
    }
}