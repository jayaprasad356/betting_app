package com.ayu.bigbillion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ayu.bigbillion.adapter.WithdrawalAdapter;
import com.ayu.bigbillion.helper.ApiConfig;
import com.ayu.bigbillion.helper.Constant;
import com.ayu.bigbillion.helper.Session;
import com.ayu.bigbillion.model.Withdrawal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WithdrawalActivity extends AppCompatActivity {

    ImageButton back;
    RecyclerView recyclerView;
    WithdrawalAdapter withdrawalAdapter;
    Activity activity;
    Session session;
    Button btnWithdrawal;
    EditText etPoint;
    TextView tvMimWithdrawal;
    int min, max, amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawal);

        activity = WithdrawalActivity.this;

        back = findViewById(R.id.back);
        session = new Session(activity);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        btnWithdrawal = findViewById(R.id.btnWithdrawal);
        etPoint = findViewById(R.id.etPoint);
        tvMimWithdrawal = findViewById(R.id.tvMimWithdrawal);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        withdrawalList();
        min = Integer.parseInt(session.getData(Constant.MIN_WITHDRAWAL));
        max = Integer.parseInt(session.getData(Constant.MAX_WITHDRAWAL));

        Log.d("SESSION_VALUE", session.getData(Constant.ID));


        btnWithdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etPoint.getText().toString().isEmpty()) {
                    try {
                        amount = (int) Long.parseLong(etPoint.getText().toString());
                    } catch (NumberFormatException e) {
                        // Handle the exception here
                        e.printStackTrace();
                    }
                }

                if (etPoint.getText().toString().equals("")) {
                    etPoint.setError("empty");
                    etPoint.requestFocus();
                } else if (etPoint.getText().toString().equals("0")) {
                    etPoint.setError("Enter Valid Points");
                    etPoint.requestFocus();
                } else if (amount < min) {
                    etPoint.setError("Minimum Withdrawal is " + min);
                    etPoint.requestFocus();
                } else if (amount > max) {
                    etPoint.setError("Maximum Withdrawal is " + max);
                    etPoint.requestFocus();
                } else if (session.getData(Constant.ACCOUNT_NO).equals("") && session.getData(Constant.PHONEPE).equals("") && session.getData(Constant.PAYTM).equals("")) {
                    Toast.makeText(activity, "Fill Account Details Before Withdrawal", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(activity, Add_Account_Details_Activity.class);
                    startActivity(intent);
                    finish();
                } else {
                    addWithdrawals();
                }
            }
        });
    }

    private void withdrawalList() {
        Map<String, String> params = new HashMap<>();
        params.put(Constant.USER_ID, session.getData(Constant.ID));
        ApiConfig.RequestToVolley((result, response) -> {
            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        JSONObject object = new JSONObject(response);
                        JSONArray jsonArray = object.getJSONArray(Constant.DATA);
                        Gson g = new Gson();
                        ArrayList<Withdrawal> withdrawals = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            if (jsonObject1 != null) {
                                Withdrawal group = g.fromJson(jsonObject1.toString(), Withdrawal.class);
                                withdrawals.add(group);
                            } else {
                                break;
                            }
                        }
                        withdrawalAdapter = new WithdrawalAdapter(activity, withdrawals);
                        recyclerView.setAdapter(withdrawalAdapter);
                    } else {
                        Toast.makeText(activity, "" + String.valueOf(jsonObject.getString(Constant.MESSAGE)), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(activity, String.valueOf(e), Toast.LENGTH_SHORT).show();
                }
            }
        }, activity, Constant.WITHDRAWALLISTS_URL, params, true);
    }

    private void addWithdrawals() {
        Map<String, String> params = new HashMap<>();
        params.put(Constant.USER_ID, session.getData(Constant.ID));
        params.put(Constant.AMOUNT, etPoint.getText().toString().trim());
        ApiConfig.RequestToVolley((result, response) -> {
            Log.d("WITHDRAWAL_RES", response);
            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        JSONArray jsonArray = jsonObject.getJSONArray(Constant.DATA);
                        session.setData(Constant.MOBILE, jsonArray.getJSONObject(0).getString(Constant.MOBILE));
                        session.setData(Constant.NAME, jsonArray.getJSONObject(0).getString(Constant.NAME));
                        session.setData(Constant.POINTS, jsonArray.getJSONObject(0).getString(Constant.POINTS));
                        Toast.makeText(activity, jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(activity, HomeActivity.class);
                        activity.startActivity(intent);
                        activity.finish();
                    } else {
                        Toast.makeText(activity, jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(activity, String.valueOf(response) + String.valueOf(result), Toast.LENGTH_SHORT).show();

            }
            //pass url
        }, activity, Constant.WITHDRAWAL_URL, params, true);
    }
}