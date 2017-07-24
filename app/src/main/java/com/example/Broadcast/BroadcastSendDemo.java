package com.example.Broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.helloworld.R;

/**
 * Created by lester.ding on 7/20/2017.
 */

public class BroadcastSendDemo extends AppCompatActivity {

    private LocalBroadcastManager manager;
    private LocalBroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcast_send_demo_layout);

        //实例化本地广播管理对象
        manager = LocalBroadcastManager.getInstance(this);

        //动态注册本地广播
        IntentFilter intentFilter = new IntentFilter("com.example.broadcast.LOCAL_BROADCAST");
        receiver = new LocalBroadcastReceiver();
        manager.registerReceiver(receiver, intentFilter);
    }

    public void doClick(View v){
        switch (v.getId()){
            case R.id.normal_bc:
                Intent normal_bc = new Intent("com.example.broadcast.NORMAL_BROADCAST");
                normal_bc.putExtra("data","NORMAL_BROADCAST");
                sendBroadcast(normal_bc);
                break;

            case R.id.order_bc:
                Intent order_bc = new Intent("com.example.broadcast.ORDER_BROADCAST");
                order_bc.putExtra("data","ORDER_BROADCAST");
                sendOrderedBroadcast(order_bc, null);
                break;

            case R.id.local_bc:
                //本地广播只能在本程序内部传递，无法被外部接收器接收，且也不能接收来自外部程序的广播
                //本地广播只能动态注册（因为只能在程序内部传递，程序必须要先启动）
                Intent local_bc = new Intent("com.example.broadcast.LOCAL_BROADCAST");
                local_bc.putExtra("data","LOCAL_BROADCAST");
                manager.sendBroadcast(local_bc);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.unregisterReceiver(receiver);
    }
}
