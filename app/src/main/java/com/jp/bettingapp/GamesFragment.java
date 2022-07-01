package com.jp.bettingapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jp.bettingapp.activities.HarufActivity;
import com.jp.bettingapp.activities.JodiActivity;
import com.jp.bettingapp.activities.OddEvenActivity;
import com.jp.bettingapp.activities.QuickCrossActivity;
import com.jp.bettingapp.helper.ApiConfig;
import com.jp.bettingapp.helper.Constant;
import com.jp.bettingapp.helper.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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
    TextView tvPoints,tvPhone;
    Session session;
    ImageView play;




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
        tvPhone = root.findViewById(R.id.tvPhone);
        play = root.findViewById(R.id.play);
        tvName.setText(session.getData(Constant.NAME));
        tvPoints.setText(session.getData(Constant.POINTS));

        lytJodi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), JodiActivity.class);
                startActivity(intent);
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(session.getData(Constant.YOUTUBE_LINK)));
                startActivity(webIntent);
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
        setText(session.getData(Constant.POINTS));




        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        settings();
    }
    private void settings() {
        Map<String, String> params = new HashMap<>();
        ApiConfig.RequestToVolley((result, response) -> {
            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        JSONArray jsonArray = jsonObject.getJSONArray(Constant.DATA);
                        session.setData(Constant.WHATSAPP_NUMBER,jsonArray.getJSONObject(0).getString(Constant.WHATSAPP_NUMBER));
                        session.setData(Constant.YOUTUBE_LINK,jsonArray.getJSONObject(0).getString(Constant.YOUTUBE_LINK));
                        tvPhone.setText(session.getData(Constant.WHATSAPP_NUMBER));

                    }
                    else {
                        //Toast.makeText(activity, jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                }



            }

            //pass url
        }, getActivity(), Constant.SETTINGS_URL, params,false);
    }

    public void setText(String data) {
        tvPoints.setText(data);
    }


}
