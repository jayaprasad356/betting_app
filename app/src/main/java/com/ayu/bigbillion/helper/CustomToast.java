package com.ayu.bigbillion.helper;


import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ayu.bigbillion.R;


public class CustomToast {
    private Context context;

    public void show(Activity activity, String textMsg) {
        this.context = activity;
        LayoutInflater li = ((Activity) context).getLayoutInflater();
        View layout = li.inflate(R.layout.custom_toast, ((Activity) context).findViewById(R.id.custom_toast_layout));
        TextView text = layout.findViewById(R.id.custom_toast_message);
        text.setText(textMsg);
        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}
