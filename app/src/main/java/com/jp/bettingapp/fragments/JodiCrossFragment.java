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

import com.jp.bettingapp.R;
import com.jp.bettingapp.adapter.JodiCrossAdapter;

public class JodiCrossFragment extends Fragment {
    View root;

    public static Activity activity;
    public static RecyclerView recyclerView;
    public static JodiCrossAdapter jodiCrossAdapter;


    public JodiCrossFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root =  inflater.inflate(R.layout.fragment_jodi_cross, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        activity = getActivity();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity,5);
        recyclerView.setLayoutManager(gridLayoutManager);

        jodiCrossAdapter = new JodiCrossAdapter(activity);
        recyclerView.setAdapter(jodiCrossAdapter);

        return root;
    }
}