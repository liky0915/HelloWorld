package com.example.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by lester.ding on 7/18/2017.
 */

public class BroadcastDemoReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();//这一步需要访问系统网络状态权限
        if(networkInfo!=null && networkInfo.isAvailable())
            Toast.makeText(context, "Network is available", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "Network is unavailable", Toast.LENGTH_SHORT).show();
    }
}
