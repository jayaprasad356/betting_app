package com.jp.bettingapp.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.jp.bettingapp.R;
import com.jp.bettingapp.adapter.JodiCrossAdapter;
import com.jp.bettingapp.adapter.JodiFastCrossAdapter;


public class JodiFastCrossFragment extends Fragment {

    View root;

    Activity activity;
    RecyclerView recyclerView;
    JodiFastCrossAdapter jodiFastCrossAdapter;
    Button btnSubmit;

    TextView tvWarning;
    Spinner spinGame;



    public JodiFastCrossFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_jodi_fast_cross, container, false);

        tvWarning = root.findViewById(R.id.tvWarning);
        recyclerView = root.findViewById(R.id.recyclerView);
        btnSubmit = root.findViewById(R.id.btnSubmit);
        spinGame = root.findViewById(R.id.spinGame);
        activity = getActivity();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);

        jodiFastCrossAdapter = new JodiFastCrossAdapter(activity,tvWarning,btnSubmit,spinGame);
        recyclerView.setAdapter(jodiFastCrossAdapter);
        return root;
    }
}