package com.jp.bettingapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GamesFragment extends Fragment {
    View root;
    TextView nametxt;
    LinearLayout lytAddpoint;
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

        lytAddpoint = root.findViewById(R.id.lytAddpoint);
        nametxt = root.findViewById(R.id.nametxt);


        lytAddpoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Add_Point_Activity.class);
                startActivity(intent);
            }
        });

        lytJodi = root.findViewById(R.id.lytJodi);

        lytJodi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),Jodi_Activity.class);
                startActivity(intent);
            }
        });

        lytHaruf = root.findViewById(R.id.lytHaruf);

        lytHaruf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Haruf_Activity.class);
                startActivity(intent);
            }
        });

        lytTransaction = root.findViewById(R.id.lytTransaction);

        lytTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),My_Transaction_Activity.class);
                startActivity(intent);
            }
        });

        lytQuickcross = root.findViewById(R.id.lytQuickcross);

        lytQuickcross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() ,Quick_Cross_Activity.class);
                startActivity(intent);
            }
        });

        lytOddeven = root.findViewById(R.id.lytOddeven);

        lytOddeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Odd_Even_Activity.class);
                startActivity(intent);
            }
        });

        lytBits = root.findViewById(R.id.lytBits);

        lytBits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Bits_History_Activity.class);
                startActivity(intent);
            }
        });

        lytWithdraw = root.findViewById(R.id.lytWithdraw);

        lytWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Withdrawal_Activity.class);
                startActivity(intent);
            }
        });

        lytSharepoints = root.findViewById(R.id.lytSharepoints);

        lytSharepoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Share_Points_Activity.class);
                startActivity(intent);
            }
        });



        return root;
    }
}