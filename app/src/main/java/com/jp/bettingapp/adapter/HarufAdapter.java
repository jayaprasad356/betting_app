package com.jp.bettingapp.adapter;

import android.app.Activity;
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

    public HarufAdapter(Activity activity) {
        this.activity = activity;
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

    }



    @Override
    public int getItemCount() {
        return 10;
    }

    static class ItemHolder extends RecyclerView.ViewHolder {

        final TextView tvAndar,tvBadar;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            tvAndar = itemView.findViewById(R.id.tvAndar);
            tvBadar = itemView.findViewById(R.id.tvBadar);


        }
    }
}

