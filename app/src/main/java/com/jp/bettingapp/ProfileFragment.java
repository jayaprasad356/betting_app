package com.jp.bettingapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ProfileFragment extends Fragment {
    View root;
    LinearLayout tvTransaction,tvBids,tvWithdrawal,tvShare,tvAccount;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root =  inflater.inflate(R.layout.fragment_profile, container, false);
        tvTransaction = root.findViewById(R.id.tvTransaction);
        tvBids = root.findViewById(R.id.tvBids);
        tvWithdrawal = root.findViewById(R.id.tvWithdrawal);
        tvShare = root.findViewById(R.id.tvShare);
        tvAccount = root.findViewById(R.id.tvAccount);

        tvTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),My_Transaction_Activity.class);
                startActivity(intent);

            }
        });

        tvBids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Bits_History_Activity.class);
                startActivity(intent);

            }
        });
        tvWithdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Withdrawal_Activity.class);
                startActivity(intent);

            }
        });
        tvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Share_Points_Activity.class);
                startActivity(intent);

            }
        });
        tvAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Add_Account_Details_Activity.class);
                startActivity(intent);

            }
        });


        return  root;
    }
}