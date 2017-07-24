package com.example.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends BaseActivity {

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity_layout);

        //Activity注册时设定属性launchMode="standard"或者不设定时（默认就是standard），
        //那么每次启动都会创建一个新的实例入栈并处于栈顶，系统不会在乎是否已经有此Activity在返回栈中存在
        Log.i("FirstActivity", "Task id is: "+getTaskId()); //获取返回栈id

        btn = (Button) findViewById(R.id.open2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(FirstActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}
