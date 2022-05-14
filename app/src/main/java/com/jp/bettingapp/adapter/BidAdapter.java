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

public class BidAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;

    public BidAdapter(Activity activity) {
        this.activity = activity;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.bids_lyt, parent, false);
        return new ItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
        final ItemHolder holder = (ItemHolder) holderParent;
        holder.tvNumber.setText(""+position);
        if (position < 10){
            holder.tvA.setText("0");
            holder.tvB.setText("0");

        }

        holder.tvJ.setText("0");
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }





    @Override
    public int getItemCount() {
        return 100;
    }

    static class ItemHolder extends RecyclerView.ViewHolder {

        TextView tvNumber,tvA,tvB,tvJ;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            tvA = itemView.findViewById(R.id.tvA);
            tvB = itemView.findViewById(R.id.tvB);
            tvJ = itemView.findViewById(R.id.tvJ);


        }
    }
}

