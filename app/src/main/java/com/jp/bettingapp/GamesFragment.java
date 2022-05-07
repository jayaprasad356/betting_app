package com.jp.bettingapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jp.bettingapp.activities.HarufActivity;
import com.jp.bettingapp.activities.JodiActivity;
import com.jp.bettingapp.activities.OddEvenActivity;
import com.jp.bettingapp.activities.QuickCrossActivity;

public class GamesFragment extends Fragment {
    View root;

    LinearLayout lytJodi;
    LinearLayout lytHaruf;
    LinearLayout lytTransaction;
    LinearLayout lytQuickcross;
    LinearLayout lytOddeven;
    LinearLayout lytBits;
    LinearLayout lytWithdraw;
    LinearLayout lytSharepoints;




    public GamesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_games, container, false);


        lytJodi = root.findViewById(R.id.lytJodi);

        lytJodi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), JodiActivity.class);
                startActivity(intent);
            }
        });

        lytHaruf = root.findViewById(R.id.lytHaruf);

        lytHaruf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HarufActivity.class);
                startActivity(intent);
            }
        });



        lytQuickcross = root.findViewById(R.id.lytQuickcross);

        lytQuickcross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , QuickCrossActivity.class);
                startActivity(intent);
            }
        });

        lytOddeven = root.findViewById(R.id.lytOddeven);

        lytOddeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OddEvenActivity.class);
                startActivity(intent);
            }
        });


        return root;
    }
}