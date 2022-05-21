package com.jp.bettingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.jp.bettingapp.helper.Session;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class MainActivity extends AppCompatActivity {

    public static SmoothBottomBar bottomNavigationView;
    public static FragmentManager fm = null;
    public static Fragment gameFragment,resultFragment,profileFragment;
    public static Fragment active;
    public static boolean gameClicked = false, bidsClicked = false, transactionClicked = false, shareClicked = false;
    Session session;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new Session(MainActivity.this);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fm = getSupportFragmentManager();
        gameFragment = new GamesFragment();
        resultFragment = new ResultFragment();
        profileFragment = new ProfileFragment();
        active = gameFragment;
        gameClicked = true;
        bidsClicked = false;
        transactionClicked = false;

        fm.beginTransaction().add(R.id.container, gameFragment).commit();

        bottomNavigationView.setItemActiveIndex(0);

        bottomNavigationView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public boolean onItemSelect(int i) {
                switch (i) {
                    case 0:
                        if (!gameClicked) {
                            fm.beginTransaction().add(R.id.container, gameFragment).show(gameFragment).hide(active).commit();
                            gameClicked = true;
                        } else {
                            fm.beginTransaction().show(gameFragment).hide(active).commit();
                        }
                        active = gameFragment;
                        break;
                    case 1:
                        if (!bidsClicked) {
                            fm.beginTransaction().add(R.id.container, resultFragment).show(resultFragment).hide(active).commit();
                            bidsClicked = true;
                        } else {
                            fm.beginTransaction().show(resultFragment).hide(active).commit();
                        }
                        active = resultFragment;

                        break;
                    case 2:
                        if (!transactionClicked) {
                            fm.beginTransaction().add(R.id.container, profileFragment).show(profileFragment).hide(active).commit();
                            transactionClicked = true;
                        } else {
                            fm.beginTransaction().show(profileFragment).hide(active).commit();
                        }
                        active = profileFragment;

                        break;
                }


                return false;

            }
        });

    }
}