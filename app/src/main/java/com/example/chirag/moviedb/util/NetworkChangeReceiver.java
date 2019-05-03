package com.example.chirag.moviedb.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.chirag.moviedb.data.Constant;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import static com.example.chirag.moviedb.data.Constant.TYPE_MOBILE;
import static com.example.chirag.moviedb.data.Constant.TYPE_WIFI;

/**
 * MovieDB
 * Created by Chirag on 11/11/18.
 */
public class NetworkChangeReceiver extends BroadcastReceiver {

        ConnectivityManager mConnectivityManager;
        NetworkInfo mActiveNetwork;
        NetworkChangeReceiver.ConnectionListener mCallBack;

        public interface ConnectionListener {
                void connectionInfo(Context context, boolean isConnected, int type);
        }

        public NetworkChangeReceiver(NetworkChangeReceiver.ConnectionListener callback) {
                Logger.addLogAdapter(new AndroidLogAdapter());
                this.mCallBack = callback;
        }

        @Override
        public void onReceive(Context context, Intent intent) {

                if (context.getApplicationInfo() != null) {
                        mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                }
                if (mConnectivityManager != null) {
                        mActiveNetwork = mConnectivityManager.getActiveNetworkInfo();
                }
                if ((mActiveNetwork != null) && (mActiveNetwork.isConnectedOrConnecting())) {
                        if (mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getType() == TYPE_WIFI) {
                                mCallBack.connectionInfo(context, true, TYPE_WIFI);
                        } else if (mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getType() == Constant.TYPE_MOBILE) {
                                mCallBack.connectionInfo(context, true, TYPE_MOBILE);
                        }
                } else {
                        mCallBack.connectionInfo(context, false, 2);
                }
        }
}
