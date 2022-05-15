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
import com.jp.bettingapp.R;
import com.jp.bettingapp.activities.JodiActivity;
import com.jp.bettingapp.helper.ApiConfig;
import com.jp.bettingapp.helper.Constant;
import com.jp.bettingapp.helper.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JodiFastCrossAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    TextView tvWarning;
    Button btnSubmit;
    ArrayList<String> addvalue = new ArrayList<>();
    ArrayList<String> PointsArray = new ArrayList<>();
    ArrayList<String> NumbersArray = new ArrayList<>();
    boolean isOnTextChanged = false;
    boolean isOnTextChanged2 = false;
    int ExpenseFinalTotal = 0;
    int NumberFinalTotal = 0;
    ArrayList<String> newPoints = new ArrayList<>();
    ArrayList<String> newNumbers = new ArrayList<>();
    String TotalPoints = "";
    Session session;
    Spinner spinGame;

    public JodiFastCrossAdapter(Activity activity, TextView tvWarning, Button btnSubmit, Spinner spinGame) {
        this.activity = activity;
        this.tvWarning = tvWarning;
        this.btnSubmit = btnSubmit;
        this.spinGame = spinGame;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.jodi_fastcross_lyt, parent, false);
        return new ItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent,  @SuppressLint("RecyclerView")int position) {
        final ItemHolder holder = (ItemHolder) holderParent;
        session = new Session(activity);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPoints.clear();
                newNumbers.clear();
                int totalPoints = 0;
                int totalNumbers = 0;
                if (PointsArray.size() > 10){
                    totalPoints = 10;


                }
                else {
                    totalPoints = PointsArray.size();
                }

                if (NumbersArray.size() > 10){
                    totalNumbers = 10;


                }
                else {
                    totalNumbers = NumbersArray.size();
                }



                for (int i = 0; i < totalPoints; i++){
                    newPoints.add(PointsArray.get(i));

                }
                for (int i = 0; i < totalNumbers; i++){
                    newNumbers.add(NumbersArray.get(i));

                }
                if (spinGame.getSelectedItemPosition() != 0 ){
                    submitGame();

                }
                else {
                    Toast.makeText(activity, "Please,Select Game", Toast.LENGTH_SHORT).show();
                }

                Log.d("JODIFASTCROSS",newPoints.toString());
                Log.d("JODIFASTCROSSNUMBER",newNumbers.toString());
            }
        });



        holder.etPoints.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isOnTextChanged = true;
                try{
                    if (s != null || !s.equals("")){
                        int num = Integer.parseInt(holder.etPoints.getText().toString());
                        int ans = num/5;
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

                        }catch (NumberFormatException e){
                            ExpenseFinalTotal = 0;
                            for (int i = 0; i<= position; i++) {
                                int newposition = position;
                                if (i== newposition){
                                    PointsArray.set(newposition,"0");
                                }

                            }
                            for (int i = 0; i <= PointsArray.size() - 1; i ++){
                                int tempTotalExpense = Integer.parseInt(PointsArray.get(i));
                                ExpenseFinalTotal = ExpenseFinalTotal + tempTotalExpense;

                            }
                            TotalPoints = ""+ExpenseFinalTotal;
                            ((JodiActivity)activity).setTotal(ExpenseFinalTotal);

                        }

                    }
                }
        });
        holder.etNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isOnTextChanged2 = true;




            }

            @Override
            public void afterTextChanged(Editable editable) {
                NumberFinalTotal = 0;
                if (isOnTextChanged2){
                    isOnTextChanged2 = false;
                    try {
                        NumberFinalTotal = 0;
                        for (int i = 0; i<= position; i++) {
                            int inposition1 = position;
                            if (i != position) {
                                NumbersArray.add("0");
                            }else {
                                NumbersArray.add("0");
                                NumbersArray.set(inposition1,editable.toString());
                                break;
                            }
                        }
                        for (int i = 0; i < NumbersArray.size() - 1; i++){
                            int tempTotalExpense = Integer.parseInt(NumbersArray.get(i));
                            NumberFinalTotal = NumberFinalTotal + tempTotalExpense;
                        }

                    }catch (NumberFormatException e){
                        NumberFinalTotal = 0;
                        for (int i = 0; i<= position; i++) {
                            int newposition = position;
                            if (i== newposition){
                                NumbersArray.set(newposition,"0");
                            }

                        }
                        for (int i = 0; i <= NumbersArray.size() - 1; i ++){
                            int tempTotalExpense = Integer.parseInt(NumbersArray.get(i));
                            NumberFinalTotal = NumberFinalTotal + tempTotalExpense;
                        }

                    }

                }
            }


        });

    }
    private void submitGame()
    {
        Map<String, String> params = new HashMap<>();
        //request
        params.put(Constant.USER_ID,session.getData(Constant.ID));
        params.put(Constant.GAME_NAME,spinGame.getSelectedItem().toString());
        params.put(Constant.GAME_TYPE,"jodi");
        params.put(Constant.GAME_METHOD,"fastcross");
        params.put(Constant.POINTS,newPoints.toString());
        params.put(Constant.NUMBER,newNumbers.toString());
        params.put(Constant.TOTAL_POINTS,TotalPoints);
        ApiConfig.RequestToVolley((result, response) -> {
            if (result) {

                try {
                    JSONObject jsonObject = new JSONObject(response);


                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        JSONArray jsonArray = jsonObject.getJSONArray(Constant.DATA);
                        session.setData(Constant.MOBILE,jsonArray.getJSONObject(0).getString(Constant.MOBILE));
                        session.setData(Constant.NAME,jsonArray.getJSONObject(0).getString(Constant.NAME));
                        session.setData(Constant.EARN,jsonArray.getJSONObject(0).getString(Constant.EARN));
                        session.setData(Constant.POINTS,jsonArray.getJSONObject(0).getString(Constant.POINTS));

                        Toast.makeText(activity, jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();


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
        }, activity, Constant.GAME_URL, params,true);


    }

    @Override
    public int getItemCount() {
        return 10;
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ItemHolder extends RecyclerView.ViewHolder {

        final EditText etNumber,etPoints;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            etNumber = itemView.findViewById(R.id.etNumber);
            etPoints = itemView.findViewById(R.id.etPoints);


        }
    }
}

