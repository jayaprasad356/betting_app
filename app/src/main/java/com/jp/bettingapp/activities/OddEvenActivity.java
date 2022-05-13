package com.jp.bettingapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jp.bettingapp.R;

import org.w3c.dom.Text;

public class OddEvenActivity extends AppCompatActivity {

    EditText tvOdd;
    EditText tvEven;
    TextView tvWarning;
    ImageButton back;
    TextView tvTotal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oddeven);

        tvOdd = findViewById(R.id.tvOdd);
        tvEven = findViewById(R.id.tvEven);
        tvWarning = findViewById(R.id.tvWarning);
        back = findViewById(R.id.back);
        tvTotal = findViewById(R.id.tvTotal);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { onBackPressed(); }
        });

        tvOdd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    if (s != null || !s.equals("")){
                        int num = Integer.parseInt(tvOdd.getText().toString());
                        int ans = num/5;
                        if (num%5 == 0){
                            tvWarning.setVisibility(View.GONE);
                        }
                        else{
                            tvWarning.setVisibility(View.VISIBLE);
                        }
                    }

                }catch (Exception e){

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")){
                    int num = Integer.parseInt(s.toString());
                    String even = tvEven.getText().toString();
                    if (even.equals("")){
                        even = "0";
                    }
                    String odd = tvOdd.getText().toString();
                    if (odd.equals("")){
                        odd = "0";
                    }
                    if (num%5 == 0){
                        int value = (Integer.parseInt(even) * 50) + (Integer.parseInt(odd) * 50);
                        tvTotal.setText(""+value);

                    }

                }
            }
        });

        tvEven.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    if (s != null || !s.equals("")){
                        int num = Integer.parseInt(tvEven.getText().toString());
                        if (num%5 == 0){
                            tvWarning.setVisibility(View.GONE);
                        }
                        else{
                            tvWarning.setVisibility(View.VISIBLE);
                        }
                    }

                }catch (Exception e){

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")){
                    int num = Integer.parseInt(s.toString());
                    String even = tvEven.getText().toString();
                    if (even.equals("")){
                        even = "0";
                    }
                    String odd = tvOdd.getText().toString();
                    if (odd.equals("")){
                        odd = "0";
                    }
                    if (num%5 == 0){
                        int value = (Integer.parseInt(even) * 50) + (Integer.parseInt(odd) * 50);
                        tvTotal.setText(""+value);

                    }

                }
            }
        });


    }
}

