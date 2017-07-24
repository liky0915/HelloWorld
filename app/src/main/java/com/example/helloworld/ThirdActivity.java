package com.example.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ThirdActivity extends BaseActivity {

    private Button btn, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity_layout);

        //Activity注册时设定属性launchMode="singleTask"或者"singleTop"时，
        //那么每次启动系统会先在返回栈里找是否已经有此Activity存在，如果有则认为可以直接使用，如果不存在还是会创建新的实例（确保相同的Activity只有一个实例存在在返回栈中）
        //singleTask与singleTop不同之处在于：singleTop只找栈顶位置，而singleTask是找整个返回栈，找到后会把在这个Activity之上所有的Activity都进行出栈操作，已达到让此Activity置顶显示的目的
        Log.i("ThirdActivity", "Task id is: "+getTaskId()); //获取返回栈id

        btn = (Button) findViewById(R.id.open1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ThirdActivity.this, FirstActivity.class);
                startActivity(intent);
            }
        });

        btn2 = (Button) findViewById(R.id.finish);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCollector.finishAll();//销毁所有Activity，但进程未结束
                android.os.Process.killProcess(android.os.Process.myPid());//杀掉当前进程，killProcess只能用于杀掉当前程序的进程，不能杀其他程序
            }
        });
    }
}
