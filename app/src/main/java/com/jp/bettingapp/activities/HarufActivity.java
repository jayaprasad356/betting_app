package com.jp.bettingapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jp.bettingapp.R;
import com.jp.bettingapp.adapter.HarufAdapter;
import com.jp.bettingapp.adapter.JodiFastCrossAdapter;

public class HarufActivity extends AppCompatActivity {

    Activity activity;
    RecyclerView recyclerView;
    HarufAdapter harufAdapter;
    EditText etAndar;
    EditText etBahar;
    ImageButton back;
    TextView tvWarning;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haruf);

        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { onBackPressed(); }
        });

        etAndar = findViewById(R.id.etAndar);
        etBahar = findViewById(R.id.etBahar);
        tvWarning = findViewById(R.id.tvWarning);
        recyclerView = findViewById(R.id.recyclerView);
        activity = HarufActivity.this;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);

        harufAdapter = new HarufAdapter(activity,tvWarning);
        recyclerView.setAdapter(harufAdapter);
    }
}