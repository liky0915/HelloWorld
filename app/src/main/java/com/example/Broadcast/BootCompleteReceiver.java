package com.example.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //onReceive中不可以开启线程的，所以在此不要做任何耗时操作
        Toast.makeText(context, "Boot Complete!", Toast.LENGTH_SHORT).show();
    }
}
