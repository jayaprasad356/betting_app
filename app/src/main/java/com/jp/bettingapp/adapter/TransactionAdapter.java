package com.jp.bettingapp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jp.bettingapp.R;
import com.jp.bettingapp.helper.Session;
import com.jp.bettingapp.model.BIDS;
import com.jp.bettingapp.model.Transaction;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    Session session;
    final ArrayList<Transaction> transactions;

    public TransactionAdapter(Activity activity, ArrayList<Transaction> transactions) {
        this.activity = activity;
        this.transactions = transactions;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.transaction_lyt, parent, false);
        return new ItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
        session = new Session(activity);
        final ItemHolder holder = (ItemHolder) holderParent;
        final Transaction transaction = transactions.get(position);
        holder.tvtitle.setText("Deducted On "+transaction.getGame_type() +" \n"+transaction.getGame_name());
        holder.tvTime.setText(transaction.getDate_created());
        holder.tvPoints.setText(transaction.getPoints());
        holder.tvBalance.setText("Balanace "+ transaction.getBalance());
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getItemCount() {
        return transactions.size();
    }

    static class ItemHolder extends RecyclerView.ViewHolder {

        TextView tvtitle,tvTime,tvPoints,tvBalance;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            tvtitle = itemView.findViewById(R.id.tvtitle);
            tvPoints = itemView.findViewById(R.id.tvPoints);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvBalance = itemView.findViewById(R.id.tvBalance);


        }
    }
}

