package com.example.helloworld;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by lester.ding on 6/29/2017.
 */

public class SecondActivity extends BaseActivity {

    private TextView tv;
    private Button btn;

    //定义actionStart静态方法，规范本Activity启动方式，方便参数管理，在别处调用此静态方法即可启动本Activity
    public static void actionStart(Context context, String data){
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra("data", data);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity_layout);

        //当Activity注册时设定属性launchMode="singleInstance"，那么此Activity将创建在一个新的返回栈中，以便与其他程序共享此Activity
        Log.i("SecondActivity", "Task id is: "+getTaskId()); //获取返回栈id

        tv = (TextView) findViewById(R.id.textView2);

        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        tv.setText("HelloWorld said to me: " + data);

        btn = (Button) findViewById(R.id.open3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override   //按下back键返回前一个Activity
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("data_return", "Hi, HelloWorld!");
        setResult(RESULT_OK, intent);
        super.onBackPressed();  //这句要写在最后，因为其中有finish()函数，如果在第一行，那其后的代码将无效
    }
}
