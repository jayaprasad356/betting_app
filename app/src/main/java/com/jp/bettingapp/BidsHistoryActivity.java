package com.jp.bettingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.jp.bettingapp.adapter.BidAdapter;
import com.jp.bettingapp.adapter.HarufAdapter;

public class BidsHistoryActivity extends AppCompatActivity {

    ImageButton back;
    RecyclerView recyclerView;
    BidAdapter bidAdapter;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bidshistory);
        activity = BidsHistoryActivity.this;

        back = findViewById(R.id.back);
        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { onBackPressed(); }
        });

        bidAdapter = new BidAdapter(activity);
        recyclerView.setAdapter(bidAdapter);


    }
}