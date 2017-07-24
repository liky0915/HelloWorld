package com.example.broadcast;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.helloworld.R;

/**
 * Created by lester.ding on 7/18/2017.
 */

public class BroadcastDemo extends AppCompatActivity {

    private BroadcastDemoReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcast_demo_layout);

        //添加接收器过滤条件，即只接受符合该Action动作的系统广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        //创建接收器实例
        receiver = new BroadcastDemoReceiver();
        //动态注册广播接收器（只有当程序启动后才能接收到广播，而静态注册任何时候都可以接收到广播）
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //动态注册的广播接收器一定要在页面销毁时（按back键）注销掉，静态注册则不需要，系统会自动注销
        unregisterReceiver(receiver);
    }
}
