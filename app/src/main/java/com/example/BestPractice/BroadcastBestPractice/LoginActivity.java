package com.example.BestPractice.BroadcastBestPractice;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.helloworld.BaseActivity;
import com.example.helloworld.R;

//这里因为当强制下线触发后需要关闭所有已打开的Activity返回到登录画面，所以我们要继承BaseActivity，利用其中的ActivityCollector
public class LoginActivity extends BaseActivity{

    private EditText username, password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bp_broadcast_login_layout);

        //初始化
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);

        //登录按钮点击事件
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username_str = username.getText().toString();
                String password_str = password.getText().toString();

                if(username_str.equals("admin")&&password_str.equals("admin")){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);  //打开主界面
                    finish();               //打开主界面后登录界面需销毁
                }else
                    Toast.makeText(LoginActivity.this, "Username or Password is invalid.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

