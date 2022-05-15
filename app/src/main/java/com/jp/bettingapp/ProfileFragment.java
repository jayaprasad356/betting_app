package com.jp.bettingapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jp.bettingapp.helper.Constant;
import com.jp.bettingapp.helper.Session;


public class ProfileFragment extends Fragment {
    View root;
    LinearLayout tvAddPoints,tvTransaction,tvBids,tvWithdrawal,tvShare,tvAccount,tvLogout;
    Session session;
    TextView tvName;
    TextView tvPoints;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root =  inflater.inflate(R.layout.fragment_profile, container, false);
        session = new Session(getActivity());
        tvAddPoints = root.findViewById(R.id.tvAddPoints);
        tvTransaction = root.findViewById(R.id.tvTransaction);
        tvBids = root.findViewById(R.id.tvBids);
        tvWithdrawal = root.findViewById(R.id.tvWithdrawal);
        tvShare = root.findViewById(R.id.tvShare);
        tvAccount = root.findViewById(R.id.tvAccount);
        tvLogout = root.findViewById(R.id.tvLogout);
        tvName = root.findViewById(R.id.tvName);
        tvPoints = root.findViewById(R.id.tvPoints);
        tvName.setText(session.getData(Constant.NAME));
        tvPoints.setText(session.getData(Constant.POINTS));

        tvAddPoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),AddPointsActivity.class);
                startActivity(intent);

            }
        });

        tvTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyTransactionActivity.class);
                startActivity(intent);

            }
        });

        tvBids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),BidsHistoryActivity.class);
                startActivity(intent);

            }
        });
        tvWithdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WithdrawalActivity.class);
                startActivity(intent);

            }
        });
        tvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SharePointsActivity.class);
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
        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
                session.setBoolean("is_logged_in",false);

            }
        });


        return  root;
    }
}