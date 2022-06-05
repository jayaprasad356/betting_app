package com.jp.bettingapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.jp.bettingapp.adapter.HarufAdapter;
import com.jp.bettingapp.adapter.JodiCrossAdapter;
import com.jp.bettingapp.adapter.JodiTabsAdapter;
import com.jp.bettingapp.R;

public class JodiActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    TextView tvwarning;
    ImageButton back;
    TextView tvTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jodi);

        back = findViewById(R.id.back);
        tvTotal = findViewById(R.id.tvTotal);
        tvwarning=findViewById(R.id.tvWarning);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { onBackPressed(); }
        });



        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("CROSS"));
        tabLayout.addTab(tabLayout.newTab().setText("FAST CROSS"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        viewPager =(ViewPager)findViewById(R.id.view_pager);
        JodiTabsAdapter tabsAdapter = new JodiTabsAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(tabsAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setOffscreenPageLimit(1);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    public void setTotal(int total) {
        tvTotal.setText("" + total);
    }
}