package com.ayu.bigbillion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.TextView;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.apache.commons.net.ntp.TimeStamp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;


public class MainActivity2 extends AppCompatActivity {

    TextView tvtime;
    long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Set the time server address
        String timeServer = "time-a.nist.gov";
        NTPUDPClient timeClient = new NTPUDPClient();
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getByName(timeServer);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        TimeInfo timeInfo = null;
        try {
            timeInfo = timeClient.getTime(inetAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
        TimeStamp timeStamp = timeInfo.getMessage().getTransmitTimeStamp();



        long timestamp = 162337961000L; // Example timestamp
        Date date = new Date(timestamp);

        System.out.println(date);

        tvtime = findViewById(R.id.tvtime);





        time(date);


    }

    private void time(Date date) {

        tvtime.setText(""+date);
    }
}