package com.jp.bettingapp.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jp.bettingapp.MainActivity;
import com.jp.bettingapp.R;
import com.jp.bettingapp.activities.JodiActivity;
import com.jp.bettingapp.adapter.JodiCrossAdapter;
import com.jp.bettingapp.helper.Constant;
import com.jp.bettingapp.helper.Functions;
import com.jp.bettingapp.model.Game;

import java.util.ArrayList;

public class JodiCrossFragment extends Fragment {
    View root;

    Activity activity;
    RecyclerView recyclerView;
    JodiCrossAdapter jodiCrossAdapter;

    TextView tvWarning;
    Button btnSubmit;
    Spinner spinGame;
    String spinGameName;


    public JodiCrossFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root =  inflater.inflate(R.layout.fragment_jodi_cross, container, false);

        tvWarning = root.findViewById(R.id.tvWarning);
        spinGame = root.findViewById(R.id.spinGame);
        recyclerView = root.findViewById(R.id.recyclerView);
        btnSubmit = root.findViewById(R.id.btnSubmit);
        activity = getActivity();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity,5);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);

        jodiCrossAdapter = new JodiCrossAdapter(activity,tvWarning,btnSubmit,spinGame);

        Functions.setData(activity,spinGame);


        recyclerView.setAdapter(jodiCrossAdapter);

        ((JodiActivity)activity).setTotal(0);




        return root;
    }


}