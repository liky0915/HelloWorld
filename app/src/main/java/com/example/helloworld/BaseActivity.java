package com.example.helloworld;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by lester.ding on 7/3/2017.
 */

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取当前实例的类名
        Log.i(TAG, "Current Activity Name: " + getClass().getSimpleName());
        //将当前正在创建的Activity添加到Activity管理器中
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //将马上要销毁的Activity从Activity管理器中移除
        ActivityCollector.removeActivity(this);
    }
}
