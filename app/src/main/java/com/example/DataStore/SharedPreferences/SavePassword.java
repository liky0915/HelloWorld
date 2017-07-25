package com.example.DataStore.SharedPreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.helloworld.R;

/**
 * Created by lester.ding on 7/25/2017.
 */

public class SavePassword extends AppCompatActivity {

    private EditText username, password;
    private CheckBox savePass;
    private Button login;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_password_layout);
        //初始化
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        savePass = (CheckBox) findViewById(R.id.save_pass);
        login = (Button) findViewById(R.id.login);
        //实例化SharedPreferences对象，使用当前应用程序的包名作为前缀来命名文件，即文件名：com.example.helloworld_preferences
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        //实例化SharedPreferences.Editor对象，用于向文件中写入数据
        editor = pref.edit();

        //如果文件中已经存在配置数据，则加载数据到画面上
        boolean isSavePass = pref.getBoolean("save_pass", false);
        if(isSavePass){
            String username = pref.getString("username", "");
            String password = pref.getString("password", "");
            this.username.setText(username);
            this.password.setText(password);
            savePass.setChecked(true);
        }

        //点击登录判断是否记住密码
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString();
                String pass = password.getText().toString();
                String msg;
                if(name.equals("admin")&&pass.equals("admin")){
                    if(savePass.isChecked()){
                        editor.putString("username", name);
                        editor.putString("password", pass);
                        editor.putBoolean("save_pass", true);
                        msg = "Password saved!";
                    }else {
                        editor.clear();
                        msg = "Password no longer saved!";
                    }
                    editor.apply();
                    Toast.makeText(SavePassword.this, "Login system successfully. "+msg, Toast.LENGTH_SHORT).show();
                    finish();

                }else
                    Toast.makeText(SavePassword.this, "Username or Password is invalid, please check.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
