package com.jp.bettingapp.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jp.bettingapp.HomeActivity;
import com.jp.bettingapp.R;
import com.jp.bettingapp.adapter.BidAdapter;
import com.jp.bettingapp.adapter.HarufBidAdapter;
import com.jp.bettingapp.helper.ApiConfig;
import com.jp.bettingapp.helper.Constant;
import com.jp.bettingapp.helper.Functions;
import com.jp.bettingapp.helper.Session;
import com.jp.bettingapp.model.BIDS;
import com.jp.bettingapp.model.Game;
import com.jp.bettingapp.model.HarufBids;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class BidsHistoryFragment extends Fragment {
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
    View root;
    String spinGameName;
    Button btnDelete;

    public BidsHistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root =  inflater.inflate(R.layout.fragment_bids_history, container, false);
        activity = getActivity();
        session = new Session(activity);

        bidsl1 = root.findViewById(R.id.bidsl1);
        bidsl2 = root.findViewById(R.id.bidsl2);
        recyclerView = root.findViewById(R.id.recyclerView);
        harufrecyclerView = root.findViewById(R.id.harufrecyclerView);
        btnSubmit = root.findViewById(R.id.btnSubmit);
        spinGame = root.findViewById(R.id.spinGame);
        spinDay = root.findViewById(R.id.spinDay);
        btnDelete = root.findViewById(R.id.btnDelete);
        Functions.setData(activity,spinGame);

        spinGame.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Game game = (Game) adapterView.getSelectedItem();
                spinGameName = game.getGamename();
                //Toast.makeText(activity, ""+game.getGamename(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        harufrecyclerView.setLayoutManager(linearLayoutManager2);
        harufrecyclerView.setHasFixedSize(true);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                if (spinGame.getSelectedItemPosition() == 0){
                    Toast.makeText(activity, "Please,Select Game", Toast.LENGTH_SHORT).show();
                }
                else if (spinDay.getSelectedItemPosition() == 0  || spinGame.getSelectedItemPosition() == 4){
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
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> params = new HashMap<>();
                params.put(Constant.USER_ID,session.getData(Constant.ID));
                params.put(Constant.GAME_NAME,spinGameName);
                params.put(Constant.DATE,date);
                ApiConfig.RequestToVolley((result, response) -> {
                    if (result) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getBoolean(Constant.SUCCESS)) {
                                JSONArray jsonArray = jsonObject.getJSONArray(Constant.DATA);
                                session.setData(Constant.MOBILE,jsonArray.getJSONObject(0).getString(Constant.MOBILE));
                                session.setData(Constant.NAME,jsonArray.getJSONObject(0).getString(Constant.NAME));
                                session.setData(Constant.EARN,jsonArray.getJSONObject(0).getString(Constant.EARN));
                                session.setData(Constant.POINTS,jsonArray.getJSONObject(0).getString(Constant.POINTS));

                                Toast.makeText(activity, jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(activity, HomeActivity.class);
                                activity.startActivity(intent);
                                activity.finish();
                            }
                            else {
                                Toast.makeText(activity, jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                    else {
                        Toast.makeText(activity, String.valueOf(response) +String.valueOf(result), Toast.LENGTH_SHORT).show();

                    }
                    //pass url
                }, activity, Constant.DELETE_BIDS_URL, params,true);
            }
        });




        return root;
    }
    private void harufbidsList()
    {
        harufBids.clear();

        Map<String, String> params = new HashMap<>();
        params.put(Constant.USER_ID,session.getData(Constant.ID));
        params.put(Constant.GAME_NAME,spinGame.getSelectedItem().toString());
        params.put(Constant.DATE,date);
        ApiConfig.RequestToVolley((result, response) -> {

            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        bidsl2.setVisibility(View.VISIBLE);
                        btnDelete.setVisibility(View.VISIBLE);
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
        params.put(Constant.GAME_NAME,spinGameName);
        params.put(Constant.DATE,date);
        ApiConfig.RequestToVolley((result, response) -> {
            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        bidsl1.setVisibility(View.VISIBLE);
                        btnDelete.setVisibility(View.VISIBLE);
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
                    Toast.makeText(activity, String.valueOf(e), Toast.LENGTH_SHORT).show();
                }
            }
        }, activity, Constant.BIDSLIST_URL, params, true);
    }
}