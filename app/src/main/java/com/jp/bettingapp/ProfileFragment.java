package com.jp.bettingapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jp.bettingapp.helper.Constant;
import com.jp.bettingapp.helper.Session;


public class ProfileFragment extends Fragment {
    View root;
    LinearLayout tvTransaction,tvBids,tvShare,tvAccount;
    ImageButton img_logout;
    Session session;
    TextView tvName;
    TextView tvPoints;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root =  inflater.inflate(R.layout.fragment_profile, container, false);
        session = new Session(getActivity());
        tvTransaction = root.findViewById(R.id.tvTransaction);
        tvBids = root.findViewById(R.id.tvBids);
        tvShare = root.findViewById(R.id.tvShare);
        tvAccount = root.findViewById(R.id.tvAccount);
        tvName = root.findViewById(R.id.tvName);
        img_logout = root.findViewById(R.id.img_logout);
        tvPoints = root.findViewById(R.id.tvPoints);
        tvName.setText(session.getData(Constant.NAME));
        tvPoints.setText(session.getData(Constant.POINTS));


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
        img_logout.setOnClickListener(new View.OnClickListener() {
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