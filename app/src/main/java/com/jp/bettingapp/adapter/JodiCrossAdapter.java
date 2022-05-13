package com.jp.bettingapp.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.jp.bettingapp.MainActivity;
import com.jp.bettingapp.OTP_Activity;
import com.jp.bettingapp.R;
import com.jp.bettingapp.activities.JodiActivity;
import com.jp.bettingapp.helper.ApiConfig;
import com.jp.bettingapp.helper.Constant;
import com.jp.bettingapp.helper.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JodiCrossAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    TextView tvWarning;
    Button btnSubmit;
    ArrayList<String> PointsArray = new ArrayList<>();
    ArrayList<String> NumbersArray = new ArrayList<>();
    boolean isOnTextChanged = false;
    int ExpenseFinalTotal = 0;
    Session session;
    Spinner spinGame;
    String TotalPoints = "";

    public JodiCrossAdapter(Activity activity, TextView tvWarning, Button btnSubmit, Spinner spinGame) {
        this.activity = activity;
        this.tvWarning = tvWarning;
        this.btnSubmit = btnSubmit;
        this.spinGame = spinGame;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.jodi_cross_lyt, parent, false);
        return new ItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, @SuppressLint("RecyclerView") int position) {
        session = new Session(activity);
        final ItemHolder holder = (ItemHolder) holderParent;
        NumberFormat f = new DecimalFormat("00");
        long time = position;
        holder.tvtitle.setText(f.format(time));

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NumbersArray.clear();
                for (int i = 0; i < PointsArray.size(); i++){
                    NumbersArray.add(String.valueOf(i));
                }
                submitGame();
            }
        });


        holder.etNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isOnTextChanged = true;



                try{
                    if (s != null || !s.equals("")){
                        int num = Integer.parseInt(holder.etNumber.getText().toString());
                        if (num%5 == 0){
                            tvWarning.setVisibility(View.GONE);
                        }
                        else{
                            tvWarning.setVisibility(View.VISIBLE);
                        }

                    }

                }catch (Exception e){

                }





            }

            @Override
            public void afterTextChanged(Editable editable) {
                ExpenseFinalTotal = 0;
                try{
                    if (!editable.toString().equals("")){
                        int num = Integer.parseInt(holder.etNumber.getText().toString());
                        if (num%5 == 0){
                            if (isOnTextChanged){
                                isOnTextChanged = false;
                                try {
                                    ExpenseFinalTotal = 0;
                                    for (int i = 0; i<= position; i++) {
                                        int inposition1 = position;
                                        if (i != position) {
                                            PointsArray.add("0");
                                        }else {
                                            PointsArray.add("0");
                                            PointsArray.set(inposition1,editable.toString());
                                            break;
                                        }
                                    }
                                    for (int i = 0; i < PointsArray.size() - 1; i++){


                                        int tempTotalExpense = Integer.parseInt(PointsArray.get(i));
                                        ExpenseFinalTotal = ExpenseFinalTotal + tempTotalExpense;

                                    }
                                    TotalPoints = ""+ExpenseFinalTotal;

                                    ((JodiActivity)activity).setTotal(ExpenseFinalTotal);
                                    Log.d("ADAPTERJODIPOSITION", ""+ PointsArray.toString());

                                }catch (NumberFormatException e){
                                    ExpenseFinalTotal = 0;
                                    for (int i = 0; i<= position; i++) {
                                        int newposition = position;
                                        if (i== newposition){
                                            PointsArray.set(newposition,"0");
                                        }

                                    }
                                    for (int i = 0; i <= PointsArray.size() - 1; i ++){
                                        //Log.d("ADAPTERJODIVALUE", ""+ExpAmtArray.get(i));
                                        int tempTotalExpense = Integer.parseInt(PointsArray.get(i));
                                        ExpenseFinalTotal = ExpenseFinalTotal + tempTotalExpense;


                                    }
                                    TotalPoints = ""+ExpenseFinalTotal;
                                    ((JodiActivity)activity).setTotal(ExpenseFinalTotal);

                                }

                            }
                        }
                        else{
                            //tvWarning.setVisibility(View.VISIBLE);
                        }

                    }

                }catch (Exception e){

                }



            }
        });

    }

    private void submitGame()
    {
        Map<String, String> params = new HashMap<>();
        //request
        params.put(Constant.USER_ID,"1");
        params.put(Constant.GAME_NAME,"GD");
        params.put(Constant.GAME_TYPE,"jodi");
        params.put(Constant.GAME_METHOD,"cross");
        params.put(Constant.POINTS,PointsArray.toString());
        params.put(Constant.NUMBER,NumbersArray.toString());
        params.put(Constant.TOTAL_POINTS,TotalPoints);
        ApiConfig.RequestToVolley((result, response) -> {
            Log.d("JODICROSSADAPTERRES",response);
            if (result) {

                try {
                    JSONObject jsonObject = new JSONObject(response);


                    if (jsonObject.getBoolean(Constant.SUCCESS)) {

                        Intent intent = new Intent(activity, MainActivity.class);
                        activity.startActivity(intent);
                        activity.finish();
                    }
                    else {
                        Toast.makeText(activity, jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();


                    }
                } catch (JSONException e){
                    e.printStackTrace();
                }



            }
            else {
                Toast.makeText(activity, String.valueOf(response) +String.valueOf(result), Toast.LENGTH_SHORT).show();

            }
            //pass url
        }, activity, Constant.JODI_URL, params,true);


    }


    @Override
    public int getItemCount() {
        return 100;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ItemHolder extends RecyclerView.ViewHolder {

        final EditText etNumber;
        final TextView tvtitle;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            etNumber = itemView.findViewById(R.id.etNumber);
            tvtitle = itemView.findViewById(R.id.tvtitle);


        }
    }
}

