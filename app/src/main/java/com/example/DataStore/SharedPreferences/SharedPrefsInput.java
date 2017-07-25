package com.example.DataStore.SharedPreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.helloworld.R;

/**
 * Created by lester.ding on 7/25/2017.
 */

public class SharedPrefsInput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shared_prefs_input_layout);

        Button loadData = (Button) findViewById(R.id.load_data);
        loadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取SharedPreferences对象用于获取数据
                SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
                //获取各种类型数据
                String name = pref.getString("name", "No name");
                int age = pref.getInt("age", 0);
                boolean married = pref.getBoolean("married", false);
                String sex = pref.getString("sex", "unknown"); //第二个参数是缺失默认值，当找不到数据时就用默认值代替
                String data = "Name: "+name+", Age: "+age+", Sex: "+sex+", Married: "+married;
                //将数据显示到画面上
                TextView content = (TextView) findViewById(R.id.content);
                content.setText(data);
            }
        });
    }
}
