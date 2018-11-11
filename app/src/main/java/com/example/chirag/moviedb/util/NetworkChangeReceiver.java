package com.example.chirag.moviedb.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.widget.Toast;

import com.example.chirag.moviedb.moviedetail.MovieDetailActivity;

/**
 * MovieDB
 * Created by Chirag on 11/11/18.
 */
public class NetworkChangeReceiver extends BroadcastReceiver {

    public NetworkChangeReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int wifiStateExtra = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,
                WifiManager.WIFI_STATE_UNKNOWN);
        switch (wifiStateExtra) {
            case WifiManager.WIFI_STATE_ENABLED:
                Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
                new MovieDetailActivity();
                break;
            case WifiManager.WIFI_STATE_DISABLED:
                Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
