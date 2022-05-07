package com.jp.bettingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class AddPointsActivity extends AppCompatActivity {

    ImageButton back;
    Button pointsbtn;
    EditText etPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpoints);

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
            }
        });
    }
}