package com.jp.bettingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import me.ibrahimsn.lib.SmoothBottomBar;

public class MainActivity extends AppCompatActivity {

    public static SmoothBottomBar bottomNavigationView;
    public static FragmentManager fm = null;
    public static Fragment gameFragment;
    public static Fragment active;
    public static boolean gameClicked = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fm = getSupportFragmentManager();
        gameFragment = new GamesFragment();
        active = gameFragment;
        gameClicked = true;

        fm.beginTransaction().add(R.id.container, gameFragment).commit();

        bottomNavigationView.setItemActiveIndex(0);

    }
}