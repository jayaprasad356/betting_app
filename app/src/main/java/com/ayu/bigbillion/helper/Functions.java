package com.ayu.bigbillion.helper;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.ayu.bigbillion.model.Game;

import java.util.ArrayList;

public class Functions {
    public static void setData(Activity activity, Spinner spinGame) {

        ArrayList<Game> countryList = new ArrayList<>();
        //Add countries




        countryList.add(new Game("", "Select Game"));
        countryList.add(new Game("FD", "FD (Faridabad) 06:00 am to 05:45 pm"));
        countryList.add(new Game("GB", "GB (Gaziabad) 06:00 am to 07:45 pm "));
        countryList.add(new Game("GL", "GL (Gali) 06:00 am to 10:45 pm"));
        countryList.add(new Game("", "Next Day Games"));
        countryList.add(new Game("DS", "DS (Disawar) 06:00 am to 02:10 pm"));

        //fill data in spinner
        ArrayAdapter<Game> adapter = new ArrayAdapter<Game>(activity, android.R.layout.simple_spinner_dropdown_item, countryList);
        spinGame.setAdapter(adapter);
        //spinGame.setSelection(adapter.getPosition());//Optional to set the selected item.
    }
}
