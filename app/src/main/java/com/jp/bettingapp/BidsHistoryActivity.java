package com.jp.bettingapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jp.bettingapp.adapter.BidAdapter;
import com.jp.bettingapp.adapter.HarufAdapter;
import com.jp.bettingapp.adapter.HarufBidAdapter;
import com.jp.bettingapp.helper.ApiConfig;
import com.jp.bettingapp.helper.Constant;
import com.jp.bettingapp.helper.Session;
import com.jp.bettingapp.model.BIDS;
import com.jp.bettingapp.model.HarufBids;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BidsHistoryActivity extends AppCompatActivity {

    ImageButton back;
    RecyclerView recyclerView;
    RecyclerView harufrecyclerView;
    BidAdapter bidAdapter;
    HarufBidAdapter harufBidAdapter;
    Activity activity;
    Button btnSubmit;
    Spinner spinGame,spinDay;
    Session session;
    String date;
    LinearLayout bidsl1,bidsl2;
    ArrayList<BIDS> bids = new ArrayList<>();
    ArrayList<HarufBids> harufBids = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bidshistory);
        activity = BidsHistoryActivity.this;
        session = new Session(BidsHistoryActivity.this);

        back = findViewById(R.id.back);
        bidsl1 = findViewById(R.id.bidsl1);
        bidsl2 = findViewById(R.id.bidsl2);
        recyclerView = findViewById(R.id.recyclerView);
        harufrecyclerView = findViewById(R.id.harufrecyclerView);
        btnSubmit = findViewById(R.id.btnSubmit);
        spinGame = findViewById(R.id.spinGame);
        spinDay = findViewById(R.id.spinDay);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        harufrecyclerView.setLayoutManager(linearLayoutManager2);
        harufrecyclerView.setHasFixedSize(true);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { onBackPressed(); }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                if (spinGame.getSelectedItemPosition() == 0){
                    Toast.makeText(activity, "Please,Select Game", Toast.LENGTH_SHORT).show();
                }
                else if (spinDay.getSelectedItemPosition() == 0){
                    Toast.makeText(activity, "Please,Select Day", Toast.LENGTH_SHORT).show();
                }
                else {
                    String pattern = "yyyy-MM-dd";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

                    if (spinDay.getSelectedItemPosition() == 1){

                        date = simpleDateFormat.format(getMeYesterday());


                    }
                    else if (spinDay.getSelectedItemPosition() == 2){
                        date = simpleDateFormat.format(new Date());


                    }
                    else if (spinDay.getSelectedItemPosition() == 3){
                        date = simpleDateFormat.format(getMeTomorrow());


                    }
                    bidsl1.setVisibility(View.GONE);
                    bidsl2.setVisibility(View.GONE);


                    bidsList();
                    harufbidsList();
                }


            }
        });







    }

    private void harufbidsList()
    {
        harufBids.clear();
        Map<String, String> params = new HashMap<>();
        params.put(Constant.USER_ID,session.getData(Constant.ID));
        params.put(Constant.GAME_NAME,spinGame.getSelectedItem().toString());
        params.put(Constant.DATE,date);
        ApiConfig.RequestToVolley((result, response) -> {
            Log.d("HARUFBIDSLIST",response);
            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        bidsl2.setVisibility(View.VISIBLE);
                        JSONObject object = new JSONObject(response);
                        JSONArray jsonArray = object.getJSONArray(Constant.DATA);
                        Gson g = new Gson();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            if (jsonObject1 != null) {
                                HarufBids group = g.fromJson(jsonObject1.toString(), HarufBids.class);
                                harufBids.add(group);
                            } else {
                                break;
                            }
                        }
                        harufBidAdapter = new HarufBidAdapter(activity, harufBids);
                        harufrecyclerView.setAdapter(harufBidAdapter);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    //Toast.makeText(BidsHistoryActivity.this, String.valueOf(e), Toast.LENGTH_SHORT).show();
                }
            }
        }, activity, Constant.HARUFBIDSLIST_URL, params, true);

    }

    private Date getMeTomorrow(){
        return new Date(System.currentTimeMillis()+24*60*60*1000);
    }
    private Date getMeYesterday(){
        return new Date(System.currentTimeMillis()-24*60*60*1000);
    }
    private void bidsList()
    {
        bids.clear();
        Map<String, String> params = new HashMap<>();
        params.put(Constant.USER_ID,session.getData(Constant.ID));
        params.put(Constant.GAME_NAME,spinGame.getSelectedItem().toString());
        params.put(Constant.DATE,date);
        ApiConfig.RequestToVolley((result, response) -> {
            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        bidsl1.setVisibility(View.VISIBLE);
                        JSONObject object = new JSONObject(response);
                        JSONArray jsonArray = object.getJSONArray(Constant.DATA);
                        Gson g = new Gson();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            if (jsonObject1 != null) {
                                BIDS group = g.fromJson(jsonObject1.toString(), BIDS.class);
                                bids.add(group);
                            } else {
                                break;
                            }
                        }
                        bidAdapter = new BidAdapter(activity, bids);
                        recyclerView.setAdapter(bidAdapter);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(BidsHistoryActivity.this, String.valueOf(e), Toast.LENGTH_SHORT).show();
                }
            }
        }, activity, Constant.BIDSLIST_URL, params, true);
    }
}