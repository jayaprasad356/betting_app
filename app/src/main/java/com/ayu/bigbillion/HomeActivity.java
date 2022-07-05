package com.ayu.bigbillion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.ayu.bigbillion.activities.ResultChartActivity;
import com.ayu.bigbillion.fragments.BidsHistoryFragment;
import com.ayu.bigbillion.fragments.SharePointsFragment;
import com.ayu.bigbillion.fragments.TransactionFragment;
import com.ayu.bigbillion.helper.ApiConfig;
import com.ayu.bigbillion.helper.Constant;
import com.ayu.bigbillion.helper.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class HomeActivity extends AppCompatActivity  implements NavigationBarView.OnItemSelectedListener{
    GamesFragment gamesFragment;
    BidsHistoryFragment bidsHistoryFragment;
    TransactionFragment transactionFragment;
    SharePointsFragment sharePointsFragment;
    BottomNavigationView bottomNavigationView;
    Activity activity;
    Session session;
    Date date1,date2;
    int days,hours,min;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        gamesFragment = new GamesFragment();
        bidsHistoryFragment = new BidsHistoryFragment();
        transactionFragment = new TransactionFragment();
        sharePointsFragment = new SharePointsFragment();
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(this);
        activity = HomeActivity.this;
        session = new Session(activity);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, gamesFragment,"GAME").commit();

        ImageView moremenu = findViewById(R.id.moremenu);
        myUser();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");


        try {
            date1 = simpleDateFormat.parse("08:00 AM");
            date2 = simpleDateFormat.parse("10:00 AM");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long diff = date2.getTime() - date1.getTime();
        long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
        Log.d("HOME_TIME",""+minutes);


        moremenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showPopup(view);
            }
        });

    }
    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.home_menu, popup.getMenu());
        popup.show();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.resultchart:
                        Intent intent3 = new Intent(activity, ResultChartActivity.class);
                        startActivity(intent3);
                        return true;
                    case R.id.acoount:
                        Intent intent = new Intent(activity,Add_Account_Details_Activity.class);
                        startActivity(intent);
                        return true;
                    case R.id.help:
                        String number = session.getData(Constant.WHATSAPP_NUMBER);
                        String url = "https://api.whatsapp.com/send?phone=+91"+number;
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                        return true;
                    case R.id.logout:
                        Intent intent2 = new Intent(activity,LoginActivity.class);
                        startActivity(intent2);
                        finish();
                        session.setBoolean("is_logged_in",false);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item0:
                myUser();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, gamesFragment,"GAME").commit();
                return true;

            case R.id.item1:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,bidsHistoryFragment,"BIDS" ).commit();
                return true;
            case R.id.item2:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,transactionFragment,"TRANS" ).commit();
                return true;
            case R.id.item3:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,sharePointsFragment,"SHARE" ).commit();
                return true;
        }

        return false;
    }



    private void myUser() {
        Map<String, String> params = new HashMap<>();
        //request
        params.put(Constant.USER_ID,session.getData(Constant.ID));
        ApiConfig.RequestToVolley((result, response) -> {
            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        JSONArray jsonArray = jsonObject.getJSONArray(Constant.DATA);
                        session.setData(Constant.MOBILE,jsonArray.getJSONObject(0).getString(Constant.MOBILE));
                        session.setData(Constant.NAME,jsonArray.getJSONObject(0).getString(Constant.NAME));
                        session.setData(Constant.POINTS,jsonArray.getJSONObject(0).getString(Constant.POINTS));
                        gamesFragment.setText(session.getData(Constant.POINTS));

                    }
                    else {
                        //Toast.makeText(activity, jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                }



            }
            else {
                Toast.makeText(activity, String.valueOf(response) +String.valueOf(result), Toast.LENGTH_SHORT).show();

            }
            //pass url
        }, activity, Constant.MYUSER_URL, params,true);

    }

    @Override
    protected void onResume() {
        super.onResume();
        myUser();

    }


}