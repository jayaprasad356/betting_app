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

import com.jp.bettingapp.R;
import com.jp.bettingapp.adapter.JodiCrossAdapter;
import com.jp.bettingapp.adapter.JodiFastCrossAdapter;


public class JodiFastCrossFragment extends Fragment {

    View root;

    public static Activity activity;
    public static RecyclerView recyclerView;
    public static JodiFastCrossAdapter jodiFastCrossAdapter;



    public JodiFastCrossFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_jodi_fast_cross, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        activity = getActivity();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);

        jodiFastCrossAdapter = new JodiFastCrossAdapter(activity);
        recyclerView.setAdapter(jodiFastCrossAdapter);
        return root;
    }
}