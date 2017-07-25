package com.example.DataStore.SharedPreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.helloworld.R;

/**
 * Created by lester.ding on 7/25/2017.
 */

public class SharedPrefsOutput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shared_prefs_output_layout);

        Button saveData = (Button) findViewById(R.id.save_data);
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取SharedPreferences.Editor对象用于添加数据
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                //添加各种类型数据
                editor.putString("name", "Lester Ding");
                editor.putInt("age", 34);
                editor.putBoolean("married", false);
                //提交数据，完成存储
                editor.apply();
            }
        });
    }
}
