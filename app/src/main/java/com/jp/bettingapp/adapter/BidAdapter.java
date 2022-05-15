package com.jp.bettingapp.adapter;

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
import com.jp.bettingapp.helper.ApiConfig;
import com.jp.bettingapp.helper.Constant;
import com.jp.bettingapp.helper.Session;
import com.jp.bettingapp.model.BIDS;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BidAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    Button btnSubmit;
    Session session;
    final ArrayList<BIDS> bids;

    public BidAdapter(Activity activity, ArrayList<BIDS> bids) {
        this.activity = activity;
        this.bids = bids;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.bids_lyt, parent, false);
        return new ItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
        session = new Session(activity);
        final ItemHolder holder = (ItemHolder) holderParent;
        final BIDS bids1 = bids.get(position);
        holder.tvNumber.setText(bids1.getNumber());
        holder.tvPoints.setText(bids1.getPoints());
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getItemCount() {
        return bids.size();
    }

    static class ItemHolder extends RecyclerView.ViewHolder {

        TextView tvNumber,tvPoints;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            tvPoints = itemView.findViewById(R.id.tvPoints);


        }
    }
}

