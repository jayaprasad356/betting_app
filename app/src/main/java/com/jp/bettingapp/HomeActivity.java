package com.jp.bettingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.jp.bettingapp.activities.HarufActivity;
import com.jp.bettingapp.activities.JodiActivity;
import com.jp.bettingapp.activities.OddEvenActivity;
import com.jp.bettingapp.activities.QuickCrossActivity;
import com.jp.bettingapp.activities.ResultChartActivity;
import com.jp.bettingapp.fragments.BidsHistoryFragment;
import com.jp.bettingapp.fragments.SharePointsFragment;
import com.jp.bettingapp.fragments.TransactionFragment;
import com.jp.bettingapp.helper.Session;

public class HomeActivity extends AppCompatActivity  implements NavigationBarView.OnItemSelectedListener{
    GamesFragment gamesFragment;
    BidsHistoryFragment bidsHistoryFragment;
    TransactionFragment transactionFragment;
    SharePointsFragment sharePointsFragment;
    BottomNavigationView bottomNavigationView;
    Activity activity;
    Session session;


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
}