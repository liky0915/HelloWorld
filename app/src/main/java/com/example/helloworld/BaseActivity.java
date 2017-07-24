package com.example.helloworld;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.BestPractice.BroadcastBestPractice.ForceOfflineReceiver;

/**
 * Created by lester.ding on 7/3/2017.
 */

public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";
    private ForceOfflineReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取当前实例的类名
        Log.i(TAG, "Current Activity Class Name: " + getClass().getSimpleName());
        //将当前正在创建的Activity添加到Activity管理器中
        ActivityCollector.addActivity(this);
    }

    //强制下线需始终保证只有栈顶位置的Activity才能接收广播
    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.broadcastbestpractice.FORCE_OFFLINE");
        receiver = new ForceOfflineReceiver();
        registerReceiver(receiver, intentFilter);
    }

    //当Activity失去栈顶位置时就会注销广播接收器
    @Override
    protected void onPause() {
        super.onPause();
        if(receiver!=null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //将马上要销毁的Activity从Activity管理器中移除
        ActivityCollector.removeActivity(this);
    }
}
