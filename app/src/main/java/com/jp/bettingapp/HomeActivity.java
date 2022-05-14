package com.jp.bettingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jp.bettingapp.activities.HarufActivity;
import com.jp.bettingapp.activities.JodiActivity;
import com.jp.bettingapp.activities.OddEvenActivity;
import com.jp.bettingapp.activities.QuickCrossActivity;

public class HomeActivity extends AppCompatActivity {

    LinearLayout addpointlyt;
    LinearLayout jodilyt;
    LinearLayout haruflyt;
    LinearLayout transactionlyt;
    LinearLayout oddlyt;
    LinearLayout crosslyt;
    LinearLayout historylyt;
    LinearLayout withdrawlyt;
    LinearLayout sharelyt;
    Button addbtn;
    Button resultbtn;
    Button sidebtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addbtn = findViewById(R.id.addbtn);
        resultbtn = findViewById(R.id.resultbtn);
        sidebtn = findViewById(R.id.sidebtn);
        addpointlyt = findViewById(R.id.addpointlyt);
        jodilyt = findViewById(R.id.jodilyt);
        haruflyt = findViewById(R.id.haruflyt);
        transactionlyt = findViewById(R.id.transactionlyt);
        oddlyt = findViewById(R.id.oddlyt);
        crosslyt = findViewById(R.id.crosslyt);
        historylyt = findViewById(R.id.historylyt);
        withdrawlyt = findViewById(R.id.withdrawlyt);
        sharelyt = findViewById(R.id.sharelyt);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,Add_Account_Details_Activity.class);
                startActivity(intent);
            }
        });

        resultbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        sidebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,Side_Menu_Bar_Activity.class);
                startActivity(intent);
            }
        });

        addpointlyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AddPointsActivity.class);
                startActivity(intent);
            }
        });

        jodilyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, JodiActivity.class);
                startActivity(intent);
            }
        });

        haruflyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, HarufActivity.class);
                startActivity(intent);
            }
        });

        transactionlyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,My_Transaction_Activity.class);
                startActivity(intent);
            }
        });

        oddlyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, OddEvenActivity.class);
                startActivity(intent);
            }
        });

        historylyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, BidsHistoryActivity.class);
                startActivity(intent);
            }
        });

        withdrawlyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,Withdrawal_Activity.class);
                startActivity(intent);
            }
        });

        crosslyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, QuickCrossActivity.class);
                startActivity(intent);
            }
        });

        sharelyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,Share_Points_Activity.class);
                startActivity(intent);
            }
        });
    }
}