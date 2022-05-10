package com.jp.bettingapp.adapter;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jp.bettingapp.R;

public class HarufAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    TextView tvWarning;

    public HarufAdapter(Activity activity,TextView tvWarning) {
        this.activity = activity;
        this.tvWarning = tvWarning;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.haruf_lyt, parent, false);
        return new ItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
        final ItemHolder holder = (ItemHolder) holderParent;
        holder.tvAndar.setText(""+position);
        holder.tvBadar.setText(""+position);

        holder.etAndar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    if (s != null || !s.equals("")){
                        int num = Integer.parseInt(holder.etAndar.getText().toString());
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
            public void afterTextChanged(Editable s) {

            }
        });

        holder.etBahar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try{
                    if (s != null || !s.equals("")){
                        int num = Integer.parseInt(holder.etBahar.getText().toString());
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
            public void afterTextChanged(Editable s) {

            }
        });

    }




    @Override
    public int getItemCount() {
        return 10;
    }

    static class ItemHolder extends RecyclerView.ViewHolder {

        final TextView tvAndar,tvBadar;
        EditText etAndar,etBahar;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            tvAndar = itemView.findViewById(R.id.tvAndar);
            tvBadar = itemView.findViewById(R.id.tvBadar);
            etAndar = itemView.findViewById(R.id.etAndar);
            etBahar = itemView.findViewById(R.id.etBahar);


        }
    }
}

