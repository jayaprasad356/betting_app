package com.jp.bettingapp.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.jp.bettingapp.R;
import com.jp.bettingapp.activities.JodiActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class JodiCrossAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    TextView tvWarning;
    ArrayList<String> ExpAmtArray = new ArrayList<>();
    boolean isOnTextChanged = false;
    int ExpenseFinalTotal = 0;

    public JodiCrossAdapter(Activity activity, TextView tvWarning) {
        this.activity = activity;
        this.tvWarning = tvWarning;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.jodi_cross_lyt, parent, false);
        return new ItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, @SuppressLint("RecyclerView") int position) {
        final ItemHolder holder = (ItemHolder) holderParent;
        NumberFormat f = new DecimalFormat("00");
        long time = position;
        holder.tvtitle.setText(f.format(time));


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
                                ExpAmtArray.add("0");
                            }else {
                                ExpAmtArray.add("0");
                                ExpAmtArray.set(inposition1,editable.toString());
                                break;
                            }
                        }
                        for (int i = 0; i < ExpAmtArray.size() - 1; i++){


                            int tempTotalExpense = Integer.parseInt(ExpAmtArray.get(i));
                            ExpenseFinalTotal = ExpenseFinalTotal + tempTotalExpense;

                            ((JodiActivity)activity).setTotal(ExpenseFinalTotal);
                        }
                        Log.d("ADAPTERJODIPOSITION", ""+ExpAmtArray.toString());

                    }catch (NumberFormatException e){
                        ExpenseFinalTotal = 0;
                        for (int i = 0; i<= position; i++) {
                            int newposition = position;
                            if (i== newposition){
                                ExpAmtArray.set(newposition,"0");
                            }

                        }
                        for (int i = 0; i <= ExpAmtArray.size() - 1; i ++){
                            //Log.d("ADAPTERJODIVALUE", ""+ExpAmtArray.get(i));
                            int tempTotalExpense = Integer.parseInt(ExpAmtArray.get(i));
                            ExpenseFinalTotal = ExpenseFinalTotal + tempTotalExpense;

                        }
                        ((JodiActivity)activity).setTotal(ExpenseFinalTotal);

                    }

                }

            }
        });

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

