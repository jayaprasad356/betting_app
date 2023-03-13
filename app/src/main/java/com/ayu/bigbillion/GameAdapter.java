package com.ayu.bigbillion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ayu.bigbillion.model.Game;

import java.util.List;

public class GameAdapter extends ArrayAdapter<Game> {
    private final Context context;
    private final List<Game> games;

    public GameAdapter(Context context, List<Game> games) {
        super(context, R.layout.spinner_dropdown_item, games);
        this.context = context;
        this.games = games;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.spinner_dropdown_item, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.texts);
        textView.setText(games.get(position).getGametime());
        return rowView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.spinner_dropdown_item, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.texts);
        textView.setText(games.get(position).getGametime());
        return rowView;
    }
}
