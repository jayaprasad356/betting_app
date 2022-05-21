package com.jp.bettingapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jp.bettingapp.activities.HarufActivity;
import com.jp.bettingapp.activities.JodiActivity;
import com.jp.bettingapp.activities.OddEvenActivity;
import com.jp.bettingapp.activities.QuickCrossActivity;
import com.jp.bettingapp.helper.Constant;
import com.jp.bettingapp.helper.Session;

public class GamesFragment extends Fragment {
    View root;

    LinearLayout lytJodi;
    LinearLayout lytHaruf;
    LinearLayout lytTransaction;
    LinearLayout lytQuickcross;
    LinearLayout lytOddeven;
    LinearLayout lytBits;
    LinearLayout lytWithdrawal;
    LinearLayout lytDeposit;
    LinearLayout lytSharepoints;
    TextView tvName;
    TextView tvPoints;
    Session session;




    public GamesFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_games, container, false);
        session = new Session(getActivity());


        lytJodi = root.findViewById(R.id.lytJodi);
        tvName = root.findViewById(R.id.tvName);
        tvPoints = root.findViewById(R.id.tvPoints);
        lytDeposit = root.findViewById(R.id.lytDeposit);
        lytHaruf = root.findViewById(R.id.lytHaruf);
        lytQuickcross = root.findViewById(R.id.lytQuickcross);
        lytOddeven = root.findViewById(R.id.lytOddeven);
        lytWithdrawal = root.findViewById(R.id.lytWithdrawal);
        tvName.setText(session.getData(Constant.NAME));
        tvPoints.setText(session.getData(Constant.POINTS));

        lytJodi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), JodiActivity.class);
                startActivity(intent);
            }
        });



        lytDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),AddPointsActivity.class);
                startActivity(intent);
            }
        });



        lytHaruf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HarufActivity.class);
                startActivity(intent);
            }
        });





        lytQuickcross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , QuickCrossActivity.class);
                startActivity(intent);
            }
        });



        lytOddeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OddEvenActivity.class);
                startActivity(intent);
            }
        });



        lytWithdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WithdrawalActivity.class);
                startActivity(intent);
            }
        });




        return root;
    }






}
