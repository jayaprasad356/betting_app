package com.ayu.bigbillion;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ayu.bigbillion.activities.HarufActivity;
import com.ayu.bigbillion.activities.JodiActivity;
import com.ayu.bigbillion.activities.OddEvenActivity;
import com.ayu.bigbillion.activities.QuickCrossActivity;
import com.ayu.bigbillion.helper.ApiConfig;
import com.ayu.bigbillion.helper.Constant;
import com.ayu.bigbillion.helper.Session;

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
    TextView tvName,tvId,tvMobile;
    TextView tvPoints,tvPhone,newsInfo;
    Session session;
    ImageView play,whatsapp;
    ImageView imgShare;




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
        whatsapp = root.findViewById(R.id.whatsapp);
        tvId=root.findViewById(R.id.tv_id);
        tvMobile=root.findViewById(R.id.tv_mobile_number);
        newsInfo = root.findViewById(R.id.newsInfo);
        imgShare = root.findViewById(R.id.imgShare);
        tvId.setText("Id: "+session.getData(Constant.ID));
        tvMobile.setText("Mobile: "+session.getData(Constant.MOBILE));
        tvName.setText(session.getData(Constant.NAME));
        tvPoints.setText(session.getData(Constant.POINTS));
        newsInfo.setText(session.getData(Constant.NEWS_INFO));

        lytJodi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), JodiActivity.class);
                startActivity(intent);
            }
        });
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = session.getData(Constant.WHATSAPP_NUMBER);
                String url = "https://api.whatsapp.com/send?phone=+91"+number;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(session.getData(Constant.YOUTUBE_LINK)));
                startActivity(webIntent);
            }
        });

        imgShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String shareMessage= "\nLet me recommend you this application\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch(Exception e) {
                    //e.toString();
                }


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
                        session.setData(Constant.NEWS_INFO,jsonArray.getJSONObject(0).getString(Constant.NEWS_INFO));
                        session.setData(Constant.SHARE_LINK,jsonArray.getJSONObject(0).getString(Constant.SHARE_LINK));
                        session.setData(Constant.MIN_DEPOSIT,jsonArray.getJSONObject(0).getString(Constant.MIN_DEPOSIT));
                        session.setData(Constant.MIN_WITHDRAWAL,jsonArray.getJSONObject(0).getString(Constant.MIN_WITHDRAWAL));
                        session.setData(Constant.MAX_WITHDRAWAL,jsonArray.getJSONObject(0).getString(Constant.MAX_WITHDRAWAL));
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
