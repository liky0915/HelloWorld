package com.example.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class OrderBroadcastReceiver2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String data = intent.getStringExtra("data");
        Toast.makeText(context, "Received " + data + " in Order Broadcast Receiver2!", Toast.LENGTH_SHORT).show();
        abortBroadcast(); //优先级高的接收器可截断广播，后续接收器不再会接收广播
    }
}
